package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.mapper.MapperComputer;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.exception.PersistenceException;
import fr.ebiz.cdb.service.datasource.ServiceDatasource;
import fr.ebiz.cdb.service.validator.exception.ValidationException;

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
    private static final String REQUEST_ATTRIBUTE_SUCCESS = "success";

    private static final String REQUEST_PARAMETER_COMPUTER_NAME = "computerName";
    private static final String REQUEST_PARAMETER_INTRODUCED = "introduced";
    private static final String REQUEST_PARAMETER_DISCONTINUED = "discontinued";
    private static final String REQUEST_PARAMETER_COMPANY_ID = "companyId";

    private ServiceDatasource dsService = ServiceDatasource.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Company> companies = dsService.listCompanies();
            req.setAttribute(REQUEST_ATTRIBUTE_COMPANIES, companies);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ADD_COMPUTER_JSP);
            dispatcher.forward(req, resp);
        } catch (PersistenceException e) {
            resp.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String computerName = req.getParameter(REQUEST_PARAMETER_COMPUTER_NAME);
        String introduced = req.getParameter(REQUEST_PARAMETER_INTRODUCED);
        String discontinued = req.getParameter(REQUEST_PARAMETER_DISCONTINUED);
        String companyId = req.getParameter(REQUEST_PARAMETER_COMPANY_ID);

        try {
            Computer computer = MapperComputer.map(computerName, introduced, discontinued, companyId);
            dsService.createComputer(computer);

            List<Company> companies = dsService.listCompanies();
            req.setAttribute(REQUEST_ATTRIBUTE_COMPANIES, companies);
            req.setAttribute(REQUEST_ATTRIBUTE_SUCCESS, true);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ADD_COMPUTER_JSP);
            dispatcher.forward(req, resp);
        } catch (PersistenceException | ValidationException e) {
            resp.sendError(500);
        }
    }

}
