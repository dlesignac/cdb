package fr.ebiz.cdb.ui.page;

import java.util.List;
import java.util.Map;

abstract class ListPage extends Page {

	protected List entities;

	public ListPage(String header, Map<String, String> options, List entities) {
		super(header, options);
		this.entities = entities;
	}

}
