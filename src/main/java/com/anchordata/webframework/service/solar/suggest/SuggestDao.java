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
package com.anchordata.webframework.service.solar.suggest;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;


@Repository("SuggestDao")
public class SuggestDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("suggest.");
	
	public int insertInfo(SuggestVO params) throws Exception{
		return update(mapper.concat("insertInfo"), params);
	}
	
	public int updateInfo(SuggestVO params) throws Exception{
		return update(mapper.concat("updateInfo"), params);
	}
	
	public int deleteInfo(SuggestVO params) throws Exception{
		return delete(mapper.concat("deleteInfo"), params);
	}
	
	public List<SuggestVO> selectAllList() throws Exception{
		return selectList(mapper.concat("selectAllList"));
	}
	
	public int updateUseYN(SuggestVO params) throws Exception{
		return update(mapper.concat("updateUseYN"), params);
	}
	
}
