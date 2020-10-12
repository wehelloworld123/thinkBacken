package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_pro_rate_recom")
public class ProComment extends BaseEntity implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableId(value = "content")
    private String content;
    @TableId(value = "creation_id")
    private String creationId;
    @TableId(value = "rate")
    private int rate;
    @TableId(value = "typ")
    private int typ;
    @TableId(value = "creator")
    private String creator;
    @TableId(value = "creator_avat")
    private String creatorAvat;

    public ProComment(){
        super();
    }
}
