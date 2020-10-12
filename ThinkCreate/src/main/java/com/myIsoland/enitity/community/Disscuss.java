package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.SexType;
import com.myIsoland.model.ImageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_com_discuss")
public class Disscuss extends BaseEntity implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private  Long id;
    @TableField(value = "content")
    private String content;
    @TableField(value = "pic1")
    private String pic1;
    @TableField(value = "pic2")
    private String pic2;
    @TableField(value = "pic3")
    private String pic3;
    @TableField(value = "pic4")
    private String pic4;
    @TableField(value = "pic5")
    private String pic5;
    @TableField(value = "pic6")
    private String pic6;
    @TableField(value = "pic7")
    private String pic7;
    @TableField(value = "pic8")
    private String pic8;
    @TableField(value = "pic")
    private String pic;
    @TableField(value = "location")
    private String location;
    @TableField(value = "label")
    private String label;
    @TableField(value = "likes")
    private int likes;
    @TableField(value = "comment_no")
    private int commentNo;
    @TableField(value = "source")
    private String source;

    @TableField(exist = false)
    private List<Comment> comments;

    @TableField(exist = false)
    private UserConcern userConcern;
    @TableField(value = "concern_no")
    private int concernNo;
    @TableField(value = "creator")
    private String creator;
    @TableField(value = "creator_sex")
    private int creatorSex;
    @TableField(value = "creator_avat")
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
    @TableField(exist = false)
    private List<ImageInfo> imgs;

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
