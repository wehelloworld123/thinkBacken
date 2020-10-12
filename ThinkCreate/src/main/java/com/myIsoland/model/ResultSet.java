package com.myIsoland.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ResultSet<T> {
    private Long count;

    private List<T> list;

    private T entity;

    private Map map;

    private double rate;

    public ResultSet(){
        super();
    }
}
