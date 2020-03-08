package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.*;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.CreateKind;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_pro_paint")
@KeySequence("painting")
public class Painting extends BaseEntity implements Serializable {

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
    @TableField(value = "part")
    private int parts;
    @TableField(value = "section")
    private int section;
    @TableField(value = "fin_part")
    private int finPart;
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

    PaintingPart part;

    public Painting() {
        super();
    }

    public Painting(String seter, String name, String cover, String topic, String purpose, String describe, CreateKind kind, int parts,
                    int section, String form, int copyright, String reward, Date deadline, String publisher) {
        this.seter = seter;
        this.name = name;
        this.cover = cover;
        this.topic = topic;
        this.purpose = purpose;
        this.describe = describe;
        this.kind = kind.getValue();
        this.parts = parts;
        this.section = section;
        this.form = form;
        this.copyright = copyright;
        this.reward = reward;
        this.deadline = deadline;
        this.publisher = publisher;
    }
}
