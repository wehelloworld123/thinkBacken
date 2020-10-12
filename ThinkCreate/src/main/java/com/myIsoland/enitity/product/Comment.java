package com.myIsoland.enitity.product;
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
@TableName("t_pro_comment")
public class Comment extends BaseEntity implements Serializable{

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableId(value = "content")
    private String content;
    @TableId(value = "root_id")
    private Long rootId;
    @TableId(value = "reply_id")
    private String replyId;
    @TableId(value = "reply_name")
    private String replyName;
    @TableId(value = "likes")
    private int likes;
    @TableId(value = "creator")
    private String creator;
    @TableId(value = "creator_sex")
    private int creatorSex;
    @TableId(value = "creator_avat")
    private String creatorAvat;
/*    @TableId(value = "summary")
    private String summary;*/
    @TableField(exist = false)
    private int isLike;
    @TableField(exist = false)
    private String rootName;
    @TableField(exist = false)
    private String rootContent;
    @TableField(exist = false)
    List<Comment> comments;
    @JsonIgnore
    @TableId(value = "favorer")
    private String favorer;

    public Comment(String content, Long rootId, String replyId, String replyName, String creator,
                   SexType creatorSex, String creatorAvat, String summary) {
        this.content = content;
        this.rootId = rootId;
        this.replyId = replyId;
        this.replyName = replyName;
        this.creator = creator;
        this.creatorSex = creatorSex.getValue();
        this.creatorAvat = creatorAvat;
       // this.summary = summary;
    }
    public Comment(Long id, int likes, String favorer) {
        this.id = id;
        this.likes = likes;
        this.favorer = favorer;
    }
    public Comment() {
        super();
    }
}
