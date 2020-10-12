package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;
import redis.clients.jedis.RedisPipeline;

import java.io.Serializable;
import java.util.Map;

@Data
@TableName("t_pro_user_adopt")
public class UserCreation extends BaseEntity implements Serializable {
    @TableId(value = "id")
    private Long id;
    @TableField(value = "pro_no")
    private String proNo;
    @TableField(value = "title")
    private String title;
    @TableField(value = "content")
    private String content;
    @TableField(value = "picture")
    private String picture;
    @TableField(value = "liter_text")
    private String literText;
    @TableField(value = "creation_id")
    private String creationId;
    @TableField(value = "creation_nm")
    private String creationNm;
    @TableField(value = "charp_id")
    private Long charpId;
    @TableField(value = "charp_nm")
    private String charpNm;
/*    @TableField(value = "set_id")
    private Long setId;
    @TableField(value = "part_id")
    private Long partId;*/
    @TableField(value = "content_id")
    private String contentId;
    @TableField(value = "typ")
    private int typ;//作品类型
    @TableField(value = "user_id")
    private String userId;//作者id
    @TableField(value = "nickname")
    private String nickname;//作者名
    @TableField(value = "avatar")
    private String avatar;//作者头像
    @TableField(value = "adopt")
    private int adopt;
    @TableField(exist = false)
    LiterContent literContent;
    @TableField(exist = false)
    PaintContent paintContent;
    @TableField(exist = false)
    PoemContent poemContent;
    @TableField(exist = false)
    Map userInfo;
    public UserCreation(){
        super();
    }
}
