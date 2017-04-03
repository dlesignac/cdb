package fr.ebiz.cdb.persistence.dao.jdbctemplate;

import fr.ebiz.cdb.persistence.dao.IUserDAO;
import fr.ebiz.cdb.persistence.mapper.UserMapper;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractJDBCTemplateDAO implements IUserDAO {

    @Override
    public UserDetails findByUsername(String username) throws UsernameNotFoundException {
        String query = new QueryBuilder()
                .select("*")
                .from("admin")
                .where("username = ?")
                .build();

        return this.jdbcTemplate.queryForObject(query, new Object[]{username}, new UserMapper());
    }

}
