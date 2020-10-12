package com.myIsoland.enitity.system;


import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.SexType;
import com.myIsoland.enums.UserType;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_sys_user")
@KeySequence("userId")
public class TsysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.INPUT)
    private String id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "email")
    private String email;
    @TableField(value = "password")
    private String password;
    @TableField(value = "nickname")
    private String nickname;
    @TableField(value = "avatar")
    private String avatar;
    @TableField(value = "sex")
    private int sex;//0代表男，1代表女
    @TableField(value = "description")
    private String description;
    @TableField(value = "think")
    private int think;
    @TableField(value = "is_del")
    private int isDel;
    @JsonIgnore
    private int type;

    public TsysUser(String id, String username, String email, String password, String nickname, String avatar, SexType sex, String describe, int type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.sex = sex.getValue();
        this.description = describe;
        this.type = type;
    }
    public TsysUser() {
        super();
    }

}