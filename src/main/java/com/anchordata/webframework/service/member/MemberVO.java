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
package com.anchordata.webframework.service.member;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@ToString
public class MemberVO {

	private static final long serialVersionUID = 1L;

	private int member_seq;
	private String member_id = "";
	private String pwd = "";
	private String birth = "";
	private String gender = "";
	private String name = "";
	private String email = "";
	private String phone = "";

	private String auth_level = "";
	private String recv_mail_yn = "";
	private String recv_msg_yn = "";
	private String reg_terms_yn = "";
	private String private_info_yn = "";
	private String use_yn = "";
	private String admin_use_yn = "";
	private String payment_yn = "";
	private String login_date = "";
	private String reg_date = "";
	
	private String point = "";
	private int total_count;
	
	private String current_pwd = "";
	private String new_pwd = "";
	
	
	
	public String getCurrent_pwd() {
		return current_pwd;
	}
	public void setCurrent_pwd(String current_pwd) {
		this.current_pwd = current_pwd;
	}
	public String getPayment_yn() {
		return payment_yn;
	}
	public void setPayment_yn(String payment_yn) {
		this.payment_yn = payment_yn;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getRecv_mail_yn() {
		return recv_mail_yn;
	}
	public void setRecv_mail_yn(String recv_mail_yn) {
		this.recv_mail_yn = recv_mail_yn;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getNew_pwd() {
		return new_pwd;
	}
	public void setNew_pwd(String new_pwd) {
		this.new_pwd = new_pwd;
	}
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public int getMember_seq() {
		return member_seq;
	}
	public void setMember_seq(int member_seq) {
		this.member_seq = member_seq;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAuth_level() {
		return auth_level;
	}
	public void setAuth_level(String auth_level) {
		this.auth_level = auth_level;
	}
	public String getRecv_msg_yn() {
		return recv_msg_yn;
	}
	public void setRecv_msg_yn(String recv_msg_yn) {
		this.recv_msg_yn = recv_msg_yn;
	}
	public String getReg_terms_yn() {
		return reg_terms_yn;
	}
	public void setReg_terms_yn(String reg_terms_yn) {
		this.reg_terms_yn = reg_terms_yn;
	}
	public String getPrivate_info_yn() {
		return private_info_yn;
	}
	public void setPrivate_info_yn(String private_info_yn) {
		this.private_info_yn = private_info_yn;
	}
	public String getAdmin_use_yn() {
		return admin_use_yn;
	}
	public void setAdmin_use_yn(String admin_use_yn) {
		this.admin_use_yn = admin_use_yn;
	}
	public String getLogin_date() {
		return login_date;
	}
	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
