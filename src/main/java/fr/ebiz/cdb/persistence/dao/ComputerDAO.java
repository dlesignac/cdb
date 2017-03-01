package fr.ebiz.cdb.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.ebiz.cdb.persistence.QueryBuilder;
import fr.ebiz.cdb.persistence.exception.QueryException;
import fr.ebiz.cdb.persistence.mapper.ComputerRSMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        Object[] args = {
                computer.getName(),
                computer.getIntroduced(),
                computer.getDiscontinued(),
                computer.getManufacturer().getId()
        };

        try (PreparedStatement statement = prepare(connection, query, args)) {
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

        Object[] args = {computer.getId()};

        try (PreparedStatement statement = prepare(connection, query, args)) {
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

        Object[] args = {
                computer.getName(),
                computer.getIntroduced(),
                computer.getDiscontinued(),
                computer.getManufacturer().getId(),
                computer.getId()
        };

        try (PreparedStatement statement = prepare(connection, query, args)) {
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

        Object[] args = {id};

        try (PreparedStatement statement = prepare(connection, query, args)) {
            ResultSet rs = statement.executeQuery();
            return new ComputerRSMapper().mapToOne(rs);
        } catch (SQLException e) {
            logger.error("could not find computer", e);
            throw new QueryException();
        }
    }

    @Override
    public int count(Connection connection, String search) throws QueryException {
        String query = new QueryBuilder()
                .select("count(c1.id)")
                .from("computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id")
                .where("c1.name LIKE ?")
                .or("c2.name LIKE ?")
                .build();

        Object[] args = {
                "%" + search + "%",
                "%" + search + "%"
        };

        try (PreparedStatement statement = prepare(connection, query, args)) {
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
    public List<Computer> fetch(Connection connection, String search, String orderBy, String order, int limit, int offset)
            throws QueryException {
        ComputerColumn orderByEnum = ComputerColumn.of(orderBy);
        Order orderEnum = Order.of(order);

        if (orderByEnum == null || orderEnum == null) {
            logger.warn("tried to order by '" + orderBy + "' '" + order + "'");
            throw new QueryException();
        }

        String query = new QueryBuilder()
                .select("c1.id AS computer_id, " +
                        "c1.name AS computer_name, " +
                        "c1.introduced, " +
                        "c1.discontinued, " +
                        "c2.id AS company_id, " +
                        "c2.name AS company_name")
                .from("computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id")
                .where("c1.name LIKE ?")
                .or("c2.name LIKE ?")
                .orderBy(orderByEnum.getColumn() + " " + orderEnum.getValue())
                .limit("?")
                .offset("?")
                .build();

        Object[] args = {
                "%" + search + "%",
                "%" + search + "%",
                limit,
                offset
        };

        try (PreparedStatement statement = prepare(connection, query, args)) {
            ResultSet rs = statement.executeQuery();
            return new ComputerRSMapper().mapToMany(rs);
        } catch (SQLException e) {
            logger.error("could not find computers", e);
            throw new QueryException();
        }
    }

    /**
     * Prepares statement.
     *
     * @param connection connection
     * @param query      query
     * @param objects    objects
     * @return PreparedStatement
     * @throws SQLException an unexpected error occurred
     */
    private PreparedStatement prepare(Connection connection, String query, Object[] objects)
            throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        for (int i = 0; i < objects.length; i++) {
            preparedStatement.setObject(i + 1, objects[i]);
        }

        return preparedStatement;
    }

}
