package fr.ebiz.cdb.persistence;

import java.util.List;

public class Page<T> {

    private int limit;
    private int offset;
    private List<T> entries;

    /**
     * Page should not be instantiated without settings attributes.
     * @param limit
     *            max page number
     * @param offset
     *            current page number
     * @param entries
     *            entry list
     */
    public Page(int limit, int offset, List<T> entries) {
        this.limit = limit;
        this.offset = offset;
        this.entries = entries;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public List<T> getEntries() {
        return entries;
    }

    public void setEntries(List<T> entries) {
        this.entries = entries;
    }

}
