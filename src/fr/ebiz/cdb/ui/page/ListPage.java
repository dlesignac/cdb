package fr.ebiz.cdb.ui.page;

import java.util.List;

abstract class ListPage extends Page {
	
	protected List entities;

	public ListPage(String header, List entities) {
		super(header);
		this.entities = entities;
	}

}
