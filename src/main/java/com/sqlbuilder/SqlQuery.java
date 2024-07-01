package com.sqlbuilder;


public interface SqlQuery {

    String getFields();

    String getFrom();

    String getWithWhere();

    String getAndWhere();
}
