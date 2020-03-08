package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@TableName("t_pro_poemset")
public class PoemSet extends BaseEntity implements Serializable {

    private Long id;

    private String charpter;

    private String describe;

    private String requirement;//创作要求

    private int creators;//创作人数

    private int number;
    @TableField(value = "root_ord")
    private int rootOrder;
    @TableField(value = "ord")
    private int order;

    private int root;

    private Long rootId;

    private String poetryId;

    private Long literId;//文章id

    private Long paintId;//绘画id

    private int isLock;//上锁

    private int finish;

    PoemContent poemContent;

    List<PoemSet> sets;

    public PoemSet(){
        super();
    }

    public PoemSet(String charpter, String describe, int order, int root, Long rootId, String poetryId, Long literId, Long paintId) {
        this.charpter = charpter;
        this.describe = describe;
        this.order = order;
        this.root = root;
        this.rootId = rootId;
        this.poetryId = poetryId;
        this.literId = literId;
        this.paintId = paintId;
    }
}
