package com.visionet.repair.entity.account;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.visionet.repair.entity.IdEntity;

@Entity
@Table(name = "c_user")
public class User extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7637524830779103305L;
	
	private String userName;
	private String userChName;
	private String password;
	private String phone;
	private String email;
	private String openId;
	private Integer userType;  //用户类型
	private Integer activity;
	private String roleId; //权限id
	private String orgId;	 //组织id
	
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserChName() {
		return userChName;
	}

	public void setUserChName(String userChName) {
		this.userChName = userChName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getActivity() {
		return activity;
	}

	public void setActivity(Integer activity) {
		this.activity = activity;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}


	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
