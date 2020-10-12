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
@TableName("t_pro_poemset")
@Document(indexName="poem_set",type = "doc")
public class PoemSet extends BaseEntity implements Serializable {
    @Id
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    @TableField(value = "charpter")
    private String charpter;
    @TableField(value = "description")
    private String description;
    @TableField(value = "requirement")
    private String requirement;//创作要求
    @TableField(value = "creators")
    private int creators;//创作人数
    @TableField(value = "pic")
    private String pic;
    @TableField(value = "number")
    private int number;
 /*   @TableField(value = "root_ord")
    private int rootOrder;*/
    @TableField(value = "ord")
    private int ord;
    @TableField(value = "root")
    private int root;
    @TableField(value = "root_id")
    private Long rootId;
    @TableField(value = "poetry_id")
    private String poetryId;
    @TableField(value = "liter_id")
    private Long literId;//文章id
    @TableField(value = "paint_id")
    private Long paintId;//绘画id
    @TableField(value = "is_lock")
    private int isLock;//上锁
    @TableField(value = "finish")
    private int finish;

    @TableField(exist = false)
    private Long views;//浏览量
    @TableField(exist = false)
    private String poetryName;//诗词名称

    @TableField(exist = false)
    PoemContent poemContent;
    @TableField(exist = false)
    List<PoemSet> sets;

    public PoemSet(){
        super();
    }

    public PoemSet(String charpter, String describe, int order, int root, Long rootId, String poetryId, Long literId, Long paintId) {
        this.charpter = charpter;
        this.description = describe;
        this.ord = order;
        this.root = root;
        this.rootId = rootId;
        this.poetryId = poetryId;
        this.literId = literId;
        this.paintId = paintId;
    }
}
