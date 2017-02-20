package fr.ebiz.cdb.ui.cli.page;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.ebiz.cdb.model.Computer;

/**
 * Computers List Page. Computes data into a string to be printed for Computer
 * listing.
 */
public class ComputersPage implements ContentPage {

    private List<Computer> computers;

    /**
     * Constructor.
     * @param computers
     *            list of computers
     */
    public ComputersPage(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public String toDisplay() {
        String display = "";

        for (Computer computer : this.computers) {
            display += StringUtils.leftPad(String.valueOf(computer.getId()), 6);
            display += ". ";
            display += computer.getName();
            display += "\n";
        }

        return display;
    }

}
