package com.myIsoland.enums;

import java.io.Serializable;

public enum CreateKind implements Serializable {
    HISTORY(1),
    LITERATURE(2),
    THINKING(3),
    GAME(4),
    ROMANTIC(5),
    PSYCHOLOGY(6),
    INCLUDED(0);//自由创作
    private final int value;

    CreateKind(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
    public static CreateKind valueOf(int value) {
        switch (value) {
            case 0:
                return CreateKind.INCLUDED;
            case 1:
                return CreateKind.HISTORY;
            case 2:
                return CreateKind.LITERATURE;
            case 3:
                return CreateKind.THINKING;
            case 4:
                return CreateKind.GAME;
            case 5:
                return CreateKind.ROMANTIC;
            case 6:
                return CreateKind.PSYCHOLOGY;
            default:
                return null;
        }
    }
}
