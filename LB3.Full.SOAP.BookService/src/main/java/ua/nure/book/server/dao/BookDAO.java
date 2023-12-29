package ua.nure.book.server.dao;

import ua.nure.book.entity.Book;
import ua.nure.book.entity.Genre;

import java.util.Collection;

public interface BookDAO {
    public Collection<Book> listBooks();
    public int addBook(Book item);
    public void removeBook(int bookId);
    public void editBook(Book book);
    public Book takeBook(int bookId);

}
