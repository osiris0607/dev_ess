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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;
import com.anchordata.webframework.base.util.DataMap;


@Repository("TrainingDao")
public class TrainingDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("training.");
	
	public int insertInfo(TrainingVO params) throws Exception{
		return update(mapper.concat("insertInfo"), params);
	}
	
	public int updateInfo(TrainingVO params) throws Exception{
		return update(mapper.concat("updateInfo"), params);
	}
	
	public int deleteInfo(TrainingVO params) throws Exception{
		return delete(mapper.concat("deleteInfo"), params);
	}
	
	public TrainingVO selectDetail(TrainingVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetail"), vo);
	}
	
	public List<TrainingVO> selectSearchList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectSearchList"), param);
	}
	
	public List<TrainingVO> selectSearchList(DataMap param, int pageRow) throws Exception{
		return selectPagingList(mapper.concat("selectSearchList"), param, pageRow);
	}
	
	public List<TrainingVO> selectSearchAllList(TrainingSearchVO param) throws Exception{
		return selectList(mapper.concat("selectSearchAllList"), param);
	}
	
	
	
	public List<TrainingVO> selectAllList() throws Exception{
		return selectList(mapper.concat("selectAllList"));
	}
	
	/**
	 * HITS 수 업데이트
	 */
	public int updateHits(TrainingVO vo) throws Exception{
		return update(mapper.concat("updateHits"), vo);
	}
	
}
