package fr.ebiz.cdb.ui.page;

import java.util.Map;

abstract class InfoPage extends Page {

	protected String info;

	public InfoPage(String header, Map<String, String> options, String info) {
		super(header, options);
		this.info = info;
	}

}
