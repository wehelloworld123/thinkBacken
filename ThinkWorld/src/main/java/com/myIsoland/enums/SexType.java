package com.myIsoland.enums;

import java.io.Serializable;

public enum SexType implements Serializable {
    MALE(0),
    FEMALE(1);
    private Integer value;

    SexType(Integer value){this.value=value;}

    public Integer getValue() {
        return value;
    }

    public static SexType valueOf(int value) {
        switch (value) {
            case 0:
                return SexType.MALE;
            case 1:
                return SexType.FEMALE;
            default:
                return null;
        }
    }
}
