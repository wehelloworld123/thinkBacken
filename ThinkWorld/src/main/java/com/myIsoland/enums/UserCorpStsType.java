package com.myIsoland.enums;

public enum UserCorpStsType {
    REJECT(-1),//拒绝申请
    UNAUDITED(0),//未审核
    ADOPT(1),//采纳通过
    DELETE(-2);//删除
    private Integer value;

    UserCorpStsType(Integer value){this.value=value;}

    public Integer getValue() {
        return value;
    }

    public UserCorpStsType valueOf(int value) {
        switch (value) {
            case -2:
                return UserCorpStsType.DELETE;
            case -1:
                return UserCorpStsType.REJECT;
            case 0:
                return UserCorpStsType.UNAUDITED;
            case 1:
                return UserCorpStsType.ADOPT;
            default:
                return null;
        }
    }
}
