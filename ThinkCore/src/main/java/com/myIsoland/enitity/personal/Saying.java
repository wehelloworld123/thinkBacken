package com.myIsoland.enitity.personal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_per_saying")
public class Saying extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    private String content;

    private String author;

    private String product;

    private int source;

    private String rootId;

}
