package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.ebiz.cdb.persistence.exception.PersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

/**
 * Computer DAO.
 */
class DAOComputer extends DAO implements IDAOComputer {

    private Logger logger = LoggerFactory.getLogger(DAO.class);

    /**
     * Constructor.
     *
     * @param connection connection to use
     */
    DAOComputer(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Computer computer) {
        String query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, computer.getName());

            LocalDate introduced = computer.getIntroduced();
            LocalDate discontinued = computer.getDiscontinued();
            Company company = computer.getManufacturer();

            if (introduced == null) {
                statement.setNull(2, Types.DATE);
            } else {
                statement.setDate(2, Date.valueOf(introduced));
            }

            if (discontinued == null) {
                statement.setNull(3, Types.DATE);
            } else {
                statement.setDate(3, Date.valueOf(discontinued));
            }

            if (company == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, company.getId());
            }

            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            logger.error("could not insert computer", e);
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Computer computer) {
        String query = "DELETE FROM computer WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, computer.getId());

            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            logger.error("could not delete computer", e);
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Computer computer) {
        String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, computer.getName());

            LocalDate introduced = computer.getIntroduced();
            LocalDate discontinued = computer.getDiscontinued();
            Company company = computer.getManufacturer();

            if (introduced == null) {
                statement.setNull(2, Types.DATE);
            } else {
                statement.setDate(2, Date.valueOf(introduced));
            }

            if (discontinued == null) {
                statement.setNull(3, Types.DATE);
            } else {
                statement.setDate(3, Date.valueOf(discontinued));
            }

            if (company == null) {
                statement.setNull(4, Types.INTEGER);
            } else {
                statement.setInt(4, company.getId());
            }

            statement.setInt(5, computer.getId());

            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            logger.error("could not update computer", e);
            return false;
        }

        return true;
    }

    @Override
    public Computer find(int id) throws PersistenceException {
        String query = "SELECT c1.id as computer_id, c1.name AS computer_name, c1.introduced, c1.discontinued, "
                + "c2.id AS company_id, c2.name AS company_name FROM computer c1 LEFT OUTER JOIN company c2 "
                + "ON c1.company_id = c2.id WHERE c1.id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.first()) {
                Computer computer = new Computer();
                computer.setId(rs.getInt("computer_id"));
                computer.setName(rs.getString("computer_name"));

                Date introduced = rs.getDate("introduced");
                Date discontinued = rs.getDate("discontinued");

                computer.setIntroduced(introduced == null ? null : introduced.toLocalDate());
                computer.setDiscontinued(discontinued == null ? null : discontinued.toLocalDate());

                int companyId = rs.getInt("company_id");

                if (companyId != 0) {
                    Company company = new Company();
                    company.setId(companyId);
                    company.setName(rs.getString("company_name"));
                    computer.setManufacturer(company);
                }

                return computer;
            }
        } catch (SQLException e) {
            logger.error("could not find computer", e);
            throw new PersistenceException();
        }

        return null;
    }

    @Override
    public int count() throws PersistenceException {
        String query = "SELECT count(id) FROM computer";

        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            if (rs.first()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("could not count computers", e);
        }

        throw new PersistenceException();
    }

    @Override
    public List<Computer> fetch(int limit, int offset) throws PersistenceException {
        String query = "SELECT c1.id AS computer_id, c1.name AS computer_name, c1.introduced, c1.discontinued, "
                + "c2.id AS company_id, c2.name AS company_name FROM computer c1 LEFT OUTER JOIN company c2 "
                + "ON c1.company_id = c2.id LIMIT " + limit + " OFFSET " + offset * limit;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            List<Computer> computers = new ArrayList<>();

            while (rs.next()) {
                Computer computer = new Computer();
                computer.setId(rs.getInt("computer_id"));
                computer.setName(rs.getString("computer_name"));

                Date introduced = rs.getDate("introduced");
                Date discontinued = rs.getDate("discontinued");

                computer.setIntroduced(introduced == null ? null : introduced.toLocalDate());
                computer.setDiscontinued(discontinued == null ? null : discontinued.toLocalDate());

                int companyId = rs.getInt("company_id");

                if (companyId != 0) {
                    Company company = new Company();
                    company.setId(companyId);
                    company.setName(rs.getString("company_name"));
                    computer.setManufacturer(company);
                }

                computers.add(computer);
            }

            return computers;
        } catch (SQLException e) {
            logger.error("could not find computers", e);
        }

        throw new PersistenceException();
    }

}
