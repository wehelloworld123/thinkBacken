package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_com_activity")
public class Activity extends BaseEntity implements Serializable {
    @TableId( value = "uid",type = IdType.UUID)
    private String uid;

    private String subject;//主题

    private String pic;//图片
    @TableField(value = "dat")
    private String date;//日期

    private String address;//地址

    private String contentHtml;//内容

    private int views;//喜欢

    private int concern;//关注

    private String corporId;//社团id

    private String organ;//组织方

    private  int finish;//完成

    public Activity(){
        super();
    }
    public Activity(String uid, int views) {
        this.uid = uid;
        this.views = views;
    }
    public Activity(String subject, String pic, String date, String address, String contentHtml, String corporId, String organ) {
        this.subject = subject;
        this.pic = pic;
        this.date = date;
        this.address = address;
        this.contentHtml = contentHtml;
        this.corporId = corporId;
        this.organ = organ;
    }
}
