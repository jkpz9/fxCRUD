package libraryloansys.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import libraryloansys.dao.BookDAO;
import libraryloansys.model.Book;
import libraryloansys.optional.BookSearchType;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class DerbyBookDAO implements BookDAO {
    
    private Connection connection;
    
    private QueryRunner dbAccess;
    
    private static final List<Book> EMPTY = new ArrayList<Book>();;

    public DerbyBookDAO()
    {
         dbAccess = new QueryRunner();
    }
    
    @Override
    public long insertBook(Book b) {
        try{
            String insertSQL = "INSERT INTO books(name, authors, publishedYear, available) VALUES(?,?,?,?)";
            long id = dbAccess.insert(connection, insertSQL, new ScalarHandler<BigDecimal>(),
            b.getName(), b.getAuthors(), b.getPublishedYear(),b.isAvailable()).longValue();
            return id;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return -1L;
    }

    @Override
    public boolean updateBook(Book b) {
        try
        {
            String updateSql = "UPDATE books SET name=?, authors=?, publishedYear=?, available=? WHERE id=?";
            dbAccess.update(connection,
                            updateSql,
                            b.getName(),
                            b.getAuthors(),
                            b.getPublishedYear(),
                            b.isAvailable(),
                            b.getId());
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(Book b) {
        try
        {
                String deleteSql = "DELETE FROM books WHERE id=?";
                dbAccess.update(connection,deleteSql, b.getId());
                return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
             return false;
        }
       
    }

    @Override
    public List<Book> findBookByProperty(BookSearchType searchType, Object val) {
       String whereClause = "";
       String valueClause = "";
       
       switch(searchType)
       {
           case AUTHORS:
                whereClause = "authors LIKE ?";
                valueClause = "%" + val.toString() + "%";
                break;
           
           case AVAILABLE:
               whereClause = "available = ?";
               valueClause = val.toString();
               break;
           
           case ID:
               whereClause = "id = ?";
               valueClause = val.toString();
               break;
               
           case NAME:
               whereClause = "name LIKE ?";
               valueClause = "%" + val.toString()+ "%";
               break;
                       
            case PUBLISHED_YEAR:
               whereClause = "publishedYear = ?";
               valueClause = val.toString();
               break;
               
            default:
                System.out.println("UNKNOW SEARCHING TYPE");
                break;
           
       }
       
       
       try 
       {
           return dbAccess.query(connection,
                          "SELECT * FROM books WHERE " + whereClause,
                          new BeanListHandler<Book>(Book.class),
                          valueClause);
       }
       catch(Exception e) { e.printStackTrace(); return EMPTY; }
       
    }

    @Override
    public List<Book> findAll() {
        try
        {
            return dbAccess.query(connection,
                                  "SELECT * FROM books",
                                  new BeanListHandler<Book>(Book.class));
        }
        catch(Exception e) { e.printStackTrace(); return EMPTY; }
    }

    @Override
    public void setup() throws Exception {
       connection = DriverManager.getConnection("jdbc:derby:books.db;create=true");
        String createTable = "CREATE TABLE books ("+
                "id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "name VARCHAR(30), authors VARCHAR(100), publishedYear INTEGER, available BOOLEAN"
                + ")";
        dbAccess.update(connection,createTable);
       
    }

    @Override
    public void connect() throws Exception {
       connection = DriverManager.getConnection("jdbc:derby:books.db;");
    }

    @Override
    public void close() throws Exception {
       connection.close();
       try 
       {
           DriverManager.getConnection("jdbc:derby:books.db;shutdown=true");
       }
       catch(Exception e)
       {
           e.printStackTrace();
       }
       
    }
    
    public static void main(String[] args) throws Exception {
        new DerbyBookDAO().setup();
    }
    
}
