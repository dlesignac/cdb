package fr.ebiz.cdb.ui.cli;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.dao.DAO;
import fr.ebiz.cdb.persistence.dao.DAOFactory;
import fr.ebiz.cdb.ui.page.Page;

public class CLI {

	public static void main(String[] args) {
		CLI cli = new CLI();
		cli.loop();
	}

	private CLIPageBuilder pageBuilder;
	private Page nextPage;
	private CLIStatus status;

	private List computers;
	private List companies;

	private DAOFactory daoFactory;
	private Scanner in = new Scanner(System.in);

	public CLI() {
		this.pageBuilder = new CLIPageBuilderImpl();
		this.daoFactory = new DAOFactory(DBConnection.getInstance());
		this.status = CLIStatus.INDEX;
		this.nextPage = this.pageBuilder.getIndexPage();
	}

	public void loop() {
		while (this.status != CLIStatus.EXIT) {
			this.nextPage.display();
			listen();
		}
	}

	private void listen() {
		String[] input = in.nextLine().split(" ");

		switch (status) {
		case INDEX:
			doIndex(input);
			break;
		case COMPANY_LIST:
			doCompanyList(input);
			break;
		case COMPUTER_LIST:
			doComputerList(input);
			break;
		case COMPUTER_DETAIL:
			doComputerDetail(input);
		default:
			break;
		}
	}

	private void doIndex(String[] input) {
		if (CLIMenu.INDEX_COMPANY_LIST.equals(input[0])) {
			DAO companyDAO = daoFactory.getCompanyDAO();
			this.companies = companyDAO.fetch();
			this.nextPage = pageBuilder.getListPage(companies);
			this.status = CLIStatus.COMPANY_LIST;
		} else if (CLIMenu.INDEX_COMPUTER_LIST.equals(input[0])) {
			DAO computerDAO = daoFactory.getComputerDAO();
			this.computers = computerDAO.fetch();
			this.nextPage = pageBuilder.getListPage(this.computers);
			this.status = CLIStatus.COMPUTER_LIST;
		} else if (CLIMenu.INDEX_QUIT.equals(input[0])) {
			this.status = CLIStatus.EXIT;
		} else {
			this.nextPage.setError(CLIMenu.ERROR_INVALID_INPUT);
		}
	}

	private void doCompanyList(String[] input) {
		if (CLIMenu.GENERAL_BACK.equals(input[0])) {
			this.nextPage = pageBuilder.getIndexPage();
			this.status = CLIStatus.INDEX;
		} else {
			this.nextPage.setError(CLIMenu.ERROR_INVALID_INPUT);
		}
	}

	private void doComputerList(String[] input) {
		if (CLIMenu.GENERAL_BACK.equals(input[0])) {
			this.nextPage = pageBuilder.getIndexPage();
			this.status = CLIStatus.INDEX;
		} else if (CLIMenu.LISTPAGE_SHOW.equals(input[0])) {
			if (input.length < 2) {
				this.nextPage.setError(CLIMenu.ERROR_MISSING_PARAMETER);
			} else {
				String parameter = input[1];
				int position;

				if (!StringUtils.isNumeric(parameter)
						|| (position = Integer.parseInt(parameter) - 1) >= this.computers.size()) {
					this.nextPage.setError(CLIMenu.ERROR_INVALID_INPUT);
				} else {
					Computer computer = (Computer) this.computers.get(position);

					Map<String, String> details = new HashMap<>();
					details.put("Name", computer.getName());

					LocalDate introduced = computer.getIntroduced();
					LocalDate discontinued = computer.getDiscontinued();
					Company manufacturer = computer.getManufacturer();

					details.put("Introduced", introduced == null ? "UNKNOWN" : introduced.toString());
					details.put("Discontinued", discontinued == null ? "UNKNOWN" : discontinued.toString());
					details.put("Manufacturer", manufacturer == null ? "UNKNOWN" : manufacturer.getName());

					this.nextPage = this.pageBuilder.getDetailPage(details);
				}
			}
		} else {
			this.nextPage.setError(CLIMenu.ERROR_INVALID_INPUT);
		}
	}

	private void doComputerDetail(String[] input) {
		if (CLIMenu.GENERAL_BACK.equals(input[0])) {
			this.nextPage = pageBuilder.getIndexPage();
			this.status = CLIStatus.INDEX;
		} else {
			this.nextPage.setError(CLIMenu.ERROR_INVALID_INPUT);
		}
	}

}
