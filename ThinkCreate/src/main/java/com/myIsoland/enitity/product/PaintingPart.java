package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_pro_paint_part")
public class PaintingPart extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String subject;

    private String introduce;//介绍

    private String requirement;//创作要求

    private int creators;//创作人数

    private int number;
    @TableField(value = "root_ord")
    private int rootOrder;
    @TableField(value = "ord")
    private int order;

    private int root;

    private Long rootId;

    private String paintId;

    private Long literId;//文章id

    private Long poemId;//诗歌id

    private int isLock;//上锁

    private int finish;

    PaintContent paintContent;

    List<PaintingPart> parts;

    public PaintingPart(){
        super();
    }

    public PaintingPart(String subject, String introduce, String requirement, int number, int order, int root,
                        Long rootId, String paintId, Long literId, Long poemId) {
        this.subject = subject;
        this.introduce = introduce;
        this.requirement = requirement;
        this.number = number;
        this.order = order;
        this.root = root;
        this.rootId = rootId;
        this.paintId = paintId;
        this.literId = literId;
        this.poemId = poemId;
    }
}
