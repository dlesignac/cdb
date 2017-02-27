package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.persistence.exception.QueryException;

import java.sql.Connection;
import java.util.List;

/**
 * ComputerDAO interface.
 */
public interface IComputerDAO {

    /**
     * Persists computer into data source.
     *
     * @param connection connection to use
     * @param computer   computer to be persisted
     * @return true if success, else false
     * @throws DatasourceException an unexpected error occurred
     */
    boolean create(Connection connection, Computer computer) throws DatasourceException;

    /**
     * Deletes computer into data source.
     *
     * @param connection connection to use
     * @param computer   computer to be deleted
     * @return true if success, else false
     * @throws DatasourceException an unexpected error occurred
     */
    boolean delete(Connection connection, Computer computer) throws DatasourceException;

    /**
     * Updates computer into data source.
     *
     * @param connection connection to use
     * @param computer   computer to be updated
     * @return true if success, else false
     * @throws DatasourceException an unexpected error occurred
     */
    boolean update(Connection connection, Computer computer) throws DatasourceException;

    /**
     * Finds computer by its id.
     *
     * @param connection connection to use
     * @param id         computer's id
     * @return computer
     * @throws QueryException      an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    Computer find(Connection connection, int id) throws QueryException, DatasourceException;

    /**
     * Counts computers.
     *
     * @param connection connection to use
     * @return computers' count
     * @throws QueryException      an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    int count(Connection connection) throws QueryException, DatasourceException;

    /**
     * Fetches computers for a given pagination.
     *
     * @param connection connection to use
     * @param limit      max number of entries
     * @param offset     number of pages to skip
     * @return entries
     * @throws QueryException      an unexpected error occurred
     * @throws DatasourceException an unexpected error occurred
     */
    List<Computer> fetch(Connection connection, int limit, int offset) throws QueryException, DatasourceException;

}
