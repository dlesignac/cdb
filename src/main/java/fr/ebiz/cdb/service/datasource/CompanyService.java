package fr.ebiz.cdb.service.datasource;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.dao.CompanyDAO;
import fr.ebiz.cdb.persistence.dao.ComputerDAO;
import fr.ebiz.cdb.persistence.dao.ICompanyDAO;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.persistence.exception.QueryException;

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
     * @throws DatasourceException an unexpected error occurred
     * @throws QueryException      an unexpected error occurred
     */
    public void delete(Company company) throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();
        computerDAO.delete(connection, company);
        companyDAO.delete(connection, company);
        connectionManager.commitTransaction(connection);
        connectionManager.closeConnection(connection);
    }

    /**
     * Find company by id.
     *
     * @param id company's id
     * @return company
     * @throws QueryException      an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    public Company find(int id) throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();
        Company company = companyDAO.find(connection, id);
        connectionManager.closeConnection(connection);
        return company;
    }

    /**
     * Fetches companies.
     *
     * @return companies
     * @throws QueryException      an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    public List<Company> list() throws DatasourceException, QueryException {
        Connection connection = connectionManager.getConnection();
        List<Company> companies = companyDAO.fetch(connection);
        connectionManager.closeConnection(connection);
        return companies;
    }

}
