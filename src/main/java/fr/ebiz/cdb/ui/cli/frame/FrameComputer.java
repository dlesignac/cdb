package fr.ebiz.cdb.ui.cli.frame;

import fr.ebiz.cdb.model.Computer;

/**
 * Computer frame.
 */
public class FrameComputer extends Frame {

    private Computer computer;

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void display() {
        displayHeader();

        String name = this.computer.getName();
        String introduced = this.computer.getIntroduced() == null ? "UNKNOWN" :
                this.computer.getIntroduced().toString();
        String discontinued = this.computer.getDiscontinued() == null ? "UNKNOWN" :
                this.computer.getDiscontinued().toString();
        String manufacturer = this.computer.getManufacturer() == null ? "UNKNOWN" :
                this.computer.getManufacturer().getName();

        String display = "Name         : " +
                name + "\n" + "Introduced   : " +
                introduced + "\n" + "Discontinued : " +
                discontinued + "\n" + "Manufacturer : " +
                manufacturer + "\n";

        System.out.print(display);

        displayOptions();
        displayError();
    }

}
