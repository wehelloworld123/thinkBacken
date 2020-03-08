package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.SexType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_com_discuss")
public class Disscuss extends BaseEntity implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private  Long id;

    private String content;

    private String pic;

    private String location;

    private String label;

    private int likes;

    private int commentNo;

    @TableField(exist = false)
    private List<Comment> comments;

    @TableField(exist = false)
    private UserConcern userConcern;

    private int concernNo;

    private String creator;

    private int creatorSex;

    private String creatorAvat;
    @TableField(exist = false)
    private int status;
    @TableField(exist = false)
    private int isLike;
    @TableField(exist = false)
    private int isCon;
    @JsonIgnore
    private String favorer;
    @TableField(exist = false)
    Comment comment;

    public Disscuss() {
        super();
    }
    public Disscuss(Long id, int likes, String favorer) {
        this.id = id;
        this.likes = likes;
        this.favorer = favorer;

    }
    public Disscuss(String content, String pic, String location, String label, String creator, SexType creatorSex, String creatorAvat) {
        this.content = content;
        this.pic = pic;
        this.location = location;
        this.label = label;
        this.creator = creator;
        this.creatorSex = creatorSex.getValue();
        this.creatorAvat = creatorAvat;
    }
}
