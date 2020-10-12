package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;
import java.io.Serializable;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_deb_topic")
@KeySequence("topic")
public class Topic extends BaseEntity implements Serializable {
    @TableId(value = "uid")
    private String uid;
    @TableField(value = "subject")
    private String subject;
    @TableField(value = "subject_id")
    private String subjectId;
    @TableField(value = "title")
    private String title;
    @TableField(value = "content")
    private String content;
    @TableField(value = "thinking")
    private int thinking;
    @TableField(value = "flexibility")
    private int flexibility;
    @TableField(value = "logicality")
    private int logicality;
    @TableField(value = "totalDiff")
    private int totalDiff;
    @TableField(value = "keyWord")
    private String keyWord;
    @TableField(value = "views")
    private int views;
    @TableField(value = "answers")
    private int answers;
    @TableField(value = "belong_name")
    private String belongName;
    @TableField(value = "belong_id")
    private String belongId;
    @JsonIgnore
    @TableField(value = "min_like")
    private int minLike;
    @JsonIgnore
    @TableField(value = "min_recom")
    private int minRecom;
    @TableField(value = "year")
    private String year;
    @TableField(value = "period")
    private int period;
    @TableField(value = "finish")
    private int finish;
    @TableField(value = "status")
    private int status;//论题状态；1为已完成，0为正在论答，-1为推荐
    //List<Answer> answerList;

    public Topic(){
        super();
    }
    public Topic(String uid, String title, String content, int thinking, int flexibility, int logicality, int totalDiff, String keyWord, String belongName, String belongId, int minLike, int minRecommend, int period, Year year) {
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.thinking = thinking;
        this.flexibility = flexibility;
        this.logicality = logicality;
        this.totalDiff = totalDiff;
        this.keyWord = keyWord;
        this.belongName = belongName;
        this.belongId = belongId;
        this.minLike = minLike;
        this.minRecom = minRecommend;
        this.period = period;
        this.year = year.toString();
    }
}
