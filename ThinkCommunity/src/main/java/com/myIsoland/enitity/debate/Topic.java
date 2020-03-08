package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("t_deb_topic")
@KeySequence("topic")
public class Topic extends BaseEntity implements Serializable {
    @TableId(value = "uid",type = IdType.INPUT)
    private String uid;

    private String title;

    private String content;

    private int thinking;

    private int flexibility;

    private int logicality;

    private int totalDiff;

    private String keyWord;

    private int view;

    private int answer;

    private String belongName;

    private String belongId;

    private int minLike;

    private int minRecommend;
}
