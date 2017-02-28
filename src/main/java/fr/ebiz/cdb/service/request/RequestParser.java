package fr.ebiz.cdb.service.request;

import fr.ebiz.cdb.dto.ComputerRequest;
import fr.ebiz.cdb.dto.DeleteRequest;
import fr.ebiz.cdb.dto.PageRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Request parser.
 */
public class RequestParser {

    private static final String PARAMETER_COMPUTER_ID = "id";
    private static final String PARAMETER_COMPUTER_NAME = "computerName";
    private static final String PARAMETER_INTRODUCED = "introduced";
    private static final String PARAMETER_DISCONTINUED = "discontinued";
    private static final String PARAMETER_COMPANY_ID = "companyId";

    private static final String PARAMETER_PAGE_LIMIT = "limit";
    private static final String PARAMETER_PAGE_NUMBER = "page";
    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_NUMBER = 1;

    private static final String PARAMETER_PAGE_DELETE = "selection";
    private static final String DELETE_SEPARATOR = ",";

    /**
     * Parses request to get ComputerRequest.
     *
     * @param req request
     * @return ComputerRequest
     */
    public static ComputerRequest parseComputer(HttpServletRequest req) {
        String id = req.getParameter(PARAMETER_COMPUTER_ID);
        String computerName = req.getParameter(PARAMETER_COMPUTER_NAME);
        String introduced = req.getParameter(PARAMETER_INTRODUCED);
        String discontinued = req.getParameter(PARAMETER_DISCONTINUED);
        String companyId = req.getParameter(PARAMETER_COMPANY_ID);

        return new ComputerRequest
                .Builder(computerName)
                .setId(id)
                .setIntroduced(introduced)
                .setDiscontinued(discontinued)
                .setCompanyId(companyId)
                .build();
    }

    /**
     * Parses request to get PageRequest.
     *
     * @param req request
     * @return PageRequest
     */
    public static PageRequest parsePage(HttpServletRequest req) {
        String reqLimit = req.getParameter(PARAMETER_PAGE_LIMIT);
        String reqPage = req.getParameter(PARAMETER_PAGE_NUMBER);

        int pageLimit = reqLimit == null ? DEFAULT_LIMIT : Integer.parseInt(reqLimit);
        int pageNumber = reqPage == null ? DEFAULT_NUMBER : Integer.parseInt(reqPage);

        return new PageRequest(pageLimit, pageNumber);
    }

    /**
     * Parses request to get DeleteRequest.
     *
     * @param req request
     * @return DeleteRequest
     */
    public static DeleteRequest parseDelete(HttpServletRequest req) {
        String[] delete = req.getParameter(PARAMETER_PAGE_DELETE).split(DELETE_SEPARATOR);

        List<Integer> ids = new ArrayList<>();

        for (String id : delete) {
            ids.add(Integer.parseInt(id));
        }

        return new DeleteRequest(ids);
    }

}
