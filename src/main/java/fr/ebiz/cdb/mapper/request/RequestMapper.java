package fr.ebiz.cdb.mapper.request;

import fr.ebiz.cdb.dto.ComputerDTO;
import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Column;
import fr.ebiz.cdb.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Request parser.
 */
public class RequestMapper {

    private static final String PARAMETER_COMPUTER_ID = "id";
    private static final String PARAMETER_COMPUTER_NAME = "computerName";
    private static final String PARAMETER_INTRODUCED = "introduced";
    private static final String PARAMETER_DISCONTINUED = "discontinued";
    private static final String PARAMETER_COMPANY_ID = "companyId";

    private static final String PARAMETER_FILTER = "filter";
    private static final String PARAMETER_SORT = "sort";
    private static final String PARAMETER_ORDER = "order";
    private static final String PARAMETER_PAGE_LIMIT = "limit";
    private static final String PARAMETER_PAGE_NUMBER = "page";

    private static final String DEFAULT_FILTER = "";
    private static final String DEFAULT_SORT = "computerName";
    private static final String DEFAULT_ORDER = "ASC";
    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_NUMBER = 1;

    private static final String PARAMETER_PAGE_DELETE = "selection";
    private static final String DELETE_SEPARATOR = ",";

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestMapper.class);

    /**
     * Parses request to get ComputerDTO.
     *
     * @param req request
     * @return ComputerDTO
     */
    public static ComputerDTO parseComputer(HttpServletRequest req) {
        String id = req.getParameter(PARAMETER_COMPUTER_ID);
        String computerName = req.getParameter(PARAMETER_COMPUTER_NAME);
        String introduced = req.getParameter(PARAMETER_INTRODUCED);
        String discontinued = req.getParameter(PARAMETER_DISCONTINUED);
        String companyId = req.getParameter(PARAMETER_COMPANY_ID);

        ComputerDTO dto = new ComputerDTO
                .Builder(computerName)
                .setId(id)
                .setIntroduced(introduced)
                .setDiscontinued(discontinued)
                .setCompanyId(companyId)
                .build();

        LOGGER.debug("request parsed into " + dto.toString());

        return dto;
    }

    /**
     * Parses request to get ComputerPageDTO.
     *
     * @param req request
     * @return ComputerPageDTO
     */
    public static ComputerPageDTO parsePage(HttpServletRequest req) {
        String filter = req.getParameter(PARAMETER_FILTER);
        String sort = req.getParameter(PARAMETER_SORT);
        String order = req.getParameter(PARAMETER_ORDER);
        String reqLimit = req.getParameter(PARAMETER_PAGE_LIMIT);
        String reqPage = req.getParameter(PARAMETER_PAGE_NUMBER);

        filter = filter == null ? DEFAULT_FILTER : filter;
        sort = sort == null || "".equals(sort) ? DEFAULT_SORT : sort;
        order = order == null || "".equals(order) ? DEFAULT_ORDER : order;
        int pageLimit = reqLimit == null ? DEFAULT_LIMIT : Integer.parseInt(reqLimit);
        int pageNumber = reqPage == null ? DEFAULT_NUMBER : Integer.parseInt(reqPage);

        ComputerPageDTO dto = new ComputerPageDTO(filter, Column.of(sort), Order.of(order), pageLimit, pageNumber);

        LOGGER.debug("request parsed into " + dto.toString());

        return dto;
    }

    /**
     * Parses request to get ComputerDeletionDTO.
     *
     * @param req request
     * @return ComputerDeletionDTO
     */
    public static ComputerDeletionDTO parseDelete(HttpServletRequest req) {
        String[] delete = req.getParameter(PARAMETER_PAGE_DELETE).split(DELETE_SEPARATOR);

        List<Integer> ids = new ArrayList<>();

        for (String id : delete) {
            if (!"".equals(id)) {
                ids.add(Integer.parseInt(id));
            }
        }

        ComputerDeletionDTO dto = new ComputerDeletionDTO(ids);

        LOGGER.debug("request parsed into " + dto.toString());

        return dto;
    }

}
