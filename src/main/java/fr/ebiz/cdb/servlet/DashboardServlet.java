package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.DeleteRequest;
import fr.ebiz.cdb.dto.PageRequest;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.persistence.exception.QueryException;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.datasource.Page;
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

    public static final String URL = "/dashboard";

    private static final String DASHBOARD_JSP = "/WEB-INF/pages/dashboard.jsp";

    private static final String REQUEST_ATTRIBUTE_COMPUTERS_COUNT = "computersCount";
    private static final String REQUEST_ATTRIBUTE_PAGE = "page";

    private static final String REQUEST_ATTRIBUTE_STATUS = "status";

    private ComputerService computerService = ComputerService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageRequest pageRequest = RequestParser.parsePage(req);

        try {
            int computersCount = computerService.countComputers();
            Page<Computer> page = computerService.pageComputers(pageRequest.getLimit(), pageRequest.getNumber());

            req.setAttribute(REQUEST_ATTRIBUTE_COMPUTERS_COUNT, computersCount);
            req.setAttribute(REQUEST_ATTRIBUTE_PAGE, page);

            getServletContext().getRequestDispatcher(DASHBOARD_JSP).forward(req, resp);
        } catch (DatasourceException | QueryException e) {
            resp.sendRedirect(req.getContextPath() + Error500Servlet.URL);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeleteRequest deleteRequest = RequestParser.parseDelete(req);

        try {
            computerService.deleteComputers(deleteRequest);
            req.setAttribute(REQUEST_ATTRIBUTE_STATUS, "success");
            doGet(req, resp);
        } catch (DatasourceException | QueryException e) {
            req.setAttribute(REQUEST_ATTRIBUTE_STATUS, "error");
            doGet(req, resp);
        }
    }

}
