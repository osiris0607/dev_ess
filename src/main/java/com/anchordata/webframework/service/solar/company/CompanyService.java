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
package com.anchordata.webframework.service.solar.company;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.uploadFile.UploadFileService;
import com.anchordata.webframework.service.uploadFile.UploadFileVO;


@Service("CompanyService")
public class CompanyService {
	
	@Autowired
	private CompanyDao companyDao;
	@Autowired
	private UploadFileService uploadFileService;
	
	
	/**
	 * 등록
	 */
	@Transactional
	public int registration(CompanyVO vo) throws Exception {
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
		return companyDao.insertInfo(vo);
	}
	
	/**
	 * 수정
	 */
	@Transactional
	public int modification(CompanyVO vo) throws Exception {
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

		return companyDao.updateInfo(vo);
	}
	
	/**
	 * 삭제
	 */
	@Transactional
	public int withdrawal(CompanyVO vo) throws Exception {
		// 연관된 File 삭제
		if ( vo.getUpload_file_id() != null && vo.getUpload_file_id() != "" ) {
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setFile_id(Integer.parseInt(vo.getUpload_file_id()));
			uploadFileService.withdrawal(uploadFileVO);
		}
		return companyDao.deleteInfo(vo);
	}
	
	/**
	 * 상세
	 */
	@Transactional
	public CompanyVO detail(CompanyVO vo) throws Exception {
		CompanyVO result = companyDao.selectDetail(vo);
		return result;
	}
	
	/**
	 * 검색
	 */
	@Transactional
	public List<CompanyVO> searchList(CompanySearchVO vo) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());
		
		
		if (  StringUtils.isEmpty(vo.getOccupation()) == false ) {
			search.put("occupation", vo.getOccupation());	
		}		
		if (  StringUtils.isEmpty(vo.getCategory_id()) == false ) {
			search.put("category_id", vo.getCategory_id());	
		}
		if (  StringUtils.isEmpty(vo.getCategory_detail_id()) == false ) {
			search.put("category_detail_id", vo.getCategory_detail_id());	
		}
		if (  vo.getSize() != null && vo.getSize().isEmpty() != true ) {
			search.put("size", vo.getSize());	
		}
		if (  vo.getRegion() != null && vo.getRegion().isEmpty() != true ) {
			search.put("region", vo.getRegion());	
		}
		if (  vo.getRegion_detail() != null && vo.getRegion_detail().isEmpty() != true ) {
			search.put("region_detail", vo.getRegion_detail());	
		}
		if (  vo.getName() != null && vo.getName().isEmpty() != true ) {
			search.put("name", vo.getName());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return companyDao.selectSearchList(new DataMap(search));
	}
	
	
	/**
	 * all List  
	 */
	@Transactional
	public List<CompanyVO> allList() throws Exception {
		return companyDao.selectAllList();
	}
	
	/**
	 * Search List  
	 */
	@Transactional
	public List<CompanyVO> searchAllList(CompanySearchVO vo) throws Exception {
		return companyDao.selectSearchAllList(vo);
	}
	
	
	/**
	 * 지역별 회사 갯수
	 */
	@Transactional
	public List<CompanyVO> regionCount(CompanySearchVO vo) throws Exception {
		return companyDao.selectRegionCount(vo);
	}
	
}
