package com.myIsoland.enitity.quotation;

import com.baomidou.mybatisplus.annotation.*;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_quo_analect")
@KeySequence("analect")
public class Analects extends BaseEntity implements Serializable{
    @TableId(value = "uid",type = IdType.INPUT)
    private String uid;

    private String title;

    private String describe;

    private String backColor;
    @TableField(value = "lock")
    private int lock;

    public Analects() {
        super();
    }

    public Analects(String title, String describe, String backColor) {
        this.title = title;
        this.describe = describe;
        this.backColor = backColor;
    }
}
