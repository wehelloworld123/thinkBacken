package com.myIsoland.enitity.system;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
@TableName("t_sys_role")
public class TsysRole implements Serializable {
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    private String name;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public TsysRole(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public TsysRole() {
		super();
	}
    
}