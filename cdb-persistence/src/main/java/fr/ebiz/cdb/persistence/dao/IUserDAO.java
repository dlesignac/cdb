package fr.ebiz.cdb.persistence.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserDAO {

    UserDetails findByUsername(String username) throws UsernameNotFoundException;
}
