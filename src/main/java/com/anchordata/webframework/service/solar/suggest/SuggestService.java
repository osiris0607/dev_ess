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
package com.anchordata.webframework.service.solar.suggest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("SuggestService")
public class SuggestService {
	
	@Autowired
	private SuggestDao faqDao;
	
	/**
	 * 등록
	 */
	@Transactional
	public int registration(SuggestVO vo) throws Exception {
		return faqDao.insertInfo(vo);
	}
	
	/**
	 * 수정
	 */
	@Transactional
	public int modification(SuggestVO vo) throws Exception {
		return faqDao.updateInfo(vo);
	}
	
	/**
	 * 삭제
	 */
	@Transactional
	public int withdrawal(SuggestVO vo) throws Exception {
		// 실제 데이터 삭제가 아니라 Flag만 바꾼다.
		return faqDao.deleteInfo(vo);
	}
	
	
	/**
	 * all List  
	 */
	@Transactional
	public List<SuggestVO> allList() throws Exception {
		return faqDao.selectAllList();
	}
	
	/**
	 * 사용여부
	 */
	@Transactional
	public int updateUseYN(SuggestVO vo) throws Exception {
		// 실제 데이터 삭제가 아니라 Flag만 바꾼다.
		return faqDao.updateUseYN(vo);
	}
	
	
	
}
