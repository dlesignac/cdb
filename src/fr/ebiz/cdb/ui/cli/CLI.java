package fr.ebiz.cdb.ui.cli;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.dao.DAO;
import fr.ebiz.cdb.persistence.dao.DAOFactory;
import fr.ebiz.cdb.service.validator.ComputerValidator;
import fr.ebiz.cdb.ui.page.ComputerHolderPage;
import fr.ebiz.cdb.ui.page.ComputerPage;
import fr.ebiz.cdb.ui.page.FullPage;
import fr.ebiz.cdb.ui.page.Page;
import fr.ebiz.cdb.ui.page.PageBuilder;

/**
 * Command Line Interface.
 */
public class CLI {

	private static Logger logger = LoggerFactory.getLogger(CLI.class);

	/**
	 * CLI entry point.
	 */
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

	/**
	 * Main loop. Prints pages and waits for user inputs.
	 */
	public void loop() {
		while (this.status != CLIStatus.EXIT) {
			this.nextPage.display();

			try {
				listen();
			} catch (Exception e) {
				this.nextPage.setError("An error occurred");
				logger.error("caught exception while running CLI", e);
			}
		}

		this.in.close();

		try {
			DBConnection.getInstance().close();
		} catch (Exception e) {
			logger.error("caught exception while closing db connection");
		}
	}

	/**
	 * Waits for and manages user inputs.
	 */
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
		case COMPUTER_CREATE:
			doComputerCreate(input);
			break;
		default:
			break;
		}
	}

	/**
	 * Do functions. Manages user inputs based on the CLI status.
	 */

	private void doIndex(String[] input) {
		if ("q".equals(input[0])) {
			this.status = CLIStatus.EXIT;
		} else if ("1".equals(input[0])) {
			callComputers();
		} else if ("2".equals(input[0])) {
			callCompanies();
		} else if ("3".equals(input[0])) {
			callComputerCreate(new Computer());
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

				if (computer == null) {
					callErrorInvalidInput();
				} else {
					callComputer(computer);
				}
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
		} else if ("s".equals(input[0])) {
			Computer computer = getComputerFromPage();
			this.computerDAO.update(computer);
			callComputer(computer);
		} else {
			doComputerChange(input);
		}
	}

	private void doComputerCreate(String[] input) {
		if ("c".equals(input[0])) {
			callIndex();
		} else if ("s".equals(input[0])) {
			Computer computer = getComputerFromPage();
			this.computerDAO.create(computer);
			callComputer(computer);
		} else {
			doComputerChange(input);
		}
	}

	private void doComputerChange(String[] input) {
		if ("1".equals(input[0])) {
			if (input.length < 2) {
				callErrorMissingParameter();
			} else {
				if (ComputerValidator.validateName(input[1])) {
					Computer computer = getComputerFromPage();
					computer.setName(input[1]);
				} else {
					this.nextPage.setError("Incorrect name specified");
				}
			}
		} else if ("2".equals(input[0])) {
			if (input.length < 2) {
				callErrorMissingParameter();
			} else {
				if (ComputerValidator.validateIntroduced(input[1])) {
					Computer computer = getComputerFromPage();
					LocalDate introduced = LocalDate.parse(input[1]);
					computer.setIntroduced(introduced);
				} else {
					this.nextPage.setError("Incorrect introduction date specified");
				}
			}
		} else if ("3".equals(input[0])) {
			if (input.length < 2) {
				callErrorMissingParameter();
			} else {
				Computer computer = getComputerFromPage();

				if (ComputerValidator.validateDiscontinued(input[1], computer)) {
					LocalDate discontinued = LocalDate.parse(input[1]);
					computer.setDiscontinued(discontinued);
				} else {
					this.nextPage.setError("Incorrect discontinuation date specified");
				}
			}
		} else if ("4".equals(input[0])) {
			if (input.length < 2) {
				callErrorMissingParameter();
			} else {
				// TODO validation
				Computer computer = getComputerFromPage();
				int companyId = Integer.parseInt(input[1]);
				computer.setManufacturer(this.companyDAO.find(companyId));
				this.computerDAO.update(computer);
			}
		} else {
			callErrorInvalidInput();
		}
	}

	/**
	 * Call functions. Call page builder to create next page and update CLI
	 * status.
	 */

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
		Computer computer = getComputerFromPage();
		this.nextPage = new PageBuilder().buildComputerChange(computer);
		this.status = CLIStatus.COMPUTER_EDIT;
	}

	private void callComputerBack() {
		Computer computer = getComputerFromPage();
		callComputer(computer);
	}

	private void callComputerCreate(Computer computer) {
		this.nextPage = new PageBuilder().buildComputerChange(computer);
		this.status = CLIStatus.COMPUTER_CREATE;
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

	/**
	 * Deletes computer.
	 */
	private void deleteComputer() {
		ComputerPage page = (ComputerPage) ((FullPage) this.nextPage).getContentPage();
		Computer computer = page.getComputer();
		this.computerDAO.delete(computer);
	}

	/**
	 * Gets computer by id using DAO.
	 */
	private Computer getComputerById(int id) {
		return this.computerDAO.find(id);
	}

	/**
	 * Gets computers held by last page.
	 */
	private Computer getComputerFromPage() {
		ComputerHolderPage page = (ComputerHolderPage) ((FullPage) this.nextPage).getContentPage();
		return page.getComputer();
	}

}
