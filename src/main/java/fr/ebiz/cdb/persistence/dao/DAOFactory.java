package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

/**
 * DAO Factory. Abstracts DAO objects instantiation.
 */
public class DAOFactory {

    private final Connection connection;

    /**
     * Constructor.
     * @param connection
     *            connection to use
     */
    public DAOFactory(Connection connection) {
        this.connection = connection;
    }

    /**
     * Returns new instance of ComputerDAO.
     * @return computer dao
     */
    public DAO<Computer> getComputerDAO() {
        return new ComputerDAO(connection);
    }

    /**
     * Returns new instance of CompanyDAO.
     * @return company dao
     */
    public DAO<Company> getCompanyDAO() {
        return new CompanyDAO(connection);
    }

}
