package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.NoticeType;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_com_notice")
public class Notice extends BaseEntity implements Serializable{

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String  title;

    private String content;

    private String link;
    @TableField(value = "typ")
    private int type;
    @TableField(value = "dat")
    private String date;

    private String creator;

    public Notice() {
        super();
    }

    public Notice(String title, String content, NoticeType type, String creator) {
        this.title = title;
        this.content = content;
        this.type = type.getValue();
        this.creator = creator;
    }
}
