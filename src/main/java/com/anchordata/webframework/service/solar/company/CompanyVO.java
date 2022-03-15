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

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CompanyVO {

	private static final long serialVersionUID = 1L;
	
	private int company_id;
	private String size = "";
	private String region = "";
	private String region_detail = "";
	private String ksic = "";
	private String name = "";
	private String address = "";
	private String phone = "";
	private String home_page = "";
	private String staff_number = "";
	private String main_product = "";
	private String associated_job_1 = "";
	private String associated_job_2 = "";
	private String associated_job_3 = "";
	private String upload_file_id = "";
	private String use_yn = "";
	private String reg_date;
	
	@JsonIgnore
	private MultipartFile attach_file;
	private String attach_file_description = "";;
	

	private int total_count;
	private int result;
	
	
	private String size_name;
	private String region_count;
	private String region_name;
	private String region_detail_name;
	private String ksic_name;
	private String associated_job_1_name;
	private String associated_job_1_definition;
	private String associated_job_2_name;
	private String associated_job_2_definition;
	private String associated_job_3_name;
	private String associated_job_3_definition;
	private String upload_file_name;
	
	
	public String getAssociated_job_1_definition() {
		return associated_job_1_definition;
	}
	public void setAssociated_job_1_definition(String associated_job_1_definition) {
		this.associated_job_1_definition = associated_job_1_definition;
	}
	public String getAssociated_job_2_definition() {
		return associated_job_2_definition;
	}
	public void setAssociated_job_2_definition(String associated_job_2_definition) {
		this.associated_job_2_definition = associated_job_2_definition;
	}
	public String getAssociated_job_3_definition() {
		return associated_job_3_definition;
	}
	public void setAssociated_job_3_definition(String associated_job_3_definition) {
		this.associated_job_3_definition = associated_job_3_definition;
	}
	public String getRegion_count() {
		return region_count;
	}
	public void setRegion_count(String region_count) {
		this.region_count = region_count;
	}
	public String getAttach_file_description() {
		return attach_file_description;
	}
	public void setAttach_file_description(String attach_file_description) {
		this.attach_file_description = attach_file_description;
	}
	public String getSize_name() {
		return size_name;
	}
	public void setSize_name(String size_name) {
		this.size_name = size_name;
	}
	public String getRegion_name() {
		return region_name;
	}
	public void setRegion_name(String region_name) {
		this.region_name = region_name;
	}
	public String getRegion_detail_name() {
		return region_detail_name;
	}
	public void setRegion_detail_name(String region_detail_name) {
		this.region_detail_name = region_detail_name;
	}
	public String getKsic_name() {
		return ksic_name;
	}
	public void setKsic_name(String ksic_name) {
		this.ksic_name = ksic_name;
	}
	public String getAssociated_job_1_name() {
		return associated_job_1_name;
	}
	public void setAssociated_job_1_name(String associated_job_1_name) {
		this.associated_job_1_name = associated_job_1_name;
	}
	public String getAssociated_job_2_name() {
		return associated_job_2_name;
	}
	public void setAssociated_job_2_name(String associated_job_2_name) {
		this.associated_job_2_name = associated_job_2_name;
	}
	public String getAssociated_job_3_name() {
		return associated_job_3_name;
	}
	public void setAssociated_job_3_name(String associated_job_3_name) {
		this.associated_job_3_name = associated_job_3_name;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
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
	public String getKsic() {
		return ksic;
	}
	public void setKsic(String ksic) {
		this.ksic = ksic;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHome_page() {
		return home_page;
	}
	public void setHome_page(String home_page) {
		this.home_page = home_page;
	}
	public String getStaff_number() {
		return staff_number;
	}
	public void setStaff_number(String staff_number) {
		this.staff_number = staff_number;
	}
	public String getMain_product() {
		return main_product;
	}
	public void setMain_product(String main_product) {
		this.main_product = main_product;
	}
	public String getAssociated_job_1() {
		return associated_job_1;
	}
	public void setAssociated_job_1(String associated_job_1) {
		this.associated_job_1 = associated_job_1;
	}
	public String getAssociated_job_2() {
		return associated_job_2;
	}
	public void setAssociated_job_2(String associated_job_2) {
		this.associated_job_2 = associated_job_2;
	}
	public String getAssociated_job_3() {
		return associated_job_3;
	}
	public void setAssociated_job_3(String associated_job_3) {
		this.associated_job_3 = associated_job_3;
	}
	public String getUpload_file_id() {
		return upload_file_id;
	}
	public void setUpload_file_id(String upload_file_id) {
		this.upload_file_id = upload_file_id;
	}
	public MultipartFile getAttach_file() {
		return attach_file;
	}
	public void setAttach_file(MultipartFile attach_file) {
		this.attach_file = attach_file;
	}
	public String getUpload_file_name() {
		return upload_file_name;
	}
	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
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
