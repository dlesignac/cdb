package fr.ebiz.cdb.ui.page;

import fr.ebiz.cdb.model.Computer;

public class ComputerPage extends ContentPage {

	private Computer computer;

	public ComputerPage(Computer computer) {
		this.computer = computer;
	}

	public Computer getComputer() {
		return this.computer;
	}

	@Override
	public String toDisplay() {
		String name = this.computer.getName();
		String introduced = this.computer.getIntroduced() == null ? "UNKNOWN"
				: this.computer.getIntroduced().toString();
		String discontinued = this.computer.getDiscontinued() == null ? "UNKNOWN"
				: this.computer.getDiscontinued().toString();
		String manufacturer = this.computer.getManufacturer() == null ? "UNKNOWN"
				: this.computer.getManufacturer().getName();

		String display = "Name         : " + name + "\n" + "Introduced   : " + introduced + "\n" + "Discontinued : "
				+ discontinued + "\n" + "Manufacturer : " + manufacturer + "\n";

		return display;
	}

}
