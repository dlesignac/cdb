package fr.ebiz.cdb.console.frame;

import fr.ebiz.cdb.binding.ComputerDTO;

public class FrameComputer extends Frame {

    private ComputerDTO computerDTO;

    public ComputerDTO getComputerDTO() {
        return computerDTO;
    }

    void setComputer(ComputerDTO computerDTO) {
        this.computerDTO = computerDTO;
    }

    @Override
    public void display() {
        displayHeader();

        String name = computerDTO.getName();
        String introduced = computerDTO.getIntroduced() == null ? "UNKNOWN" : computerDTO.getIntroduced();
        String discontinued = computerDTO.getDiscontinued() == null ? "UNKNOWN" : computerDTO.getDiscontinued();
        String manufacturer = computerDTO.getCompanyName() == null ? "UNKNOWN" : computerDTO.getCompanyName();

        String display = "Name         : " + name + "\n" +
                "Introduced   : " + introduced + "\n" +
                "Discontinued : " + discontinued + "\n" +
                "Manufacturer : " + manufacturer + "\n";

        System.out.print(display);

        displayOptions();
        displayError();
    }

}