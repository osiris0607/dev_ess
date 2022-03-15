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
package com.anchordata.webframework.service.notice;

import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class NoticeVO {

	private static final long serialVersionUID = 1L;
	
	private int notice_id;
	private String title;
	private String writer;
	private String reg_date;
	private String use_yn;
	private String explanation;
	private String type;
	private String hits;
	private String main_page_yn;
	
	@JsonIgnore
	private MultipartFile attach_file_1;
	private String attach_file_id_1;
	private String attach_file_name_1;
	
	@JsonIgnore
	private MultipartFile attach_file_2;
	private String attach_file_id_2;
	private String attach_file_name_2;
	
	private int total_count;
	private int result;
	
	
	public String getHits() {
		return hits;
	}
	public void setHits(String hits) {
		this.hits = hits;
	}
	public String getMain_page_yn() {
		return main_page_yn;
	}
	public void setMain_page_yn(String main_page_yn) {
		this.main_page_yn = main_page_yn;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public MultipartFile getAttach_file_1() {
		return attach_file_1;
	}
	public void setAttach_file_1(MultipartFile attach_file_1) {
		this.attach_file_1 = attach_file_1;
	}
	public String getAttach_file_id_1() {
		return attach_file_id_1;
	}
	public void setAttach_file_id_1(String attach_file_id_1) {
		this.attach_file_id_1 = attach_file_id_1;
	}
	public String getAttach_file_name_1() {
		return attach_file_name_1;
	}
	public void setAttach_file_name_1(String attach_file_name_1) {
		this.attach_file_name_1 = attach_file_name_1;
	}
	public MultipartFile getAttach_file_2() {
		return attach_file_2;
	}
	public void setAttach_file_2(MultipartFile attach_file_2) {
		this.attach_file_2 = attach_file_2;
	}
	public String getAttach_file_id_2() {
		return attach_file_id_2;
	}
	public void setAttach_file_id_2(String attach_file_id_2) {
		this.attach_file_id_2 = attach_file_id_2;
	}
	public String getAttach_file_name_2() {
		return attach_file_name_2;
	}
	public void setAttach_file_name_2(String attach_file_name_2) {
		this.attach_file_name_2 = attach_file_name_2;
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
