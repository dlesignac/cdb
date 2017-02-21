package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.exception.PersistenceException;

import java.util.List;

/**
 * DAOCompany interface.
 */
public interface IDAOCompany {

    /**
     * Finds company by its id.
     *
     * @param id company's id
     * @return company
     * @throws PersistenceException an unexpected error occurred
     */
    Company find(int id) throws PersistenceException;

    /**
     * Counts companies.
     *
     * @return companies' count
     * @throws PersistenceException an unexpected error occurred
     */
    int count() throws PersistenceException;

    /**
     * Fetches companies.
     *
     * @return entries
     * @throws PersistenceException an unexpected error occurred
     */
    List<Company> fetch() throws PersistenceException;
}
