package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.Page;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import fr.ebiz.cdb.service.request.RequestParser;

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
public class DashboardServlet extends HttpServlet {

    static final String URL = "/dashboard";

    private static final String VIEW = "/WEB-INF/pages/dashboard.jsp";

    private static final String ATTRIBUTE_PAGE = "page";
    private static final String ATTRIBUTE_STATUS = "status";

    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ComputerPageDTO computerPageDTO = RequestParser.parsePage(req);

        try {
            Page<Computer> page = computerService.page(computerPageDTO);
            req.setAttribute(ATTRIBUTE_PAGE, page);

            getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } catch (TransactionFailedException e) {
            resp.sendRedirect(req.getContextPath() + Error500Servlet.URL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ComputerDeletionDTO computerDeletionDTO = RequestParser.parseDelete(req);

        try {
            if (!computerDeletionDTO.getIds().isEmpty()) {
                computerService.deleteMany(computerDeletionDTO);
                req.setAttribute(ATTRIBUTE_STATUS, "success");
            }

            doGet(req, resp);
        } catch (TransactionFailedException e) {
            req.setAttribute(ATTRIBUTE_STATUS, "error");
            doGet(req, resp);
        }
    }

}
