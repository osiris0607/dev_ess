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

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.anchordata.webframework.service.commonCode.CommonCodeVO;
import com.anchordata.webframework.service.solar.license.LicenseVO;
import com.fasterxml.jackson.annotation.JsonIgnore;



public class JobVO {

	
	private static final long serialVersionUID = 1L;
	
	private int job_id = 0;
	private String category_id = "";					// 기본정보 > 생태계 분류  (Common Code의 CATEGORY_ID)
	private String category_detail_id = "";				// 기본정보 > 생태계 세부 분류  (Common Code의 CATEGORY_DETAIL_ID)
	private String occupation = "";						// 기본정보 > 직종 분류  (Common Code 이용)
	private String name = "";							// 일자리정보 > 일자리명
	private String definition = "";						// 일자리정보 > 일자리 정의
	private String description = "";					// 일자리정보 > 직무 내용
	private String education = "";						// 직무자격 > 학력  (Common Code 이용)
	private String major = "";							// 직무자격 > 전공  (Common Code 이용 - 종복 허용)
	private String license_id = "";						// 직무자격 > 자격증 (중복 허용)
	private String career = "";							// 직무자격 > 경력
	private String difficulty = "";						// 직무자격 > 직무 난이도 (Common Code 이용)
	private String knowledge = "";						// 직무능력 > 지식
	private String skill = "";							// 직무능력 > 기술
	private String attitude = "";						// 직무능력 > 태도
	private int salary_level = 0;						// 근무여건 > 급여수준
	private String prospect = "";						// 일자리전망 > 일자리전망
	private String ranking = "";						// 일자리전망 > 유망 일자리 순위
	private String related_job = "";					// 기타 > 관련일자리
	private String related_occupation = ""; 			// 기타 > 관련직업(타산업)
	private String keco = "";							// 기타 > 한국고용직업분류
	private String company_1 = "";						// 해당기업 > 기업 1
	private String company_2 = "";						// 해당기업 > 기업 2
	private String company_3 = "";						// 해당기업 > 기업 3	
	private String future_career_1 = "";				// 향후진로 > 향후진로 1
	private String future_career_2 = "";				// 향후진로 > 향후진로 2
	private String future_career_3 = "";				// 향후진로 > 향후진로 3
	private String upload_file_id = "";					// 업로드 파일 ID 
	
	private String use_yn = "";
	private String reg_date = "";
	
	
	private List<String> major_list;
	private List<String> license_list;
	
	@JsonIgnore
	private MultipartFile attach_file;
	private String attach_file_description = "";
	

	private int total_count = 0;
	private int result;
	
	
	private String upload_file_name = "";
	private byte[] upload_file_image;
	private String category_name = "";
	private String category_detail_name = "";
	private String occupation_name = "";
	private String education_name = "";
	private String difficulty_name = "";
	private String company_1_name = "";
	private byte[] company_1_logo;
	private String company_1_size = "";
	private String company_1_address = "";
	private String company_1_main_product = "";
	private String company_2_name = "";
	private byte[] company_2_logo;
	private String company_2_size = "";
	private String company_2_address = "";
	private String company_2_main_product = "";
	private String company_3_name = "";
	private byte[] company_3_logo;
	private String company_3_size = "";
	private String company_3_address = "";
	private String company_3_main_product = "";
	private String future_career_1_name = "";
	private String future_career_2_name = "";
	private String future_career_3_name = "";
	
	
	List<CommonCodeVO> return_major_list;
	List<LicenseVO> return_license_list;
	
	
	
