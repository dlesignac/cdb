package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.Page;
import fr.ebiz.cdb.persistence.dao.DAOFactory;
import fr.ebiz.cdb.persistence.dao.IDAOComputer;
import fr.ebiz.cdb.persistence.exception.PersistenceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Dashboard servlet.
 */
@WebServlet("/dashboard")
public class ServletDashboard extends HttpServlet {

    private static final String DASHBOARD_JSP = "/WEB-INF/pages/dashboard.jsp";

    private static final String REQUEST_ATTRIBUTE_COMPUTERS = "computers";
    private static final String REQUEST_ATTRIBUTE_COUNT = "count";

    private IDAOComputer dao = new DAOFactory(DBConnection.getInstance()).getComputerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Page<Computer> page = this.dao.fetch(10, 0);
            int count = this.dao.count();
            req.setAttribute(REQUEST_ATTRIBUTE_COMPUTERS, page.getEntries());
            req.setAttribute(REQUEST_ATTRIBUTE_COUNT, count);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(DASHBOARD_JSP);
            dispatcher.forward(req, resp);
        } catch (PersistenceException e) {
            resp.sendError(500);
        }
    }

}
