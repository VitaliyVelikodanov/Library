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

import java.io.IOException;

public class AddBook extends HttpServlet {
    private static final String DEBUG_ERROR_MSG = "Set errorMsg to the session";
    private static final long serialVersionUID = 1L;
    private final transient Logger log = LoggerFactory.getLogger(AddBook.class);

    private transient BookService service;

    @Override
    public void init() throws ServletException {
        super.init();
        ServletContext ctx = getServletContext();
        service = (BookService) ctx.getAttribute("BookService");
        log.trace("Get attribute BookService : {}",service);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = new Book();
        HttpSession session = request.getSession();
        log.debug("Requst encoding : {}",request.getCharacterEncoding());
        String errMsg = null;
        try {
            book.setTitle(request.getParameter("title"));
            book.setBody(request.getParameter("bookBody"));

            var author = new Author();
            author.setName(request.getParameter("authorName"));
            author.setBiography(request.getParameter("biography"));
            author.setMajorWorks(request.getParameter("majorWorks"));
            author.setPlaceOfBirth(request.getParameter("placeOfBirth"));
            book.setAuthor(author);

            var genre = new Genre();
            genre.setGenreName(request.getParameter("genreName"));
            book.setGenre(genre);

            log.debug("Get book from request : {}", book);

            // ----------------------------
            // TO DO Validate book
            // ----------------------------

            // send request to web-service
            int id = service.addBook(book);
            log.debug("Book added with id : {}",id);

            // if ok set book into session attribute
            session.setAttribute("book", book);
        } catch (Exception e) {
            // Any other exceptions
            errMsg = "Error: Can not communicate with article service";
            log.debug(DEBUG_ERROR_MSG, e);
        }
        if (errMsg != null) {
            session.setAttribute("errorMsg", errMsg);
        }

        // PRG pattern
        response.sendRedirect("viewbook.jsp");
        log.debug("Redirect to viewbook.jsp");
    }

}
