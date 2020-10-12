package com.myIsoland.common.base;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {
    @TableField(value = "create_by",fill = FieldFill.INSERT)//新增执行
    private String createBy;
    @TableField(value = "create_dat", fill = FieldFill.INSERT)
    private Date createDat;
//    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE) // 新增和更新执行
//    private String updateBy;

    @TableField(value = "l_update_dat", fill = FieldFill.INSERT_UPDATE)
    private Date lUpdateDat;

    @TableLogic
    @JsonIgnore
    @TableField(value = "is_del")
    private String isDel;
}
