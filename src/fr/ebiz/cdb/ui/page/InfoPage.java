package fr.ebiz.cdb.ui.page;

public class InfoPage implements ContentPage {

	private String info;

	public InfoPage(String info) {
		this.info = info;
	}

	@Override
	public String toDisplay() {
		return info;
	}

}
