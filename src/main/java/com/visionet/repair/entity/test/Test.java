package com.visionet.repair.entity.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "c_role")
public class Test {

	private String id;
	private String sheetName;
	private String colorName;
	private String titleName;
	private String clo1;
	private String clo2;
	private String clo3;
	
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getClo1() {
		return clo1;
	}

	public void setClo1(String clo1) {
		this.clo1 = clo1;
	}

	public String getClo2() {
		return clo2;
	}

	public void setClo2(String clo2) {
		this.clo2 = clo2;
	}

	public String getClo3() {
		return clo3;
	}

	public void setClo3(String clo3) {
		this.clo3 = clo3;
	}
}
