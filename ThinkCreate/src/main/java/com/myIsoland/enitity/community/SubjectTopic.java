package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.*;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

@Data
@TableName("t_pro_sub_topic")
@KeySequence("")
public class SubjectTopic extends BaseEntity {
    @TableId(value = "topic_id",type = IdType.INPUT)
    private String topicId;
    @TableField(value = "topic")
    private String topic;
    @TableField(value = "creator")
    private int creator;
    @TableField(value = "product_nm")
    private int productNm;
    @TableField(value = "description")
    private String description;
    @TableField(value = "label1")
    private String label1;
    @TableField(value = "label2")
    private String label2;
    @TableField(value = "label3")
    private String label3;
    @TableField(value = "rank")
    private int rank;
    @TableField(value = "logo")
    private String logo;
    @TableField(value = "dn")
    private String dn;

    public SubjectTopic() {
        super();
    }
}
