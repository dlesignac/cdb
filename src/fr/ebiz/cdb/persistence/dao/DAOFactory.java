package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;

import fr.ebiz.cdb.persistence.dao.impl.ComputerDAO;
import fr.ebiz.cdb.persistence.dao.impl.CompanyDAO;

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
	public DAO getComputerDAO() {
		return new ComputerDAO(connection);
	}

	/**
	 * Returns new instance of CompanyDAO.
	 */
	public DAO getCompanyDAO() {
		return new CompanyDAO(connection);
	}

}
