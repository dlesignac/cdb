package fr.ebiz.cdb.ui.cli.page;

/**
 * Navigation Option. Describes a CLI navigation option that can be passed to a
 * OptionsPage in order to be displayed.
 */
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
