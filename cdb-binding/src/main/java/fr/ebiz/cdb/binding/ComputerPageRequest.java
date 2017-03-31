package fr.ebiz.cdb.binding;

import fr.ebiz.cdb.core.Column;
import fr.ebiz.cdb.core.Order;

public class ComputerPageRequest {

    private String filter;
    private Column sort;
    private Order order;
    private Integer limit;
    private Integer page;

    public String getFilter() {
        return filter;
    }

    public Column getSort() {
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

    public void setSort(Column sort) {
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