package com.myIsoland.enitity.personInfo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

@Data
@TableName("t_percom_info")
public class PersonInfo extends BaseEntity {


    private String uid;

    private int discuss;

    private int corporations;

    private int activities;

    private int concern;
    @TableField(exist = false)
    private String nickname;
    @TableField(exist = false)
    private String avatar;
    @TableField(exist = false)
    private int sex;
    @TableField(exist = false)
    private String description;

    public PersonInfo(){
        super();
    }
}
