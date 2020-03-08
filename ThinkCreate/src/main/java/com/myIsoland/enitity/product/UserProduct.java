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

    private String creationId;

    private String userId;
    @TableField(value = "typ")
    private int type;//作品类型

    private int kind;//收录类型

    private int status;

    Literature literature;

    Painting painting;

    Poetry poetry;

    public UserProduct(){
        super();
    }
}