	public byte[] getUpload_file_image() {
		return upload_file_image;
	}
	public void setUpload_file_image(byte[] upload_file_image) {
		this.upload_file_image = upload_file_image;
	}
	public String getCompany_1_size() {
		return company_1_size;
	}
	public void setCompany_1_size(String company_1_size) {
		this.company_1_size = company_1_size;
	}
	public String getCompany_1_address() {
		return company_1_address;
	}
	public void setCompany_1_address(String company_1_address) {
		this.company_1_address = company_1_address;
	}
	public String getCompany_1_main_product() {
		return company_1_main_product;
	}
	public void setCompany_1_main_product(String company_1_main_product) {
		this.company_1_main_product = company_1_main_product;
	}
	public String getCompany_2_size() {
		return company_2_size;
	}
	public void setCompany_2_size(String company_2_size) {
		this.company_2_size = company_2_size;
	}
	public String getCompany_2_address() {
		return company_2_address;
	}
	public void setCompany_2_address(String company_2_address) {
		this.company_2_address = company_2_address;
	}
	public String getCompany_2_main_product() {
		return company_2_main_product;
	}
	public void setCompany_2_main_product(String company_2_main_product) {
		this.company_2_main_product = company_2_main_product;
	}
	public String getCompany_3_size() {
		return company_3_size;
	}
	public void setCompany_3_size(String company_3_size) {
		this.company_3_size = company_3_size;
	}
	public String getCompany_3_address() {
		return company_3_address;
	}
	public void setCompany_3_address(String company_3_address) {
		this.company_3_address = company_3_address;
	}
	public String getCompany_3_main_product() {
		return company_3_main_product;
	}
	public void setCompany_3_main_product(String company_3_main_product) {
		this.company_3_main_product = company_3_main_product;
	}
	public byte[] getCompany_1_logo() {
		return company_1_logo;
	}
	public void setCompany_1_logo(byte[] company_1_logo) {
		this.company_1_logo = company_1_logo;
	}
	public byte[] getCompany_2_logo() {
		return company_2_logo;
	}
	public void setCompany_2_logo(byte[] company_2_logo) {
		this.company_2_logo = company_2_logo;
	}
	public byte[] getCompany_3_logo() {
		return company_3_logo;
	}
	public void setCompany_3_logo(byte[] company_3_logo) {
		this.company_3_logo = company_3_logo;
	}
	public String getAttach_file_description() {
		return attach_file_description;
	}
	public void setAttach_file_description(String attach_file_description) {
		this.attach_file_description = attach_file_description;
	}
	public String getUpload_file_name() {
		return upload_file_name;
	}
	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}
	public String getCompany_1_name() {
		return company_1_name;
	}
	public void setCompany_1_name(String company_1_name) {
		this.company_1_name = company_1_name;
	}
	public String getCompany_2_name() {
		return company_2_name;
	}
	public void setCompany_2_name(String company_2_name) {
		this.company_2_name = company_2_name;
	}
	public String getCompany_3_name() {
		return company_3_name;
	}
	public void setCompany_3_name(String company_3_name) {
		this.company_3_name = company_3_name;
	}
	public String getFuture_career_1_name() {
		return future_career_1_name;
	}
	public void setFuture_career_1_name(String future_career_1_name) {
		this.future_career_1_name = future_career_1_name;
	}
	public String getFuture_career_2_name() {
		return future_career_2_name;
	}
	public void setFuture_career_2_name(String future_career_2_name) {
		this.future_career_2_name = future_career_2_name;
	}
	public String getFuture_career_3_name() {
		return future_career_3_name;
	}
	public void setFuture_career_3_name(String future_career_3_name) {
		this.future_career_3_name = future_career_3_name;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public String getCategory_detail_name() {
		return category_detail_name;
	}
	public void setCategory_detail_name(String category_detail_name) {
		this.category_detail_name = category_detail_name;
	}
	public String getOccupation_name() {
		return occupation_name;
	}
	public void setOccupation_name(String occupation_name) {
		this.occupation_name = occupation_name;
	}
	public String getEducation_name() {
		return education_name;
	}
	public void setEducation_name(String education_name) {
		this.education_name = education_name;
	}
	public String getDifficulty_name() {
		return difficulty_name;
	}
	public void setDifficulty_name(String difficulty_name) {
		this.difficulty_name = difficulty_name;
	}
	public List<String> getMajor_list() {
		return major_list;
	}
	public void setMajor_list(List<String> major_list) {
		this.major_list = major_list;
	}
	public List<String> getLicense_list() {
		return license_list;
	}
	public void setLicense_list(List<String> license_list) {
		this.license_list = license_list;
	}
	public List<CommonCodeVO> getReturn_major_list() {
		return return_major_list;
	}
	public void setReturn_major_list(List<CommonCodeVO> return_major_list) {
		this.return_major_list = return_major_list;
	}
	public List<LicenseVO> getReturn_license_list() {
		return return_license_list;
	}
	public void setReturn_license_list(List<LicenseVO> return_license_list) {
		this.return_license_list = return_license_list;
	}
	public MultipartFile getAttach_file() {
		return attach_file;
	}
	public void setAttach_file(MultipartFile attach_file) {
		this.attach_file = attach_file;
	}
	public String getLicense_id() {
		return license_id;
	}
	public void setLicense_id(String license_id) {
		this.license_id = license_id;
	}
	public String getUpload_file_id() {
		return upload_file_id;
	}
	public void setUpload_file_id(String upload_file_id) {
		this.upload_file_id = upload_file_id;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public int getJob_id() {
		return job_id;
	}
	public void setJob_id(int job_id) {
		this.job_id = job_id;
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
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDefinition() {
		return definition;
	}
	public void setDefinition(String definition) {
		this.definition = definition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getAttitude() {
		return attitude;
	}
	public void setAttitude(String attitude) {
		this.attitude = attitude;
	}
	public int getSalary_level() {
		return salary_level;
	}
	public void setSalary_level(int salary_level) {
		this.salary_level = salary_level;
	}
	public String getProspect() {
		return prospect;
	}
	public void setProspect(String prospect) {
		this.prospect = prospect;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getRelated_job() {
		return related_job;
	}
	public void setRelated_job(String related_job) {
		this.related_job = related_job;
	}
	public String getRelated_occupation() {
		return related_occupation;
	}
	public void setRelated_occupation(String related_occupation) {
		this.related_occupation = related_occupation;
	}
	public String getKeco() {
		return keco;
	}
	public void setKeco(String keco) {
		this.keco = keco;
	}
	public String getCompany_1() {
		return company_1;
	}
	public void setCompany_1(String company_1) {
		this.company_1 = company_1;
	}
	public String getCompany_2() {
		return company_2;
	}
	public void setCompany_2(String company_2) {
		this.company_2 = company_2;
	}
	public String getCompany_3() {
		return company_3;
	}
	public void setCompany_3(String company_3) {
		this.company_3 = company_3;
	}
	public String getFuture_career_1() {
		return future_career_1;
	}
	public void setFuture_career_1(String future_career_1) {
		this.future_career_1 = future_career_1;
	}
	public String getFuture_career_2() {
		return future_career_2;
	}
	public void setFuture_career_2(String future_career_2) {
		this.future_career_2 = future_career_2;
	}
	public String getFuture_career_3() {
		return future_career_3;
	}
	public void setFuture_career_3(String future_career_3) {
		this.future_career_3 = future_career_3;
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
