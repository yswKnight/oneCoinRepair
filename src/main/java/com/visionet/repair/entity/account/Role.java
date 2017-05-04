package com.visionet.repair.entity.account;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.visionet.repair.entity.IdEntity;

@Entity
@Table(name = "c_role")
public class Role extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5589900182744087781L;
	
	private String name;
	//private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



}
