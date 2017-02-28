package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

import fr.ebiz.cdb.persistence.QueryBuilder;
import fr.ebiz.cdb.persistence.exception.QueryException;
import fr.ebiz.cdb.persistence.mapper.ComputerRSMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Computer;

/**
 * Computer DAO.
 */
public enum ComputerDAO implements IComputerDAO {

    INSTANCE;

    private Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

    @Override
    public void create(Connection connection, Computer computer) throws QueryException {
        String query = new QueryBuilder()
                .insertInto("computer(name, introduced, discontinued, company_id)")
                .values("(?, ?, ?, ?)")
                .build();

        logger.debug(query);

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareInsert(statement, computer);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not insert computer", e);
            throw new QueryException();
        }
    }

    @Override
    public void delete(Connection connection, Computer computer) throws QueryException {
        String query = new QueryBuilder()
                .deleteFrom("computer")
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, computer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not delete computer", e);
            throw new QueryException();
        }
    }

    @Override
    public void update(Connection connection, Computer computer) throws QueryException {
        String query = new QueryBuilder()
                .update("computer")
                .set("name = ?, introduced = ?, discontinued = ?, company_id = ?")
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            prepareInsert(statement, computer);
            statement.setInt(5, computer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("could not update computer", e);
            throw new QueryException();
        }
    }

    @Override
    public Computer find(Connection connection, int id) throws QueryException {
        String query = new QueryBuilder()
                .select("c1.id AS computer_id, " +
                        "c1.name AS computer_name, " +
                        "c1.introduced, " +
                        "c1.discontinued, " +
                        "c2.id AS company_id, " +
                        "c2.name AS company_name")
                .from("computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id")
                .where("c1.id = ?")
                .build();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            return new ComputerRSMapper().mapToOne(rs);
        } catch (SQLException e) {
            logger.error("could not find computer", e);
            throw new QueryException();
        }
    }

    @Override
    public int count(Connection connection) throws QueryException {
        String query = new QueryBuilder()
                .select("count(id)")
                .from("computer")
                .build();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();

            if (rs.first()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("could not count computers", e);
        }

        throw new QueryException();
    }

    @Override
    public List<Computer> fetch(Connection connection, int limit, int offset) throws QueryException {
        String query = new QueryBuilder()
                .select("c1.id AS computer_id, " +
                        "c1.name AS computer_name, " +
                        "c1.introduced, " +
                        "c1.discontinued, " +
                        "c2.id AS company_id, " +
                        "c2.name AS company_name")
                .from("computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id")
                .limit(limit)
                .offset(offset * limit)
                .build();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet rs = statement.executeQuery();
            return new ComputerRSMapper().mapToMany(rs);
        } catch (SQLException e) {
            logger.error("could not find computers", e);
            throw new QueryException();
        }
    }

    /**
     * Prepares create or update statement.
     *
     * @param statement statement to be prepared
     * @param computer  computer to prepare
     * @return PreparedStatement
     * @throws SQLException an unexpected error occurred
     */
    private PreparedStatement prepareInsert(PreparedStatement statement, Computer computer) throws SQLException {
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

        return statement;
    }

}
