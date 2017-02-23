package fr.ebiz.cdb.ui.cli.frame;

import fr.ebiz.cdb.model.Computer;

/**
 * Computer change frame.
 */
public class FrameComputerChange extends Frame {

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

        String name = this.computer.getName() == null ? "" : this.computer.getName();
        String introduced = this.computer.getIntroduced() == null ? "" : this.computer.getIntroduced().toString();
        String discontinued = this.computer.getDiscontinued() == null ? "" : this.computer.getDiscontinued().toString();
        String manufacturer = this.computer.getManufacturer() == null ? "" : this.computer.getManufacturer().getName();

        String display = "Name         : " +
                name + "\n" + "Introduced   : " +
                introduced + "\n" + "Discontinued : "
                + discontinued + "\n" + "Manufacturer : "
                + manufacturer + "\n";

        System.out.print(display);

        displayOptions();
        displayError();
    }

}
