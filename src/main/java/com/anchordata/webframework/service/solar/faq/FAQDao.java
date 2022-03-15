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
package com.anchordata.webframework.service.solar.faq;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;
import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingVO;


@Repository("FAQDao")
public class FAQDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("faq.");
	
	public int insertInfo(FAQVO params) throws Exception{
		return update(mapper.concat("insertInfo"), params);
	}
	
	public int updateInfo(FAQVO params) throws Exception{
		return update(mapper.concat("updateInfo"), params);
	}
	
	public int deleteInfo(FAQVO params) throws Exception{
		return delete(mapper.concat("deleteInfo"), params);
	}
	
	public FAQVO selectDetail(FAQVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetail"), vo);
	}
	
	public List<FAQVO> selectSearchList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectSearchList"), param);
	}
	
	public List<FAQVO> selectSearchAllList(FAQSearchVO param) throws Exception{
		return selectList(mapper.concat("selectSearchAllList"), param);
	}
	
	public List<FAQVO> selectAllList() throws Exception{
		return selectList(mapper.concat("selectAllList"));
	}
	
	/**
	 * HITS 수 업데이트
	 */
	public int updateHits(FAQVO vo) throws Exception{
		return update(mapper.concat("updateHits"), vo);
	}
	
}
