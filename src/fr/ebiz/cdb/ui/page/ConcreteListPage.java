package fr.ebiz.cdb.ui.page;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

class ConcreteListPage extends ListPage {

	ConcreteListPage(String header, List entities) {
		super(header, entities);
	}

	@Override
	public void display() {
		String display = this.header;

		display += "\n";

		int size = this.entities.size();
		int length = String.valueOf(size).length();

		for (int i = 1; i <= size; i++) {
			display += StringUtils.leftPad(String.valueOf(i), length);
			display += ". ";
			display += this.entities.get(i - 1).toString();
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

		display += "  >>";

		System.out.println(display);
	}

}
