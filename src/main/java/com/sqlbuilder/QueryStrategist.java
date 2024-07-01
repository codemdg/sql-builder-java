package com.sqlbuilder;

public class QueryStrategist {

    private SqlGenerator sqlGenerator;

    public QueryStrategist(SqlGenerator sqlGenerator) {
        this.sqlGenerator = sqlGenerator;
    }

    public String toSql(){
        return this.sqlGenerator.getSQL();
    }
}
