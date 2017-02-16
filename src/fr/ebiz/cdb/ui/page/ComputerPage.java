package fr.ebiz.cdb.ui.page;

import fr.ebiz.cdb.model.Computer;

/**
 * Computer Details Page. Computes data into a string to be printed for Computer
 * details. Also holds computer object that can be got by the controller.
 */
public class ComputerPage implements ComputerHolderPage {

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
