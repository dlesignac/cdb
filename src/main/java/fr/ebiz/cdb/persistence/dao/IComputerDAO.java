package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;

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
     * @throws DAOQueryException an unexpected error occurred
     */
    void create(Connection connection, Computer computer) throws DAOQueryException;

    /**
     * Deletes computer into data source.
     *
     * @param connection connection to use
     * @param computer   computer to be deleted
     * @throws DAOQueryException an unexpected error occurred
     */
    void delete(Connection connection, Computer computer) throws DAOQueryException;

    /**
     * Deletes all computers for a given manufacturer.
     *
     * @param connection connection to use
     * @param company    manufacturer
     * @throws DAOQueryException an unexpected error occurred
     */
    void delete(Connection connection, Company company) throws DAOQueryException;

    /**
     * Updates computer into data source.
     *
     * @param connection connection to use
     * @param computer   computer to be updated
     * @throws DAOQueryException an unexpected error occurred
     */
    void update(Connection connection, Computer computer) throws DAOQueryException;

    /**
     * Finds computer by its id.
     *
     * @param connection connection to use
     * @param id         computer's id
     * @return computer
     * @throws DAOQueryException an unexpected error occurred
     */
    Computer find(Connection connection, int id) throws DAOQueryException;

    /**
     * Counts computers.
     *
     * @param connection connection to use
     * @param search     search
     * @return computers' count
     * @throws DAOQueryException an unexpected error occurred
     */
    int count(Connection connection, String search) throws DAOQueryException;

    /**
     * Fetches computers for a given pagination.
     *
     * @param connection connection to use
     * @param search     search
     * @param orderBy    orderBy
     * @param order      order
     * @param limit      max number of entries
     * @param offset     number of pages to skip
     * @return entries
     * @throws DAOQueryException an unexpected error occurred
     */
    List<Computer> fetch(Connection connection, String search, String orderBy, String order, int limit, int offset) throws DAOQueryException;

}