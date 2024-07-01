package com.sqlbuilder;

import java.util.List;

import com.sqlbuilder.exception.FromMissingException;

public interface QueryBuilder {

    QueryBuilder withSelect(List<String> select);

    QueryBuilder withFrom(String from);

    MySQLQuery build() throws FromMissingException;

    QueryBuilder withWhere(String string);
}