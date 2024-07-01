package com.sqlbuilder;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sqlbuilder.exception.FromMissingException;

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
    public void should_return_a_query_with_custom_select() throws FromMissingException {
        StringBuilder expectedQueryBuilder = new StringBuilder();
        expectedQueryBuilder.append("SELECT first_name, last_name FROM table1;");
        String expectedQuery = expectedQueryBuilder.toString();
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        List<String> listField = new ArrayList<String>();
        listField.add("first_name");
        listField.add("last_name");
        MySQLQuery mySQLQuery = queryBuilder
                .withSelect("first_name", "last_name")
                .withFrom("table1")
                .build();

        SqlGenerator sqlGenerator = new MySQLGenerator(mySQLQuery);
        String sql = sqlGenerator.getSQL();

        assertTrue(expectedQuery.equals(sql));
    }

    @Test
    public void should_return_a_query_with_custom_select_and_where_clause() throws FromMissingException {
        StringBuilder expectedQueryBuilder = new StringBuilder();
        expectedQueryBuilder.append("SELECT first_name, last_name FROM table1 WHERE table1.first_name > 0;");
        String expectedQuery = expectedQueryBuilder.toString();
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        List<String> listField = new ArrayList<String>();
        listField.add("first_name");
        listField.add("last_name");
        MySQLQuery mySQLQuery = queryBuilder
                .withSelect(listField)
                .withFrom("table1")
                .withWhere("table1.first_name > 0")
                .build();

        SqlGenerator sqlGenerator = new MySQLGenerator(mySQLQuery);
        String sql = sqlGenerator.getSQL();

        assertTrue(expectedQuery.equals(sql));

    }

    public void should_return_query_with_condition_and_where(){
        StringBuilder expecStringBuilder = new StringBuilder();
        expecStringBuilder.append("SELECT first_name, last_name FROM table1 WHERE table1.first_name > 0 AND table1.age > 18;");
        String expectedQuery = expecStringBuilder.toString();
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        List<String> listField = new ArrayList<String>();

        
    }
}
