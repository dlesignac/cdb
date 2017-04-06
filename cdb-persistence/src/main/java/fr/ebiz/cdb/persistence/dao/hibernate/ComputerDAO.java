package fr.ebiz.cdb.persistence.dao.hibernate;

import fr.ebiz.cdb.binding.ComputerPageRequest;
import fr.ebiz.cdb.core.Column;
import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.core.Order;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComputerDAO extends AbstractHibernateDAO implements IComputerDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDAO.class);

    @Override
    public void create(Computer computer) {
        getSession().save(computer);
    }

    @Override
    public void delete(Computer computer) {
        getSession().delete(computer);
    }

    @Override
    public void delete(Company company) {
        String query = new QueryBuilder()
                .deleteFrom("computer as c")
                .innerJoin("c.manufacturer as m")
                .where("m.id = :id")
                .build();

        getSession()
                .createQuery(query)
                .setParameter("id", company.getId())
                .executeUpdate();
    }

    @Override
    public void update(Computer computer) {
        getSession().update(computer);
    }

    @Override
    public Computer find(int id) {
        return getSession().get(Computer.class, id);
    }

    @Override
    public int count(String filter) {
        String query = new QueryBuilder()
                .select("count(c.id)")
                .from("computer as c")
                .leftOuterJoin("c.manufacturer as m")
                .where("c.name LIKE :filter")
                .or("m.name LIKE :filter")
                .build();

        return ((Long) getSession()
                .createQuery(query)
                .setParameter("filter", "%" + filter + "%")
                .getSingleResult()).intValue();
    }

    @Override
    public List<Computer> fetch(ComputerPageRequest pageRequest) {
        String query = new QueryBuilder()
                .from("computer as c")
                .leftOuterJoin("c.manufacturer as m")
                .where("c.name LIKE :filter")
                .or("m.name LIKE :filter")
                .orderBy(getColumnName(pageRequest.getSort()) + " " + getOrder(pageRequest.getOrder()))
                .build();

        List list = getSession()
                .createQuery(query)
                .setFirstResult((pageRequest.getPage() - 1) * pageRequest.getLimit())
                .setMaxResults(pageRequest.getLimit())
                .setParameter("filter", "%" + pageRequest.getFilter() + "%")
                .list();

        List<Computer> computers = new ArrayList<>();

        for (Object o : list) {
            computers.add((Computer) ((Object[]) o)[0]);
        }

        return computers;
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
                return "c.name";
            case INTRODUCED:
                return "c.introduced";
            case DISCONTINUED:
                return "c.discontinued";
            case COMPANY_NAME:
                return "m.name";
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