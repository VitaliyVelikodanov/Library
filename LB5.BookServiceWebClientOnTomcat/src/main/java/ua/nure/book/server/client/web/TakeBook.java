package ua.nure.book.server.client.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.book.entity.Book;
import ua.nure.book.server.service.BookService;

import java.io.IOException;

/**
 * Servlet implementation class ListArticles
 */
public class TakeBook extends HttpServlet {
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

        var books = service.listBooks();
        var bookIds = books.stream().map(Book::getId).toList();
        session.setAttribute("bookIds", bookIds);
        request.getRequestDispatcher("takeBook.jsp").forward(request, response);
        log.trace("Redirect to takeBook.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String errMsg = null;

        try {
            var bookId = request.getParameter("bookId");
            var book = service.takeBook(Integer.parseInt(bookId));

            session.setAttribute("book", book);

        } catch (Exception e) {
            // Any other exceptions
            errMsg = "Error: Can not communicate with book service";
            log.debug(DEBUG_ERROR_MSG, e);
        }
        if (errMsg != null) {
            session.setAttribute("errorMsg", errMsg);
        }
        response.sendRedirect("viewbook.jsp");
        log.debug("Redirect to viewbook.jsp");
    }
}
