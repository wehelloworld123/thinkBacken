package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_deb_recom")
public class Recommend extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value = "content")
    private String content;
    @TableField(value = "likes")
    private int likes;
    @TableField(value = "creator")
    private String creator;
    @TableField(value = "abs_id")
    private String ansId;

    public Recommend(){
        super();
    }
}
