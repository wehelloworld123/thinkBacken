package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_deb_answer")
public class Answer extends BaseEntity implements Serializable {
    @TableId(value = "id")
    private String no;

    private String summary;

    private String content;

    private int likes;

    private int recommend;

    private String topicId;

    private int grade;

    private String creator;

    private String creatorAvat;

    private List<Recommend> recommends;

    public Answer() {
        super();
    }
}
