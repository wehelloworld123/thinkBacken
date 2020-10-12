package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;
import redis.clients.jedis.RedisPipeline;

import java.io.Serializable;

@Data
@TableName("t_pro_user_product")
public class UserProduct extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value = "creation_id")
    private String creationId;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "typ")
    private int typ;//作品类型
    @TableField(value = "kind")
    private int kind;//收录类型
    @TableField(value = "status")
    private int status;
    @TableField(exist = false)
    Literature literature;
    @TableField(exist = false)
    Painting painting;
    @TableField(exist = false)
    Poetry poetry;
    @TableField(exist = false)
    int count;
    public UserProduct(){
        super();
    }
}
