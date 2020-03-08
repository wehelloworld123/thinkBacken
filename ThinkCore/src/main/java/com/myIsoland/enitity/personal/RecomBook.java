package com.myIsoland.enitity.personal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_per_recombook")
public class RecomBook extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    private String bookname;

    private String label;

    private String reason;

    private int source;

    private String rootId;


}
