package com.myIsoland.enitity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enitity.system.TsysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@TableName("t_pro_content")
public class LiterContent extends BaseEntity implements Serializable {

    @TableId(value = "no")
    private String no;
    @TableField(value = "title")
    private String title;
    @TableField(value = "summary")
    private String summary;
    @TableField(value = "content")
    private String content;
    @TableField(value = "file")
    private String file;
    @TableField(value = "charp_id")
    private Long charpId;
    @TableField(value = "book_id")
    private String bookId;
    @TableField(value = "likes")
    private int likes;
    @TableField(value = "recom_no")
    private int recomNo;
    @TableField(value = "view")
    private int view;//是否展示
    @TableField(value = "adopt")
    private int adopt;
    @TableField(value = "charp_name")
    private String charpName;
    @TableField(value = "sec_name")
    private String secName;
    @TableField(value = "creator")
    private String creator;
    @TableField(exist = false)
    private String bookName;
    @TableField(exist = false)
    private int islike;
    @TableField(value = "favorer")
    @JsonIgnore
    private String favorer;
    @TableField(exist = false)
    private int creators;
    @TableField(exist = false)
    Map userInfo;
    @TableField(exist = false)
    List<Recommend> recommends;

    public LiterContent() {
        super();
    }
    public LiterContent(String no,int likes,String favorer) {
       this.no = no;
       this.likes = likes;
       this.favorer =favorer;
    }
    public LiterContent(String title,String summary,String content, String file, Long charpId, int view) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.file = file;
        this.charpId = charpId;
        this.view = view;
    }
}
