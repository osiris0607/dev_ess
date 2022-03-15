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
package com.anchordata.webframework.service.solar.license;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.solar.job.JobVO;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingVO;

@Service("LicenseService")
public class LicenseService {
	
	@Autowired
	private LicenseDao licenseDao;
	
	
	/**
	 * 등록
	 */
	@Transactional
	public int registration(LicenseVO vo) throws Exception {
		return licenseDao.insertInfo(vo);
	}
	
	/**
	 * 수정
	 */
	@Transactional
	public int modification(LicenseVO vo) throws Exception {
		return licenseDao.updateInfo(vo);
	}
	
	/**
	 * 삭제
	 */
	@Transactional
	public int withdrawal(LicenseVO vo) throws Exception {
		// 실제 데이터 삭제가 아니라 Flag만 바꾼다.
		return licenseDao.deleteInfo(vo);
	}
	
	/**
	 * 상세
	 */
	@Transactional
	public LicenseVO detail(LicenseVO vo) throws Exception {
		LicenseVO result = licenseDao.selectDetail(vo);
		return result;
	}
	
	/**
	 * 검색
	 */
	@Transactional
	public List<LicenseVO> searchList(LicenseSearchVO vo) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());
		
		if (  vo.getField() != null && vo.getField().isEmpty() != true ) {
			search.put("field", vo.getField());	
		}
		if (  vo.getName() != null && vo.getName().isEmpty() != true ) {
			search.put("name", vo.getName());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return licenseDao.selectSearchList(new DataMap(search));
	}
	
	
	/**
	 * Search List  
	 */
	@Transactional
	public List<LicenseVO> searchList(LicenseSearchVO vo, int pageRow) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());
		
		if (  vo.getField() != null && vo.getField().isEmpty() != true ) {
			search.put("field", vo.getField());	
		}
		if (  vo.getName() != null && vo.getName().isEmpty() != true ) {
			search.put("name", vo.getName());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return licenseDao.selectSearchList(new DataMap(search), pageRow);
	}	
	
	
	/**
	 * Search List  All
	 */
	@Transactional
	public List<LicenseVO> searchAllList(LicenseSearchVO vo) throws Exception {
		return licenseDao.selectSearchAllList(vo);
	}
	
	
	/**
	 * all List  
	 */
	@Transactional
	public List<LicenseVO> allList() throws Exception {
		return licenseDao.selectAllList();
	}
	
	
	/**
	 * all List  
	 */
	@Transactional
	public List<LicenseVO> relativeJobList(JobVO vo) throws Exception {
		return licenseDao.selectRelativeJobList(Integer.toString(vo.getJob_id()));
	}
	
	
	/**
	 * 검색된 실제 라이센스 갯수
	 * Main의 License 화면 설계가 License Type별로 실제 License Data를 표시하는 것이다.
	 * One Query로 하기 힘듬.  
	 */
	@Transactional
	public int countByDataRow(LicenseSearchVO vo) throws Exception {
		return licenseDao.countByDataRow(vo);
	}
	
	
	/**
	 * 검색된 실제 라이센스들의 Type 갯수
	 * 화공 / 전지 / 기계 등의 Type의 갯수이다.
	 * Main의 License 화면 설계가 License Type별로 실제 License Data를 표시하는 것이다.
	 * One Query로 하기 힘듬.  
	 */
	@Transactional
	public int countBylicenseType(LicenseSearchVO vo) throws Exception {
		return licenseDao.countBylicenseType(vo);
	}
	
	
	
}
