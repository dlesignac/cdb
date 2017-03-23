package fr.ebiz.cdb.core.service;

import fr.ebiz.cdb.core.model.Company;
import fr.ebiz.cdb.core.persistence.dao.ICompanyDAO;
import fr.ebiz.cdb.core.persistence.dao.IComputerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private ICompanyDAO companyDAO;

    @Autowired
    private IComputerDAO computerDAO;

    @Override
    @Transactional(rollbackFor = java.lang.Exception.class)
    public void delete(Company company) {
        computerDAO.delete(company);
        companyDAO.delete(company);
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