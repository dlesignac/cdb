package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;
import fr.ebiz.cdb.persistence.Page;

/**
 * Computer DAO. Interacts with a data source to persist and retrieve Computer
 * objects.
 */
class ComputerDAO extends DAO<Computer> {

    private Logger logger = LoggerFactory.getLogger(DAO.class);

    private static final String SQL_TABLE_COMPUTER = "computer";
    private static final String SQL_COLUMN_ID = "id";
    private static final String SQL_COLUMN_NAME = "name";
    private static final String SQL_COLUMN_INTRODUCED = "introduced";
    private static final String SQL_COLUMN_DISCONTINUED = "discontinued";
    private static final String SQL_COLUMN_COMPANY_ID = "company_id";

    /**
     * ComputerDAO should not be instantiated without parameters.
     * @param connection
     *            connection to use
     */
    ComputerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(Computer obj) {
        String query = "INSERT INTO " + SQL_TABLE_COMPUTER + "(" + SQL_COLUMN_NAME + ", " + SQL_COLUMN_INTRODUCED + ","
                + SQL_COLUMN_DISCONTINUED + "," + SQL_COLUMN_COMPANY_ID + ") VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, obj.getName());

            LocalDate introduced = obj.getIntroduced();
            LocalDate discontinued = obj.getDiscontinued();
            Company company = obj.getManufacturer();

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
            logger.error("could not insert Computer object", e);
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Computer obj) {
        String query = "DELETE FROM " + SQL_TABLE_COMPUTER + " WHERE " + SQL_COLUMN_ID + " = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, obj.getId());

            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            logger.error("could not delete Computer object", e);
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Computer obj) {
        String query = "UPDATE " + SQL_TABLE_COMPUTER + " SET " + SQL_COLUMN_NAME + " = ?, " + SQL_COLUMN_INTRODUCED
                + " = ?, " + SQL_COLUMN_DISCONTINUED + " = ?, " + SQL_COLUMN_COMPANY_ID + " = ?" + " WHERE "
                + SQL_COLUMN_ID + " = ?";

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, obj.getName());

            LocalDate introduced = obj.getIntroduced();
            LocalDate discontinued = obj.getDiscontinued();
            Company company = obj.getManufacturer();

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

            statement.setInt(5, obj.getId());

            statement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            logger.error("could not update Computer object", e);
            return false;
        }

        return true;
    }

    @Override
    public Computer find(int id) {
        Computer computer = null;
        String query = "SELECT c1.id as computer_id, c1.name as computer_name, c1.introduced, c1.discontinued, "
                + "c2.id as company_id, c2.name as company_name FROM computer c1 LEFT OUTER JOIN company c2 "
                + "ON c1.company_id = c2.id";

        try (PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            if (rs.first()) {
                computer = new Computer();
                computer.setId(rs.getInt("computer_id"));
                computer.setName(rs.getString("computer_name"));

                Date introduced = rs.getDate("introduced");
                Date discontinued = rs.getDate("discontinued");

                computer.setIntroduced(introduced == null ? null : introduced.toLocalDate());
                computer.setDiscontinued(discontinued == null ? null : discontinued.toLocalDate());

                Integer companyId = rs.getInt("company_id");

                if (companyId != null) {
                    Company company = new Company();
                    company.setId(companyId);
                    company.setName(rs.getString("company_name"));
                    computer.setManufacturer(company);
                }
            }
        } catch (SQLException e) {
            logger.error("could not find Computer object", e);
        }

        return computer;
    }

    @Override
    public Page<Computer> fetch(int limit, int offset) {
        String query = "SELECT c1.id as computer_id, c1.name as computer_name, c1.introduced, c1.discontinued, "
                + "c2.id as company_id, c2.name as company_name FROM computer c1 LEFT OUTER JOIN company c2 "
                + "ON c1.company_id = c2.id LIMIT " + limit + " OFFSET " + offset;
        List<Computer> computers = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Computer computer = new Computer();
                computer.setId(rs.getInt("computer_id"));
                computer.setName(rs.getString("computer_name"));

                Date introduced = rs.getDate("introduced");
                Date discontinued = rs.getDate("discontinued");

                computer.setIntroduced(introduced == null ? null : introduced.toLocalDate());
                computer.setDiscontinued(discontinued == null ? null : discontinued.toLocalDate());

                Integer companyId = rs.getInt("company_id");

                if (companyId != null) {
                    Company company = new Company();
                    company.setId(companyId);
                    company.setName(rs.getString("company_name"));
                    computer.setManufacturer(company);
                }

                computers.add(computer);
            }
        } catch (SQLException e) {
            logger.error("", e); // TODO
        }

        return new Page<Computer>(limit, offset, computers);
    }

    @Override
    public Page<Computer> fetchAll() {
        return fetch(10, 0);
    }

}
