package fr.ebiz.cdb.persistence.dao.impl;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.dao.DAO;
import fr.ebiz.cdb.persistence.dao.DAOFactory;

public class ComputerDAOTest {
	
	private static Logger logger = LoggerFactory.getLogger(ComputerDAOTest.class);
	
	public static void main(String[] args) {
		
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private static void testCreate() {
		DAO<Computer> computerDAO = DAOFactory.getComputerDAO();
		
		Computer computer = new Computer();
		computer.setName("MySuperComputer");
		
		computerDAO.create(computer);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private static void testFetch() {
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
