package fr.ebiz.cdb.ui.page;

abstract class InfoPage extends Page {

	protected String info;
	
	public InfoPage(String header, String info) {
		super(header);
		this.info = info;
	}

}
