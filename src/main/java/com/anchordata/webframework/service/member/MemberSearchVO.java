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


public class MemberSearchVO {

	private static final long serialVersionUID = 1L;
	
	// 번역가 소개에서 검색시에 사용
	private String content;

	
	// 번역가 소개 / Admin 회원 리스트 에서 검색시에 사용
	private String name;
	
	// Admin 회원 리스트 에서 검색시에 사용
	private String member_id;

	// paging List Index
	private String pageIndex;
	
	// Admin Page와 번역가 소개 Page 구분자.
	// 'P' 이면 Admin, 'N'이면 번역가 소개
	private String member_type;
	
	// Paging List Order by
	private String orderby;
	
	

	
	
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	public String getMember_type() {
		return member_type;
	}
	public void setMember_type(String member_type) {
		this.member_type = member_type;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
