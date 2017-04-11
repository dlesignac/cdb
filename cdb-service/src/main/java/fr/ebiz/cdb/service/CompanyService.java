package fr.ebiz.cdb.service;

import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.persistence.dao.ICompanyDAO;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyService implements ICompanyService {

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private IComputerDAO computerDAO;

    @Override
    public int delete(int id) {
        int r = computerDAO.deleteByCompany(id);
        companyDAO.delete(id);
        return r;
    }

    @Override
    public Company find(int id) {
        return companyDAO.find(id);
    }

    @Override
    public List<Company> list() {
        return companyDAO.fetch();
    }

}