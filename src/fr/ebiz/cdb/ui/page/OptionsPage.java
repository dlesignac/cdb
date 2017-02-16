package fr.ebiz.cdb.ui.page;

import java.util.List;
import java.util.Map;

public class OptionsPage implements PageComponent {

	private List<Option> options;

	public OptionsPage(List<Option> options) {
		this.options = options;
	}

	@Override
	public String toDisplay() {
		String display = "";

		for (Option option : this.options) {
			display += option.getId();
			display += " -> ";
			display += option.getDescription();
			display += "\n";
		}

		return display;
	}

}
