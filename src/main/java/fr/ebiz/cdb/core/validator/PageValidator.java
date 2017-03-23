package fr.ebiz.cdb.core.validator;

import fr.ebiz.cdb.core.dto.ComputerPageRequest;
import fr.ebiz.cdb.core.model.Column;
import fr.ebiz.cdb.core.model.Order;

import java.util.ArrayList;
import java.util.List;

public class PageValidator {

    private static final String DEFAULT_FILTER = "";
    private static final Column DEFAULT_SORT = Column.COMPUTER_NAME;
    private static final Order DEFAULT_ORDER = Order.ASC;
    private static final int NUMBER_MIN = 1;
    private static final int DEFAULT_LIMIT = 10;
    private static final int LIMIT_MIN = 1;
    private static final int LIMIT_MAX = 100;

    /**
     * Validates page request.
     *
     * @param page page request
     * @return errors
     */
    public static List<String> validate(ComputerPageRequest page) {
        List<String> errors = new ArrayList<>();

        if (page.getPage() == null || page.getPage() < NUMBER_MIN) {
            errors.add("Page number must be greater or equal to 1");
            page.setPage(NUMBER_MIN);
        }

        if (page.getLimit() == null || page.getLimit() < LIMIT_MIN) {
            errors.add("Page limit must be greater than 0");
            page.setLimit(DEFAULT_LIMIT);
        }

        if (page.getLimit() > LIMIT_MAX) {
            errors.add("Page limit must be lower than 100");
            page.setLimit(LIMIT_MAX);
        }

        if (page.getFilter() == null) {
            page.setFilter(DEFAULT_FILTER);
        }

        if (page.getSort() == null) {
            errors.add("Can not sort on an unknown column");
            page.setSort(DEFAULT_SORT);
        }

        if (page.getOrder() == null) {
            errors.add("Can not sort for an unknown order");
            page.setOrder(DEFAULT_ORDER);
        }

        return errors;
    }

}