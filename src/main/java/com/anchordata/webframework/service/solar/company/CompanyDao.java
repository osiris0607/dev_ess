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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;
import com.anchordata.webframework.base.util.DataMap;


@Repository("CompanyDao")
public class CompanyDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("company.");
	
	public int insertInfo(CompanyVO params) throws Exception{
		return update(mapper.concat("insertInfo"), params);
	}
	
	public int updateInfo(CompanyVO params) throws Exception{
		return update(mapper.concat("updateInfo"), params);
	}
	
	public int deleteInfo(CompanyVO params) throws Exception{
		return delete(mapper.concat("deleteInfo"), params);
	}
	
	public CompanyVO selectDetail(CompanyVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetail"), vo);
	}
	
	public List<CompanyVO> selectSearchList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectSearchList"), param);
	}
	
	public List<CompanyVO> selectAllList() throws Exception{
		return selectList(mapper.concat("selectAllList"));
	}
	
	public List<CompanyVO> selectSearchAllList(CompanySearchVO param) throws Exception{
		return selectList(mapper.concat("selectSearchAllList"), param);
	}
	
	public List<CompanyVO> selectRegionCount(CompanySearchVO param) throws Exception{
		return selectList(mapper.concat("selectRegionCount"), param);
	}
	
	
	
	/**
	 * 공지 사항 HITS 수 업데이트
	 */
	public int updateHits(CompanyVO vo) throws Exception{
		return update(mapper.concat("updateHits"), vo);
	}
	
	
	/**
	 * PJS : 2020-03-28
	 * 공지 목록  갯수만큼 최신으로 검색
	 */
	public List<CompanyVO> selectCountList(String count) throws Exception{
		return selectList(mapper.concat("selectCountList"), Integer.parseInt(count));
	}

	
	/**
	 * PJS : 2020-05-17
	 * 이전글 / 다음글 처리
	 */
	public List<CompanyVO> selectPreNextList(DataMap param) throws Exception{
		return selectList(mapper.concat("selectPreNextList"), param);
	}
	
	

	
	
}
