package fr.ebiz.cdb.ui.cli.page;

/**
 * Error Page Component. Component holding an error that can be printed at next
 * full built page display.
 */
public class ErrorPage implements PageComponent {

	private String error;

	public ErrorPage(String error) {
		this.error = error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toDisplay() {
		String display = "";

		if (this.error != null) {
			display += this.error + "\n\n";
		}

		return display;
	}

}
