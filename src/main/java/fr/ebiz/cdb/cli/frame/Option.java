package fr.ebiz.cdb.cli.frame;

public class Option {

    private String id;
    private String description;

    /**
     * Constructor.
     *
     * @param id          option id
     * @param description option description
     */
    Option(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

}