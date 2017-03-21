package fr.ebiz.cdb.core.service;

import fr.ebiz.cdb.core.dto.ComputerDeletionDTO;
import fr.ebiz.cdb.core.dto.ComputerPageDTO;
import fr.ebiz.cdb.core.model.Computer;
import fr.ebiz.cdb.core.model.Page;
import fr.ebiz.cdb.core.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.core.service.exception.TransactionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComputerService implements IComputerService {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private IComputerDAO computerDAO;

    @Override
    public void create(Computer computer) throws TransactionFailedException {
        computerDAO.create(computer);
    }

    @Override
    public void delete(Computer computer) throws TransactionFailedException {
        computerDAO.delete(computer);
    }

    @Override
    @Transactional(rollbackFor = java.lang.Exception.class)
    public void deleteMany(ComputerDeletionDTO computerDeletionDTO) throws TransactionFailedException {
        for (String id : computerDeletionDTO.getIds()) {
            Computer computer = new Computer();
            computer.setId(Integer.parseInt(id));
            computerDAO.delete(computer);
        }
    }

    @Override
    public void update(Computer computer) throws TransactionFailedException {
        computerDAO.update(computer);
    }

    @Override
    public Computer find(int id) throws TransactionFailedException {
        return computerDAO.find(id);
    }

    @Override
    @Transactional(rollbackFor = java.lang.Exception.class)
    public Page<Computer> page(ComputerPageDTO pageRequest) throws TransactionFailedException {
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

        return page;
    }

}