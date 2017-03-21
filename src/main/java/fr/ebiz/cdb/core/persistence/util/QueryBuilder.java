package fr.ebiz.cdb.core.persistence.util;

/**
 * Query builder.
 */
public class QueryBuilder {

    private StringBuilder query = new StringBuilder();

    /**
     * Builds select query.
     *
     * @param select select
     * @return QueryBuilder
     */
    public QueryBuilder select(String select) {
        query.append("SELECT ").append(select);
        return this;
    }

    /**
     * Builds from query.
     *
     * @param from from
     * @return QueryBuilder
     */
    public QueryBuilder from(String from) {
        query.append(" FROM ").append(from);
        return this;
    }

    /**
     * Builds insert query.
     *
     * @param insert insert
     * @return QueryBuilder
     */
    public QueryBuilder insertInto(String insert) {
        query.append("INSERT INTO ").append(insert);
        return this;
    }

    /**
     * Builds values query.
     *
     * @param values values
     * @return QueryBuilder
     */
    public QueryBuilder values(String values) {
        query.append(" VALUES ").append(values);
        return this;
    }

    /**
     * Builds delete query.
     *
     * @param delete delete
     * @return QueryBuilder
     */
    public QueryBuilder deleteFrom(String delete) {
        query.append("DELETE FROM ").append(delete);
        return this;
    }

    /**
     * Builds update query.
     *
     * @param update update
     * @return QueryBuilder
     */
    public QueryBuilder update(String update) {
        query.append("UPDATE ").append(update);
        return this;
    }

    /**
     * Builds set query.
     *
     * @param set set
     * @return QueryBuilder
     */
    public QueryBuilder set(String set) {
        query.append(" SET ").append(set);
        return this;
    }

    /**
     * Builds where query.
     *
     * @param where where
     * @return QueryBuilder
     */
    public QueryBuilder where(String where) {
        query.append(" WHERE ").append(where);
        return this;
    }

    /**
     * Builds and query.
     *
     * @param and and
     * @return QueryBuilder
     */
    public QueryBuilder and(String and) {
        query.append(" AND ").append(and);
        return this;
    }

    /**
     * Builds or query.
     *
     * @param or or
     * @return QueryBuilder
     */
    public QueryBuilder or(String or) {
        query.append(" OR ").append(or);
        return this;
    }

    /**
     * Builds orderBy query.
     *
     * @param orderBy orderBy
     * @return QueryBuilder
     */
    public QueryBuilder orderBy(String orderBy) {
        query.append(" ORDER BY ").append(orderBy);
        return this;
    }

    /**
     * Builds limit query.
     *
     * @param limit limit
     * @return QueryBuilder
     */
    public QueryBuilder limit(String limit) {
        query.append(" LIMIT ").append(limit);
        return this;
    }

    /**
     * Builds offset query.
     *
     * @param offset offset
     * @return QueryBuilder
     */
    public QueryBuilder offset(String offset) {
        query.append(" OFFSET ").append(offset);
        return this;
    }

    /**
     * Builds query.
     *
     * @return built query
     */
    public String build() {
        return query.toString();
    }

}