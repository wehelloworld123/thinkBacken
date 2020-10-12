package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@TableName("t_pro_ado_poetry")
@KeySequence("poetry")
public class AdoPoetry  extends BaseEntity implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value = "pro_no")
    private String proNo;
    @TableField(value = "content")
    private String content;
    @TableField(value = "no")
    private String no;
    @TableField(value = "brand")
    private String brand;
    @TableField(value = "title")
    private String title;


    @TableField(value = "charp_id")
    private Long charpId;
    @TableField(value = "part_id")
    private Long partId;
    @TableField(value = "set_id")
    private Long setId;
    @TableField(value = "poetry_id")
    private String poetryId;


    @TableField(value = "poetry_name")
    private String poetryName;
    @TableField(value = "charp_name")
    private String charpName;
    @TableField(value = "sec_name")
    private String secName;

    @TableField(value = "author_id")
    private String authorId;
    @TableField(exist = false)
    Map userInfo;

    public AdoPoetry(){
        super();
    }
}
