package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.mapper.request.RequestMapper;
import fr.ebiz.cdb.service.datasource.ComputerService;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServlet.class);

    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ComputerPageDTO computerPageDTO = RequestMapper.parsePage(req);

            req.setAttribute(ATTRIBUTE_PAGE, computerService.page(computerPageDTO));
            getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error("failed to get dashboard page", e);
            throw new ServletException("caught an exception while getting dashboard", e);
        }
    }

}
