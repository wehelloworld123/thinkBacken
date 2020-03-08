package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_deb_user_topic")
public class UserTopic extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String topicId;

    private String ansId;

    private int rank;//名次

    private int status;//引用书籍状态

    private String referBook;//引用书籍名称

    private String referType;//引用书籍类型

    private Topic topic;

    private Answer answer;

    public UserTopic(){
        super();
    }
    public UserTopic(String topicId, String ansId, int rank) {
        this.topicId = topicId;
        this.ansId = ansId;
        this.rank = rank;
    }
}
