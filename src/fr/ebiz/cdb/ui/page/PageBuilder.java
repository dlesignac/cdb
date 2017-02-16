package fr.ebiz.cdb.ui.page;

import java.util.ArrayList;
import java.util.List;

import fr.ebiz.cdb.model.Computer;

public class PageBuilder {

	public Page buildIndex() {
		List<String> levels = new ArrayList<>();
		levels.add("CDB");
		HeaderPage header = new HeaderPage(levels);

		ContentPage content = new InfoPage("");

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(new Option("1", "List computers"));
		optionsList.add(new Option("q", "Quit"));
		OptionsPage options = new OptionsPage(optionsList);

		ErrorPage error = new ErrorPage(null);

		return new FullPage(header, content, options, error);
	}

	public Page buildComputers(List<Computer> computers) {
		List<String> levels = new ArrayList<>();
		levels.add("CDB");
		levels.add("Computers");
		HeaderPage header = new HeaderPage(levels);

		ContentPage content = new ComputersPage(computers);

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(new Option("b", "Back"));
		OptionsPage options = new OptionsPage(optionsList);

		ErrorPage error = new ErrorPage(null);

		return new FullPage(header, content, options, error);
	}

}
