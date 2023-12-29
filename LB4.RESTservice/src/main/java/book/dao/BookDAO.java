package book.dao;

import book.entity.Book;

import java.util.Collection;

public interface BookDAO {
    public Collection<Book> listBooks();
    public int addBook(Book item);
    public void removeBook(int bookId);
    public void editBook(Book book);
    public Book takeBook(int bookId);

}
