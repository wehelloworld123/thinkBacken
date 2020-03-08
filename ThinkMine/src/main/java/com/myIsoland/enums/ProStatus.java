package com.myIsoland.enums;

public enum ProStatus {
    noFinish(0),
    finish(1),
    urgent(2),
    store(3);

    private int value;

    ProStatus(int value){
        this.value=value;
    }
    public int getValue() {
        return value;
    }
    public ProStatus valueOf(int value) {
        switch (value) {
            case 0:
                return ProStatus.noFinish;
            case 1:
                return ProStatus.finish;
            case 2:
                return ProStatus.urgent;
            case 3:
                return ProStatus.store;
            default:
                return null;
        }
    }
}
