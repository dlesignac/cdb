package fr.ebiz.cdb.servlet;

import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Column;
import fr.ebiz.cdb.model.Order;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.service.validator.PageValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends AutowiredServlet {
    static final String URL = "/dashboard";

    private static final String VIEW = "/WEB-INF/pages/dashboard.jsp";

    private static final String ATTRIBUTE_PAGE = "page";

    private static final String PARAMETER_FILTER = "filter";
    private static final String PARAMETER_SORT = "sort";
    private static final String PARAMETER_ORDER = "order";
    private static final String PARAMETER_PAGE_LIMIT = "limit";
    private static final String PARAMETER_PAGE_NUMBER = "page";

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServlet.class);

    @Autowired
    private ComputerService computerService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ComputerPageDTO computerPageDTO = parseRequest(req);
            List<String> errors = PageValidator.validate(computerPageDTO);
            LOGGER.warn(errors.toString());
            req.setAttribute(ATTRIBUTE_PAGE, computerService.page(computerPageDTO));
            getServletContext().getRequestDispatcher(VIEW).forward(req, resp);
        } catch (Exception e) {
            LOGGER.error("failed to get dashboard page", e);
            throw new ServletException("caught an exception while getting dashboard", e);
        }
    }

    /**
     * Parses request to get ComputerPageDTO.
     *
     * @param req request
     * @return ComputerPageDTO
     */
    private static ComputerPageDTO parseRequest(HttpServletRequest req) {
        String filter = req.getParameter(PARAMETER_FILTER);
        String sort = req.getParameter(PARAMETER_SORT);
        String order = req.getParameter(PARAMETER_ORDER);
        String reqLimit = req.getParameter(PARAMETER_PAGE_LIMIT);
        String reqPage = req.getParameter(PARAMETER_PAGE_NUMBER);

        int pageLimit = reqLimit == null ? 0 : Integer.parseInt(reqLimit);
        int pageNumber = reqPage == null ? 0 : Integer.parseInt(reqPage);

        return new ComputerPageDTO(filter, Column.of(sort), Order.of(order), pageLimit, pageNumber);
    }

}
