package com.myIsoland.model;

import lombok.Data;

import java.util.List;

@Data
public class ResultSet<T> {
    private Long count;

    private List<T> list;

    private T entity;

    private double rate;

    public ResultSet(){
        super();
    }
}
