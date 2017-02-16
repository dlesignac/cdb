package fr.ebiz.cdb.ui.cli;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.dao.DAO;
import fr.ebiz.cdb.persistence.dao.DAOFactory;
import fr.ebiz.cdb.ui.page.ComputerHolderPage;
import fr.ebiz.cdb.ui.page.ComputerPage;
import fr.ebiz.cdb.ui.page.FullPage;
import fr.ebiz.cdb.ui.page.Page;
import fr.ebiz.cdb.ui.page.PageBuilder;

public class CLI {

	public static void main(String[] args) {
		new CLI().loop();
	}

	private DAO<Company> companyDAO;
	private DAO<Computer> computerDAO;
	private Scanner in;
	private Page nextPage;
	private CLIStatus status;

	public CLI() {
		DAOFactory daoFactory = new DAOFactory(DBConnection.getInstance());
		this.companyDAO = daoFactory.getCompanyDAO();
		this.computerDAO = daoFactory.getComputerDAO();

		this.in = new Scanner(System.in);
		this.nextPage = new PageBuilder().buildIndex();
		this.status = CLIStatus.INDEX;
	}

	public void loop() {
		while (this.status != CLIStatus.EXIT) {
			this.nextPage.display();
			listen();
		}

		this.in.close();

		try {
			DBConnection.getInstance().close();
		} catch (SQLException e) {
			// TODO
		}
	}

	private void listen() {
		String[] input = this.in.nextLine().split(" ");

		switch (this.status) {
		case INDEX:
			doIndex(input);
			break;
		case COMPUTERS:
			doComputers(input);
			break;
		case COMPUTER:
			doComputer(input);
			break;
		case COMPANIES:
			doCompanies(input);
			break;
		case COMPUTER_EDIT:
			doComputerEdit(input);
			break;
		default:
			break;
		}
	}

	private void doIndex(String[] input) {
		if ("q".equals(input[0])) {
			this.status = CLIStatus.EXIT;
		} else if ("1".equals(input[0])) {
			callComputers();
		} else if ("2".equals(input[0])) {
			callCompanies();
		} else {
			callErrorInvalidInput();
		}
	}

	private void doComputers(String input[]) {
		if ("b".equals(input[0])) {
			callIndex();
		} else if ("s".equals(input[0])) {
			if (input.length < 2) {
				callErrorMissingParameter();
			} else if (!StringUtils.isNumeric(input[1])) {
				callErrorInvalidParameter();
			} else {
				int id = Integer.parseInt(input[1]);
				Computer computer = getComputerById(id);
				callComputer(computer);
			}
		} else {
			callErrorInvalidInput();
		}
	}

	private void doComputer(String[] input) {
		if ("b".equals(input[0])) {
			callComputers();
		} else if ("d".equals(input[0])) {
			deleteComputer();
			callComputers();
		} else if ("e".equals(input[0])) {
			callComputerEdit();
		} else {
			callErrorInvalidInput();
		}
	}

	private void doCompanies(String[] input) {
		if ("b".equals(input[0])) {
			callIndex();
		} else {
			callErrorInvalidInput();
		}
	}

	private void doComputerEdit(String[] input) {
		if ("c".equals(input[0])) {
			callComputerBack();
		} else if ("1".equals(input[0])) {
			Computer computer = getComputerFromPage();

			if (input.length < 2) {
				callErrorMissingParameter();
			} else {
				String name = input[1]; // TODO validation
				computer.setName(name);
				this.computerDAO.update(computer);
				this.nextPage = new PageBuilder().buildComputer(computer);
				this.status = CLIStatus.COMPUTER;
			}
		} else if ("2".equals(input[0])) {
			if (input.length < 2) {
				callErrorMissingParameter();
			} else {

			}
		} else if ("3".equals(input[0])) {
			if (input.length < 2) {
				callErrorMissingParameter();
			} else {

			}
		} else {
			callErrorInvalidInput();
		}
	}

	private void callIndex() {
		this.nextPage = new PageBuilder().buildIndex();
		this.status = CLIStatus.INDEX;
	}

	private void callComputers() {
		List<Computer> computers = this.computerDAO.fetch();
		this.nextPage = new PageBuilder().buildComputers(computers);
		this.status = CLIStatus.COMPUTERS;
	}

	private void callComputer(Computer computer) {
		this.nextPage = new PageBuilder().buildComputer(computer);
		this.status = CLIStatus.COMPUTER;
	}

	private void callCompanies() {
		List<Company> companies = this.companyDAO.fetch();
		this.nextPage = new PageBuilder().buildCompanies(companies);
		this.status = CLIStatus.COMPANIES;
	}

	private void callComputerEdit() {
		ComputerPage page = (ComputerPage) ((FullPage) this.nextPage).getContentPage();
		Computer computer = page.getComputer();
		this.nextPage = new PageBuilder().buildComputerEdit(computer);
		this.status = CLIStatus.COMPUTER_EDIT;
	}

	private void callComputerBack() {
		Computer computer = getComputerFromPage();
		callComputer(computer);
	}

	private void callErrorInvalidInput() {
		this.nextPage.setError("Invalid input");
	}

	private void callErrorMissingParameter() {
		this.nextPage.setError("Invalid parameter");
	}

	private void callErrorInvalidParameter() {
		this.nextPage.setError("Invalid parameter");
	}

	private void deleteComputer() {
		ComputerPage page = (ComputerPage) ((FullPage) this.nextPage).getContentPage();
		Computer computer = page.getComputer();
		this.computerDAO.delete(computer);
	}

	private Computer getComputerById(int id) {
		return this.computerDAO.find(id);
	}

	private Computer getComputerFromPage() {
		ComputerHolderPage page = (ComputerHolderPage) ((FullPage) this.nextPage).getContentPage();
		return page.getComputer();
	}

}
