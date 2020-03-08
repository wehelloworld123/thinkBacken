package com.myIsoland.enitity.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private String username;

    private String email;

    private String password;

    private String nickname;

    private String avatar;

    private SexType sex;//0代表男，1代表女

    private String describe;

    private Integer isDel;
    @JsonIgnore
    private int type;

    public TsysUser(String id, String username, String email, String password, String nickname, String avatar, SexType sex, String describe, int type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.avatar = avatar;
        this.sex = sex;
        this.describe = describe;
        this.type = type;
    }

    public TsysUser() {
        super();
    }

}