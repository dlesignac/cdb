package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.mapper.dto.ComputerDTOMapper;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.datasource.CompanyService;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import fr.ebiz.cdb.service.validator.ComputerValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Computer creating servlet.
 */
@WebServlet("/add-computer")
public class AddComputerServlet extends AutowiredServlet {

    private static final String VIEW = "/WEB-INF/pages/addComputer.jsp";

    private static final String ATTRIBUTE_COMPANIES = "companies";
    private static final String ATTRIBUTE_ERRORS = "errors";

    private static final String PARAMETER_COMPUTER_ID = "id";
    private static final String PARAMETER_COMPUTER_NAME = "computerName";
    private static final String PARAMETER_INTRODUCED = "introduced";
    private static final String PARAMETER_DISCONTINUED = "discontinued";
    private static final String PARAMETER_COMPANY_ID = "companyId";

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputerServlet.class);

    @Autowired
    private CompanyService companyService;

    @Autowired
    private ComputerService computerService;

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
            ComputerDTO computerDTO = parseRequest(req);
            List<String> errors = ComputerValidator.validate(computerDTO);
            req.setAttribute(ATTRIBUTE_ERRORS, errors);

            if (errors.isEmpty()) {
                Computer computer = ComputerDTOMapper.mapFromDTO(computerDTO);
                computerService.create(computer);
            }
        } catch (TransactionFailedException e) {
            LOGGER.error("could not post on add-computer page", e);
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            req.setAttribute(ATTRIBUTE_ERRORS, errors);
        } finally {
            doGet(req, resp);
        }
    }

    /**
     * Parses request to get ComputerDTO.
     *
     * @param req request
     * @return ComputerDTO
     */
    private static ComputerDTO parseRequest(HttpServletRequest req) {
        String id = req.getParameter(PARAMETER_COMPUTER_ID);
        String computerName = req.getParameter(PARAMETER_COMPUTER_NAME);
        String introduced = req.getParameter(PARAMETER_INTRODUCED);
        String discontinued = req.getParameter(PARAMETER_DISCONTINUED);
        String companyId = req.getParameter(PARAMETER_COMPANY_ID);

        ComputerDTO dto = new ComputerDTO
                .Builder(computerName)
                .id(id)
                .introduced(introduced)
                .discontinued(discontinued)
                .companyId(companyId)
                .build();

        LOGGER.debug("request parsed into " + dto.toString());

        return dto;
    }

}
