package fr.ebiz.cdb.persistence.dao.hibernate;

import fr.ebiz.cdb.binding.PageRequest;
import fr.ebiz.cdb.core.Computer;
import fr.ebiz.cdb.core.Order;
import fr.ebiz.cdb.core.Sort;
import fr.ebiz.cdb.persistence.dao.IComputerDAO;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComputerDAO extends AbstractHibernateDAO implements IComputerDAO {

    @Override
    public int create(Computer computer) {
        return (Integer) getSession().save(computer);
    }

    @Override
    public int delete(int id) {
        String query = new QueryBuilder()
                .deleteFrom("computer as c")
                .where("c.id = :id")
                .build();

        return getSession()
                .createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public int deleteByCompany(int id) {
        String query = new QueryBuilder()
                .deleteFrom("computer as c")
                .innerJoin("c.manufacturer as m")
                .where("m.id = :id")
                .build();

        return getSession()
                .createQuery(query)
                .setParameter("id", id)
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
    public List<Computer> fetch(PageRequest pageRequest) {
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
    private static String getColumnName(Sort column) {
        switch (column) {
            case ID:
                return "c.id";
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