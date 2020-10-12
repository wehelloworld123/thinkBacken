package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.model.ImageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@TableName("t_pro_paint_content")
public class PaintContent extends BaseEntity implements Serializable {
    @TableId(value = "no")
    private String no;
    @TableField(value = "title")
    private String title;
    @TableField(value = "content")
    private String content;
    @TableField(value = "image")
    private String image;
    @TableField(value = "image")
    private String image1;
    @TableField(value = "image")
    private String image2;
    @TableField(value = "image")
    private String image3;
    @TableField(value = "image")
    private String image4;
    @TableField(value = "part_id")
    private Long partId;
    @TableField(value = "paint_id")
    private String paintId;
    @TableField(value = "paint_name")
    private String paintName;
    @TableField(value = "sec_name")
    private String secName;
    @TableField(value = "part_name")
    private String partName;
    @TableField(value = "likes")
    private int likes;
    @TableField(value = "recom_no")
    private int recomNo;

//    private int view;//是否展示
    @TableField(value = "adopt")
    private int adopt;
    @TableField(exist = false)
    private Map map;
    @TableField(exist = false)
    Map userInfo;
    @TableField(exist = false)
    List<ImageInfo> imageList;
    @TableField(exist = false)
    private int islike;
    @TableField(value = "favorer")
    @JsonIgnore
    private String favorer;
    @TableField(exist = false)
    private int creators;

    List<Recommend> recommends;

    public PaintContent() {
        super();
    }

    public PaintContent(String no, String title, String image, Long partId, String paintId, String paintName,String secName, String partName) {
        this.no = no;
        this.title = title;
        this.image = image;
        this.partId = partId;
        this.paintId = paintId;
        this.paintName = paintName;
        this.secName = secName;
        this.partName = partName;
    }
}
