package fr.ebiz.cdb.service;

import fr.ebiz.cdb.binding.ComputerDeleteRequest;
import fr.ebiz.cdb.binding.ComputerPageRequest;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.core.Page;

public interface IComputerService {

    /**
     * Insert new computer into datasource.
     *
     * @param computer computer to be inserted
     */
    void create(Computer computer);

    /**
     * Delete computer into datasource.
     *
     * @param computer computer to be deleted
     */
    void delete(Computer computer);

    /**
     * Delete computers into datasource.
     *
     * @param computerDeletionDTO computerDeletionDTO
     */
    void deleteMany(ComputerDeleteRequest computerDeletionDTO);

    /**
     * Update computer into datasource.
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
     * Get a frame of computers.
     *
     * @param pageRequest computerPageDTO
     * @return frame of computers
     */
    Page<Computer> page(ComputerPageRequest pageRequest);

}
