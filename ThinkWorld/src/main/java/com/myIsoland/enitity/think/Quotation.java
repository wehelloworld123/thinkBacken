package com.myIsoland.enitity.think;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_thi_quot")
public class Quotation extends BaseEntity implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField(value = "num")
    private String num;
    @TableField(value = "question")
    private String question;
    @TableField(value = "content")
    private String content;
    @TableField(value = "explation")
    private String explation;
    @TableField(value = "theory_no")
    private String theoryNo;
    @TableField(value = "views")
    private int views;
    @TableField(value = "is_lock")
    private int isLock;

    public  Quotation(){

    }

    public Quotation(String num,String content, String explation, String theoryNo) {
        this.num = num;
        this.content = content;
        this.explation = explation;
        this.theoryNo = theoryNo;
    }
}
