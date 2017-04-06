package fr.ebiz.cdb.persistence.dao.hibernate;

import fr.ebiz.cdb.core.User;
import fr.ebiz.cdb.persistence.dao.IUserDAO;
import fr.ebiz.cdb.persistence.util.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractHibernateDAO implements IUserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        String query = new QueryBuilder()
                .from("user as user")
                .where("user.username = :username")
                .build();

        LOGGER.debug("USER ");

        User user = (User) getSession()
                .createQuery(query)
                .setParameter("username", username)
                .getSingleResult();

        LOGGER.debug("USER " + user);

        return user;

    }

}
