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
package com.anchordata.webframework.service.solar.job;

public class JobRelativeVO {

	private static final long serialVersionUID = 1L;
	
	private int relative_id;
	private String relative_master_id;
	private String relative_detail_id;
	private String job_id;
	
	private int total_count;
	private int result;
	
	

	public int getRelative_id() {
		return relative_id;
	}
	public void setRelative_id(int relative_id) {
		this.relative_id = relative_id;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getRelative_master_id() {
		return relative_master_id;
	}
	public void setRelative_master_id(String relative_master_id) {
		this.relative_master_id = relative_master_id;
	}
	public String getRelative_detail_id() {
		return relative_detail_id;
	}
	public void setRelative_detail_id(String relative_detail_id) {
		this.relative_detail_id = relative_detail_id;
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
