package fr.ebiz.cdb.ui.page;

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
