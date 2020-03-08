package com.myIsoland.enitity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_sys_user_pro")
public class TSysUserProduct extends BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_id")
    private String userId;
    @TableField(value = "username")
    private String username;
    @TableField(value = "kind")
    private int kind;
    @TableField(value = "status")
    private int status;
    @TableField(value = "pro_id")
    private String proId;
    @TableField(value = "store")
    private int store;
}
