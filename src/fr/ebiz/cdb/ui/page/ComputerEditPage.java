package fr.ebiz.cdb.ui.page;

public class ComputerEditPage extends ContentPage {

	private String computerId;

	public ComputerEditPage(String computerId) {
		this.computerId = computerId;
	}

	public String getComputerId() {
		return this.computerId;
	}

	@Override
	public String toDisplay() {
		return "Editing computer";
	}

}
