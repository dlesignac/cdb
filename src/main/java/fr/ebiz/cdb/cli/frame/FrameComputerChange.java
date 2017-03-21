package fr.ebiz.cdb.cli.frame;

import fr.ebiz.cdb.core.dto.ComputerDTO;

public class FrameComputerChange extends Frame {

    private ComputerDTO computerDTO;

    public ComputerDTO getComputerDTO() {
        return computerDTO;
    }

    void setComputerDTO(ComputerDTO computerDTO) {
        this.computerDTO = computerDTO;
    }

    @Override
    public void display() {
        displayHeader();

        String name = computerDTO.getName() == null ? "" : computerDTO.getName();
        String introduced = computerDTO.getIntroduced() == null ? "" : computerDTO.getIntroduced();
        String discontinued = computerDTO.getDiscontinued() == null ? "" : computerDTO.getDiscontinued();
        String manufacturer = computerDTO.getCompanyId() == null ? "" : computerDTO.getCompanyId().toString();

        String display = "Name         : " + name + "\n" +
                "Introduced   : " + introduced + "\n" +
                "Discontinued : " + discontinued + "\n" +
                "Manufacturer : " + manufacturer + "\n";

        System.out.print(display);

        displayOptions();
        displayError();
    }

}