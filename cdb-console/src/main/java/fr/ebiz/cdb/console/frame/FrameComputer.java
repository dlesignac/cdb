package fr.ebiz.cdb.console.frame;

import fr.ebiz.cdb.core.Computer;

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

        String name = computer.getName();
        String introduced = computer.getIntroduced() == null ? "UNKNOWN" : computer.getIntroduced().toString();
        String discontinued = computer.getDiscontinued() == null ? "UNKNOWN" : computer.getDiscontinued().toString();
        String manufacturer = computer.getManufacturer() == null ? "UNKNOWN" : computer.getManufacturer().getName();

        String display = "Name         : " + name + "\n" +
                "Introduced   : " + introduced + "\n" +
                "Discontinued : " + discontinued + "\n" +
                "Manufacturer : " + manufacturer + "\n";

        System.out.print(display);

        displayOptions();
        displayError();
    }

}