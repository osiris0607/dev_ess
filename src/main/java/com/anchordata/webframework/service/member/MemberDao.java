/*******************************************************************************
 *
 * Copyright �뱬 2019 namu C&D corp. All rights reserved.
 *
 * This is a proprietary software of namu C&D corp, and you may not use this file except in
 * compliance with license agreement with namu C&D corp. Any redistribution or use of this
 * software, with or without modification shall be strictly prohibited without prior written
 * approval of namu C&D corp, and the copyright notice above does not evidence any actual or
 * intended publication of such software.
 *
 *******************************************************************************/
package com.anchordata.webframework.service.member;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;
import com.anchordata.webframework.base.util.DataMap;


@Repository("MemberDao")
public class MemberDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("member.");
	
	
	public int updateAdminUseYN(MemberVO params) throws Exception{
		return update(mapper.concat("updateAdminUseYN"), params);
	}
	
	public MemberVO findLoginInfo(String param) {
		return selectOne(mapper.concat("findLoginInfo"), param);
	}
	
	/**
	 * 이메일 중복 체크
	 */
	public List<MemberVO> findEmail(String param) {
		return selectList(mapper.concat("findEmail"), param);
	}
	
	
	public int insertMemberInfo(MemberVO vo) throws Exception{
		return update(mapper.concat("insertMemberInfo"), vo);
	}
	
	
	/**
	 * PJS : 2020-03-21
	 * Login 
	 */
	public MemberVO findLoginInfo(MemberVO vo) throws Exception{
		return selectOne(mapper.concat("findLoginInfo"), vo);
	}
	
	/**
	 * PJS : 2020-03-21
	 * findID 
	 */
	public MemberVO findID(MemberVO vo) throws Exception{
		return selectOne(mapper.concat("findID"), vo);
	}
	
	
	/**
	 * PJS : 2020-03-21
	 * 회원 정보 업데이트 
	 */
	public int updateMemberInfo(MemberVO vo) throws Exception{
		return update(mapper.concat("updateMemberInfo"), vo);
	}
	
	/**
	 * PJS : 2020-03-21
	 * 회원 정보 삭제. USE Flag 만 'N'로 설정 
	 */
	public int deleteMemberInfo(MemberVO vo) throws Exception{
		return update(mapper.concat("deleteMemberInfo"), vo);
	}
	
	/**
	 * 회원 차단
	 */
	public int updateMemberBlock(String param) throws Exception{
		return update(mapper.concat("updateMemberBlock"), param);
	}
	
	/**
	 * 회원 삭제
	 */
	public int deleteMember(String param) throws Exception{
		return update(mapper.concat("deleteMember"), param);
	}
	
	/**
	 * 회원 수 정보
	 * author : PJS
	 * Date : 2020-04-01
	 */
	public HashMap<String, Object> selectMemberCount() throws Exception{
		return selectOne(mapper.concat("selectMemberCount"));
	}
	
	
	public List<MemberVO> selectPagingList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectPagingList"), param);
	}
	
	/**
	 * PJS : 2020-03-28
	 * 회원 상세
	 */
	public MemberVO selectDetailView(MemberVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetailView"), vo);
	}
	
	
	/**
	 * PJS : 2020-03-28
	 * 회원 검색
	 */
	public List<MemberVO> adminSearchList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectAdminSearchPagingList"), param);
	}
	
	
	/**
	 * PJS : 2020-03-28
	 * 회원 검색 All
	 */
	public List<MemberVO> selectAllList() throws Exception{
		return selectList(mapper.concat("selectAllList"));
	}
	
	
	
	/**
	 * PJS : 2020-07-13
	 * checkMemberExist 
	 */
	public MemberVO checkMemberExist(MemberVO params) throws Exception{
		return selectOne(mapper.concat("selectMemeberExist"), params);
	}
	
	/**
	 * PJS : 2020-07-13
	 * selectMemberPwd 
	 */
	public MemberVO selectMemberPwd(MemberVO params) throws Exception{
		return selectOne(mapper.concat("selectMemeberPwd"), params);
	}
	
	/**
	 * PJS : 2020-03-21
	 * New Password Insert
	 */
	public int updateNewPwd(MemberVO param) throws Exception{
		return update(mapper.concat("updateNewPwd"), param);
	}
	
	/**
	 * PJS : 2020-09-08
	 * Login Time update
	 */
	public int updateLoginTime(String param) throws Exception{
		return update(mapper.concat("updateLoginTime"), param);
	}
	
	
	/**
	 * PJS : 2020-09-08
	 * Payment YN update
	 */
	public int updatePaymentYN(MemberVO param) throws Exception{
		return update(mapper.concat("updatePayment"), param);
	}
	
	
	
	
	
	

}
