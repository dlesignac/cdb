package fr.ebiz.cdb.ui.cli;

import java.util.List;
import java.util.Scanner;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.dao.DAO;
import fr.ebiz.cdb.persistence.dao.DAOFactory;
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
	}

	private void listen() {
		String[] input = this.in.nextLine().split(" ");

		switch (this.status) {
		case INDEX:
			doIndex(input);
			break;
		default:
			break;
		}
	}

	private void doIndex(String[] input) {
		if ("q".equals(input[0])) {
			this.status = CLIStatus.EXIT;
		} else if ("1".equals(input[0])) {
			List<Computer> computers = this.computerDAO.fetch();

			this.nextPage = new PageBuilder().buildComputers(computers);
			this.status = CLIStatus.COMPUTERS;
		} else {
			this.nextPage.setError("Invalid input");
		}
	}

}
