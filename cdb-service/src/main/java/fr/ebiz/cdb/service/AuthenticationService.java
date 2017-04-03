package fr.ebiz.cdb.service;

import fr.ebiz.cdb.persistence.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private IUserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        } else {
            return user;
        }
    }

}
