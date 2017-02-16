package fr.ebiz.cdb.ui.page;

/**
 * Info Content Page Component. Computes data into a String to be printed for
 * further info.
 */
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
