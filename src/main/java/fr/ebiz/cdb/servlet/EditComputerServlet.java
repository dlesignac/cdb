package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.mapper.dto.ComputerDTOMapper;
import fr.ebiz.cdb.service.datasource.CompanyService;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import fr.ebiz.cdb.mapper.request.RequestMapper;
import fr.ebiz.cdb.mapper.exception.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Edit computer servlet.
 */
@WebServlet("/edit-computer")
public class EditComputerServlet extends HttpServlet {
    private static final String VIEW = "/WEB-INF/pages/editComputer.jsp";

    private static final String ATTRIBUTE_STATUS = "status";
    private static final String ATTRIBUTE_COMPUTER = "computer";
    private static final String ATTRIBUTE_COMPANIES = "companies";

    private static final String PARAMETER_COMPUTER_ID = "id";

    private CompanyService companyService = CompanyService.INSTANCE;
    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter(PARAMETER_COMPUTER_ID);

        try {
            req.setAttribute(ATTRIBUTE_COMPUTER, computerService.find(Integer.parseInt(id)));
            req.setAttribute(ATTRIBUTE_COMPANIES, companyService.list());
            getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } catch (TransactionFailedException e) {
            resp.sendRedirect(req.getContextPath() + Error500Servlet.URL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ComputerDTO computerDTO = RequestMapper.parseComputer(req);

        try {
            computerService.update(ComputerDTOMapper.mapDTO(computerDTO));
            req.setAttribute(ATTRIBUTE_STATUS, "success");
            resp.sendRedirect(req.getContextPath() + DashboardServlet.URL);
        } catch (TransactionFailedException e) {
            req.setAttribute(ATTRIBUTE_STATUS, "error");
            resp.sendRedirect(req.getContextPath() + DashboardServlet.URL);
        } catch (ValidationException e) {
            resp.sendRedirect(req.getContextPath() + Error500Servlet.URL);
        }
    }
}
