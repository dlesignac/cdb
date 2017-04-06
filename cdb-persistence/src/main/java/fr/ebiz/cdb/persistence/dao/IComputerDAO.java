package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.binding.ComputerPageRequest;
import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.core.Computer;

import java.util.List;

public interface IComputerDAO {

    /**
     * Persist computer into data source.
     *
     * @param computer computer to be persisted
     */
    void create(Computer computer);

    /**
     * Delete computer into data source.
     *
     * @param computer computer to be deleted
     */
    void delete(Computer computer);

    /**
     * Delete all computers for a given manufacturer.
     *
     * @param company manufacturer
     */
    void delete(Company company);

    /**
     * Update computer into data source.
     *
     * @param computer computer to be updated
     */
    void update(Computer computer);

    /**
     * Find computer by its id.
     *
     * @param id computer's id
     * @return computer
     */
    Computer find(int id);

    /**
     * Count computers.
     *
     * @param search search
     * @return computers' count
     */
    int count(String search);

    /**
     * Fetch computers for a given pagination.
     *
     * @param pageRequest page request
     * @return entries
     */
    List<Computer> fetch(ComputerPageRequest pageRequest);

}