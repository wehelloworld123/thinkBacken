package com.myIsoland.enums;

public enum CreditType  {
    RECOMMED(1),//推荐
    CREATE(2),//创作
    DEBATE(3),//辩论
    ANALECT(4),//名言
    CONSUMER(5);//消费

    private int value;
    CreditType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public CreditType valueOf(int value) {
        switch (value) {
            case 1:
                return CreditType.RECOMMED;
            case 2:
                return CreditType.CREATE;
            case 3:
                return CreditType.DEBATE;
            case 4:
                return CreditType.ANALECT;
            case 5:
                return CreditType.CONSUMER;
            default:
                return null;
        }
    }

}
