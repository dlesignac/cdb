package fr.ebiz.cdb.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.ebiz.cdb.dto.ComputerPageDTO;
import fr.ebiz.cdb.model.Column;
import fr.ebiz.cdb.model.Company;
import fr.ebiz.cdb.model.Order;
import fr.ebiz.cdb.persistence.ConnectionManager;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import fr.ebiz.cdb.persistence.exception.DAOQueryException;
import fr.ebiz.cdb.mapper.rs.ComputerRSMapper;

import fr.ebiz.cdb.model.Computer;

/**
 * Computer DAO.
 */
public enum ComputerDAO implements IComputerDAO {

    INSTANCE;

    private ConnectionManager connectionManager = ConnectionManager.INSTANCE;

    @Override
    public void create(Computer computer) throws DAOQueryException {
        String query = new QueryBuilder()
                .insertInto("computer(name, introduced, discontinued, company_id)")
                .values("(?, ?, ?, ?)")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(query,
                computer.getName(),
                computer.getIntroduced(),
                computer.getDiscontinued(),
                computer.getManufacturer() == null ? null : computer.getManufacturer().getId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOQueryException("could create computer " + computer.toString(), e);
        }
    }

    @Override
    public void delete(Computer computer) throws DAOQueryException {
        String query = new QueryBuilder()
                .deleteFrom("computer")
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(query, computer.getId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOQueryException("could not delete computer " + computer.toString(), e);
        }
    }

    @Override
    public void delete(Company company) throws DAOQueryException {
        String query = new QueryBuilder()
                .deleteFrom("computer")
                .where("company_id = ?")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(query, company.getId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOQueryException("could not delete computers for company " + company.toString(), e);
        }
    }

    @Override
    public void update(Computer computer) throws DAOQueryException {
        String query = new QueryBuilder()
                .update("computer")
                .set("name = ?, introduced = ?, discontinued = ?, company_id = ?")
                .where("id = ?")
                .build();

        try (PreparedStatement statement = connectionManager.prepareStatement(query,
                computer.getName(),
                computer.getIntroduced(),
                computer.getDiscontinued(),
                computer.getManufacturer() == null ? null : computer.getManufacturer().getId(),
                computer.getId())) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOQueryException("could not update computer " + computer.toString(), e);
        }
    }

    @Override
    public Computer find(int id) throws DAOQueryException {
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

        try (PreparedStatement statement = connectionManager.prepareStatement(query, id)) {
            ResultSet rs = statement.executeQuery();
            return new ComputerRSMapper().mapToOne(rs);
        } catch (SQLException e) {
            throw new DAOQueryException("could not find computer by id " + id, e);
        }
    }

    @Override
    public int count(String search) throws DAOQueryException {
        String query = new QueryBuilder()
                .select("count(c1.id)")
                .from("computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id")
                .where("c1.name LIKE ?")
                .or("c2.name LIKE ?")
                .build();

        search = "%" + search + "%";

        try (PreparedStatement statement = connectionManager.prepareStatement(query, search, search)) {
            ResultSet rs = statement.executeQuery();
            rs.first();
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new DAOQueryException("could not count computers with search " + search, e);
        }
    }

    @Override
    public List<Computer> fetch(ComputerPageDTO pageRequest) throws DAOQueryException {
        String sortString = getColumnName(pageRequest.getSort());
        String orderString = getOrder(pageRequest.getOrder());

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
                .orderBy(sortString + " " + orderString)
                .limit("?")
                .offset("?")
                .build();

        String search = "%" + pageRequest.getFilter() + "%";

        try (PreparedStatement statement = connectionManager.prepareStatement(query,
                search,
                search,
                pageRequest.getLimit(),
                (pageRequest.getNumber() - 1) * pageRequest.getLimit())) {
            ResultSet rs = statement.executeQuery();
            return new ComputerRSMapper().mapToMany(rs);
        } catch (SQLException e) {
            throw new DAOQueryException("could not find computers", e); // TODO more details here
        }
    }

    /**
     * Gets column name.
     *
     * @param column column
     * @return column name
     */
    private static String getColumnName(Column column) {
        switch (column) {
            case COMPUTER_NAME:
                return "c1.name";
            case INTRODUCED:
                return "introduced";
            case DISCONTINUED:
                return "discontinued";
            case COMPANY_NAME:
                return "c2.name";
        }

        return null;
    }

    /**
     * Gets order.
     *
     * @param order order
     * @return order
     */
    private static String getOrder(Order order) {
        switch (order) {
            case ASC:
                return "ASC";
            case DESC:
                return "DESC";
        }

        return null;
    }

}
