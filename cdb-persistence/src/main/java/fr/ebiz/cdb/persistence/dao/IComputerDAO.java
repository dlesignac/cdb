package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.binding.PageRequest;
import fr.ebiz.cdb.core.Computer;

import java.util.List;

public interface IComputerDAO {

    /**
     * Persist computer into data source.
     *
     * @param computer computer to be persisted
     * @return status
     */
    int create(Computer computer);

    /**
     * Delete computer into data source.
     *
     * @param id to be deleted
     * @return status
     */
    int delete(int id);

    /**
     * Delete all computers for a given manufacturer.
     *
     * @param id manufacturer
     * @return status
     */
    int deleteByCompany(int id);

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
    List<Computer> fetch(PageRequest pageRequest);

}