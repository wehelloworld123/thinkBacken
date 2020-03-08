package com.myIsoland.enums;

public enum DisscuStatus {
    CONCERN(1),
    NONE(0),
    DISGUS(-1);

    private int value;

    DisscuStatus(int value){this.value=value;}

    public static DisscuStatus valueOf(int value) {
        switch (value) {
            case -1:
                return DisscuStatus.DISGUS;
            case 0:
                return DisscuStatus.NONE;
            case 1:
                return DisscuStatus.CONCERN;
            default:
                return null;
        }
    }
}
