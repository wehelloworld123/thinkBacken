package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_deb_answer")
public class Answer extends BaseEntity implements Serializable {
    @TableId(value = "no")
    private String no;
    @TableField(value = "conclusion")
    private String conclusion;
    @TableField(value = "content")
    private String content;
    @TableField(value = "likes")
    private int likes;
    @TableField(value = "recommend")
    private int recommend;
    @TableField(value = "topic_id")
    private String topicId;
    @TableField(value = "grade")
    private int grade;
    @TableField(value = "creator")
    private String creator;
    @TableField(value = "creator_avat")
    private String creatorAvat;
    @TableField(exist = false)
    private List<Recommend> recommends;
    @TableField(exist = false)
    private int islike;
    @TableField(value = "favorer")
    @JsonIgnore
    private String favorer;

    public Answer() {
        this.favorer = ";";
    }
}
