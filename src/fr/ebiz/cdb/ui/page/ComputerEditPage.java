package fr.ebiz.cdb.ui.page;

import fr.ebiz.cdb.model.Computer;

public class ComputerEditPage implements ComputerHolderPage {

	private Computer computer;

	public ComputerEditPage(Computer computer) {
		this.computer = computer;
	}

	@Override
	public Computer getComputer() {
		return this.computer;
	}

	@Override
	public String toDisplay() {
		return "Editing computer";
	}

}
