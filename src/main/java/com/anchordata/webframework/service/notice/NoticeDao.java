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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;
import com.anchordata.webframework.base.util.DataMap;


@Repository("NoticeDao")
public class NoticeDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("notice.");
	
	/**
	 * PJS : 2020-03-28
	 * 공지 등록 
	 */
	public int insertNoticeInfo(NoticeVO params) throws Exception{
		return update(mapper.concat("insertNoticeInfo"), params);
	}
	
	
	/**
	 * PJS : 2020-03-28
	 * 공지 수정 
	 */
	public int updateNoticeInfo(NoticeVO params) throws Exception{
		return update(mapper.concat("updateNoticeInfo"), params);
	}
	
	/**
	 * PJS : 2020-03-28
	 * 공지 삭제
	 */
	public int deleteNoticeInfo(NoticeVO params) throws Exception{
		return update(mapper.concat("deleteNoticeInfo"), params);
	}
	
	/**
	 * PJS : 2020-03-28
	 * 공지 목록
	 */
	public List<NoticeVO> selectPagingList(DataMap params) throws Exception{
		return selectPagingList(mapper.concat("selectPagingList"), params);
	}
	
	/**
	 * PJS : 2020-03-28
	 * 공지 상세 검색
	 */
	public NoticeVO selectDetailView(NoticeVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetailView"), vo);
	}
	
	
	/**
	 * 공지 사항 HITS 수 업데이트
	 */
	public int updateHits(NoticeVO vo) throws Exception{
		return update(mapper.concat("updateHits"), vo);
	}
	
	/**
	 * PJS : 2020-03-28
	 * 공지 검색
	 */
	public List<NoticeVO> selectSearchList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectSearchPagingList"), param);
	}
	
	/**
	 * PJS : 2020-03-28
	 * 공지 목록 삭제
	 */
	public int updateNoticeBlock(String params) throws Exception{
		return update(mapper.concat("updateNoticeBlock"), params);
	}

	
	/**
	 * PJS : 2020-03-28
	 * 공지 목록  갯수만큼 최신으로 검색
	 */
	public List<NoticeVO> selectCountList(String count) throws Exception{
		return selectList(mapper.concat("selectCountList"), Integer.parseInt(count));
	}

	
	/**
	 * PJS : 2020-05-17
	 * 이전글 / 다음글 처리
	 */
	public List<NoticeVO> selectPreNextList(DataMap param) throws Exception{
		return selectList(mapper.concat("selectPreNextList"), param);
	}
	
	
	
	public int updateMainPageYN(NoticeVO params) throws Exception{
		return update(mapper.concat("updateMainPageYN"), params);
	}
	
}
