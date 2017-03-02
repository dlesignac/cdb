package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.mapper.ComputerMapper;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.datasource.CompanyService;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import fr.ebiz.cdb.service.request.RequestParser;
import fr.ebiz.cdb.service.validator.exception.ValidationException;

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
public class AddComputerServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/pages/addComputer.jsp";

    private static final String ATTRIBUTE_COMPANIES = "companies";
    private static final String ATTRIBUTE_STATUS = "status";

    private CompanyService companyService = CompanyService.INSTANCE;
    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Company> companies = companyService.list();
            req.setAttribute(ATTRIBUTE_COMPANIES, companies);
            getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } catch (TransactionFailedException e) {
            resp.sendRedirect(req.getContextPath() + Error500Servlet.URL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ComputerDTO computerDTO = RequestParser.parseComputer(req);

        try {
            Computer computer = ComputerMapper.map(computerDTO);
            computerService.create(computer);
            req.setAttribute(ATTRIBUTE_STATUS, "success");
            doGet(req, resp);
        } catch (TransactionFailedException e) {
            req.setAttribute(ATTRIBUTE_STATUS, "error");
            doGet(req, resp);
        } catch (ValidationException e) {
            resp.sendRedirect(req.getContextPath() + Error500Servlet.URL);
        }
    }

}
