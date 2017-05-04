package com.visionet.repair.entity.account;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.visionet.repair.entity.IdEntity;


@Entity
@Table(name = "c_role_menu")
public class RoleMenu extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6848834159419743553L;
	
	private String menuId;
	private String roleId;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


}
