package fr.ebiz.cdb.service;

import fr.ebiz.cdb.binding.ComputerDTO;
import fr.ebiz.cdb.binding.ComputerDTOMapper;
import fr.ebiz.cdb.binding.ComputerDeleteRequest;
import fr.ebiz.cdb.binding.PageRequest;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.core.Page;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class ComputerService implements IComputerService {

    @Autowired
    private IComputerDAO computerDAO;

    @Override
    public int create(ComputerDTO computerDTO) {
        Computer computer = ComputerDTOMapper.mapFromDTO(computerDTO);
        return computerDAO.create(computer);
    }

    @Override
    public int delete(int id) {
        return computerDAO.delete(id);
    }

    @Override
    public int deleteMany(ComputerDeleteRequest computerDeletionDTO) {
        int i = 0;

        for (String id : computerDeletionDTO.getSelection()) {
            i += computerDAO.delete(Integer.parseInt(id));
        }

        return i;
    }

    @Override
    public void update(ComputerDTO computerDTO) {
        Computer computer = ComputerDTOMapper.mapFromDTO(computerDTO);
        computerDAO.update(computer);
    }

    @Override
    public ComputerDTO find(int id) {
        return ComputerDTOMapper.mapToDTO(computerDAO.find(id));
    }

    @Override
    public Page<ComputerDTO> page(PageRequest pageRequest) {
        int computersCount = computerDAO.count(pageRequest.getFilter());
        int pageCount = (computersCount + pageRequest.getLimit() - 1) / pageRequest.getLimit();
        List<Computer> computers = computerDAO.fetch(pageRequest);

        Page<ComputerDTO> page = new Page<>();
        page.setNumber(pageRequest.getPage());
        page.setLast(pageCount);
        page.setCount(computersCount);
        page.setLimit(pageRequest.getLimit());
        page.setFilter(pageRequest.getFilter());
        page.setSort(pageRequest.getSort().toString());
        page.setOrder(pageRequest.getOrder().toString());
        page.setEntries(computers.stream().map(ComputerDTOMapper::mapToDTO).collect(Collectors.toList()));

        return page;
    }

}