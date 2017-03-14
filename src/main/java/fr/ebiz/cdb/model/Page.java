package fr.ebiz.cdb.model;

import java.util.List;

public class Page<T> {

    private int number;
    private int last;
    private int count;
    private int limit;
    private String filter;
    private String sort;
    private String order;
    private List<T> entries;

    /**
     * Constructor.
     **/
    public Page() {

    }

    public int getNumber() {
        return number;
    }

    public int getLast() {
        return last;
    }

    public int getLimit() {
        return limit;
    }

    public int getCount() {
        return count;
    }

    public String getFilter() {
        return filter;
    }

    public String getSort() {
        return sort;
    }

    public String getOrder() {
        return order;
    }

    public List<T> getEntries() {
        return entries;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

}
