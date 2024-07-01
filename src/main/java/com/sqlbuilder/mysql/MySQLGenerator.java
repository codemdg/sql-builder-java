package com.sqlbuilder.mysql;

import com.sqlbuilder.SqlGenerator;
import com.sqlbuilder.SqlQuery;

public class MySQLGenerator implements SqlGenerator{
    private SqlQuery mySqlQuery;

    private StringBuilder mysqlStringBuilder = new StringBuilder();

    public MySQLGenerator(SqlQuery mySQLQuery){
        this.mySqlQuery = mySQLQuery;
    }

    public String getSQL() {
        this.mysqlStringBuilder.append("SELECT ");
        if (mySqlQuery.getFields() == null) {
            this.mysqlStringBuilder.append("* " );
        } else {
            this.mysqlStringBuilder.append(mySqlQuery.getFields() + " ");
        }

        this.mysqlStringBuilder.append("FROM ")
        .append(mySqlQuery.getFrom());

        if (mySqlQuery.getWithWhere() != null) {
            this.mysqlStringBuilder.append(" WHERE ");
            this.mysqlStringBuilder.append(mySqlQuery.getWithWhere());
        }

        if(mySqlQuery.getAndWhere() != null) {
            this.mysqlStringBuilder.append(mySqlQuery.getAndWhere());
        }

        this.mysqlStringBuilder.append(";");

        return this.mysqlStringBuilder.toString();
    }
}
