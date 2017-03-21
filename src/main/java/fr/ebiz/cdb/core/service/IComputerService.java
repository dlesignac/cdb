package fr.ebiz.cdb.core.service;

import fr.ebiz.cdb.core.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.core.dto.ComputerPageDTO;
import fr.ebiz.cdb.core.model.Computer;
import fr.ebiz.cdb.core.model.Page;
import fr.ebiz.cdb.core.service.exception.TransactionFailedException;

public interface IComputerService {

    /**
     * Inserts new computer into datasource.
     *
     * @param computer computer to be inserted
     * @throws TransactionFailedException an unexpected error occurred
     */
    void create(Computer computer) throws TransactionFailedException;

    /**
     * Deletes computer into datasource.
     *
     * @param computer computer to be deleted
     * @throws TransactionFailedException an unexpected error occurred
     */
    void delete(Computer computer) throws TransactionFailedException;

    /**
     * Deletes computers into datasource.
     *
     * @param computerDeletionDTO computerDeletionDTO
     * @throws TransactionFailedException an unexpected error occurred
     */
    void deleteMany(ComputerDeletionDTO computerDeletionDTO) throws TransactionFailedException;

    /**
     * Updates computer into datasource.
     *
     * @param computer computer to be updated
     * @throws TransactionFailedException an unexpected error occurred
     */
    void update(Computer computer) throws TransactionFailedException;

    /**
     * Find computer by id.
     *
     * @param id computer's id
     * @return computer
     * @throws TransactionFailedException an unexpected error occurred
     */
    Computer find(int id) throws TransactionFailedException;

    /**
     * Gets a frame of computers.
     *
     * @param pageRequest computerPageDTO
     * @return frame of computers
     * @throws TransactionFailedException an unexpected error occurred
     */
    Page<Computer> page(ComputerPageDTO pageRequest) throws TransactionFailedException;

}
