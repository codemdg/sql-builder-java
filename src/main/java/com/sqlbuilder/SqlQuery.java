package com.sqlbuilder;

import java.util.List;

public interface SqlQuery {

    String getFields();

    String getFrom();

    String getWithWhere();

    List<String> getAndWhere();
}
