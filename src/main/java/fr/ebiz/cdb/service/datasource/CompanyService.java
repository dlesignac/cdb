package fr.ebiz.cdb.service.datasource;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.dao.CompanyDAO;
import fr.ebiz.cdb.persistence.dao.ComputerDAO;
import fr.ebiz.cdb.persistence.dao.ICompanyDAO;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;

import java.sql.Connection;
import java.util.List;

/**
 * Company service.
 */
public enum CompanyService {

    INSTANCE;

    private ConnectionManager connectionManager;
    private ICompanyDAO companyDAO;
    private IComputerDAO computerDAO;

    /**
     * Constructor.
     */
    CompanyService() {
        this.connectionManager = ConnectionManager.INSTANCE;
        this.companyDAO = CompanyDAO.INSTANCE;
        this.computerDAO = ComputerDAO.INSTANCE;
    }

    /**
     * Deletes company into datasource.
     *
     * @param company company to be deleted
     * @throws TransactionFailedException an unexpected error occurred
     */
    public void delete(Company company) throws TransactionFailedException {
        try {
            Connection connection = connectionManager.getConnection();

            try {
                computerDAO.delete(connection, company);
                companyDAO.delete(connection, company);
                connectionManager.commit(connection);
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback(connection);
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

    /**
     * Find company by id.
     *
     * @param id company's id
     * @return company
     * @throws TransactionFailedException an unexpected error occurred
     */
    public Company find(int id) throws TransactionFailedException {
        try {
            Connection connection = connectionManager.getConnection();

            try {
                return companyDAO.find(connection, id);
            } catch (DAOQueryException e) {
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

    /**
     * Fetches companies.
     *
     * @return companies
     * @throws TransactionFailedException an unexpected error occurred
     */
    public List<Company> list() throws TransactionFailedException {
        try {
            Connection connection = connectionManager.getConnection();

            try {
                return companyDAO.fetch(connection);
            } catch (DAOQueryException e) {
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

}
