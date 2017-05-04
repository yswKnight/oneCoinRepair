package com.visionet.repair.entity.account;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.visionet.repair.entity.IdEntity;


/************************************8
 * 
 * @author 王永圣
 *	@param 组织实体类（Organization）
 */
@Entity
@Table(name="c_org")
public class Org extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//组织名称
	private String orgName;
	//地址
	private String orgAddress;
	
	

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	
	

}
