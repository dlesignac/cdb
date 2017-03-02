package fr.ebiz.cdb.dto;

/**
 * Page request.
 */
public class ComputerPageDTO {

    private String search;
    private String orderBy;
    private String order;
    private int limit;
    private int number;

    /**
     * Constructor.
     *
     * @param search  search
     * @param orderBy orderBy
     * @param order   order
     * @param limit   limit
     * @param number  number
     */
    public ComputerPageDTO(String search, String orderBy, String order, int limit, int number) {
        this.search = search;
        this.order = order;
        this.orderBy = orderBy;
        this.limit = limit;
        this.number = number;
    }

    public String getSearch() {
        return search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrder() {
        return order;
    }

    public int getLimit() {
        return limit;
    }

    public int getNumber() {
        return number;
    }

}
