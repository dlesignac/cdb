package fr.ebiz.cdb.core.persistence.dao;

import fr.ebiz.cdb.core.dto.ComputerPageDTO;
import fr.ebiz.cdb.core.model.Company;
import fr.ebiz.cdb.core.model.Computer;

import java.util.List;

/**
 * ComputerDAO interface.
 */
public interface IComputerDAO {

    /**
     * Persists computer into data source.
     *
     * @param computer computer to be persisted
     */
    void create(Computer computer);

    /**
     * Deletes computer into data source.
     *
     * @param computer computer to be deleted
     */
    void delete(Computer computer);

    /**
     * Deletes all computers for a given manufacturer.
     *
     * @param company manufacturer
     */
    void delete(Company company);

    /**
     * Updates computer into data source.
     *
     * @param computer computer to be updated
     */
    void update(Computer computer);

    /**
     * Finds computer by its id.
     *
     * @param id computer's id
     * @return computer
     */
    Computer find(int id);

    /**
     * Counts computers.
     *
     * @param search search
     * @return computers' count
     */
    int count(String search);

    /**
     * Fetches computers for a given pagination.
     *
     * @param pageRequest page request
     * @return entries
     */
    List<Computer> fetch(ComputerPageDTO pageRequest);

}