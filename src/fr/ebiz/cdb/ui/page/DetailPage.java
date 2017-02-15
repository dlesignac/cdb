package fr.ebiz.cdb.ui.page;

import java.util.Map;

abstract class DetailPage extends Page {

	protected Map<String, String> details;

	public DetailPage(String header, Map<String, String> options, Map<String, String> details) {
		super(header, options);
		this.details = details;
	}

}
