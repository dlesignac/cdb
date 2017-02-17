package fr.ebiz.cdb.ui.page;

import java.util.ArrayList;
import java.util.List;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.ui.cli.CLIOptions;

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
		optionsList.add(new Option(CLIOptions.LIST_COMPUTERS, "List computers"));
		optionsList.add(new Option(CLIOptions.LIST_COMPANIES, "List companies"));
		optionsList.add(new Option(CLIOptions.CREATE_COMPUTER, "Create computer"));
		optionsList.add(new Option(CLIOptions.QUIT, "Quit"));
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
		optionsList.add(new Option(CLIOptions.SHOW + " <id>", "Show computer identified by <id>"));
		optionsList.add(new Option(CLIOptions.BACK, "Back"));
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
		optionsList.add(new Option(CLIOptions.EDIT, "Edit"));
		optionsList.add(new Option(CLIOptions.DELETE, "Delete"));
		optionsList.add(new Option(CLIOptions.BACK, "Back"));
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
		optionsList.add(new Option(CLIOptions.BACK, "Back"));
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

		ContentPage content = new ComputerChangePage(computer);

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(new Option(CLIOptions.NEW_NAME + " <new>", "Name"));
		optionsList.add(new Option(CLIOptions.NEW_INTRODUCED + " <new>", "Introduced"));
		optionsList.add(new Option(CLIOptions.NEW_DISCONTINUED + " <new>", "Discontinued"));
		optionsList.add(new Option(CLIOptions.NEW_MANUFACTURER + " <new>", "Manufacturer"));
		optionsList.add(new Option(CLIOptions.SAVE, "Save"));
		optionsList.add(new Option(CLIOptions.CANCEL, "Cancel"));
		OptionsPage options = new OptionsPage(optionsList);

		ErrorPage error = new ErrorPage(null);

		return new FullPage(header, content, options, error);
	}

	public Page buildComputerCreate(Computer computer) {
		List<String> levels = new ArrayList<>();
		levels.add("CDB");
		levels.add("new computer");
		HeaderPage header = new HeaderPage(levels);

		ContentPage content = new ComputerChangePage(computer);

		List<Option> optionsList = new ArrayList<>();
		optionsList.add(new Option(CLIOptions.NEW_NAME + " <new>", "Name"));
		optionsList.add(new Option(CLIOptions.NEW_INTRODUCED + " <new>", "Introduced"));
		optionsList.add(new Option(CLIOptions.NEW_DISCONTINUED + " <new>", "Discontinued"));
		optionsList.add(new Option(CLIOptions.NEW_MANUFACTURER + " <new>", "Manufacturer"));
		optionsList.add(new Option(CLIOptions.SAVE, "Save"));
		optionsList.add(new Option(CLIOptions.CANCEL, "Cancel"));
		OptionsPage options = new OptionsPage(optionsList);

		ErrorPage error = new ErrorPage(null);

		return new FullPage(header, content, options, error);
	}

}
