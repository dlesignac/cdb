package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.mapper.request.RequestMapper;
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
 * Delete computer servlet.
 */
@WebServlet("/delete-computers")
public class DeleteComputerServlet extends HttpServlet {

    private static final String ATTRIBUTE_ERRORS = "errors";

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteComputerServlet.class);

    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ComputerDeletionDTO computerDeletionDTO = RequestMapper.parseDelete(req);
            computerService.deleteMany(computerDeletionDTO);
        } catch (TransactionFailedException e) {
            LOGGER.error("could not post on delete-computers page", e);
            req.setAttribute(ATTRIBUTE_ERRORS, ""); // TODO
        } finally {
            resp.sendRedirect(req.getContextPath() + DashboardServlet.URL);
        }
    }

}
