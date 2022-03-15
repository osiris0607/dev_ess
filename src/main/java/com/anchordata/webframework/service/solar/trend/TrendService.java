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
package com.anchordata.webframework.service.solar.trend;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingVO;
import com.anchordata.webframework.service.uploadFile.UploadFileService;
import com.anchordata.webframework.service.uploadFile.UploadFileVO;


@Service("TrendService")
public class TrendService {
	
	@Autowired
	private TrendDao trendDao;
	@Autowired
	private UploadFileService uploadFileService;
	
	/**
	 * 등록
	 */
	@Transactional
	public int registration(TrendVO vo) throws Exception {
		// 첨부 파일 1 Binary로 DB 저장		
		if (vo.getAttach_file() != null && vo.getAttach_file().getSize() > 0 ) {
			// 나머지 정보는 Upload File Table에 저장
			String fileName = vo.getAttach_file().getOriginalFilename();
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setName(fileName);
			uploadFileVO.setBinary_content(vo.getAttach_file().getBytes());
			uploadFileService.registration(uploadFileVO);
			
			vo.setUpload_file_id(Integer.toString(uploadFileVO.getFile_id()));
		} else {
			vo.setUpload_file_id("");
		}
		return trendDao.insertInfo(vo);
	}
	
	/**
	 * 수정
	 */
	@Transactional
	public int modification(TrendVO vo) throws Exception {
		// 첨부 파일이 있으면 변경
		if (vo.getAttach_file() != null && vo.getAttach_file().getSize() > 0 ) {
			// 나머지 정보는 Upload File Table에 저장
			String fileName = vo.getAttach_file().getOriginalFilename();
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setName(fileName);
			uploadFileVO.setBinary_content(vo.getAttach_file().getBytes());
			uploadFileService.registration(uploadFileVO);
			
			vo.setUpload_file_id(Integer.toString(uploadFileVO.getFile_id()));
		} else {
			vo.setUpload_file_id("");
		}
		return trendDao.updateInfo(vo);
	}
	
	/**
	 * 삭제
	 */
	@Transactional
	public int withdrawal(TrendVO vo) throws Exception {
		// 실제 데이터 삭제가 아니라 Flag만 바꾼다.
		return trendDao.deleteInfo(vo);
	}
	
	/**
	 * 상세
	 */
	@Transactional
	public TrendVO detail(TrendVO vo) throws Exception {
		trendDao.updateHits(vo);
		return trendDao.selectDetail(vo);
	}
	
	/**
	 * 검색
	 */
	@Transactional
	public List<TrendVO> searchList(TrendSearchVO vo) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());
		
		if (  vo.getTitle() != null && vo.getTitle().isEmpty() != true ) {
			search.put("title", vo.getTitle());	
		}
		if (  vo.getWriter() != null && vo.getWriter().isEmpty() != true ) {
			search.put("writer", vo.getWriter());	
		}
		if (  vo.getReg_date() != null && vo.getReg_date().isEmpty() != true ) {
			search.put("reg_date", vo.getReg_date());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return trendDao.selectSearchList(new DataMap(search));
	}
	
	/**
	 * Search List  All
	 */
	@Transactional
	public List<TrendVO> searchAllList(TrendSearchVO vo) throws Exception {
		return trendDao.selectSearchAllList(vo);
	}
	
	
	/**
	 * all List  
	 */
	@Transactional
	public List<TrendVO> allList() throws Exception {
		return trendDao.selectAllList();
	}
	
	
	/**
	 * preNext 검색
	 */
	@Transactional
	public List<TrendVO> preNextList(TrendSearchVO vo) throws Exception {
		HashMap<String, Object> search = new HashMap<String, Object>();

		if (  vo.getTrend_id() != null && vo.getTrend_id().isEmpty() != true ) {
			search.put("trend_id", vo.getTrend_id());	
		}

		return trendDao.selectPreNextList(new DataMap(search));
	}
	
	
}
