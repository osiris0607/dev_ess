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
package com.anchordata.webframework.service.solar.license;

public class LicenseVO {

	private static final long serialVersionUID = 1L;
	
	private int license_id;
	private String field;
	private String field_name;
	private String kor_name;
	private String eng_name;
	private String ministry;
	private String enforcement_agency;
	private String summary;
	private String change_process;
	private String performance_job;
	private String prospect;
	private String use_yn;
	private String reg_date;
	
	private int total_count;
	private int result;
	
	
	public String getField_name() {
		return field_name;
	}
	public void setField_name(String field_name) {
		this.field_name = field_name;
	}
	public int getLicense_id() {
		return license_id;
	}
	public void setLicense_id(int license_id) {
		this.license_id = license_id;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getKor_name() {
		return kor_name;
	}
	public void setKor_name(String kor_name) {
		this.kor_name = kor_name;
	}
	public String getEng_name() {
		return eng_name;
	}
	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}
	public String getMinistry() {
		return ministry;
	}
	public void setMinistry(String ministry) {
		this.ministry = ministry;
	}
	public String getEnforcement_agency() {
		return enforcement_agency;
	}
	public void setEnforcement_agency(String enforcement_agency) {
		this.enforcement_agency = enforcement_agency;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getChange_process() {
		return change_process;
	}
	public void setChange_process(String change_process) {
		this.change_process = change_process;
	}
	public String getPerformance_job() {
		return performance_job;
	}
	public void setPerformance_job(String performance_job) {
		this.performance_job = performance_job;
	}
	public String getProspect() {
		return prospect;
	}
	public void setProspect(String prospect) {
		this.prospect = prospect;
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
