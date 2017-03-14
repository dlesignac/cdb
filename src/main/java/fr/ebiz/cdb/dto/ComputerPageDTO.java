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
    private int limit;
    private int number;

    /**
     * Constructor.
     *
     * @param filter filter
     * @param sort   sort
     * @param order  order
     * @param limit  limit
     * @param number number
     */
    public ComputerPageDTO(String filter, Column sort, Order order, int limit, int number) {
        this.filter = filter;
        this.sort = sort;
        this.order = order;
        this.limit = limit;
        this.number = number;
    }

    public String getFilter() {
        return filter;
    }

    public Column getSort() {
        return sort;
    }

    public Order getOrder() {
        return order;
    }

    public int getLimit() {
        return limit;
    }

    public int getNumber() {
        return number;
    }

}
