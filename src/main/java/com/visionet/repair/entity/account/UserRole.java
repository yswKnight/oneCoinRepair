package com.visionet.repair.entity.account;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.visionet.repair.entity.IdEntity;


@Entity
@Table(name = "c_user_role")
public class UserRole extends IdEntity {
	
	private static final long serialVersionUID = 1509952747443671861L;

	private String userId;
	private String roleId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


}
