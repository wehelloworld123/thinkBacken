package com.myIsoland.enitity.evaluate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_eva_subject")
public class Subject  extends BaseEntity implements Serializable{
    @TableId(value = "id",type= IdType.AUTO)
    private Integer id;//主键

    private String question;//问题

    private String evalId;//测评卷id

    private  int type;//测评类型

    private int order;//序号

    public Subject(){
        super();
    }

    public Subject(String question, String evalId, int type, int order) {
        this.question = question;
        this.evalId = evalId;
        this.type = type;
        this.order = order;
    }
}
