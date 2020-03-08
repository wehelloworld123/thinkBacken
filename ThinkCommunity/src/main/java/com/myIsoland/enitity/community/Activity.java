package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("com_activity")
public class Activity extends BaseEntity implements Serializable {
    @TableId( value = "uid",type = IdType.UUID)
    private String uid;

    private String subject;

    private String pic;

    private String contentHtml;

    private int likes;

    private String corporId;

    private String organ;

    private  int finish;

    public Activity(){
        super();
    }

    public Activity(String subject, String pic, String contentHtml, String corporId) {
        this.subject = subject;
        this.pic = pic;
        this.contentHtml = contentHtml;
        this.corporId = corporId;
    }
}
