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
@TableName("t_pro_charpt")
public class LiterCharpt extends BaseEntity implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String title;

    private String introduce;//前言

    private String requirement;//创作要求

    private int number;

    private int creators;//创作人数

    private int root;

    private String bookId;

    private Long rootId;//章节id

    private Long paintId;//绘画id

    private Long poemId;//诗歌id
    @TableField(value = "root_ord")
    private int rootOrder;
    @TableField(value = "ord")
    private int order;

    private int isLock;//上锁

    private int finish;

    LiterContent literContent;

    List<LiterCharpt> charpts;

    public LiterCharpt() {
        super();
    }

    public LiterCharpt(String title, int root, String bookId, Long rootId, int order) {
        this.title = title;
        this.root = root;
        this.bookId = bookId;
        this.rootId = rootId;
        this.order = order;
    }
}
