package com.myIsoland.enums;

public enum RankType {
    NORMAL(-1),GOLDER(1),SILVER(2),COPPER(3);

    private Integer value;

    RankType(Integer value){this.value=value;}

    public Integer getValue() {
        return value;
    }
    public RankType valueOf(int value) {
        switch (value) {
            case -1:
                return RankType.NORMAL;
            case 1:
                return RankType.GOLDER;
            case 2:
                return RankType.SILVER;
            case 3:
                return RankType.COPPER;
            default:
                return null;
        }
    }
}
