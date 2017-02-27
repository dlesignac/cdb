package fr.ebiz.cdb.service.datasource;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.dao.CompanyDAO;
import fr.ebiz.cdb.persistence.dao.ICompanyDAO;
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

    /**
     * Constructor.
     */
    CompanyService() {
        this.connectionManager = ConnectionManager.INSTANCE;
        this.companyDAO = CompanyDAO.INSTANCE;
    }

    /**
     * Find company by id.
     *
     * @param id company's id
     * @return company
     * @throws QueryException      an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    public Company findCompany(int id) throws QueryException, DatasourceException {
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
    public List<Company> listCompanies() throws QueryException, DatasourceException {
        Connection connection = connectionManager.getConnection();
        List<Company> companies = companyDAO.fetch(connection);
        connectionManager.closeConnection(connection);
        return companies;
    }

}
