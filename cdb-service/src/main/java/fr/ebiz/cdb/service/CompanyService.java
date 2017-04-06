package fr.ebiz.cdb.service;

import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.persistence.dao.ICompanyDAO;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyService implements ICompanyService {

    private final PlatformTransactionManager platformTransactionManager;
    private final ICompanyDAO companyDAO;
    private final IComputerDAO computerDAO;

    /**
     * @param platformTransactionManager platformTransactionManager
     * @param companyDAO companyDAO
     * @param computerDAO computerDAO
     */
    @Autowired
    public CompanyService(PlatformTransactionManager platformTransactionManager, ICompanyDAO companyDAO, IComputerDAO computerDAO) {
        this.platformTransactionManager = platformTransactionManager;
        this.companyDAO = companyDAO;
        this.computerDAO = computerDAO;
    }

    @Override
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