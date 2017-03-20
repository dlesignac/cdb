package fr.ebiz.cdb.model;

public enum Column {

    COMPUTER_NAME("computerName"),
    INTRODUCED("introducedDate"),
    DISCONTINUED("discontinuedDate"),
    COMPANY_NAME("companyName");

    private String name;

    /**
     * Constructor.
     *
     * @param name name
     */
    Column(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Finds Column for a given name.
     *
     * @param id name
     * @return Column
     */
    public static Column of(String id) {
        for (Column column : Column.values()) {
            if (column.name.equals(id)) {
                return column;
            }
        }

        return null;
    }

}