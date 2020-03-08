package com.myIsoland.enums;

public enum NoticeType {
    Sys(1),
    CorpPri(2),
    CorpPub(3),
    Act(4);
    private int value;

    NoticeType(int value){this.value=value;}

    public int getValue() {
        return value;
    }

    public NoticeType valueOf(int value) {
        switch (value) {
            case 1:
                return NoticeType.Sys;
            case 2:
                return NoticeType.CorpPri;
            case 3:
                return NoticeType.CorpPub;
            case 4:
                return NoticeType.Act;
            default:
                return null;
        }
    }
}
