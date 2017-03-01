package fr.ebiz.cdb.persistence.dao;

/**
 * Order.
 */
public enum Order {

    ASC("ASC", "ASC"),
    DESC("DESC", "DESC");

    private String id;
    private String value;

    /**
     * Constructor.
     *
     * @param id    id
     * @param value value
     */
    Order(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Finds Order for a given id.
     *
     * @param id id
     * @return Order
     */
    public static Order of(String id) {
        for (Order order : Order.values()) {
            if (order.id.equals(id)) {
                return order;
            }
        }

        return null;
    }

}
