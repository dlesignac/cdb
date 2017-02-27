package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.Page;

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

    private static final String DASHBOARD_JSP = "/WEB-INF/pages/dashboard.jsp";

    private static final String REQUEST_ATTRIBUTE_COMPUTERS_COUNT = "computersCount";
    private static final String REQUEST_ATTRIBUTE_PAGE = "page";

    private static final String REQUEST_PARAMETER_PAGE_LIMIT = "limit";
    private static final String REQUEST_PARAMETER_PAGE_NUMBER = "page";

    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_NUMBER = 1;

    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqLimit = req.getParameter(REQUEST_PARAMETER_PAGE_LIMIT);
        String reqPage = req.getParameter(REQUEST_PARAMETER_PAGE_NUMBER);

        try {
            int pageLimit = reqLimit == null ? DEFAULT_LIMIT : Integer.parseInt(reqLimit);
            int pageNumber = reqPage == null ? DEFAULT_NUMBER : Integer.parseInt(reqPage);

            int computersCount = computerService.countComputers();
            Page<Computer> page = computerService.pageComputers(pageLimit, pageNumber);

            req.setAttribute(REQUEST_ATTRIBUTE_COMPUTERS_COUNT, computersCount);
            req.setAttribute(REQUEST_ATTRIBUTE_PAGE, page);

            getServletContext().getRequestDispatcher(DASHBOARD_JSP).forward(req, resp);
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + Error500Servlet.URL);
        }
    }

}
