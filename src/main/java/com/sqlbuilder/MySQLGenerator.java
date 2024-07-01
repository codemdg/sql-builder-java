package com.sqlbuilder;

public class MySQLGenerator implements SqlGenerator{
    private SqlQuery mySqlQuery;

    private StringBuilder mysqlStringBuilder = new StringBuilder();

    public MySQLGenerator(SqlQuery mySQLQuery){
        this.mySqlQuery = mySQLQuery;
    }

    public String getSQL() {
        this.mysqlStringBuilder.append("SELECT ");
        if (mySqlQuery.getCustomSelect() == null) {
            this.mysqlStringBuilder.append("* " );
        } else {
            this.mysqlStringBuilder.append(String.join(", ", mySqlQuery.getCustomSelect()) + " ");
        }

        this.mysqlStringBuilder.append("FROM ")
        .append(mySqlQuery.getFrom());

        this.mysqlStringBuilder.append(";");

        return this.mysqlStringBuilder.toString();
    }
}
