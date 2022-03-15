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
package com.anchordata.webframework.service.notice;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.uploadFile.UploadFileService;
import com.anchordata.webframework.service.uploadFile.UploadFileVO;


@Service("NoticeService")
public class NoticeService {
	
	@Autowired
	private NoticeDao noticeDao;
	@Autowired
	private UploadFileService uploadFileService;
	
	
	
	/**
	 * 공지사항 등록
	 */
	@Transactional
	public int registration(NoticeVO vo) throws Exception {
		
		// 첨부 파일 1 Binary로 DB 저장		
		if (vo.getAttach_file_1().getSize() > 0 ) {
			// 나머지 정보는 Upload File Table에 저장
			String fileName = vo.getAttach_file_1().getOriginalFilename();
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setName(fileName);
			uploadFileVO.setBinary_content(vo.getAttach_file_1().getBytes());
			uploadFileService.registration(uploadFileVO);
			
			vo.setAttach_file_id_1(Integer.toString(uploadFileVO.getFile_id()));
		} else {
			vo.setAttach_file_id_1("");
		}
		
		// Member Insert
		return noticeDao.insertNoticeInfo(vo);
	}
	
	/**
	 * 공지사항 수정
	 */
	@Transactional
	public int modification(NoticeVO vo) throws Exception {

	
		// 첨부 파일 1 Binary로 DB 저장		
		if (vo.getAttach_file_1().getSize() > 0 ) {
			
			// 나머지 정보는 Upload File Table에 저장
			String fileName = vo.getAttach_file_1().getOriginalFilename();
			UploadFileVO uploadFileVO  = new UploadFileVO();
			uploadFileVO.setName(fileName);
			uploadFileVO.setBinary_content(vo.getAttach_file_1().getBytes());
			uploadFileService.registration(uploadFileVO);
			
			vo.setAttach_file_id_1(Integer.toString(uploadFileVO.getFile_id()));
		}
		
		// 사진 파일 Binary로 DB 저장
		return noticeDao.updateNoticeInfo(vo);
	}
	
	/**
	 * 공지사항 삭제
	 * 실제 데이터 삭제가 아니라 Flag만 바꾼다.
	 * author : PJS
	 * Date : 2020-03-27
	 */
	@Transactional
	public int withdrawal(NoticeVO vo) throws Exception {
		// 실제 데이터 삭제가 아니라 Flag만 바꾼다.
		return noticeDao.deleteNoticeInfo(vo);
	}
	
	/**
	 * 공지사항 Paging List 
	 * author : PJS
	 * Date : 2020-03-27
	 */
	@Transactional
	public List<NoticeVO> allList(NoticeSearchVO vo) throws Exception {
		HashMap<String, Object> search = new HashMap<String, Object>();

		search.put("pageIndex", vo.getPageIndex());
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return noticeDao.selectPagingList(new DataMap(search));
	}
	
	/**
	 * 공지사항 One Detail List  
	 * author : PJS
	 * Date : 2020-03-27
	 */
	@Transactional
	public NoticeVO detailView(NoticeVO vo) throws Exception {
		// Hits 수를 올린다.
		noticeDao.updateHits(vo);
		return noticeDao.selectDetailView(vo);
	}
	
	
	/**
	 * 공지 Search List  
	 * author : PJS
	 * Date : 2020-03-27
	 */
	@Transactional
	public List<NoticeVO> searchList(NoticeSearchVO vo) throws Exception {
		
		HashMap<String, Object> search = new HashMap<String, Object>();
		search.put("pageIndex", vo.getPageIndex());

		if (  vo.getTitle() != null && vo.getTitle().isEmpty() != true ) {
			search.put("title", vo.getTitle());	
		}
		if (  vo.getExplanation() != null && vo.getExplanation().isEmpty() != true ) {
			search.put("explanation", vo.getExplanation());	
		}
		if (  vo.getTitleExplanation() != null && vo.getTitleExplanation().isEmpty() != true ) {
			search.put("titleExplanation", vo.getTitleExplanation());	
		}
		if (  vo.getWriter() != null && vo.getWriter().isEmpty() != true ) {
			search.put("writer", vo.getWriter());	
		}
		if (  vo.getMember_type() != null && vo.getMember_type().isEmpty() != true ) {
			search.put("member_type", vo.getMember_type());	
		}
		if (  vo.getType() != null && vo.getType().isEmpty() != true ) {
			search.put("type", vo.getType());	
		}
		if (  vo.getOrderby() != null && vo.getOrderby().isEmpty() != true ) {
			search.put("orderby", vo.getOrderby());	
		}
		
		return noticeDao.selectSearchList(new DataMap(search));
	}
	
	
	/**
	 * 공지 목록 삭제
	 * author : PJS
	 * Date : 2020-04-01
	 */
	public int block(List<String> param) throws Exception {
		
		if (param.size() <= 0) {
			System.out.println("MemberService::block] 삭제할 공지사항 목록 없음");
			return 0;
		}
		else {
			for (int i=0; i<param.size(); i++) {
				noticeDao.updateNoticeBlock(param.get(i));	
			}
		}
		
		return 1;
	}
	
	/**
	 * 공지 목록  갯수만큼 최신으로 검색
	 * author : PJS
	 * Date : 2020-03-29
	 */
	@Transactional
	public List<NoticeVO> countList(String count) throws Exception {
		return noticeDao.selectCountList(count);
	}
	
	
	/**
	 * 공지 목록  갯수만큼 최신으로 검색
	 * author : PJS
	 * Date : 2020-03-29
	 */
	@Transactional
	public List<NoticeVO> preNextList(NoticeSearchVO vo) throws Exception {
		HashMap<String, Object> search = new HashMap<String, Object>();

		if (  vo.getNotice_id() != null && vo.getNotice_id().isEmpty() != true ) {
			search.put("notice_id", vo.getNotice_id());	
		}
		if (  vo.getMember_type() != null && vo.getMember_type().isEmpty() != true ) {
			search.put("member_type", vo.getMember_type());	
		}
		if (  vo.getType() != null && vo.getType().isEmpty() != true ) {
			search.put("type", vo.getType());	
		}

		return noticeDao.selectPreNextList(new DataMap(search));
	}
	
	
	public int setMainPageYN(NoticeVO vo) throws Exception {
		
		return noticeDao.updateMainPageYN(vo);	
		
	}
	
	
	
	
	
}
