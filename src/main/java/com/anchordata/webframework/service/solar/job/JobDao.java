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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.core.BaseDao;
import com.anchordata.webframework.base.util.DataMap;
import com.anchordata.webframework.service.commonCode.CommonCodeVO;


@Repository("JobDao")
public class JobDao extends BaseDao {
	
	private String mapper	= Constants.NAMESPACE.concat("job.");
	

	public int insertInfo(JobVO params) throws Exception{
		return update(mapper.concat("insertInfo"), params);
	}
	
	public int updateInfo(JobVO params) throws Exception{
		return update(mapper.concat("updateInfo"), params);
	}
	
	public int deleteInfo(JobVO params) throws Exception{
		return update(mapper.concat("deleteInfo"), params);
	}
	
	public JobVO selectDetailView(JobVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetailView"), vo);
	}
	
	public List<JobVO> selectSearchList(DataMap param) throws Exception{
		return selectPagingList(mapper.concat("selectSearchList"), param);
	}
	
	public List<JobVO> selectSearchList(DataMap param, int pageRow) throws Exception{
		return selectPagingList(mapper.concat("selectSearchList"), param, pageRow);
	}
	
	public int insertRelativeInfo(JobRelativeVO params) throws Exception{
		return update(mapper.concat("insertRelativeInfo"), params);
	}
	
	public int deleteRelativeInfo(JobRelativeVO params) throws Exception{
		return update(mapper.concat("deleteRelativeInfo"), params);
	}
	
	public List<JobVO> selectAllList() throws Exception{
		return selectList(mapper.concat("selectAllList"));
	}
	
	public List<JobVO> selectSearchAllList(JobVO param) throws Exception{
		return selectList(mapper.concat("selectSearchAllList"), param);
	}
	
	public JobVO selectDetail(JobVO vo) throws Exception{
		return selectOne(mapper.concat("selectDetail"), vo);
	}
	
	
	public List<CommonCodeVO> selectMajorList(JobVO param) throws Exception{
		return selectList(mapper.concat("selectMajorList"), param);
	}
	
	///////////////////////////////////////////////////////////////////////////
	// JOB RANK 관련
	///////////////////////////////////////////////////////////////////////////
	public int updateRank(JobVO params) throws Exception{
		return update(mapper.concat("updateRank"), params);
	}
	
	public List<JobVO> allListRank() throws Exception{
		return selectList(mapper.concat("allListRank"));
	}
	
	
}
