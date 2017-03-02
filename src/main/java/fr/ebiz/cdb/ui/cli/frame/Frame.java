package fr.ebiz.cdb.ui.cli.frame;

import java.util.List;

/**
 * Frame.
 */
public class Frame {
    private List<String> levels;
    private List<Option> options;
    protected String error;

    public List<String> getLevels() {
        return levels;
    }

    public List<Option> getOptions() {
        return options;
    }

    public String getError() {
        return error;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void setError(String error) {
        this.error = error;
    }

    /**
     * Displays header.
     */
    void displayHeader() {
        String display = levels.get(0);

        int size = levels.size();

        for (int i = 1; i < size; i++) {
            display += " > " + levels.get(i);
        }

        display += "\n";

        System.out.print(display);
    }

    /**
     * Displays options.
     */
    void displayOptions() {
        String display = "";

        for (Option option : options) {
            display += option.getId() + " -> " + option.getDescription() + "\n";
        }

        System.out.print(display);
    }

    /**
     * Displays error.
     */
    void displayError() {
        String display = "";

        if (error != null) {
            display += error + "\n\n";
        }

        System.out.print(display);
    }

    /**
     * Displays frame.
     */
    public void display() {
        displayHeader();
        displayOptions();
        displayError();
    }
}
