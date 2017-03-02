package fr.ebiz.cdb.model;

import java.util.List;

public class Page<T> {

    private int number;
    private int maxNumber;
    private int limit;
    private int count;
    private String search;
    private String orderBy;
    private List<T> entries;

    /**
     * Constructor.
     *
     * @param number    current frame number
     * @param maxNumber max number
     * @param limit     max number of entries
     * @param count     number of pages
     * @param search    search
     * @param orderBy   orderBy
     * @param entries   entry list
     */
    public Page(int number, int maxNumber, int limit, int count, String search, String orderBy, List<T> entries) {
        if (number < 1) {
            throw new RuntimeException("page number must be greater or equal to one");
        }
        if (maxNumber < 0) {
            throw new RuntimeException("page number must be greater or equal to one");
        }
        if (limit <= 0) {
            throw new RuntimeException("page limit must be greater than zero");
        }
        if (count < 0) {
            throw new RuntimeException("page count must be greater or equal to zero");
        }
        if (entries == null) {
            throw new NullPointerException("page entries must not be null");
        }

        this.number = number;
        this.maxNumber = maxNumber;
        this.limit = limit;
        this.count = count;
        this.search = search;
        this.orderBy = orderBy;
        this.entries = entries;
    }

    public int getNumber() {
        return number;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getLimit() {
        return limit;
    }

    public int getCount() {
        return count;
    }

    public String getSearch() {
        return search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public List<T> getEntries() {
        return entries;
    }

}
