package fr.ebiz.cdb.persistence.util;

public class QueryBuilder {

    private StringBuilder query = new StringBuilder();

    /**
     * Build select query.
     *
     * @param select select
     * @return QueryBuilder
     */
    public QueryBuilder select(String select) {
        query.append("SELECT ").append(select);
        return this;
    }

    /**
     * Build from query.
     *
     * @param from from
     * @return QueryBuilder
     */
    public QueryBuilder from(String from) {
        query.append(" FROM ").append(from);
        return this;
    }

    /**
     * Build delete query.
     *
     * @param delete delete
     * @return QueryBuilder
     */
    public QueryBuilder deleteFrom(String delete) {
        query.append("DELETE FROM ").append(delete);
        return this;
    }

    /**
     * Build where query.
     *
     * @param where where
     * @return QueryBuilder
     */
    public QueryBuilder where(String where) {
        query.append(" WHERE ").append(where);
        return this;
    }

    /**
     * Build or query.
     *
     * @param or or
     * @return QueryBuilder
     */
    public QueryBuilder or(String or) {
        query.append(" OR ").append(or);
        return this;
    }

    /**
     * Build orderBy query.
     *
     * @param orderBy orderBy
     * @return QueryBuilder
     */
    public QueryBuilder orderBy(String orderBy) {
        query.append(" ORDER BY ").append(orderBy);
        return this;
    }

    /**
     * Build innerJoin query.
     *
     * @param innerJoin innerJoin
     * @return QueryBuilder
     */
    public QueryBuilder innerJoin(String innerJoin) {
        query.append(" INNER JOIN ").append(innerJoin);
        return this;
    }

    /**
     * Build leftOuterJoin query.
     *
     * @param leftOuterJoin leftOuterJoin
     * @return QueryBuilder
     */
    public QueryBuilder leftOuterJoin(String leftOuterJoin) {
        query.append(" LEFT OUTER JOIN ").append(leftOuterJoin);
        return this;
    }

    /**
     * Build query.
     *
     * @return built query
     */
    public String build() {
        return query.toString();
    }

}