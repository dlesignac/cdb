package fr.ebiz.cdb.service;

import fr.ebiz.cdb.binding.ComputerDTO;
import fr.ebiz.cdb.binding.ComputerDeleteRequest;
import fr.ebiz.cdb.binding.PageRequest;
import fr.ebiz.cdb.core.Page;

public interface IComputerService {

    /**
     * Insert new computer into datasource.
     *
     * @param computerDTO to be inserted
     */
    void create(ComputerDTO computerDTO);

    /**
     * Delete computer into datasource.
     *
     * @param id to be deleted
     */
    void delete(int id);

    /**
     * Delete computers into datasource.
     *
     * @param computerDeletionDTO computerDeletionDTO
     */
    void deleteMany(ComputerDeleteRequest computerDeletionDTO);

    /**
     * Update computer into datasource.
     *
     * @param computerDTO to be updated
     */
    void update(ComputerDTO computerDTO);

    /**
     * Find computer by id.
     *
     * @param id computer's id
     * @return computer
     */
    ComputerDTO find(int id);

    /**
     * Get a frame of computers.
     *
     * @param pageRequest computerPageDTO
     * @return frame of computers
     */
    Page<ComputerDTO> page(PageRequest pageRequest);

}
