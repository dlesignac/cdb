package fr.ebiz.cdb.ui.page;

import java.util.Map;

public abstract class Page {

	protected final String header;
	protected String error;
	protected Map<String, String> options;

	public Page(String header, Map<String, String> options) {
		this.header = header;
		this.options = options;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setOptions(Map<String, String> options) {
		this.options = options;
	}

	public abstract void display();

}
