package fr.ebiz.cdb.persistence.dao.jdbctemplate;

import fr.ebiz.cdb.binding.ComputerPageRequest;
import fr.ebiz.cdb.core.Column;
import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.core.Order;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.mapper.ComputerMapper;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComputerDAO extends AbstractJDBCTemplateDAO implements IComputerDAO {

    @Override
    public void create(Computer computer) {
        String query = new QueryBuilder()
                .insertInto("computer(name, introduced, discontinued, company_id)")
                .values("(?, ?, ?, ?)")
                .build();

        Object[] params = {
                computer.getName(),
                computer.getIntroduced(),
                computer.getDiscontinued(),
                computer.getManufacturer() == null ? null : computer.getManufacturer().getId()
        };
        this.jdbcTemplate.update(query, params);
    }

    @Override
    public void delete(Computer computer) {
        String query = new QueryBuilder()
                .deleteFrom("computer")
                .where("id = ?")
                .build();

        this.jdbcTemplate.update(query, computer.getId());
    }

    @Override
    public void delete(Company company) {
        String query = new QueryBuilder()
                .deleteFrom("computer")
                .where("company_id = ?")
                .build();

        this.jdbcTemplate.update(query, company.getId());
    }

    @Override
    public void update(Computer computer) {
        String query = new QueryBuilder()
                .update("computer")
                .set("name = ?, introduced = ?, discontinued = ?, company_id = ?")
                .where("id = ?")
                .build();

        Object[] params = {
                computer.getName(),
                computer.getIntroduced(),
                computer.getDiscontinued(),
                computer.getManufacturer() == null ? null : computer.getManufacturer().getId(),
                computer.getId()
        };
        this.jdbcTemplate.update(query, params);
    }

    @Override
    public Computer find(int id) {
        String query = new QueryBuilder()
                .select("c1.id AS computer_id, c1.name AS computer_name, c1.introduced, c1.discontinued, c2.id AS company_id, c2.name AS company_name")
                .from("computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id")
                .where("c1.id = ?")
                .build();

        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new ComputerMapper());
    }

    @Override
    public int count(String search) {
        String query = new QueryBuilder()
                .select("count(c1.id)")
                .from("computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id")
                .where("c1.name LIKE ?")
                .or("c2.name LIKE ?")
                .build();

        search = "%" + search + "%";
        Object[] params = {search, search};
        return this.jdbcTemplate.queryForObject(query, params, Integer.class);
    }

    @Override
    public List<Computer> fetch(ComputerPageRequest pageRequest) {
        String query = new QueryBuilder()
                .select("c1.id AS computer_id, c1.name AS computer_name, c1.introduced, c1.discontinued, c2.id AS company_id, c2.name AS company_name")
                .from("computer c1 LEFT OUTER JOIN company c2 ON c1.company_id = c2.id")
                .where("c1.name LIKE ?")
                .or("c2.name LIKE ?")
                .orderBy(getColumnName(pageRequest.getSort()) + " " + getOrder(pageRequest.getOrder()))
                .limit("?")
                .offset("?")
                .build();

        String search = "%" + pageRequest.getFilter() + "%";
        Object[] params = {search, search, pageRequest.getLimit(), (pageRequest.getPage() - 1) * pageRequest.getLimit()};
        return this.jdbcTemplate.query(query, params, new ComputerMapper());
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