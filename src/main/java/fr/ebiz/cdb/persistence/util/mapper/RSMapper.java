package fr.ebiz.cdb.persistence.util.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ResultSet mapper.
 */
public abstract class RSMapper<T> {

    /**
     * Maps result set into one entity.
     *
     * @param rs result set
     * @return entity
     * @throws SQLException an unexpected error occurred
     */
    public T mapToOne(ResultSet rs) throws SQLException {
        return rs.first() ? map(rs) : null;
    }

    /**
     * Maps result set into several entities.
     *
     * @param rs result set
     * @return entities
     * @throws SQLException an unexpected error occurred
     */
    public List<T> mapToMany(ResultSet rs) throws SQLException {
        List<T> entities = new ArrayList<>();

        while (rs.next()) {
            entities.add(map(rs));
        }

        return entities;
    }

    /**
     * Maps result set into an entity.
     *
     * @param rs result set
     * @return entity
     * @throws SQLException an unexpected error occurred
     */
    protected abstract T map(ResultSet rs) throws SQLException;

}
