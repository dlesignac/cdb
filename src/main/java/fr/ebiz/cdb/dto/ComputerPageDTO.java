package fr.ebiz.cdb.dto;

import fr.ebiz.cdb.model.Column;
import fr.ebiz.cdb.model.Order;

/**
 * Page request.
 */
public class ComputerPageDTO {

    private String filter;
    private Column sort;
    private Order order;
    private Integer limit;
    private Integer number;

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

    public Integer getNumber() {
        return number;
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

    public void setNumber(Integer number) {
        this.number = number;
    }

}