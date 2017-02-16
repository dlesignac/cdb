package fr.ebiz.cdb.ui.page;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fr.ebiz.cdb.model.Computer;

public class ComputersPage extends ContentPage {

	private List<Computer> computers;

	public ComputersPage(List<Computer> computers) {
		this.computers = computers;
	}

	@Override
	public String toDisplay() {
		String display = "";

		for (Computer computer : this.computers) {
			display += StringUtils.leftPad(String.valueOf(computer.getId()), 6);
			display += ". ";
			display += computer.toString();
			display += "\n";
		}

		return display;
	}

}
