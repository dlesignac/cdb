package fr.ebiz.cdb.test;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.dao.DAO;
import fr.ebiz.cdb.persistence.dao.DAOFactory;

public class Test {
	
	private static Logger logger = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		
	}
	
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void testCompanies() {
		DAO<Company> companyDAO = DAOFactory.getCompanyDAO();
		List<Company> companies = companyDAO.fetch();
		
		for (Company company : companies) {
			logger.info(company.getName());
		}
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void testComputers() {
		DAO<Computer> computerDAO = DAOFactory.getComputerDAO();
		List<Computer> computers = computerDAO.fetch();
		
		for (Computer computer : computers) {
			logger.info(computer.getName());
			
			LocalDate introduced = computer.getIntroduced();
			LocalDate discontinued = computer.getDiscontinued();
			
			logger.info(introduced == null ? "null" : introduced.toString());
			logger.info(discontinued == null ? "null" : discontinued.toString());
		}
	}

}
