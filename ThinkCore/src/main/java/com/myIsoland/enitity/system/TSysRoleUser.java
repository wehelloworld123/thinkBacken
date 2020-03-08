package com.myIsoland.enitity.system;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("t_sys_role_user")
public class TSysRoleUser implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;

    private String sysUserId;

    private String sysRoleId;

    private static final long serialVersionUID = 1L;


	public TSysRoleUser() {
		super();
	}

	public TSysRoleUser(String id, String sysUserId, String sysRoleId) {
		super();
		this.id = id;
		this.sysUserId = sysUserId;
		this.sysRoleId = sysRoleId;
	}
    
    
}