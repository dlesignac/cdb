package fr.ebiz.cdb.persistence.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserMapper implements RowMapper<UserDetails> {

    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    @Override
    public UserDetails mapRow(ResultSet resultSet, int i) throws SQLException {
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
        return new User(
                resultSet.getString(COLUMN_USERNAME),
                resultSet.getString(COLUMN_PASSWORD),
                Arrays.asList(authority));
    }
}
