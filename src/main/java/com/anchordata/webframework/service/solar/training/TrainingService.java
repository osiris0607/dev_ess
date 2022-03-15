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
package com.anchordata.webframework.service.solar.training;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.uploadFile.UploadFileService;
import com.anchordata.webframework.service.uploadFile.UploadFileVO;


@Service("TrainingService")
public class TrainingService {
	
	@Autowired
	private TrainingDao trainingDao;
	@Autowired
	private UploadFileService uploadFileService;
	
	/**
	 * 등록
	 */
	@Transactional
	public int registration(TrainingVO vo) throws Exception {
		// 첨부 파일 1 Binary로 DB 저장		
		if (vo.getAttach_file() != null && vo.getAttach_file().getSize() > 0 ) {
			// 나머지 정보는 Upload File Table에 저장
			String fileName = vo.getAttach_file().getOriginalFilename();
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setName(fileName);
			uploadFileVO.setDescription(vo.getAttach_file_description());
			uploadFileVO.setBinary_content(vo.getAttach_file().getBytes());
			uploadFileService.registration(uploadFileVO);
			
			vo.setUpload_file_id(Integer.toString(uploadFileVO.getFile_id()));
		} else {
			vo.setUpload_file_id("");
		}
		
		return trainingDao.insertInfo(vo);
	}
	
	/**
	 * 수정
	 */
	@Transactional
	public int modification(TrainingVO vo) throws Exception {
		// 첨부 파일이 있으면 변경
		if ( StringUtils.isEmpty(vo.getUpload_file_id()) == false ) {
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setFile_id(Integer.parseInt(vo.getUpload_file_id()));
			uploadFileVO.setDescription(vo.getAttach_file_description());
			
			if (vo.getAttach_file() != null && vo.getAttach_file().getSize() > 0 ) {
				String fileName = vo.getAttach_file().getOriginalFilename();
				uploadFileVO.setName(fileName);
				uploadFileVO.setBinary_content(vo.getAttach_file().getBytes());
			}
			
			uploadFileService.modification(uploadFileVO);			
		}
		else {
			if (vo.getAttach_file() != null && vo.getAttach_file().getSize() > 0 ) {
				// 나머지 정보는 Upload File Table에 저장
				String fileName = vo.getAttach_file().getOriginalFilename();
				UploadFileVO uploadFileVO  = new UploadFileVO();
				uploadFileVO.setName(fileName);
				uploadFileVO.setDescription(vo.getAttach_file_description());
				uploadFileVO.setBinary_content(vo.getAttach_file().getBytes());
				uploadFileService.registration(uploadFileVO);
				
				vo.setUpload_file_id(Integer.toString(uploadFileVO.getFile_id()));
			}
		}
		return trainingDao.updateInfo(vo);
	}
	
	/**
	 * 삭제
	 */
	@Transactional
	public int withdrawal(TrainingVO vo) throws Exception {
		// 연관된 File 삭제
		if ( vo.getUpload_file_id() != null && vo.getUpload_file_id() != "" ) {
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setFile_id(Integer.parseInt(vo.getUpload_file_id()));
			uploadFileService.withdrawal(uploadFileVO);
		}
		return trainingDao.deleteInfo(vo);
	}
	
	/**
	 * 상세
	 */
	@Transactional
	public TrainingVO detail(TrainingVO vo) throws Exception {
		TrainingVO result = trainingDao.selectDetail(vo);
		return result;
	}
	
	/**
	 * 검색
	 */
	@Transactional
	public List<TrainingVO> searchList(TrainingSearchVO vo) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());
		
		if (  vo.getTitle() != null && vo.getTitle().isEmpty() != true ) {
			search.put("title", vo.getTitle());	
		}
		if (  vo.getEnforcement_agency() != null && vo.getEnforcement_agency().isEmpty() != true ) {
			search.put("enforcement_agency", vo.getEnforcement_agency());	
		}
		if (  vo.getReg_date() != null && vo.getReg_date().isEmpty() != true ) {
			search.put("reg_date", vo.getReg_date());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return trainingDao.selectSearchList(new DataMap(search));
	}
	
	
	/**
	 * Search List  
	 */
	@Transactional
	public List<TrainingVO> searchList(TrainingSearchVO vo, int pageRow) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());
		
		if (  vo.getTitle() != null && vo.getTitle().isEmpty() != true ) {
			search.put("title", vo.getTitle());	
		}
		if (  vo.getEnforcement_agency() != null && vo.getEnforcement_agency().isEmpty() != true ) {
			search.put("enforcement_agency", vo.getEnforcement_agency());	
		}
		if (  vo.getReg_date() != null && vo.getReg_date().isEmpty() != true ) {
			search.put("reg_date", vo.getReg_date());	
		}
		if (  StringUtils.isEmpty(vo.getAddress()) == false )  {
			search.put("address", vo.getAddress());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return trainingDao.selectSearchList(new DataMap(search), pageRow);
	}
	
	
	/**
	 * Search List  All
	 */
	@Transactional
	public List<TrainingVO> searchAllList(TrainingSearchVO vo) throws Exception {
		return trainingDao.selectSearchAllList(vo);
	}
	
	
	/**
	 * all List  
	 */
	@Transactional
	public List<TrainingVO> allList() throws Exception {
		return trainingDao.selectAllList();
	}
	
	
}
