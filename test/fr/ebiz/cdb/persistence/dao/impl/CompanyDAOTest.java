package fr.ebiz.cdb.persistence.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.persistence.dao.DAO;
import fr.ebiz.cdb.persistence.dao.DAOFactory;

public class CompanyDAOTest {
	
	private static Logger logger = LoggerFactory.getLogger(CompanyDAOTest.class);

	public static void main(String[] args) {
		
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void testCreate() {
		DAO<Company> companyDAO = DAOFactory.getCompanyDAO();
		
		Company company = new Company();
		company.setName("MySuperCompany");
		
		companyDAO.create(company);
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void testDelete() {
		DAO<Company> companyDAO = DAOFactory.getCompanyDAO();
		
		Company company = new Company();
		company.setId(44);
		
		companyDAO.delete(company);
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void testUpdate() {
		DAO<Company> companyDAO = DAOFactory.getCompanyDAO();
		
		Company company = new Company();
		company.setId(45);
		company.setName("SuperCompanyName");
		
		companyDAO.update(company);
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void testFind() {
		DAO<Company> companyDAO = DAOFactory.getCompanyDAO();
		
		Company company = companyDAO.find(2);
		
		logger.info(company.getName());
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void testFetch() {
		DAO<Company> companyDAO = DAOFactory.getCompanyDAO();
		List<Company> companies = companyDAO.fetch();
		
		for (Company company : companies) {
			logger.info(company.getName());
		}
	}

}
