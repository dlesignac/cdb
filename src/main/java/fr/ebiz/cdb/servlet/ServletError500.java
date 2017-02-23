package fr.ebiz.cdb.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Error 500 servlet.
 */
@WebServlet("/error500")
public class ServletError500 extends HttpServlet {

    static final String URL = "/error500";

    private static final String ERROR_500_JSP = "/WEB-INF/pages/500.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(ERROR_500_JSP).forward(req, resp);
    }

}
