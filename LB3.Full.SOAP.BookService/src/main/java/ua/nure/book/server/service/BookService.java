package ua.nure.book.server.service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import ua.nure.book.entity.Book;

import ua.nure.book.server.dao.DAOException;

import java.util.Collection;

@WebService(targetNamespace = Const.SERVICE_NS)
public interface BookService {

    @WebMethod()
    public int addBook(
            @WebParam(name = "Book", targetNamespace = "http://book.nure.ua/entity")
            Book book) throws DAOException;

    @WebMethod()
    public void removeBook(
            @WebParam(name = "bookId")
            int bookId) throws DAOException;
    @WebMethod()
    public void editBook(
            @WebParam(name = "Book", targetNamespace = "http://book.nure.ua/entity")
            Book book) throws DAOException;

    @WebMethod()
    @WebResult(name="Book", targetNamespace = "http://book.nure.ua/entity")
    public Book takeBook(
            @WebParam(name = "bookId")
            int bookId) throws DAOException;

    @WebMethod()
    @WebResult(name = "bookList", targetNamespace = "http://book.nure.ua/entity")
    public Collection<Book> listBooks();

}

