package com.myIsoland.enums;

public enum SexType {
    MALE(0),FEMALE(1);
    private Integer value;

    SexType(Integer value){this.value=value;}

    public Integer getValue() {
        return value;
    }
    public SexType valueOf(int value) {
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
