package com.myIsoland.enums;

public enum SourceEnmu {
    NULL(0),//空
    INPUT(1),//输入
    SYSTEM(2),//系统
    USER(3);//用户

    private int value;
    SourceEnmu(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public SourceEnmu valueOf(int value) {
        switch (value) {
            case 0:
                return SourceEnmu.NULL;
            case 1:
                return SourceEnmu.INPUT;
            case 2:
                return SourceEnmu.SYSTEM;
            case 3:
                return SourceEnmu.USER;
            default:
                return null;
        }
    }
}
