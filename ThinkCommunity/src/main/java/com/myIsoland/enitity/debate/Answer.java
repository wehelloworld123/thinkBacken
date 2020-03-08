package com.myIsoland.enitity.debate;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_deb_answer")
public class Answer extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    private String content;


}
