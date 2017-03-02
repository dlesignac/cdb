package fr.ebiz.cdb.service.datasource;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.dao.ComputerDAO;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;

import java.sql.Connection;
import java.util.List;

/**
 * Computer service.
 * Gives connection to ComputerDAO and handles transactions.
 */
public enum ComputerService {

    INSTANCE;

    private ConnectionManager connectionManager;
    private IComputerDAO computerDAO;

    /**
     * Constructor.
     */
    ComputerService() {
        this.connectionManager = ConnectionManager.INSTANCE;
        this.computerDAO = ComputerDAO.INSTANCE;
    }

    /**
     * Inserts new computer into datasource.
     *
     * @param computer computer to be inserted
     * @throws TransactionFailedException an unexpected error occurred
     */
    public void create(Computer computer) throws TransactionFailedException {
        try {
            Connection connection = connectionManager.getConnection();

            try {
                computerDAO.create(connection, computer);
                connectionManager.commit(connection);
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback(connection);
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

    /**
     * Deletes computer into datasource.
     *
     * @param computer computer to be deleted
     * @throws TransactionFailedException an unexpected error occurred
     */
    public void delete(Computer computer) throws TransactionFailedException {
        try {
            Connection connection = connectionManager.getConnection();

            try {
                computerDAO.delete(connection, computer);
                connectionManager.commit(connection);
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback(connection);
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

    /**
     * Deletes computers into datasource.
     *
     * @param computerDeletionDTO computerDeletionDTO
     * @throws TransactionFailedException an unexpected error occurred
     */
    public void deleteMany(ComputerDeletionDTO computerDeletionDTO) throws TransactionFailedException {
        try {
            Connection connection = connectionManager.getConnection();

            try {
                for (Integer id : computerDeletionDTO.getIds()) {
                    Computer computer = new Computer();
                    computer.setId(id);
                    computerDAO.delete(connection, computer);
                }

                connectionManager.commit(connection);
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback(connection);
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

    /**
     * Updates computer into datasource.
     *
     * @param computer computer to be updated
     * @throws TransactionFailedException an unexpected error occurred
     */
    public void update(Computer computer) throws TransactionFailedException {
        try {
            Connection connection = connectionManager.getConnection();

            try {
                computerDAO.update(connection, computer);
                connectionManager.commit(connection);
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback(connection);
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

    /**
     * Find computer by id.
     *
     * @param id computer's id
     * @return computer
     * @throws TransactionFailedException an unexpected error occurred
     */
    public Computer find(int id) throws TransactionFailedException {
        try {
            Connection connection = connectionManager.getConnection();

            try {
                return computerDAO.find(connection, id);
            } catch (DAOQueryException e) {
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

    /**
     * Gets a frame of computers.
     *
     * @param computerPageDTO computerPageDTO
     * @return frame of computers
     * @throws TransactionFailedException an unexpected error occurred
     */
    public Page<Computer> page(ComputerPageDTO computerPageDTO) throws TransactionFailedException {
        int limit = computerPageDTO.getLimit();
        int number = computerPageDTO.getNumber();
        String search = computerPageDTO.getSearch();
        String orderBy = computerPageDTO.getOrderBy();
        String order = computerPageDTO.getOrder();

        try {
            Connection connection = connectionManager.getConnection();

            try {
                int computersCount = computerDAO.count(connection, search);
                int pageCount = (computersCount + limit - 1) / limit;
                List<Computer> computers = computerDAO.fetch(connection, search, orderBy, order, limit, (number - 1) * limit);
                return new Page<>(number, pageCount, limit, computersCount, search, orderBy, computers);
            } catch (DAOQueryException e) {
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close(connection);
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

}
