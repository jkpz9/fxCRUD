package libraryloansys.dao;

import java.util.List;
import libraryloansys.model.Book;
import libraryloansys.optional.BookSearchType;

public interface BookDAO extends BaseDAO {
    public long insertBook(Book b);
    public boolean updateBook(Book b);
    public boolean deleteBook(Book b);
    
    public List<Book> findBookByProperty(BookSearchType searchType, Object val);
    public List<Book> findAll();
}
