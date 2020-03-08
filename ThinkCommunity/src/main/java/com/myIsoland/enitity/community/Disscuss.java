package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.SexType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("com_disscuss")
public class Disscuss extends BaseEntity implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private  Integer id;

    private String content;

    private String pic;

    private String location;

    private String label;

    private int like;

    private int commentNo;

    @TableField(exist = false)
    private List<Comment> comments;

    private int concernNo;

    private String creator;

    private SexType creatorSex;

    private String creatorAvat;

    public Disscuss() {
        super();
    }

    public Disscuss(String content, String pic, String location, String label, String creator, SexType creatorSex, String creatorAvat) {
        this.content = content;
        this.pic = pic;
        this.location = location;
        this.label = label;
        this.creator = creator;
        this.creatorSex = creatorSex;
        this.creatorAvat = creatorAvat;
    }
}
