package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;
import redis.clients.jedis.RedisPipeline;

import java.io.Serializable;
@Data
@TableName("t_pro_user_adopt")
public class UserCreation extends BaseEntity implements Serializable {
    @TableId(value = "id")
    private Long id;
    @TableField(value = "creation_id")
    private String creationId;
    @TableField(value = "charp_id")
    private Long charpId;
    @TableField(value = "content_id")
    private String contentId;
    @TableField(value = "typ")
    private int typ;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "avatar")
    private String avatar;
    @TableField(value = "adopt")
    private int adopt;
    @TableField(exist = false)
    LiterContent literContent;
    @TableField(exist = false)
    PaintContent paintContent;
    @TableField(exist = false)
    PoemContent poemContent;

    public UserCreation(){
        super();
    }
}
