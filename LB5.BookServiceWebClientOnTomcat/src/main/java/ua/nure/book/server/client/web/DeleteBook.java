package ua.nure.book.server.client.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.book.server.service.BookService;

import java.io.IOException;

public class DeleteBook extends HttpServlet {
    private static final String DEBUG_ERROR_MSG = "Set errorMsg to the session";
    private static final long serialVersionUID = 1L;
    private final transient Logger log = LoggerFactory.getLogger(DeleteBook.class);

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
        HttpSession session = request.getSession();
        log.debug("Requst encoding : {}",request.getCharacterEncoding());
        String errMsg = null;
        try {
            var bookId = request.getParameter("bookId");
            service.removeBook(Integer.parseInt(bookId));

            var books = service.listBooks();
            session.setAttribute("books", books);
            log.debug("Book removed : {}", bookId);

//            session.setAttribute("book", book);
        } catch (Exception e) {
            // Any other exceptions
            errMsg = "Error: Can not communicate with book service";
            log.debug(DEBUG_ERROR_MSG, e);
        }
        if (errMsg != null) {
            session.setAttribute("errorMsg", errMsg);
        }

        // PRG pattern
        response.sendRedirect("list.jsp");
        log.debug("Redirect to list.jsp");
    }

}
