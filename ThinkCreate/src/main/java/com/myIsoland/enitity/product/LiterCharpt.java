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
@TableName("t_pro_charpt")
@Document(indexName="liter_chapt",type = "doc")
public class LiterCharpt extends BaseEntity implements Serializable {
    @Id
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    @TableField(value = "title")
    private String title;
    @TableField(value = "introduce")
    private String introduce;//前言
    @TableField(value = "requirement")
    private String requirement;//创作要求
    @TableField(value = "number")
    private int number;
    @TableField(value = "creators")
    private int creators;//创作人数
    @TableField(value = "root")
    private int root;
    @TableField(value = "book_id")
    private String bookId;
    @TableField(value = "root_id")
    private Long rootId;//章节id
    @TableField(value = "paint_id")
    private Long paintId;//绘画id
    @TableField(value = "poem_id")
    private Long poemId;//诗歌id
    @TableField(value = "root_ord")
    private int rootOrder;
    @TableField(value = "ord")
    private int order;
    @TableField(value = "is_lock")
    private int isLock;//上锁
    @TableField(value = "finish")
    private int finish;

    @TableField(value = "chap_type")
    private int chapType;//创作类型 同作品创作类型
    @TableField(exist = false)
    private String label;//标签类型 同作品创作标签
    @TableField(exist = false)
    private String bookName;//书籍名称 同作品
    @TableField(exist = false)
    private Long views;//浏览量
    @TableField(exist = false)
    LiterContent literContent;
    @TableField(exist = false)
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
