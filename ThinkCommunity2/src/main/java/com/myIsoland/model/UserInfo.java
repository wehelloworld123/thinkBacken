package com.myIsoland.model;

import lombok.Data;

@Data
public class UserInfo {

    private String userName;

    private String userId;

    private String userAvat;

    public UserInfo(String userName, String userId, String userAvat) {
        this.userName = userName;
        this.userId = userId;
        this.userAvat = userAvat;
    }
    public  UserInfo(){
        super();
    }
}
