package com.sqlbuilder.mysql;

import com.sqlbuilder.QueryBuilder;
import com.sqlbuilder.SqlQuery;
import com.sqlbuilder.exception.FromMissingException;

public class MySQLQuery implements SqlQuery {

    private String fields;

    private String from;

    private String withWhere;

    private String andWhere;

    public static QueryBuilder getMySQLQueryBuilder() {
        return new MySQLQueryBuilder();
    }

    public static class MySQLQueryBuilder implements QueryBuilder {

        private String fields;

        private String from;

        private String withWhere;

        private String andWhere;

        @Override
        public QueryBuilder withSelect(String fields) {
            this.fields = fields;
            return this;
        }

        @Override
        public QueryBuilder withFrom(String from) {
            this.from = from;

            return this;
        }

        @Override
        public MySQLQuery build() throws FromMissingException {
            MySQLQuery mySQLQuery = new MySQLQuery();
            mySQLQuery.fields = this.fields;
            if (this.from == null)
                throw new FromMissingException("[From] cannot be null in MySQL query");
            mySQLQuery.from = this.from;
            mySQLQuery.withWhere = this.withWhere;
            mySQLQuery.andWhere = this.andWhere;

            return mySQLQuery;
        }

        @Override
        public QueryBuilder withWhere(String firstField, String operator, String fieldSecond) {
            this.withWhere = this.where(firstField, operator, fieldSecond);

            return this;
        }

        @Override
        public QueryBuilder andWhere(String firstField, String operator, String fieldSecond) {
            StringBuilder stringBuilder = new StringBuilder();
            this.andWhere = stringBuilder
                    .append(" AND ")
                    .append(this.where(firstField, operator, fieldSecond))
                    .toString();

            return this;
        }

        private String where(String firstField, String operator, String fieldSecond) {
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(firstField)
                    .append(" ")
                    .append(operator)
                    .append(" ")
                    .append(fieldSecond)
                    .toString();
        }
    }

    public String getFields() {
        return this.fields;
    }

    public String getFrom() {
        return this.from;
    }

    public String getWithWhere() {
        return this.withWhere;
    }

    public String getAndWhere() {
        return this.andWhere;
    }
}
