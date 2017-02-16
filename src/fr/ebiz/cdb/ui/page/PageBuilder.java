package fr.ebiz.cdb.ui.page;

import java.util.ArrayList;
import java.util.List;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

/**
 * Page Builder. Builds pages for the CLI.
 */
public class PageBuilder {

	public Page buildIndex() {
		List<String> levels = new ArrayList<>();
		levels.add("CDB");
		HeaderPage header = new HeaderPage(levels);

		ContentPage content = new InfoPage("Manage computers");

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(new Option("1", "List computers"));
		optionsList.add(new Option("2", "List companies"));
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
		optionsList.add(new Option("s <id>", "Show computer identified by <id>"));
		optionsList.add(new Option("b", "Back"));
		OptionsPage options = new OptionsPage(optionsList);

		ErrorPage error = new ErrorPage(null);

		return new FullPage(header, content, options, error);
	}

	public Page buildComputer(Computer computer) {
		List<String> levels = new ArrayList<>();
		levels.add("CDB");
		levels.add("Computers");
		levels.add(String.valueOf(computer.getId()));
		HeaderPage header = new HeaderPage(levels);

		ContentPage content = new ComputerPage(computer);

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(new Option("e", "Edit"));
		optionsList.add(new Option("d", "Delete"));
		optionsList.add(new Option("b", "Back"));
		OptionsPage options = new OptionsPage(optionsList);

		ErrorPage error = new ErrorPage(null);

		return new FullPage(header, content, options, error);
	}

	public Page buildCompanies(List<Company> companies) {
		List<String> levels = new ArrayList<>();
		levels.add("CDB");
		levels.add("Companies");
		HeaderPage header = new HeaderPage(levels);

		ContentPage content = new CompaniesPage(companies);

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(new Option("b", "Back"));
		OptionsPage options = new OptionsPage(optionsList);

		ErrorPage error = new ErrorPage(null);

		return new FullPage(header, content, options, error);
	}

	public Page buildComputerEdit(Computer computer) {
		List<String> levels = new ArrayList<>();
		levels.add("CDB");
		levels.add("Computers");
		levels.add(String.valueOf(computer.getId()));
		levels.add("edit");
		HeaderPage header = new HeaderPage(levels);

		ContentPage content = new ComputerEditPage(computer);

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(new Option("1 <new>", "Name"));
		optionsList.add(new Option("2 <new>", "Introduced"));
		optionsList.add(new Option("3 <new>", "Discontinued"));
		optionsList.add(new Option("4 <new>", "Manufacturer"));
		optionsList.add(new Option("c", "Cancel"));
		OptionsPage options = new OptionsPage(optionsList);

		ErrorPage error = new ErrorPage(null);

		return new FullPage(header, content, options, error);
	}

}
