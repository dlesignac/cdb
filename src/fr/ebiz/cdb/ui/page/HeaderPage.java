package fr.ebiz.cdb.ui.page;

import java.util.List;

/**
 * Header Page Component. Computes data into a string to be printed for
 * navigation tracking.
 */
public class HeaderPage implements PageComponent {

	private List<String> levels;

	public HeaderPage(List<String> levels) {
		this.levels = levels;
	}

	@Override
	public String toDisplay() {
		String display = this.levels.get(0);

		int size = this.levels.size();

		for (int i = 1; i < size; i++) {
			display += " > ";
			display += this.levels.get(i);
		}

		display += "\n";

		return display;
	}

}
