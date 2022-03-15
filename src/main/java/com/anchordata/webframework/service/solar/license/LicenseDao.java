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
package com.anchordata.webframework.service.solar.license;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;
import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingVO;


@Repository("LicenseDao")
public class LicenseDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("license.");
	
	public int insertInfo(LicenseVO params) throws Exception{
		return update(mapper.concat("insertInfo"), params);
	}
	
	public int updateInfo(LicenseVO params) throws Exception{
		return update(mapper.concat("updateInfo"), params);
	}
	
	public int deleteInfo(LicenseVO params) throws Exception{
		return delete(mapper.concat("deleteInfo"), params);
	}
	
	public LicenseVO selectDetail(LicenseVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetail"), vo);
	}
	
	public List<LicenseVO> selectSearchList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectSearchList"), param);
	}
	
	public List<LicenseVO> selectSearchList(DataMap param, int pageRow) throws Exception{
		return selectPagingList(mapper.concat("selectSearchListMain"), param, pageRow);
	}
	
	
	public List<LicenseVO> selectAllList() throws Exception{
		return selectList(mapper.concat("selectAllList"));
	}
	
	public List<LicenseVO> selectRelativeJobList(String param) throws Exception{
		return selectList(mapper.concat("selectRelativeJobList"), param);
	}
	
	public List<LicenseVO> selectSearchAllList(LicenseSearchVO param) throws Exception{
		return selectList(mapper.concat("selectSearchAllList"), param);
	}
	
	
	/**
	 * 검색된 실제 라이센스 갯수
	 */
	public int countByDataRow(LicenseSearchVO vo) throws Exception{
		return selectOne(mapper.concat("selectCountByDataRow"), vo);
	}
	
	/**
	 * 검색된 실제 라이센스들의 Type 갯수
	 */
	public int countBylicenseType(LicenseSearchVO vo) throws Exception{
		return selectOne(mapper.concat("seclectCountBylicenseType"), vo);
	}
	
	
	
}
