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
package com.anchordata.webframework.service.solar.faq;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingVO;


@Service("FAQService")
public class FAQService {
	
	@Autowired
	private FAQDao faqDao;
	
	/**
	 * 등록
	 */
	@Transactional
	public int registration(FAQVO vo) throws Exception {
		return faqDao.insertInfo(vo);
	}
	
	/**
	 * 수정
	 */
	@Transactional
	public int modification(FAQVO vo) throws Exception {
		return faqDao.updateInfo(vo);
	}
	
	/**
	 * 삭제
	 */
	@Transactional
	public int withdrawal(FAQVO vo) throws Exception {
		// 실제 데이터 삭제가 아니라 Flag만 바꾼다.
		return faqDao.deleteInfo(vo);
	}
	
	/**
	 * 상세
	 */
	@Transactional
	public FAQVO detail(FAQVO vo) throws Exception {
		FAQVO result = faqDao.selectDetail(vo);
		return result;
	}
	
	/**
	 * 검색
	 */
	@Transactional
	public List<FAQVO> searchList(FAQSearchVO vo) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());
		
		if (  vo.getAnswer() != null && vo.getAnswer().isEmpty() != true ) {
			search.put("answer", vo.getAnswer());	
		}
		if (  vo.getQuestion() != null && vo.getQuestion().isEmpty() != true ) {
			search.put("question", vo.getQuestion());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return faqDao.selectSearchList(new DataMap(search));
	}
	
	/**
	 * Search List  All
	 */
	@Transactional
	public List<FAQVO> searchAllList(FAQSearchVO vo) throws Exception {
		return faqDao.selectSearchAllList(vo);
	}
	
	
	/**
	 * all List  
	 */
	@Transactional
	public List<FAQVO> allList() throws Exception {
		return faqDao.selectAllList();
	}
	
	
}
