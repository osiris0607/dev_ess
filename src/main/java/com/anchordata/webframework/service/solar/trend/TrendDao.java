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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;
import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingVO;


@Repository("TrendDao")
public class TrendDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("trend.");
	
	public int insertInfo(TrendVO params) throws Exception{
		return update(mapper.concat("insertInfo"), params);
	}
	
	public int updateInfo(TrendVO params) throws Exception{
		return update(mapper.concat("updateInfo"), params);
	}
	
	public int deleteInfo(TrendVO params) throws Exception{
		return delete(mapper.concat("deleteInfo"), params);
	}
	
	public TrendVO selectDetail(TrendVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetail"), vo);
	}
	
	public List<TrendVO> selectSearchList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectSearchList"), param);
	}
	
	public List<TrendVO> selectSearchAllList(TrendSearchVO param) throws Exception{
		return selectList(mapper.concat("selectSearchAllList"), param);
	}
	
	public List<TrendVO> selectAllList() throws Exception{
		return selectList(mapper.concat("selectAllList"));
	}
	

	
	/**
	 * HITS 수 업데이트
	 */
	public int updateHits(TrendVO vo) throws Exception{
		return update(mapper.concat("updateHits"), vo);
	}
	
	
	/**
	 * 이전글 / 다음글 처리
	 */
	public List<TrendVO> selectPreNextList(DataMap param) throws Exception{
		return selectList(mapper.concat("selectPreNextList"), param);
	}
	
	
	
}
