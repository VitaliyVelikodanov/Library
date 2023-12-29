package book.service;

import article.entity.ArticleTag;
import article.entity.Comment;
import article.entity.Grade;
import book.dao.BookDAO;
import book.dao.BookDAOInMemoryImpl;
import book.entity.Book;
import com.sun.research.ws.wadl.Application;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import book.additions.ReturnsAtomic;
import article.dao.DAOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path(Constants.BOOKS_SERVICE_PATH)
public class BookServiceRestImpl extends Application {

    private static final long LONG_WORK_WAIT_TIMEOUT = 1000;

    private static final int DEFAULT_THREAD_POOL_SIZE = 10;

    private BookDAO store;

    ExecutorService executor = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);

    private void log(String msg) {
        System.out.println(this.getClass().getSimpleName() + ": " + msg);
    }

    @PostConstruct
    public void init() {
        log("init");
        store = BookDAOInMemoryImpl.instance();
    }

    @PreDestroy
    public void destroy() {
        log("destroy");
        store = null;
        executor.shutdown();
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response listBooks() {
        log("all");
        var books = this.store.listBooks();
        System.out.println("getting all");
        return Response.ok(books).build();
    }


    @Path("search")
    public BookSearcher find() {
        return new BookSearcher();
    }

    @Path("addBook")
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ReturnsAtomic<Integer> addBook(Book book) throws DAOException {
        log("add Book");
        var id = store.addBook(book);
        return new ReturnsAtomic<Integer>(id);
    }

    @Path("removeBook")
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void removeBook(int bookId) throws DAOException {
        log("remove book");
        store.removeBook(bookId);
    }

    @Path("editBook")
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void editBook(Book book) throws DAOException {
        log("edit Book");
        store.editBook(book);
    }

    @Path("takeBook")
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Book takeBook(int bookId) throws DAOException {
        log("add article tag");
        var id = store.takeBook(bookId);
        return id;
    }
}
