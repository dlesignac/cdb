package fr.ebiz.cdb.cli.frame;

import fr.ebiz.cdb.core.model.Computer;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Computers frame.
 */
public class FrameComputers extends Frame {

    private int pageNumber;
    private List<Computer> computers;

    public int getPageNumber() {
        return pageNumber;
    }

    public List<Computer> getComputers() {
        return computers;
    }

    void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setComputers(List<Computer> computers) {
        this.computers = computers;
    }

    @Override
    public void display() {
        displayHeader();

        String display = "";

        for (Computer computer : computers) {
            display += StringUtils.leftPad(String.valueOf(computer.getId()), 6);
            display += ". ";
            display += computer.getName();
            display += "\n";
        }

        System.out.print(display);

        displayOptions();
        displayError();
    }

}