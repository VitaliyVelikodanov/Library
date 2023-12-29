package ua.nure.book.server.service;

import jakarta.jws.WebService;
import ua.nure.book.entity.Book;
import ua.nure.book.server.dao.*;

import java.util.Collection;

@WebService(serviceName = "Books",
        portName = "BookPort",
        endpointInterface = "ua.nure.book.server.service.BookService",
        targetNamespace = "http://book.nure.ua/server/service")

public class BookServiceImpl implements BookService {

    private static BookDAO bookDAO = BookDAOInMemoryImpl.instance();

    @Override
    public int addBook(Book book) throws DAOException {
        return bookDAO.addBook(book);
    }

    @Override
    public void removeBook(int bookId) throws DAOException {
        bookDAO.removeBook(bookId);
    }

    @Override
    public void editBook(Book book) throws DAOException {
        bookDAO.editBook(book);
    }

    @Override
    public Book takeBook(int bookId) throws DAOException {
        return bookDAO.takeBook(bookId);
    }

    @Override
    public Collection<Book> listBooks() {
        return bookDAO.listBooks();
    }

}

