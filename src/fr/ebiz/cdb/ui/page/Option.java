package fr.ebiz.cdb.ui.page;

public class Option {

	private String id;
	private String description;

	public Option(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

}
