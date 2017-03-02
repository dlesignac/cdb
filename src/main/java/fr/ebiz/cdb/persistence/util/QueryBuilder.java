package fr.ebiz.cdb.persistence.util;

/**
 * Query builder.
 */
public class QueryBuilder {

    private String query = "";

    /**
     * Builds select query.
     *
     * @param select select
     * @return QueryBuilder
     */
    public QueryBuilder select(String select) {
        query = "SELECT " + select;
        return this;
    }

    /**
     * Builds from query.
     *
     * @param from from
     * @return QueryBuilder
     */
    public QueryBuilder from(String from) {
        query += " FROM " + from;
        return this;
    }

    /**
     * Builds insert query.
     *
     * @param insert insert
     * @return QueryBuilder
     */
    public QueryBuilder insertInto(String insert) {
        query = "INSERT INTO " + insert;
        return this;
    }

    /**
     * Builds values query.
     *
     * @param values values
     * @return QueryBuilder
     */
    public QueryBuilder values(String values) {
        query += " VALUES " + values;
        return this;
    }

    /**
     * Builds delete query.
     *
     * @param delete delete
     * @return QueryBuilder
     */
    public QueryBuilder deleteFrom(String delete) {
        query = "DELETE FROM " + delete;
        return this;
    }

    /**
     * Builds update query.
     *
     * @param update update
     * @return QueryBuilder
     */
    public QueryBuilder update(String update) {
        query = "UPDATE " + update;
        return this;
    }

    /**
     * Builds set query.
     *
     * @param set set
     * @return QueryBuilder
     */
    public QueryBuilder set(String set) {
        query += " SET " + set;
        return this;
    }

    /**
     * Builds where query.
     *
     * @param where where
     * @return QueryBuilder
     */
    public QueryBuilder where(String where) {
        query += " WHERE " + where;
        return this;
    }

    /**
     * Builds and query.
     *
     * @param and and
     * @return QueryBuilder
     */
    public QueryBuilder and(String and) {
        query += " AND " + and;
        return this;
    }

    /**
     * Builds or query.
     *
     * @param or or
     * @return QueryBuilder
     */
    public QueryBuilder or(String or) {
        query += " OR " + or;
        return this;
    }

    /**
     * Builds orderBy query.
     *
     * @param orderBy orderBy
     * @return QueryBuilder
     */
    public QueryBuilder orderBy(String orderBy) {
        query += " ORDER BY " + orderBy;
        return this;
    }

    /**
     * Builds limit query.
     *
     * @param limit limit
     * @return QueryBuilder
     */
    public QueryBuilder limit(String limit) {
        query += " LIMIT " + limit;
        return this;
    }

    /**
     * Builds offset query.
     *
     * @param offset offset
     * @return QueryBuilder
     */
    public QueryBuilder offset(String offset) {
        query += " OFFSET " + offset;
        return this;
    }

    /**
     * Builds query.
     *
     * @return built query
     */
    public String build() {
        return query;
    }

}
