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
package com.anchordata.webframework.service.solar.training;

public class TrainingSearchVO {

	private static final long serialVersionUID = 1L;
	
	private String training_id = "";
	private String title = "";
	private String enforcement_agency = "";
	private String reg_date = "";
	private String address = "";
	
	// paging List Index
	private String pageIndex;
	// Paging List Order by
	private String orderby;
	
	
	private int total_count;
	private int result;
	
	


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTraining_id() {
		return training_id;
	}
	public void setTraining_id(String training_id) {
		this.training_id = training_id;
	}
	public String getEnforcement_agency() {
		return enforcement_agency;
	}
	public void setEnforcement_agency(String enforcement_agency) {
		this.enforcement_agency = enforcement_agency;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
