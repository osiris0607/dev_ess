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

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TrainingVO {

	private static final long serialVersionUID = 1L;
	
	private String training_id = "";
	private String title = "";
	private String enforcement_agency = "";
	private String address = "";
	private String contact = "";
	private String home_page = "";
	private String link = "";
	private String reg_date = "";
	private String hits = "0";
	private String upload_file_id = "";
	private String upload_file_name = "";
	private byte[] upload_file_image;
	
	@JsonIgnore
	private MultipartFile attach_file;
	private String attach_file_description = "";;
	
	
	private int total_count;
	private int result;
	
	
	
	public byte[] getUpload_file_image() {
		return upload_file_image;
	}
	public void setUpload_file_image(byte[] upload_file_image) {
		this.upload_file_image = upload_file_image;
	}
	public String getUpload_file_id() {
		return upload_file_id;
	}
	public void setUpload_file_id(String upload_file_id) {
		this.upload_file_id = upload_file_id;
	}
	public String getUpload_file_name() {
		return upload_file_name;
	}
	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}
	public MultipartFile getAttach_file() {
		return attach_file;
	}
	public void setAttach_file(MultipartFile attach_file) {
		this.attach_file = attach_file;
	}
	public String getAttach_file_description() {
		return attach_file_description;
	}
	public void setAttach_file_description(String attach_file_description) {
		this.attach_file_description = attach_file_description;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getHome_page() {
		return home_page;
	}
	public void setHome_page(String home_page) {
		this.home_page = home_page;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHits() {
		return hits;
	}
	public void setHits(String hits) {
		this.hits = hits;
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
