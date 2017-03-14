package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.mapper.request.RequestMapper;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;

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

    private static final String ATTRIBUTE_STATUS = "status";

    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ComputerDeletionDTO computerDeletionDTO = RequestMapper.parseDelete(req);

        try {
            computerService.deleteMany(computerDeletionDTO);
            req.setAttribute(ATTRIBUTE_STATUS, "success");
        } catch (TransactionFailedException e) {
            req.setAttribute(ATTRIBUTE_STATUS, "error");
        }

        resp.sendRedirect(req.getContextPath() + DashboardServlet.URL);
    }

}
