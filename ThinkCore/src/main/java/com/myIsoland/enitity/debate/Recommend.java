package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_deb_recommend")
public class Recommend extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String content;

    private String creator;

    private String parentId;

}
