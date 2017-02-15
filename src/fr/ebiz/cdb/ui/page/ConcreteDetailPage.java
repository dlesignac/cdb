package fr.ebiz.cdb.ui.page;

import java.util.Map;

class ConcreteDetailPage extends DetailPage {

	public ConcreteDetailPage(String header, Map<String, String> options, Map<String, String> details) {
		super(header, options, details);
	}

	@Override
	public void display() {
		String display = this.header;

		display += "\n";

		for (Map.Entry<String, String> detail : details.entrySet()) {
			display += detail.getKey();
			display += " : ";
			display += detail.getValue();
			display += "\n";
		}

		display += "\n";

		for (Map.Entry<String, String> option : options.entrySet()) {
			display += option.getKey();
			display += " -> ";
			display += option.getValue();
			display += "\n";
		}

		display += "\n";

		if (this.error != null) {
			display += this.error + "\n\n";
		}

		display += "  >> ";

		System.out.print(display);
	}

}
