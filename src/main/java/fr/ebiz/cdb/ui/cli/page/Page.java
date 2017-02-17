package fr.ebiz.cdb.ui.cli.page;

/**
 * Page interface. Abstracts a CLI Page.
 */
public interface Page {

	void display();

	void setError(String error);

}
