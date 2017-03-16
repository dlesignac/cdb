package fr.ebiz.cdb.service.datasource;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.dao.ICompanyDAO;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private ConnectionManager connectionManager;

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private IComputerDAO computerDAO;

    /**
     * Deletes company into datasource.
     *
     * @param company company to be deleted
     * @throws TransactionFailedException an unexpected error occurred
     */
    public void delete(Company company) throws TransactionFailedException {
        try {
            connectionManager.getConnection();

            try {
                computerDAO.delete(company);
                companyDAO.delete(company);
                connectionManager.commit();
                logger.debug("successfully updated computer " + company.getId());
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback();
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
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
            connectionManager.getConnection();

            try {
                return companyDAO.find(id);
            } catch (DAOQueryException e) {
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
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
            connectionManager.getConnection();

            try {
                return companyDAO.fetch();
            } catch (DAOQueryException e) {
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

}
