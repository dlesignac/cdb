package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.persistence.exception.QueryException;

import java.sql.Connection;
import java.util.List;

/**
 * CompanyDAO interface.
 */
public interface ICompanyDAO {

    /**
     * Finds company by its id.
     *
     * @param connection connection to use
     * @param id         company's id
     * @return company
     * @throws QueryException an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    Company find(Connection connection, int id) throws QueryException, DatasourceException;

    /**
     * Counts companies.
     *
     * @param connection connection to use
     * @return companies' count
     * @throws QueryException an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    int count(Connection connection) throws QueryException, DatasourceException;

    /**
     * Fetches companies.
     *
     * @param connection connection to use
     * @return entries
     * @throws QueryException an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    List<Company> fetch(Connection connection) throws QueryException, DatasourceException;
}
