package com.myIsoland.model;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class LiteratureModel {
    @TableId(value = "uid")
    private String uid;
    @TableField(value = "name")
    private String name;
    @TableField(value = "cover")
    private String cover;
    @TableField(value = "type")
    private String type;
    @TableField(value = "label")
    private String label;
    @TableField(value = "introduce")
    private String introduce;
    @TableField(value = "kind")
    private int kind;
    @TableField(value = "is_top")
    private int isTop;
    @TableField(value = "create_dat")
    private DateTime createDate;
}
