package fr.ebiz.cdb.ui.cli.page;

import fr.ebiz.cdb.model.Computer;

public class ComputerChangePage implements ComputerHolderPage {

	private Computer computer;

	public ComputerChangePage(Computer computer) {
		this.computer = computer;
	}

	@Override
	public String toDisplay() {
		String name = this.computer.getName() == null ? "" : this.computer.getName();
		String introduced = this.computer.getIntroduced() == null ? "" : this.computer.getIntroduced().toString();
		String discontinued = this.computer.getDiscontinued() == null ? "" : this.computer.getDiscontinued().toString();
		String manufacturer = this.computer.getManufacturer() == null ? "" : this.computer.getManufacturer().getName();

		String display = "Name         : " + name + "\n" + "Introduced   : " + introduced + "\n" + "Discontinued : "
				+ discontinued + "\n" + "Manufacturer : " + manufacturer + "\n";

		return display;
	}

	@Override
	public Computer getComputer() {
		return this.computer;
	}

}
