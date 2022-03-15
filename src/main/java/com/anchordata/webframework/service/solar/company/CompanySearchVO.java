/*******************************************************************************
 *
 * Copyright ⓒ 2019 namu C&D corp. All rights reserved.
 *
 * This is a proprietary software of namu C&D corp, and you may not use this file except in
 * compliance with license agreement with namu C&D corp. Any redistribution or use of this
 * software, with or without modification shall be strictly prohibited without prior written
 * approval of namu C&D corp, and the copyright notice above does not evidence any actual or
 * intended publication of such software.
 *
 *******************************************************************************/
package com.anchordata.webframework.service.solar.company;

public class CompanySearchVO {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String size;
	private String region;
	private String region_detail;
	private String category_id;
	private String category_detail_id;
	private String occupation;
	
	
	
	// paging List Index
	private String pageIndex;
	// Paging List Order by
	private String orderby;
	
	
	private int total_count;
	private int result;
	
	

	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getCategory_detail_id() {
		return category_detail_id;
	}
	public void setCategory_detail_id(String category_detail_id) {
		this.category_detail_id = category_detail_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegion_detail() {
		return region_detail;
	}
	public void setRegion_detail(String region_detail) {
		this.region_detail = region_detail;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
