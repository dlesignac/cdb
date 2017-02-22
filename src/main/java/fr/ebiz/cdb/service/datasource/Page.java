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
     * @param number  current page number
     * @param entries entry list
     */
    public Page(int limit, int count, int number, List<T> entries) {
        this.limit = limit;
        this.count = count;
        this.number = number;
        this.entries = entries;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<T> getEntries() {
        return entries;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

}
