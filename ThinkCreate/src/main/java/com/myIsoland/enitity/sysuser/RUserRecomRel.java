package com.myIsoland.enitity.sysuser;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.myIsoland.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName(value = "r_user_recom")
public class RUserRecomRel extends BaseEntity implements Serializable {
    @TableId(value = "id")
    private Long id;
    @TableField(value = "no")
    private String no;//创作编号
    @TableField(value = "kind")
    private int kind;//推荐类型
    @TableField(value = "userId")
    private String userId;
    @TableField(value = "status")
    private int status;//推荐状态
}
