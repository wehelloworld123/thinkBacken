package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enitity.system.TsysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_com_corporation")
public class Corporation extends BaseEntity implements Serializable{
    @TableId(value = "id",type = IdType.UUID)
    private String id;//社团号

    private String name;//社团名称

    private String description;//描述

    private String slogan;//标语

    private String logo;//图标

    private String label;//标签

    @TableField(value = "member_no")
    private int memberNo;//社团人数

    @TableField(exist = false)
    private List<Notice> notices;

    private String institution;//所属机构

    @TableField(value = "typ")
    private String type;//社团类型

//    private String contribution;//社团贡献度

    private int star;//级别

    private int likes;//点赞
    @TableField(exist = false)
    private int isLike;
    @TableField(exist = false)
    private int isPart;
    @JsonIgnore
    private String favorer;

    @TableField(exist = false)
    private List<TsysUser> member;

    public Corporation() {
        super();
    }

    public Corporation(String id, String name, String description, String slogan, String logo, String label,String institution,String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.slogan = slogan;
        this.logo = logo;
        this.label = label;
        this.institution = institution;
        this.type=type;
    }
}
