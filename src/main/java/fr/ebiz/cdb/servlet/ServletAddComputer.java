package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.dao.DAOFactory;
import fr.ebiz.cdb.persistence.dao.IDAOCompany;
import fr.ebiz.cdb.persistence.exception.PersistenceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Computer creating servlet.
 */
@WebServlet("/add-computer")
public class ServletAddComputer extends HttpServlet {

    private static final String ADD_COMPUTER_JSP = "/WEB-INF/pages/addComputer.jsp";

    private static final String REQUEST_ATTRIBUTE_COMPANIES = "companies";

    private IDAOCompany dao = new DAOFactory(DBConnection.getInstance()).getCompanyDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Company> companies = this.dao.fetch();
            req.setAttribute(REQUEST_ATTRIBUTE_COMPANIES, companies);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ADD_COMPUTER_JSP);
            dispatcher.forward(req, resp);
        } catch (PersistenceException e) {
            resp.sendError(500);
        }
    }

}
