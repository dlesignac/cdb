package fr.ebiz.cdb.persistence.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDAO {

    /**
     * @param username username
     * @return UserDetails
     * @throws UsernameNotFoundException UsernameNotFoundException
     */
    UserDetails findByUsername(String username) throws UsernameNotFoundException;
}
