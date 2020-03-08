package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("t_pro_paint_content")
public class PaintContent extends BaseEntity implements Serializable {
    @TableId(value = "no")
    private String no;

    private String title;

    private String image;

    private Long partId;

    private String paintId;

    private String paintName;

    private String secName;

    private String partName;

    private int likes;

    private int recomNo;

//    private int view;//是否展示

    private int adopt;

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
