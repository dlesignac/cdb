package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import fr.ebiz.cdb.service.validator.ComputerDeletionValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Delete computer servlet.
 */
@WebServlet("/delete-computers")
public class DeleteComputerServlet extends AutowiredServlet {

    private static final String PARAMETER_PAGE_DELETE = "selection";
    private static final String DELETE_SEPARATOR = ",";

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputerServlet.class);

    @Autowired
    private ComputerService computerService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ComputerDeletionDTO computerDeletionDTO = parseRequest(req);
            List<String> errors = ComputerDeletionValidator.validate(computerDeletionDTO);
            LOGGER.warn(errors.toString());
            computerService.deleteMany(computerDeletionDTO);
        } catch (TransactionFailedException e) {
            LOGGER.error("could not post on delete-computers page", e);
        } finally {
            resp.sendRedirect(req.getContextPath() + DashboardServlet.URL);
        }
    }

    /**
     * Parses request to get ComputerDeletionDTO.
     *
     * @param req request
     * @return ComputerDeletionDTO
     */
    private ComputerDeletionDTO parseRequest(HttpServletRequest req) {
        String[] delete = req.getParameter(PARAMETER_PAGE_DELETE).split(DELETE_SEPARATOR);

        List<String> ids = new ArrayList<>();
        Collections.addAll(ids, delete);

        return new ComputerDeletionDTO(ids);
    }

}
