package fr.ebiz.cdb.service.datasource;

import java.util.List;

public class Page<T> {

    private int limit;
    private int count;
    private int number;
    private List<T> entries;

    /**
     * Constructor.
     *
     * @param limit   max number of entries
     * @param count   number of pages
     * @param number  current frame number
     * @param entries entry list
     */
    public Page(int limit, int count, int number, List<T> entries) {
        if (limit <= 0) {
            throw new RuntimeException("page limit must be greater than zero");
        }
        if (count < 0) {
            throw new RuntimeException("page count must be greater or equal to zero");
        }
        if (number < 1) {
            throw new RuntimeException("page number must be greater or equal to one");
        }
        if (entries == null) {
            throw new NullPointerException("page entries must not be null");
        }

        this.limit = limit;
        this.count = count;
        this.number = number;
        this.entries = entries;
    }

    public int getLimit() {
        return limit;
    }

    public int getCount() {
        return count;
    }

    public int getNumber() {
        return number;
    }

    public List<T> getEntries() {
        return entries;
    }

}
