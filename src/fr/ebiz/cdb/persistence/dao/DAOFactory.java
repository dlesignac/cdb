package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;

import fr.ebiz.cdb.persistence.DBConnection;
import fr.ebiz.cdb.persistence.dao.impl.ComputerDAO;
import fr.ebiz.cdb.persistence.dao.impl.CompanyDAO;

public class DAOFactory {

	private static final Connection connection = DBConnection.getInstance();

	public static DAO getComputerDAO() {
		return new ComputerDAO(DAOFactory.connection);
	}
	
	public static DAO getCompanyDAO() {
		return new CompanyDAO(DAOFactory.connection);
	}
}
