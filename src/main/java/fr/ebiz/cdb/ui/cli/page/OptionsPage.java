package fr.ebiz.cdb.ui.cli.page;

import java.util.List;

/**
 * Options Page Component. Computes data into a string to be printed for
 * navigation options.
 */
public class OptionsPage implements PageComponent {

    private List<Option> options;

    /**
     * Constructor.
     * @param options
     *            list of available options
     */
    public OptionsPage(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toDisplay() {
        String display = "";

        for (Option option : this.options) {
            display += option.getId();
            display += " -> ";
            display += option.getDescription();
            display += "\n";
        }

        return display;
    }

}