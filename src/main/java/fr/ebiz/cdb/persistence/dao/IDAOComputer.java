package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.exception.PersistenceException;

import java.util.List;

/**
 * DAOComputer interface.
 */
public interface IDAOComputer {

    /**
     * Persists computer into data source.
     *
     * @param computer computer to be persisted
     * @return true if success, else false
     */
    boolean create(Computer computer);

    /**
     * Deletes computer into data source.
     *
     * @param computer computer to be deleted
     * @return true if success, else false
     */
    boolean delete(Computer computer);

    /**
     * Updates computer into data source.
     *
     * @param computer computer to be updated
     * @return true if success, else false
     */
    boolean update(Computer computer);

    /**
     * Finds computer by its id.
     *
     * @param id computer's id
     * @return computer
     * @throws PersistenceException an unexpected error occurred
     */
    Computer find(int id) throws PersistenceException;

    /**
     * Counts computers.
     *
     * @return computers' count
     * @throws PersistenceException an unexpected error occurred
     */
    int count() throws PersistenceException;

    /**
     * Fetches computers for a given pagination.
     *
     * @param limit  max number of entries
     * @param offset number of pages to skip
     * @return entries
     * @throws PersistenceException an unexpected error occurred
     */
    List<Computer> fetch(int limit, int offset) throws PersistenceException;

}
