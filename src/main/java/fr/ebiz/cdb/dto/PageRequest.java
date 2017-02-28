package fr.ebiz.cdb.dto;

/**
 * Page request.
 */
public class PageRequest {

    private int limit;
    private int number;

    /**
     * Constructor.
     *
     * @param limit  limit
     * @param number number
     */
    public PageRequest(int limit, int number) {
        this.limit = limit;
        this.number = number;
    }

    public int getLimit() {
        return limit;
    }

    public int getNumber() {
        return number;
    }

}
