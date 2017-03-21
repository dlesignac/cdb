package fr.ebiz.cdb.core.model;

public enum Order {

    ASC("ASC"),
    DESC("DESC");

    private String name;

    /**
     * Constructor.
     *
     * @param name name
     */
    Order(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Finds Order for a given name.
     *
     * @param id name
     * @return Order
     */
    public static Order of(String id) {
        for (Order order : Order.values()) {
            if (order.name.equals(id)) {
                return order;
            }
        }

        return null;
    }

}