package com.myIsoland.model;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class ReportModel {
    @TableId(value = "no")
    private String no;
    @TableField(value = "name")
    private String name;
    @TableField(value = "create_dat")
    private DateTime dateTime;

}
