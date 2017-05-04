package com.visionet.repair.common.utils;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;


public class PageInfo {
	private int pageNumber;
	private int pageSize;
	private Direction sortType = Direction.DESC;
	private String sortTypeStr = "DESC";
	//默认根据id排序
	private String sortColumn = "id";
	
	private int totalCount;
	private PageRequest pageRequest ;
	
	private List<Order> orders;
	//是不是最后一页
	private boolean isLast;
	//是不是第一页
	private boolean isFirst;
	
//	private String pageType;			//for mobile: up/down (首次请求为"")
//	private Long pageTagId;				//for mobile: 分页所需标签id，即上次最末的业务数据id(首次请求为"")
	
	
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
	public PageInfo(){
        this.pageNumber = 1;
        this.pageSize   = 10;
	}
	public PageInfo(int pageNumber,int pageSize,Direction sortType){
		this.pageNumber = pageNumber;
		this.pageSize   = pageSize;
		this.sortType   = sortType;
	}
	public int getPageNumber() {
		return pageNumber==0?1:pageNumber;
	}
	public PageInfo setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
		return this;
	}
	public int getPageSize() {
		return pageSize==0?10:pageSize;
	}
	public PageInfo setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Direction getSortType() {
		return sortType;
	}
	public PageInfo setSortType(Direction sortType) {
		this.sortType = sortType;
		return this;
	}
	public String getSortColumn() {
		return sortColumn;
	}
	public PageInfo setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
		return this;
	}
	
	public PageRequest getPageRequestInfo(){
		if(Validator.isNotNull(sortColumn)){
			Sort sort = new Sort(sortType,sortColumn);
			pageRequest = new PageRequest(pageNumber -1 , pageSize, sort);
		}else{
			pageRequest = new PageRequest(pageNumber -1 , pageSize);
		}
		return pageRequest;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getSortTypeStr() {
		return sortTypeStr;
	}
	public void setSortTypeStr(String sortTypeStr) {
		this.sortTypeStr = sortTypeStr;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public int getCurrentPageNumber() {
		return (this.getPageNumber()-1) * this.getPageSize();
	}
	
	
}
