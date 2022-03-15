package com.anchordata.webframework.service.commonCode;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anchordata.webframework.service.notice.NoticeVO;
import com.anchordata.webframework.service.uploadFile.UploadFileVO;

@Service("CommonCodeService")
public class CommonCodeService {
	
	@Autowired
	private CommonCodeDao dao;
	
	public List<CommonCodeVO> selectListAll() throws Exception {
		List<CommonCodeVO> result = dao.selectListAll();
		return result;
	}
	
	public List<CommonCodeVO> selectListAllUseYN() throws Exception {
		List<CommonCodeVO> result = dao.selectListAllUseYN();
		return result;
	}
	
	
	public List<CommonCodeVO> selectListByMasterCode(CommonCodeVO vo) throws Exception {
//		return dao.selectListByMasterCode(vo);
		return null;
	}
	
	
	public int updateUseYN(CommonCodeVO vo) throws Exception {
		return dao.updateUseYN(vo);
	}
	
	
	@Transactional
	public int registration(CommonCodeVO vo) throws Exception {
		// Member Insert
		return dao.insertInfo(vo);
	}
	
	public CommonCodeVO selectDetailIdDescOne(CommonCodeVO vo) throws Exception {
		CommonCodeVO result = dao.selectDetailIdDescOne(vo);
		return result;
	}
	
	
	
	
	
	
	

}
