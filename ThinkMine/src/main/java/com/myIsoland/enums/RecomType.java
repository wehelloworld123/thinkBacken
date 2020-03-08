package com.myIsoland.enums;

import java.io.Serializable;

public enum RecomType implements Serializable {

    LITERATURE(1),PAINTING(2),POEMTY(3);

    private final int value;

    RecomType(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static RecomType valueOf(int value) {
        switch (value) {
            case 1:
                return RecomType.LITERATURE;
            case 2:
                return RecomType.PAINTING;
            case 3:
                return RecomType.POEMTY;
            default:
                return null;
        }
    }


}
