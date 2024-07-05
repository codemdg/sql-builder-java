package com.sqlbuilder;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.sqlbuilder.exception.FromMissingException;
import com.sqlbuilder.mysql.MySQLGenerator;
import com.sqlbuilder.mysql.MySQLQuery;

import enums.OperatorEnum;

public class MySQLQueryGeneratorTest {
    @Test
    public void should_return_a_query_with_default_select() throws FromMissingException {
        String expectedQuery = "SELECT * FROM table1;";
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();

        MySQLQuery mySQLQuery = queryBuilder
                .withFrom("table1")
                .build();

        SqlGenerator sqlGenerator = new MySQLGenerator(mySQLQuery);

        assertTrue("sqlGenerator should generate an sql string equals to expectedQuery",
                expectedQuery.compareTo(sqlGenerator.getSQL()) == 0);
    }

    @Test
    public void should_return_a_query_with_fields_in_select() throws FromMissingException {
        StringBuilder expectedQueryBuilder = new StringBuilder();
        expectedQueryBuilder.append("SELECT first_name, last_name FROM table1;");
        String expectedQuery = expectedQueryBuilder.toString();
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        MySQLQuery mySQLQuery = queryBuilder
                .withSelect("first_name, last_name")
                .withFrom("table1")
                .build();

        QueryStrategist queryStrategist = new QueryStrategist(new MySQLGenerator(mySQLQuery));
        String sql = queryStrategist.toSql();

        assertTrue(expectedQuery.equals(sql));
    }

    @Test
    public void should_return_a_query_with_custom_select_and_where_clause() throws FromMissingException {
        StringBuilder expectedQueryBuilder = new StringBuilder();
        expectedQueryBuilder.append("SELECT amount, bill_code FROM bill WHERE bill.amount > 0;");
        String expectedQuery = expectedQueryBuilder.toString();
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        MySQLQuery mySQLQuery = queryBuilder
                .withSelect("amount, bill_code")
                .withFrom("bill")
                .withWhere("bill.amount", OperatorEnum.GREATER_THAN.value(), "0")
                .build();

        QueryStrategist queryStrategist = new QueryStrategist(new MySQLGenerator(mySQLQuery));
        String sql = queryStrategist.toSql();

        System.out.println(sql.toString());

        assertTrue(expectedQuery.equals(sql));

    }

    @Test
    public void should_return_query_with_condition_and_where() throws FromMissingException {
        StringBuilder expecStringBuilder = new StringBuilder();
        expecStringBuilder.append(
                "SELECT first_name, last_name FROM table1 WHERE table1.first_name IS NOT NULL AND table1.age > 18;");
        String expectedQuery = expecStringBuilder.toString();
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        MySQLQuery mysqlQuery = queryBuilder
                .withSelect("first_name, last_name")
                .withFrom("table1")
                .withWhere("table1.first_name", OperatorEnum.IS_NOT.value(), "NULL")
                .andWhere("table1.age", OperatorEnum.GREATER_THAN.value(), "18")
                .build();
        QueryStrategist queryStrategist = new QueryStrategist(new MySQLGenerator(mysqlQuery));
        String sql = queryStrategist.toSql();

        System.out.println(sql);

        assertTrue(expectedQuery.equals(sql));
    }

    @Test
    public void should_return_query_with_successive_and_where() throws FromMissingException{
        StringBuilder exStringBuilder = new StringBuilder();
        exStringBuilder.append("SELECT first_name, last_name FROM table1 WHERE table1.first_name IS NOT NULL AND table1.age > 18 AND table1.gender = H;");
        String expectedQuery = exStringBuilder.toString();
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        MySQLQuery mysqlQuery = queryBuilder
        .withSelect("first_name, last_name")
        .withFrom("table1")
        .withWhere("table1.first_name", OperatorEnum.IS_NOT.value(),"NULL")
        .andWhere("table1.age", OperatorEnum.GREATER_THAN.value(), "18")
        .andWhere("table1.gender", OperatorEnum.EQUAL.value(), "H")
        .build();

        QueryStrategist queryStrategist = new QueryStrategist(new MySQLGenerator(mysqlQuery));
        String sql = queryStrategist.toSql();

        assertTrue(expectedQuery.equals(sql));
    }
}
