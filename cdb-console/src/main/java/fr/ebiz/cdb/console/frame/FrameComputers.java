package fr.ebiz.cdb.console.frame;

import fr.ebiz.cdb.binding.ComputerDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class FrameComputers extends Frame {

    private int pageNumber;
    private List<ComputerDTO> computers;

    public int getPageNumber() {
        return pageNumber;
    }

    public List<ComputerDTO> getComputers() {
        return computers;
    }

    void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setComputers(List<ComputerDTO> computers) {
        this.computers = computers;
    }

    @Override
    public void display() {
        displayHeader();

        String display = "";

        for (ComputerDTO computer : computers) {
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