package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_pro_paint_part")
@Document(indexName="paint_part",type = "doc")
public class PaintingPart extends BaseEntity implements Serializable {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value = "subject")
    private String subject;
    @TableField(value = "introduce")
    private String introduce;//介绍
    @TableField(value = "pic")
    private String pic;//介绍
    @TableField(value = "requirement")
    private String requirement;//创作要求
    @TableField(value = "creators")
    private int creators;//创作人数
    @TableField(value = "number")
    private int number;
    @TableField(value = "part_type")
    private String partType;
    @TableField(value = "root_ord")
    private int rootOrder;
    @TableField(value = "ord")
    private int order;
    @TableField(value = "root")
    private int root;
    @TableField(value = "root_id")
    private Long rootId;
    @TableField(value = "paint_id")
    private String paintId;
    @TableField(value = "liter_id")
    private Long literId;//文章id
    @TableField(value = "poem_id")
    private Long poemId;//诗歌id
    @TableField(value = "is_lock")
    private int isLock;//上锁
    @TableField(value = "finish")
    private int finish;

    @TableField(exist = false)
    private String seter;
    @TableField(exist = false)
    private String paintName;
    @TableField(exist = false)
    PaintContent paintContent;
    @TableField(exist = false)
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
