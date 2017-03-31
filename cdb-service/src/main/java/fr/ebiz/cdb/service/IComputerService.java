package fr.ebiz.cdb.service;

import fr.ebiz.cdb.binding.ComputerDeleteRequest;
import fr.ebiz.cdb.binding.ComputerPageRequest;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.core.Page;

public interface IComputerService {

    /**
     * Inserts new computer into datasource.
     *
     * @param computer computer to be inserted
     */
    void create(Computer computer);

    /**
     * Deletes computer into datasource.
     *
     * @param computer computer to be deleted
     */
    void delete(Computer computer);

    /**
     * Deletes computers into datasource.
     *
     * @param computerDeletionDTO computerDeletionDTO
     */
    void deleteMany(ComputerDeleteRequest computerDeletionDTO);

    /**
     * Updates computer into datasource.
     *
     * @param computer computer to be updated
     */
    void update(Computer computer);

    /**
     * Find computer by id.
     *
     * @param id computer's id
     * @return computer
     */
    Computer find(int id);

    /**
     * Gets a frame of computers.
     *
     * @param pageRequest computerPageDTO
     * @return frame of computers
     */
    Page<Computer> page(ComputerPageRequest pageRequest);

}
