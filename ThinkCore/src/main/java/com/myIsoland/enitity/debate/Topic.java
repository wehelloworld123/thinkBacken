package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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

    private String title;

    private String content;

    private int thinking;

    private int flexibility;

    private int logicality;

    private int totalDiff;

    private String keyWord;

    private int views;

    private int answers;

    private String belongName;

    private String belongId;
    @JsonIgnore
    private int minLike;
    @JsonIgnore
    private int minRecommend;
    
    private String year;

    private int times;

    private int finish;

    //List<Answer> answerList;

    public Topic(){
        super();
    }
    public Topic(String uid, String title, String content, int thinking, int flexibility, int logicality, int totalDiff, String keyWord, String belongName, String belongId, int minLike, int minRecommend, int times, Year year) {
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
        this.minRecommend = minRecommend;
        this.times = times;
        this.year = year.toString();
    }
}
