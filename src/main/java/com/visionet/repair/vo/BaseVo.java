package com.visionet.repair.vo;

import com.visionet.repair.common.utils.PageInfo;

public class BaseVo {

	protected String id;
	//分页信息
	private PageInfo pageInfo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
	
}
