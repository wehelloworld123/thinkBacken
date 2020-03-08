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
@Data
@TableName("t_pro_recom")
public class Recommend extends BaseEntity implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "content")
    private String conent;

    @TableField(value = "content_id")
    private String contenId;

    @TableField(value = "kind")
    private int kind;
    @TableField(value = "likes")
    private int likes;
    @TableField(exist = false)
    private String creator;
    @TableField(exist = false)
    private String createAvat;
    @TableField(exist = false)
    private String total;
    @TableField(value = "favorer")
    @JsonIgnore
    private String favorer;
    public Recommend() {
        super();
    }

    public Recommend(String conent,RecomType kind,int likes, String contenId) {
        this.conent = conent;
        this.kind=kind.getValue();
        this.likes=likes;
        this.contenId = contenId;
    }
}

