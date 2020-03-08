package com.myIsoland.enitity.myself;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.enums.testType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_my_report")
@KeySequence("report")
public class Report implements Serializable {
    @TableId(value = "uid",type = IdType.INPUT)
    private String uid;

    private String name;

    private String result;

    private String feature;

    private String suggestion;

    private int score;

    private testType type;

    private int aNum;

    private int bNum;

    private int cNum;

    private int dNum;

    private int eNum;

    private Date createDat;

}
