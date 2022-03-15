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
package com.anchordata.webframework.service.solar.trend;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TrendVO {

	private static final long serialVersionUID = 1L;
	
	private String trend_id = "";
	private String title = "";
	private String writer = "";
	private String explanation = "";
	private String upload_file_id = "";
	private String use_yn = "";
	private String reg_date = "";
	private String hits = "0";
	
		
	
	@JsonIgnore
	private MultipartFile attach_file;
	private String upload_file_name = "";
	
	private int total_count;
	private int result;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUpload_file_name() {
		return upload_file_name;
	}
	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}
	public String getHits() {
		return hits;
	}
	public void setHits(String hits) {
		this.hits = hits;
	}
	public MultipartFile getAttach_file() {
		return attach_file;
	}
	public void setAttach_file(MultipartFile attach_file) {
		this.attach_file = attach_file;
	}
	public String getTrend_id() {
		return trend_id;
	}
	public void setTrend_id(String trend_id) {
		this.trend_id = trend_id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public String getUpload_file_id() {
		return upload_file_id;
	}
	public void setUpload_file_id(String upload_file_id) {
		this.upload_file_id = upload_file_id;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
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
