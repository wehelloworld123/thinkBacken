package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.*;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.CreateKind;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_pro_liter")
@KeySequence("literature")
public class Literature extends BaseEntity implements Serializable{
    @TableId(value = "uid")
    private String uid;
    @TableField(value = "name")
    private String name;
    @TableField(value = "cover")
    private String cover;
    @TableField(value = "typ")
    private String typ;
    @TableField(value = "label")
    private String label;
    @TableField(value = "introduce")
    private String introduce;
    @TableField(value = "description")
    private String description;
    @TableField(value = "kind")
    private int kind;
    @TableField(value = "charpter")
    private int charpter;
    @TableField(value = "section")
    private int section;
    @TableField(value = "fin_charp")
    private int finCharp;
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
    @TableField(exist = false)
    LiterCharpt charpt;

    public Literature() {
        super();
    }

    public Literature(String name, String cover, String type, String label, String introduce, String describe, CreateKind kind, int charpter, int section,
                      String form, int copyright, String reward, Date deadline, String publisher) {
        this.name = name;
        this.cover = cover;
        this.typ = type;
        this.label = label;
        this.introduce = introduce;
        this.description = describe;
        this.kind = kind.getValue();
        this.charpter = charpter;
        this.section = section;
        this.form = form;
        this.copyright = copyright;
        this.reward = reward;
        this.deadline = deadline;
        this.publisher = publisher;
    }
}
