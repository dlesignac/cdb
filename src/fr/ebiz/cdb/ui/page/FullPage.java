package fr.ebiz.cdb.ui.page;

public class FullPage implements Page {

	private HeaderPage header;
	private ContentPage content;
	private OptionsPage options;
	private ErrorPage error;

	public FullPage(HeaderPage header, ContentPage content, OptionsPage options, ErrorPage error) {
		this.header = header;
		this.content = content;
		this.options = options;
		this.error = error;
	}

	public ContentPage getContentPage() {
		return this.content;
	}

	@Override
	public void display() {
		String display = header.toDisplay() + "\n" + content.toDisplay() + "\n" + options.toDisplay() + "\n"
				+ error.toDisplay() + " >> ";

		System.out.print(display);
	}

	@Override
	public void setError(String error) {
		this.error.setError(error);
	}

}
