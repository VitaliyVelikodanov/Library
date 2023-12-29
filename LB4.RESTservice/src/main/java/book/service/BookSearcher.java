package book.service;

import book.dao.BookDAO;
import book.dao.BookDAOInMemoryImpl;
import book.entity.Book;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;


import java.util.Collection;

public class BookSearcher {
    private BookDAO store = BookDAOInMemoryImpl.instance();
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book search(@QueryParam("pattern") int pattern) {
        System.out.println("search for Book id: " + pattern);
        return store.takeBook(pattern);
    }
}
