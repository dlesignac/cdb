package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Computer;
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
     * @throws QueryException an unexpected error occurred
     */
    void create(Connection connection, Computer computer) throws QueryException;

    /**
     * Deletes computer into data source.
     *
     * @param connection connection to use
     * @param computer   computer to be deleted
     * @throws QueryException an unexpected error occurred
     */
    void delete(Connection connection, Computer computer) throws QueryException;

    /**
     * Updates computer into data source.
     *
     * @param connection connection to use
     * @param computer   computer to be updated
     * @throws QueryException an unexpected error occurred
     */
    void update(Connection connection, Computer computer) throws QueryException;

    /**
     * Finds computer by its id.
     *
     * @param connection connection to use
     * @param id         computer's id
     * @return computer
     * @throws QueryException an unexpected error occurred
     */
    Computer find(Connection connection, int id) throws QueryException;

    /**
     * Counts computers.
     *
     * @param connection connection to use
     * @return computers' count
     * @throws QueryException an unexpected error occurred
     */
    int count(Connection connection) throws QueryException;

    /**
     * Fetches computers for a given pagination.
     *
     * @param connection connection to use
     * @param limit      max number of entries
     * @param offset     number of pages to skip
     * @return entries
     * @throws QueryException an unexpected error occurred
     */
    List<Computer> fetch(Connection connection, int limit, int offset) throws QueryException;

}
