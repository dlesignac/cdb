package fr.ebiz.cdb.core.persistence.dao;

import fr.ebiz.cdb.core.model.Company;
import fr.ebiz.cdb.core.persistence.mapper.CompanyMapper;
import fr.ebiz.cdb.core.persistence.util.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyDAO extends AbstractJDBCTemplateDAO implements ICompanyDAO {

    @Override
    public void delete(Company company) {
        String query = new QueryBuilder()
                .deleteFrom("company")
                .where("id = ?")
                .build();

        this.jdbcTemplate.update(query, company.getId());
    }

    @Override
    public Company find(int id) {
        String query = new QueryBuilder()
                .select("*")
                .from("company")
                .where("id = ?")
                .build();

        return this.jdbcTemplate.queryForObject(query, new Object[]{id}, new CompanyMapper());
    }

    @Override
    public List<Company> fetch() {
        String query = new QueryBuilder()
                .select("*")
                .from("company")
                .build();

        return this.jdbcTemplate.query(query, new CompanyMapper());
    }

}