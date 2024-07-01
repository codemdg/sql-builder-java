package com.sqlbuilder;

import java.util.List;

public interface SqlQuery {

    List<String> getCustomSelect();

    String getFrom();
}
