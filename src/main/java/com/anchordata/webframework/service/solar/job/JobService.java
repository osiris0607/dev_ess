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
package com.anchordata.webframework.service.solar.job;


import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.commonCode.CommonCodeVO;
import com.anchordata.webframework.service.solar.license.LicenseService;
import com.anchordata.webframework.service.solar.license.LicenseVO;
import com.anchordata.webframework.service.uploadFile.UploadFileService;
import com.anchordata.webframework.service.uploadFile.UploadFileVO;


@Service("JobService")
public class JobService {
	
	@Autowired
	private JobDao JobDao;
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private LicenseService licenseService;
	
	/**
	 * 등록
	 */
	@Transactional
	public int registration(JobVO vo) throws Exception {
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
		
		// Member Insert
		JobDao.insertInfo(vo);
		
		// Job 관련된 공통코드 입력 (자격증)
		if (vo.getLicense_list().size() > 0 ) {
			for(String id : vo.getLicense_list()){
				JobRelativeVO jobRelativeVO = new JobRelativeVO();
				jobRelativeVO.setJob_id(Integer.toString(vo.getJob_id()));
				jobRelativeVO.setRelative_master_id("M000013");
				jobRelativeVO.setRelative_detail_id(id);
				JobDao.insertRelativeInfo(jobRelativeVO);
			}
		}
		// Job 관련된 공통코드 입력 (전공)
		if (vo.getMajor_list().size() > 0 ) {
			for(String id : vo.getMajor_list()){
				JobRelativeVO jobRelativeVO = new JobRelativeVO();
				jobRelativeVO.setJob_id(Integer.toString(vo.getJob_id()));
				jobRelativeVO.setRelative_master_id("M000006");
				jobRelativeVO.setRelative_detail_id(id);
				JobDao.insertRelativeInfo(jobRelativeVO);
			}
		}	
		return 1;
	}
	
	/**
	 * 수정
	 */
	@Transactional
	public int modification(JobVO vo) throws Exception {
		// 첨부 파일  저장		
		if ( StringUtils.isEmpty(vo.getUpload_file_id()) == false  ) {
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
		
		// Job 관련된 공통코드 입력 (자격증)
		if (vo.getLicense_list().size() > 0 ) {
			JobRelativeVO jobRelativeVO = new JobRelativeVO();
			jobRelativeVO.setJob_id(Integer.toString(vo.getJob_id()));
			jobRelativeVO.setRelative_master_id("M000013");
			// 기존 데이터를 모두 삭제한다.
			JobDao.deleteRelativeInfo(jobRelativeVO);
			
			for(String id : vo.getLicense_list()){
				jobRelativeVO.setRelative_detail_id(id);
				JobDao.insertRelativeInfo(jobRelativeVO);
			}
		}
		// Job 관련된 공통코드 입력 (전공)
		if (vo.getMajor_list().size() > 0 ) {
			JobRelativeVO jobRelativeVO = new JobRelativeVO();
			jobRelativeVO.setJob_id(Integer.toString(vo.getJob_id()));
			jobRelativeVO.setRelative_master_id("M000006");
			// 기존 데이터를 모두 삭제한다.
			JobDao.deleteRelativeInfo(jobRelativeVO);
			
			for(String id : vo.getMajor_list()){
				jobRelativeVO.setRelative_detail_id(id);
				JobDao.insertRelativeInfo(jobRelativeVO);
			}
		}	
		return JobDao.updateInfo(vo);
	}
	
	/**
	 * 삭제
	 */
	@Transactional
	public int withdrawal(JobVO vo) throws Exception {
		// 연관된 File 삭제
		if ( vo.getUpload_file_id() != null && vo.getUpload_file_id() != "" ) {
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setFile_id(Integer.parseInt(vo.getUpload_file_id()));
			uploadFileService.withdrawal(uploadFileVO);
		}
		return JobDao.deleteInfo(vo);
	}
	
	/**
	 * Search List  
	 */
	@Transactional
	public List<JobVO> searchList(JobSearchVO vo) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());

		if (  vo.getCategory_id() != null && vo.getCategory_id().isEmpty() != true ) {
			search.put("category_id", vo.getCategory_id());	
		}
		if (  vo.getCategory_detail_id() != null && vo.getCategory_detail_id().isEmpty() != true ) {
			search.put("category_detail_id", vo.getCategory_detail_id());	
		}
		if (  vo.getOccupation() != null && vo.getOccupation().isEmpty() != true ) {
			search.put("occupation", vo.getOccupation());	
		}
		if (  vo.getMajor() != null && vo.getMajor().isEmpty() != true ) {
			search.put("major", vo.getMajor());	
		}
		if (  vo.getEducation() != null && vo.getEducation().isEmpty() != true ) {
			search.put("education", vo.getEducation());	
		}
		if (  vo.getDifficulty() != null && vo.getDifficulty().isEmpty() != true ) {
			search.put("difficulty", vo.getDifficulty());	
		}
		if (  vo.getName() != null && vo.getName().isEmpty() != true ) {
			search.put("name", vo.getName());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return JobDao.selectSearchList(new DataMap(search));
	}
	
	
	/**
	 * Search List  
	 */
	@Transactional
	public List<JobVO> searchList(JobSearchVO vo, int pageRow) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());

		if (  vo.getCategory_id() != null && vo.getCategory_id().isEmpty() != true ) {
			search.put("category_id", vo.getCategory_id());	
		}
		if (  vo.getCategory_detail_id() != null && vo.getCategory_detail_id().isEmpty() != true ) {
			search.put("category_detail_id", vo.getCategory_detail_id());	
		}
		if (  vo.getOccupation() != null && vo.getOccupation().isEmpty() != true ) {
			search.put("occupation", vo.getOccupation());	
		}
		if (  vo.getMajor() != null && vo.getMajor().isEmpty() != true ) {
			search.put("major", vo.getMajor());	
		}
		if (  vo.getEducation() != null && vo.getEducation().isEmpty() != true ) {
			search.put("education", vo.getEducation());	
		}
		if (  vo.getDifficulty() != null && vo.getDifficulty().isEmpty() != true ) {
			search.put("difficulty", vo.getDifficulty());	
		}
		if (  vo.getName() != null && vo.getName().isEmpty() != true ) {
			search.put("name", vo.getName());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return JobDao.selectSearchList(new DataMap(search), pageRow);
	}
	
	/**
	 * 일자리 all List  
	 */
	@Transactional
	public List<JobVO> allList() throws Exception {
		return JobDao.selectAllList();
	}
	
	
	/**
	 * Search List  
	 */
	@Transactional
	public List<JobVO> searchAllList(JobVO vo) throws Exception {
		return JobDao.selectSearchAllList(vo);
	}
	
	
	/**
	 * Detail
	 */
	@Transactional
	public JobVO detail(JobVO vo) throws Exception {
		JobVO result = JobDao.selectDetail(vo);
		
		List<CommonCodeVO> majorList = JobDao.selectMajorList(vo);
		List<LicenseVO> licenseList = licenseService.relativeJobList(vo);
		
		result.setReturn_major_list(majorList);
		result.setReturn_license_list(licenseList);
		return result;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// JOB RANK 관련
	///////////////////////////////////////////////////////////////////////////
	/**
	 * RANK 등록 여부
	 */
	@Transactional
	public int updateRank(JobVO vo) throws Exception {
		return JobDao.updateRank(vo);
	}
	
	
	/**
	 * 일자리 all List  
	 */
	@Transactional
	public List<JobVO> allListRank() throws Exception {
		return JobDao.allListRank();
	}
	
	
	
}
