package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enitity.system.TsysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
@TableName("t_com_corporation")
public class Corporation extends BaseEntity implements Serializable{
    @TableId(value = "uid",type = IdType.UUID)
    private String id;//社团号

    private String name;//社团名称

    private String description;//描述

    private String slogan;//标语

    private String logo;//图标

    @TableField(exist = false)
    private List<TsysUser> member;

    private int memberNo;//社团人数


    @TableField(exist = false)
    private List<Notice> notices;

//    @TableField(exist = false)
//    private List<Product> products;


    private String institution;//所属机构

    private String type;//社团类型

    private String contribution;//社团贡献度

    private int star;//级别

    private int like;//点赞

    public Corporation() {
        super();
    }

    public Corporation(String id, String name, String description, String slogan, String logo, String institution,String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.slogan = slogan;
        this.logo = logo;
        this.institution = institution;
        this.type=type;
    }
}
