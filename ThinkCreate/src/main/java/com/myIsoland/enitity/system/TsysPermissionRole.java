package com.myIsoland.enitity.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_sys_permission_role")
public class TsysPermissionRole implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    private String roleId;

    private String permissionId;

    private static final long serialVersionUID = 1L;


    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId == null ? null : permissionId.trim();
    }

	public TsysPermissionRole(String roleId, String permissionId) {
		super();

		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public TsysPermissionRole() {
		super();
	}
    
    
}