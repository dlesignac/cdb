package fr.ebiz.cdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Error 404 servlet.
 */
@WebServlet("/error404")
public class ServletError404 extends HttpServlet {

    private static final String ERROR_404_JSP = "/WEB-INF/pages/404.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(ERROR_404_JSP).forward(req, resp);
    }

}
