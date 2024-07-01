package com.sqlbuilder;

import com.sqlbuilder.exception.FromMissingException;
import com.sqlbuilder.mysql.MySQLQuery;

public interface QueryBuilder {

    QueryBuilder withSelect(String fields);

    QueryBuilder withFrom(String from);

    MySQLQuery build() throws FromMissingException;

    QueryBuilder withWhere(String firstField, String operator, String fieldSecond);

    QueryBuilder andWhere(String firstField, String operator, String fieldSecond);
}