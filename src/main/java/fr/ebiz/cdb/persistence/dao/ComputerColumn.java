package fr.ebiz.cdb.persistence.dao;

/**
 * Computer column.
 */
public enum ComputerColumn {

    COMPUTER_NAME("computerName", "c1.name"),
    INTRODUCED("introduced", "introduced"),
    DISCONTINUED("discontinued", "discontinued"),
    COMPANY_NAME("companyName", "c2.name");

    private String id;
    private String column;

    /**
     * Constructor.
     *
     * @param id     id
     * @param column column
     */
    ComputerColumn(String id, String column) {
        this.id = id;
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    /**
     * Finds ComputerColumn for a given id.
     *
     * @param id id
     * @return ComputerColumn
     */
    public static ComputerColumn of(String id) {
        for (ComputerColumn column : ComputerColumn.values()) {
            if (column.id.equals(id)) {
                return column;
            }
        }

        return null;
    }

}
