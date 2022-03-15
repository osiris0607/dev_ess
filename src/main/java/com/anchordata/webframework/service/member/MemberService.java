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


import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.base.util.DataMap;


@Service("MemberService")
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	
	/**
	 * Admin Page에서 검색  
	 */
	@Transactional
	public List<MemberVO> adminSearchList(MemberSearchVO vo) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		
		search.put("pageIndex", vo.getPageIndex());
		if (  vo.getName() != null && vo.getName().isEmpty() != true ) {
			search.put("name", vo.getName());	
		}
		if (  vo.getMember_id() != null && vo.getMember_id().isEmpty() != true ) {
			search.put("member_id", vo.getMember_id());	
		}
		if (  vo.getContent() != null && vo.getContent().isEmpty() != true ) {
			search.put("content", vo.getContent());	
		}
		if (  vo.getMember_type() != null && vo.getMember_type().isEmpty() != true ) {
			search.put("member_type", vo.getMember_type());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return memberDao.adminSearchList(new DataMap(search));
	}
	
	
	/**
	 * 사용여부
	 */
	@Transactional
	public int updateAdminUseYN(MemberVO vo) throws Exception {
		// 실제 데이터 삭제가 아니라 Flag만 바꾼다.
		return memberDao.updateAdminUseYN(vo);
	}
	
	
	/**
	 * 패스워드 초기화
	 */
	@Transactional
	public int initPassword(MemberVO vo) throws Exception {
		// Password Encrypt 
		String encodedPassword = new BCryptPasswordEncoder().encode("1234");
		vo.setPwd(encodedPassword);
		
		int result = memberDao.updateNewPwd(vo);
		return result;
	}
	
	
	
	/**
	 * 로그인 정보 찾기
	 */
	@Transactional
	public MemberVO findLoginInfo(String id) throws Exception {
		MemberVO result = memberDao.findLoginInfo(id);
		return result;
	}
	
	/**
	 * Barun 회원 가입
	 */
	@Transactional
	public int registration(MemberVO vo) throws Exception {
		
		MemberVO result = memberDao.findLoginInfo(vo.getMember_id());
		if (result != null)
		{
			System.out.println("이미 사용중인 아이디입니다. 다른 아이디를 선택해 주십시요.");
			return 404;
		}
		
		byte[] pwdBytes = Base64.getDecoder().decode(vo.getPwd());
		String decodeBase64Pwd = new String(pwdBytes);
		
		// Password Encrypt 
		String encodedPassword = new BCryptPasswordEncoder().encode(decodeBase64Pwd);
		vo.setPwd(encodedPassword);
				
		// Member Insert
		return memberDao.insertMemberInfo(vo);
	}
	
	/**
	 * Barun 회원 정보 수정
	 */
	@Transactional
	public int modification(MemberVO vo) throws Exception {
		// Member modification
		MemberVO result = memberDao.findLoginInfo(vo.getMember_id());
		if (result == null)
		{
			System.out.println("해당 아이디의 정보가 없습니다.");
			return -1;
		}
				
		return memberDao.updateMemberInfo(vo);
	}
	
	
	/**
	 * Barun 회원 탈퇴
	 * 실제 데이터 삭제가 아니라 Flag만 바꾼다.
	 */
	@Transactional
	public int withdrawal(MemberVO vo) throws Exception {
		// 실제 데이터 삭제가 아니라 Flag만 바꾼다.
		return memberDao.deleteMemberInfo(vo);
	}
	
	/**
	 * Barun 아이디 찾기
	 */
	@Transactional
	public MemberVO searchID(MemberVO vo) throws Exception {
		MemberVO result = memberDao.findID(vo);
		return result;
	}
	
	/**
	 * 회원 차단
	 */
	public int delete(List<String> param) throws Exception {
		
		if (param.size() <= 0) {
			System.out.println("MemberService::block] 차단할 회원 아이디 없음");
			return 0;
		}
		else {
			for (int i=0; i<param.size(); i++) {
				memberDao.deleteMember(param.get(i));	
			}
		}
		return 1;
	}
	
	/**
	 * 로그인 정보 기록
	 */
	public int updateLoginTime(String memberId) throws Exception {
		return memberDao.updateLoginTime(memberId);	
	}
	
	/**
	 * 회원 수 정보
	 * 
	 */
	public HashMap<String, Object> status() throws Exception {
		return memberDao.selectMemberCount();
	}
	
	
	/**
	 * 
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	public List<MemberVO> pagingList(DataMap param) throws Exception {
		return memberDao.selectPagingList(param);
	}
	
	/**
	 * 회원 Detail List  
	 */
	@Transactional
	public MemberVO detailView(MemberVO vo) throws Exception {
		return memberDao.selectDetailView(vo);
	}
	
	
	
	/**
	 * 회원 all List  
	 */
	@Transactional
	public List<MemberVO> allList() throws Exception {
		return memberDao.selectAllList();
	}
	
	
	/**
	 * 해당 계정이 있는지 조회
	 */
	@Transactional
	public MemberVO checkMemberExist(MemberVO vo) throws Exception {
		// Member Insert
		
		MemberVO result = memberDao.checkMemberExist(vo);
		return result;
	}
	
	
	/**
	 * 해당 계정/Password가 있는지 조회
	 */
	@Transactional
	public boolean checkMemberPwd(MemberVO vo) throws Exception {
		
		byte[] pwdBytes = Base64.getDecoder().decode(vo.getCurrent_pwd());
		String decodeBase64Pwd = new String(pwdBytes);
		vo.setCurrent_pwd(decodeBase64Pwd);
		
		MemberVO result = memberDao.selectMemberPwd(vo);
		if (result == null) {
			return false;
		}
		return new BCryptPasswordEncoder().matches(vo.getCurrent_pwd(), result.getPwd());
	}
	
	
	/**
	 * 해당 계정에 새로운 Pwd 생성
	 */
	@Transactional
	public int changePwd(MemberVO vo) throws Exception {
		// Password Encrypt 
		
		byte[] pwdBytes = Base64.getDecoder().decode(vo.getNew_pwd());
		String decodeBase64Pwd = new String(pwdBytes);
		
		// Password Encrypt 
		String encodedPassword = new BCryptPasswordEncoder().encode(decodeBase64Pwd);
		vo.setPwd(encodedPassword);
		
		return memberDao.updateNewPwd(vo);
	}
	
	
	/**
	 * Barun 해당 계정에 새로운 Pwd 생성
	 * author : PJS
	 * Date : 2020-07-13
	 */
	@Transactional
	public int createNewPwd(MemberVO vo) throws Exception {
		// 새로운 PWD Random 생성
		Random rnd =new Random();
		StringBuffer pwd =new StringBuffer();
		for(int i=0;i<10;i++){
		    // rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
		    if(rnd.nextBoolean()){
		    	pwd.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		    	pwd.append((rnd.nextInt(10)));
		    }
		}
		
		// Password Encrypt 
		String encodedPassword = new BCryptPasswordEncoder().encode(pwd.toString());
		vo.setPwd(encodedPassword);
		
		
		int result = memberDao.updateNewPwd(vo);
		// 성공인 경우 메일로 해당 암호 정보를 전달한다.
		if ( result > 0 ) {
			String mailSubject = "[바른 미디어] 변경된 패스워드 입니다.";
			String mailTo = vo.getEmail();
			String mailContent = "";
			mailContent += "<p>변경 패스워드 : " + pwd.toString() + "</p>";
			
//			try {
//				if ( ContactNaverSender.sender(mailSubject, mailTo, mailContent).compareToIgnoreCase("success") != 0 ) {
//					result = -1;
//				}
//			} catch (Exception e) {
//				result = -1;
//			}
		}
		
		return result;
	}
	
		
}
