package fr.ebiz.cdb.config;

import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.dao.CompanyDAO;
import fr.ebiz.cdb.persistence.dao.ComputerDAO;
import fr.ebiz.cdb.persistence.dao.ICompanyDAO;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.service.datasource.CompanyService;
import fr.ebiz.cdb.service.datasource.ComputerService;
import fr.ebiz.cdb.ui.cli.CLI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * ConnectionManager.
     *
     * @return ConnectionManager
     */
    @Bean
    public ConnectionManager connectionManager() {
        return new ConnectionManager();
    }

    /**
     * IComputerDAO.
     *
     * @return IComputerDAO
     */
    @Bean
    public IComputerDAO computerDAO() {
        return new ComputerDAO();
    }

    /**
     * ICompanyDAO.
     *
     * @return ICompanyDAO
     */
    @Bean
    public ICompanyDAO companyDAO() {
        return new CompanyDAO();
    }

    /**
     * ComputerService.
     *
     * @return ComputerService
     */
    @Bean
    public ComputerService computerService() {
        return new ComputerService();
    }

    /**
     * CompanyService.
     *
     * @return CompanyService
     */
    @Bean
    public CompanyService companyService() {
        return new CompanyService();
    }

    /**
     * CLI.
     *
     * @return CLI
     */
    @Bean
    public CLI cli() {
        return new CLI();
    }

}
