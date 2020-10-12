package com.myIsoland.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private String id;

    private String nickname;

    private String avatar;

    private int sex;

    private String description;


    public UserInfo(String id,String nickname, String avatar, int sex,String description) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.sex = sex;
        this.description = description;
    }
    public  UserInfo(){
        super();
    }
}
