package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;

import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.dao.impl.ComputerDAO;
import fr.ebiz.cdb.persistence.dao.impl.CompanyDAO;

/**
 * DAO Factory.
 * Abstracts DAO objects instantiation.
 */
public class DAOFactory {

	private static final Connection connection = DBConnection.getInstance();

	/**
	 * Returns new instance of ComputerDAO.
	 */
	public static DAO getComputerDAO() {
		return new ComputerDAO(DAOFactory.connection);
	}
	
	/**
	 * Returns new instance of CompanyDAO.
	 */
	public static DAO getCompanyDAO() {
		return new CompanyDAO(DAOFactory.connection);
	}
}
