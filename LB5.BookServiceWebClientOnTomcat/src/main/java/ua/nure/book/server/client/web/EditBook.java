package ua.nure.book.server.client.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.book.entity.Author;
import ua.nure.book.entity.Book;
import ua.nure.book.entity.Genre;
import ua.nure.book.server.service.BookService;
import ua.nure.book.server.service.DAOException_Exception;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ListArticles
 */
public class EditBook extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String DEBUG_ERROR_MSG = "Set errorMsg to the session";
    private final transient Logger log = LoggerFactory.getLogger(EditBook.class);

    private BookService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        service = (BookService) ctx.getAttribute("BookService");
        log.trace("Get attribute BookService : {}", service);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        var bookId = Integer.parseInt(request.getParameter("editBookId"));
        Book book;
        try {
            book = service.takeBook(bookId);
        } catch (DAOException_Exception e) {
            throw new RuntimeException(e);
        }
        log.debug("Get book from BookService : {}", book);
        request.setAttribute("book", book);
        log.trace("Set book to the session : {}", book);
        request.getRequestDispatcher("editBook.jsp").forward(request, response);
        log.trace("Redirect to editBook.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String errMsg = null;

        try {
            String title = request.getParameter("title");
            String body = request.getParameter("bookBody");
            String authorName = request.getParameter("authorName");
            String placeOfBirth = request.getParameter("placeOfBirth");
            String biography = request.getParameter("biography");
            String majorWorks = request.getParameter("majorWorks");
            String genreName = request.getParameter("genreName");
            int bookId = Integer.parseInt(request.getParameter("bookId"));

            Book book = new Book();
            book.setTitle(title);
            book.setBody(body);

            Author author = new Author();
            author.setName(authorName);
            author.setPlaceOfBirth(placeOfBirth);
            author.setBiography(biography);
            author.setMajorWorks(majorWorks);

            Genre genre = new Genre();
            genre.setGenreName(genreName);
            genre.setBookId(bookId);
            book.setAuthor(author);
            book.setGenre(genre);
            book.setId(bookId);

            service.editBook(book);

            session.removeAttribute("book");

        } catch (Exception e) {
            // Any other exceptions
            errMsg = "Error: Can not communicate with book service";
            log.debug(DEBUG_ERROR_MSG, e);
        }
        if (errMsg != null) {
            session.setAttribute("errorMsg", errMsg);
        }

        var books = service.listBooks();
        session.setAttribute("books", books);
        // PRG pattern
        response.sendRedirect("list.jsp");
        log.debug("Redirect to list.jsp");
    }
}
