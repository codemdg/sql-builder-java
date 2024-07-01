package com.sqlbuilder;

import java.util.List;

import com.sqlbuilder.exception.FromMissingException;

public class MySQLQuery implements SqlQuery {

    private List<String> customSelect;

    private String from;

    public static QueryBuilder getMySQLQueryBuilder() {
        return new MySQLQueryBuilder();
    }

    public static class MySQLQueryBuilder implements QueryBuilder {

        private List<String> customSelect;

        private String from;

        @Override
        public QueryBuilder withSelect(List<String> select) {
            this.customSelect = select;
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
            mySQLQuery.customSelect = this.customSelect;
            if(this.from == null) throw new FromMissingException("[From] cannot be null in MySQL query");
            mySQLQuery.from = this.from;

            return mySQLQuery;
        }

        @Override
        public QueryBuilder withWhere(String string) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'withWhere'");
        }
    }

    public List<String> getCustomSelect(){
        return this.customSelect;
    }

    public String getFrom(){
        return this.from;
    }
}
