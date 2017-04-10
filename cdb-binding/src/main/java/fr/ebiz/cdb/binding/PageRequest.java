package fr.ebiz.cdb.binding;

import fr.ebiz.cdb.core.Order;
import fr.ebiz.cdb.core.Sort;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class PageRequest {

    private static final String DEFAULT_FILTER = "";
    private static final Sort DEFAULT_SORT = Sort.COMPUTER_NAME;
    private static final Order DEFAULT_ORDER = Order.ASC;
    private static final Integer DEFAULT_LIMIT = 10;
    private static final Integer DEFAULT_PAGE = 1;

    private String filter = DEFAULT_FILTER;
    private Sort sort = DEFAULT_SORT;
    private Order order = DEFAULT_ORDER;

    @Min(1)
    @Max(100)
    private Integer limit = DEFAULT_LIMIT;

    @Min(1)
    private Integer page = DEFAULT_PAGE;

    public String getFilter() {
        return filter;
    }

    public Sort getSort() {
        return sort;
    }

    public Order getOrder() {
        return order;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

}