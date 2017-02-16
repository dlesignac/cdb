package fr.ebiz.cdb.ui.page;

import fr.ebiz.cdb.model.Computer;

/**
 * Computer Edit Page. Computes data into a string to be printed for Computer
 * editing. Also holds computer object that can be got by the controller.
 */
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
