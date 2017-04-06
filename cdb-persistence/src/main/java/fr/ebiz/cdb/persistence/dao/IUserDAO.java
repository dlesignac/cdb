package fr.ebiz.cdb.persistence.dao;

import fr.ebiz.cdb.core.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDAO {

    /**
     * Find user by username.
     *
     * @param username username
     * @return User
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    User findByUsername(String username) throws UsernameNotFoundException;
}
