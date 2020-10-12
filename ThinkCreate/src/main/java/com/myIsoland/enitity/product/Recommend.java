package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.RecomType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_pro_recom")
public class Recommend extends BaseEntity implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "content")
    private String content;

    @TableField(value = "content_id")
    private String contentId;
    @TableField(value = "summary")
    private String summary;
    @TableField(value = "title")
    private String title;
    @TableField(value = "typ")
    private int kind;
    @TableField(value = "likes")
    private int likes;
    @TableField(value = "creator")
    private String creator;
    @TableField(value = "create_avat")
    private String createAvat;
    @TableField(value = "adopt")
    private int adopt;
    @TableField(exist = false)
    private String total;
    @TableField(value = "favorer")
    @JsonIgnore
    private String favorer;
    @TableField(exist = false)
    int islike;
    @TableField(exist = false)
    List<Comment> comments;
    public Recommend() {
        super();
    }

    public Recommend(String content,RecomType kind,int likes, String contenId) {
        this.content = content;
        this.kind=kind.getValue();
        this.likes=likes;
        this.contentId = contenId;
    }
}

