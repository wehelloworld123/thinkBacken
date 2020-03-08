package com.myIsoland.enitity.think;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import com.sun.mail.imap.protocol.ID;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
@TableName("t_thi_theory")
public class Theory extends BaseEntity implements Serializable {
    @TableId( value = "id",type = IdType.AUTO)
    private int id;
    @TableField(value = "num")
    private String num;
    @TableField(value = "title")
    private String title;
    @TableField(value = "description")
    private String description;
    @TableField(value = "cover")
    private String cover;
    @TableField(value = "views")
    private int views;
    @TableField(value = "is_lock")
    private int isLock;
    public Theory(){

    }

    public Theory(String num, String title, String description, String cover) {
        this.num = num;
        this.title = title;
        this.description = description;
        this.cover = cover;
    }
}
