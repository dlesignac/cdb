package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.mapper.dto.ComputerDTOMapper;
import fr.ebiz.cdb.mapper.exception.ValidationException;
import fr.ebiz.cdb.mapper.request.RequestMapper;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.datasource.CompanyService;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Computer creating servlet.
 */
@WebServlet("/add-computer")
public class AddComputerServlet extends HttpServlet {

    private static final String VIEW = "/WEB-INF/pages/addComputer.jsp";
    private static final String ATTRIBUTE_COMPANIES = "companies";
    private static final String ATTRIBUTE_ERRORS = "errors";

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerServlet.class);

    private CompanyService companyService = CompanyService.INSTANCE;
    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute(ATTRIBUTE_COMPANIES, companyService.list());
            getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error("could not get add-computer page", e);
            throw new ServletException("caught an exception while getting add-computer page", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ComputerDTO computerDTO = RequestMapper.parseComputer(req);
            Computer computer = ComputerDTOMapper.mapDTO(computerDTO);
            computerService.create(computer);
        } catch (ValidationException | TransactionFailedException e) {
            LOGGER.error("could not post on add-computer page", e);
            req.setAttribute(ATTRIBUTE_ERRORS, ""); // TODO
        } finally {
            doGet(req, resp);
        }
    }

}
