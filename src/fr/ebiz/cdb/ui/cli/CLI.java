package fr.ebiz.cdb.ui.cli;

import java.util.Scanner;

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
	private DAOFactory daoFactory;
	private Scanner in = new Scanner(System.in);
	private CLIStatus status;
	private Page nextPage;

	public CLI() {
		this.pageBuilder = new CLIPageBuilderImpl();
		this.daoFactory = new DAOFactory(DBConnection.getInstance());
		this.status = CLIStatus.INDEX;
		this.nextPage = pageBuilder.getIndexPage();
	}

	public void loop() {
		while (this.status != CLIStatus.EXIT) {
			this.nextPage.display();
			listen();
		}
	}

	private void listen() {
		String input = in.nextLine().trim();

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
		default:
			break;
		}
	}

	private void doIndex(String input) {
		if (CLIMenu.INDEX_COMPANY_LIST.equals(input)) {
			DAO companyDAO = daoFactory.getCompanyDAO();
			this.nextPage = pageBuilder.getListPage(companyDAO.fetch());
			this.status = CLIStatus.COMPANY_LIST;
		} else if (CLIMenu.INDEX_COMPUTER_LIST.equals(input)) {
			DAO computerDAO = daoFactory.getComputerDAO();
			this.nextPage = pageBuilder.getListPage(computerDAO.fetch());
			this.status = CLIStatus.COMPUTER_LIST;
		} else if (CLIMenu.INDEX_QUIT.equals(input)) {
			this.status = CLIStatus.EXIT;
		} else {
			this.nextPage.setError("Invalid input");
		}
	}

	private void doCompanyList(String input) {
		if (CLIMenu.LISTPAGE_BACK.equals(input)) {
			this.nextPage = pageBuilder.getIndexPage();
			this.status = CLIStatus.INDEX;
		} else {
			this.nextPage.setError("Invalid input");
		}
	}

	private void doComputerList(String input) {
		if (CLIMenu.LISTPAGE_BACK.equals(input)) {
			this.nextPage = pageBuilder.getIndexPage();
			this.status = CLIStatus.INDEX;
		} else {
			this.nextPage.setError("Invalid input");
		}
	}

}
