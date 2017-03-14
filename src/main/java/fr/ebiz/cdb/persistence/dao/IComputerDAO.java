package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;

import java.util.List;

/**
 * ComputerDAO interface.
 */
public interface IComputerDAO {

    /**
     * Persists computer into data source.
     *
     * @param computer computer to be persisted
     * @throws DAOQueryException an unexpected error occurred
     */
    void create(Computer computer) throws DAOQueryException;

    /**
     * Deletes computer into data source.
     *
     * @param computer computer to be deleted
     * @throws DAOQueryException an unexpected error occurred
     */
    void delete(Computer computer) throws DAOQueryException;

    /**
     * Deletes all computers for a given manufacturer.
     *
     * @param company manufacturer
     * @throws DAOQueryException an unexpected error occurred
     */
    void delete(Company company) throws DAOQueryException;

    /**
     * Updates computer into data source.
     *
     * @param computer computer to be updated
     * @throws DAOQueryException an unexpected error occurred
     */
    void update(Computer computer) throws DAOQueryException;

    /**
     * Finds computer by its id.
     *
     * @param id computer's id
     * @return computer
     * @throws DAOQueryException an unexpected error occurred
     */
    Computer find(int id) throws DAOQueryException;

    /**
     * Counts computers.
     *
     * @param search search
     * @return computers' count
     * @throws DAOQueryException an unexpected error occurred
     */
    int count(String search) throws DAOQueryException;

    /**
     * Fetches computers for a given pagination.
     *
     * @param pageRequest page request
     * @return entries
     * @throws DAOQueryException an unexpected error occurred
     */
    List<Computer> fetch(ComputerPageDTO pageRequest) throws DAOQueryException;

}
