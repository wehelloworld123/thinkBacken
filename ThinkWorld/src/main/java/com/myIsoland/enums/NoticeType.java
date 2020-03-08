package com.myIsoland.enums;

public enum NoticeType {
    Sys(1),
    Corp(2),
    Act(3);
    private Integer value;

    NoticeType(Integer value){this.value=value;}

    public Integer getValue() {
        return value;
    }

    public NoticeType valueOf(int value) {
        switch (value) {
            case 1:
                return NoticeType.Sys;
            case 2:
                return NoticeType.Corp;
            case 3:
                return NoticeType.Act;
            default:
                return null;
        }
    }
}
