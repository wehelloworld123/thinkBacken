package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.*;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_pro_poetry")
@KeySequence("poetry")
public class Poetry extends BaseEntity implements Serializable {
    @TableId(value = "uid",type = IdType.INPUT)
    private String uid;
    @TableField(value = "seter")
    private String seter;
    @TableField(value = "name")
    private String name;
    @TableField(value = "cover")
    private String cover;
    @TableField(value = "topic")
    private String topic;
    @TableField(value = "purpose")
    private String purpose;
    @TableField(value = "describe")
    private String describe;
    @TableField(value = "kind")
    private int kind;
    @TableField(value = "charpter")
    private int charpter;
    @TableField(value = "section")
    private int section;
    @TableField(value = "fin_charpter")
    private int finCharpt;
    @TableField(value = "fin_section")
    private int finSection;
    @TableField(value = "form")
    private String form;
    @TableField(value = "copyright")
    private int copyright;
    @TableField(value = "reward")
    private String reward;
    @TableField(value = "deadline")
    private Date deadline;
    @TableField(value = "publisher")
    private String publisher;
    @TableField(value = "partner")
    private int partner;
    @TableField(value = "views")
    private Long views;
    @TableField(value = "is_top")
    private int isTop;
    @TableField(value = "finish")
    private int finish;

    PoemSet poemSet;
    public Poetry() {
        super();
    }
}
