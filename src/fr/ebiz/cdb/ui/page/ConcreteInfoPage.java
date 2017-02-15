package fr.ebiz.cdb.ui.page;

import java.util.Map;

class ConcreteInfoPage extends InfoPage {

	public ConcreteInfoPage(String header, String info) {
		super(header, info);
	}

	@Override
	public void display() {
		String display = this.header;
		
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

		System.out.println(display);

	}

}
