package com.myIsoland.enums;

import java.io.Serializable;

public enum DataSourceEnum implements Serializable {
    DB1("db1"),
    DB2("db2");

    private String value;

    DataSourceEnum(String value){this.value=value;}

    public String getValue() {
        return value;
    }
}
