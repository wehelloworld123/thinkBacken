package com.myIsoland.model;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;

public class ProductModel {
    private String proId;

    private String proName;

    private String cover;

    private int kind;

    private int status;

    private int finCharp;

    private int finSection;

    private Date deadline;

    private String inviter;

    private Date createDat;

}
