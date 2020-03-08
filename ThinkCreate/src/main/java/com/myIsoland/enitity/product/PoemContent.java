package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_pro_poem_content")
public class PoemContent extends BaseEntity implements Serializable {
    @TableId(value = "no")
    private String no;
    @TableField(value = "brand")
    private String brand;
    @TableField(value = "title")
    private String title;
    @TableField(value = "content")
    private String content;

    @TableField(value = "charp_id")
    private Long charpId;
    @TableField(value = "poetry_id")
    private String poetryId;
    @TableField(value = "likes")
    private int likes;
    @TableField(value = "recom_no")
    private int recomNo;

    @TableField(value = "poetry_name")
    private String poetryName;
    @TableField(value = "charp_name")
    private String charpName;
    @TableField(value = "sec_name")
    private String secName;
    @TableField(value = "adopt")
    private int adopt;
    @TableField(exist = false)
    private int islike;
    @TableField(value = "favorer")
    @JsonIgnore
    private String favorer;
    @TableField(exist = false)
    private int creators;
    List<Recommend> recommends;

    public PoemContent(){
        super();
    }

}
