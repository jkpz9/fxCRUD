package libraryloansys.service;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import libraryloansys.dao.*;

import libraryloansys.model.Book;
import libraryloansys.optional.BookSearchType;



public class Library {
    
    private BookDAO bookDAO;

    public Library(BookDAO bookDAO)
    {
        this.bookDAO = bookDAO;
        try {
            this.bookDAO.connect();
        } catch (Exception ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addNewBook(String name, String authors, int year)
    {
        Book book = new Book();
        book.setName(name);
        book.setAuthors(authors);
        book.setPublishedYear(year);
        book.setAvailable(true);
        
        bookDAO.insertBook(book);
    }
    public void loanBook(long id)
    {
        List<Book> books = bookDAO.findBookByProperty(BookSearchType.ID, id);
        if (books.size() > 0)
        {
            Book  book = books.get(0);
            book.setAvailable(false);
            bookDAO.updateBook(book);
        }
    }
    
    public void returnBook(long id)
    {
         List<Book> books = bookDAO.findBookByProperty(BookSearchType.ID, id);
        if (books.size() > 0)
        {
            Book  book = books.get(0);
            book.setAvailable(true);
            bookDAO.updateBook(book);
        }
    }
    
    public void close()
    {
        try {
            bookDAO.close();
        } catch (Exception ex) {
            Logger.getLogger(Library.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Book> search(BookSearchType type, Object val)
    {
        return this.bookDAO.findBookByProperty(type, val);
    }
}
