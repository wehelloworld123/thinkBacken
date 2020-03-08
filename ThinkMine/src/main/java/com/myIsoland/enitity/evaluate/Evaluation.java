package com.myIsoland.enitity.evaluate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_eva_evaluation")
public class Evaluation extends BaseEntity implements Serializable {
    @TableId(value = "uid",type = IdType.UUID)
    private String uid;//测试id

    private int type;//测评大类

    private String name;//测试题名称

    private String describe;//测试题描述

    private int number;//测试题数量

    private int conTime;//限时

    public Evaluation(){
        super();
    }

    public Evaluation(int type, String name, String describe, int number, int conTime) {
        this.type = type;
        this.name = name;
        this.describe = describe;
        this.number = number;
        this.conTime = conTime;
    }
}
