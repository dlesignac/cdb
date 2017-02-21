package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;

/**
 * DAO Factory.
 */
public class DAOFactory {

    private final Connection connection;

    /**
     * Constructor.
     *
     * @param connection connection to use
     */
    public DAOFactory(Connection connection) {
        this.connection = connection;
    }

    /**
     * Returns new instance of DAOComputer.
     *
     * @return computer dao
     */
    public IDAOComputer getComputerDAO() {
        return new DAOComputer(connection);
    }

    /**
     * Returns new instance of DAOCompany.
     *
     * @return company dao
     */
    public IDAOCompany getCompanyDAO() {
        return new DAOCompany(connection);
    }

}
