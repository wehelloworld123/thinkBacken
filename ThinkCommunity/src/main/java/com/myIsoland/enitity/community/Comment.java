package com.myIsoland.enitity.community;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import com.myIsoland.enums.SexType;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("comment")
public class Comment extends BaseEntity implements Serializable{

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String content;

    private Integer rootId;

    private Integer parentId;

    private String creator;

    private SexType creatorSex;

    private String creatorAvat;

    public Comment(String content, Integer rootId, Integer parentId, String creator, SexType creatorSex, String creatorAvat) {
        this.content = content;
        this.rootId = rootId;
        this.parentId = parentId;
        this.creator = creator;
        this.creatorSex = creatorSex;
        this.creatorAvat = creatorAvat;
    }

    public Comment() {
    }
}
