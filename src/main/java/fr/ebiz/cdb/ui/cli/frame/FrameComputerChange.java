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

        String name = computer.getName() == null ? "" : computer.getName();
        String introduced = computer.getIntroduced() == null ? "" : computer.getIntroduced().toString();
        String discontinued = computer.getDiscontinued() == null ? "" : computer.getDiscontinued().toString();
        String manufacturer = computer.getManufacturer() == null ? "" : computer.getManufacturer().getName();

        String display = "Name         : " + name + "\n" +
                "Introduced   : " + introduced + "\n" +
                "Discontinued : " + discontinued + "\n" +
                "Manufacturer : " + manufacturer + "\n";

        System.out.print(display);

        displayOptions();
        displayError();
    }
}
