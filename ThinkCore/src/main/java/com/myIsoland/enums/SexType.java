package com.myIsoland.enums;

public enum SexType {
    MALE(0),FEMALE(1);
    private int value;

    SexType(int value){this.value=value;}

    public int getValue() {
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
