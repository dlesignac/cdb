package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import fr.ebiz.cdb.mapper.request.RequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServlet.class);

    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ComputerPageDTO computerPageDTO = RequestMapper.parsePage(req);

        try {
            req.setAttribute(ATTRIBUTE_PAGE, computerService.page(computerPageDTO));
            getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } catch (TransactionFailedException e) {
            LOGGER.error("transaction exception", e);
            resp.sendRedirect(req.getContextPath() + Error500Servlet.URL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ComputerDeletionDTO computerDeletionDTO = RequestMapper.parseDelete(req);

        try {
            computerService.deleteMany(computerDeletionDTO);
            req.setAttribute(ATTRIBUTE_STATUS, "success");
            doGet(req, resp);
        } catch (TransactionFailedException e) {
            req.setAttribute(ATTRIBUTE_STATUS, "error");
            doGet(req, resp);
        }
    }
}
