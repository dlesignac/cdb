package fr.ebiz.cdb.service.datasource;

import fr.ebiz.cdb.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.model.Page;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;
import fr.ebiz.cdb.persistence.exception.DatasourceException;
import fr.ebiz.cdb.service.datasource.exception.TransactionFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {

    private final Logger logger = LoggerFactory.getLogger(ComputerService.class);

    @Autowired
    private ConnectionManager connectionManager;

    @Autowired
    private IComputerDAO computerDAO;

    /**
     * Inserts new computer into datasource.
     *
     * @param computer computer to be inserted
     * @throws TransactionFailedException an unexpected error occurred
     */
    public void create(Computer computer) throws TransactionFailedException {
        try {
            connectionManager.getConnection();

            try {
                computerDAO.create(computer);
                connectionManager.commit();
                logger.debug("successfully created computer " + computer.toString());
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback();
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
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
            connectionManager.getConnection();

            try {
                computerDAO.delete(computer);
                connectionManager.commit();
                logger.debug("successfully deleted computer " + computer.getId());
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback();
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
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
            connectionManager.getConnection();

            try {
                for (Integer id : computerDeletionDTO.getIds()) {
                    Computer computer = new Computer();
                    computer.setId(id);
                    computerDAO.delete(computer);
                }

                connectionManager.commit();
                logger.debug("successfully deleted computers " + computerDeletionDTO.getIds());
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback();
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
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
            connectionManager.getConnection();

            try {
                computerDAO.update(computer);
                connectionManager.commit();
                logger.debug("successfully updated computer " + computer.getId());
            } catch (DatasourceException | DAOQueryException e) {
                connectionManager.rollback();
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
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
            connectionManager.getConnection();

            try {
                Computer computer = computerDAO.find(id);
                logger.debug("found computer " + computer.toString());
                return computer;
            } catch (DAOQueryException e) {
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

    /**
     * Gets a frame of computers.
     *
     * @param pageRequest computerPageDTO
     * @return frame of computers
     * @throws TransactionFailedException an unexpected error occurred
     */
    public Page<Computer> page(ComputerPageDTO pageRequest) throws TransactionFailedException {
        try {
            connectionManager.getConnection();

            try {
                int computersCount = computerDAO.count(pageRequest.getFilter());
                int pageCount = (computersCount + pageRequest.getLimit() - 1) / pageRequest.getLimit();
                List<Computer> computers = computerDAO.fetch(pageRequest);

                Page<Computer> page = new Page<>();
                page.setNumber(pageRequest.getNumber());
                page.setLast(pageCount);
                page.setCount(computersCount);
                page.setLimit(pageRequest.getLimit());
                page.setFilter(pageRequest.getFilter());
                page.setSort(pageRequest.getSort().getName());
                page.setOrder(pageRequest.getOrder().getName());
                page.setEntries(computers);

                logger.debug("successfully created page from dto " + pageRequest.toString());
                return page;
            } catch (DAOQueryException e) {
                throw new TransactionFailedException(TransactionFailedException.FAILURE_QUERYING, e);
            } finally {
                connectionManager.close();
            }
        } catch (DatasourceException e) {
            throw new TransactionFailedException(TransactionFailedException.FAILURE_OPENING, e);
        }
    }

}
