package fr.ebiz.cdb.persistence.dao.hibernate;

import fr.ebiz.cdb.core.Company;
import fr.ebiz.cdb.persistence.dao.ICompanyDAO;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyDAO extends AbstractHibernateDAO implements ICompanyDAO {

    @Override
    public int delete(int id) {
        String query = new QueryBuilder()
                .deleteFrom("company as c")
                .where("c.id = :id")
                .build();

        return getSession()
                .createQuery(query)
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Company find(int id) {
        return getSession().get(Company.class, id);
    }

    @Override
    public List<Company> fetch() {
        String query = new QueryBuilder()
                .from("company")
                .build();

        List list = getSession()
                .createQuery(query)
                .getResultList();

        List<Company> companies = new ArrayList<>();

        for (Object o : list) {
            companies.add((Company)  o);
        }

        return companies;
    }

}