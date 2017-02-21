package fr.ebiz.cdb.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Error 403 servlet.
 */
@WebServlet("/error403")
public class ServletError403 extends HttpServlet {

    private static final String ERROR_403_JSP = "/WEB-INF/pages/403.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ERROR_403_JSP);
        dispatcher.forward(req, resp);
    }

}
