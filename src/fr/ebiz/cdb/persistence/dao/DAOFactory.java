package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

/**
 * DAO Factory. Abstracts DAO objects instantiation.
 */
public class DAOFactory {

	private final Connection connection;

	public DAOFactory(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Returns new instance of ComputerDAO.
	 */
	public DAO<Computer> getComputerDAO() {
		return new ComputerDAO(connection);
	}

	/**
	 * Returns new instance of CompanyDAO.
	 */
	public DAO<Company> getCompanyDAO() {
		return new CompanyDAO(connection);
	}

}
