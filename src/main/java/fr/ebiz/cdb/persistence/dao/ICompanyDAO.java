package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;

import java.util.List;

/**
 * CompanyDAO interface.
 */
public interface ICompanyDAO {

    /**
     * Deletes company.
     *
     * @param company to be deleted
     * @throws DAOQueryException an unexpected error occurred
     */
    void delete(Company company) throws DAOQueryException;

    /**
     * Finds company by its id.
     *
     * @param id company's id
     * @return company
     * @throws DAOQueryException an unexpected error occurred
     */
    Company find(int id) throws DAOQueryException;

    /**
     * Fetches companies.
     *
     * @return entries
     * @throws DAOQueryException an unexpected error occurred
     */
    List<Company> fetch() throws DAOQueryException;
}
