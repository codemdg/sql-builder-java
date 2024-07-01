package com.sqlbuilder;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.sqlbuilder.exception.FromMissingException;

/**
 * Unit test for MySQLQuery
 */
public class MySQLQueryTest 
{

    @Test
    public void should_return_mysql_query_object_with_from() throws FromMissingException{
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        
        queryBuilder.withFrom("table1");

        MySQLQuery mySQLQuery = queryBuilder.build();
        
        assertTrue(mySQLQuery.getFrom().equals("table1"));
    }

    @Test(expected = FromMissingException.class)
    public void should_throw_exception_for_mysql_query_object_without_from() throws FromMissingException{
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        
        MySQLQuery mySQLQuery = queryBuilder.build();
    }

    @Test
    public void should_return_mysql_query_object_with_custom_select() throws FromMissingException{
        QueryBuilder queryBuilder = MySQLQuery.getMySQLQueryBuilder();
        List<String> listField = new ArrayList<String>();
        listField.add("firstname");
        listField.add("lastname");
        queryBuilder.withSelect(listField)
        .withFrom("table1");

        MySQLQuery mySQLQuery = queryBuilder.build();
        
        assertTrue(mySQLQuery.getCustomSelect().size() > 0);

        assertTrue(mySQLQuery.getCustomSelect().contains("firstname"));

        assertTrue(mySQLQuery.getCustomSelect().contains("lastname"));
    }
}
