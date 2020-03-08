package com.myIsoland.enitity.person;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_per_person")
public class Person extends BaseEntity implements Serializable {

    private String no;//编号

    private String name;//名称

    private String avat;//头像

    private Integer info;//基本信息

    private String model;//模型

    private Integer personality;//人格

    private Integer character;//性格

    private Integer lifeLine;//人生线

    private Integer story;//故事

    private Integer honor;//成就

    private Integer motto;//格言

    public Person(){
        super();
    }


}
