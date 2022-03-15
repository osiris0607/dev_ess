package com.anchordata.webframework.controller.admin.job;


import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.commonCode.CommonCodeService;
import com.anchordata.webframework.service.commonCode.CommonCodeVO;
import com.anchordata.webframework.service.solar.job.JobSearchVO;
import com.anchordata.webframework.service.solar.job.JobService;
import com.anchordata.webframework.service.solar.job.JobVO;


@Controller("AdminJobController")
public class AdminJobController {
	
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private JobService jobService;
	
	@RequestMapping("/admin/job/registration")
	public ModelAndView rdtRegistration(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("job/registration.admin");
		return mv;
	}
	
	@RequestMapping("/admin/job/codeManagement")
	public ModelAndView rdtcodeManagement(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAll();
		mv.addObject("commonCode", result);
		mv.setViewName("job/codeManagement.admin");
		return mv;
	}
	
	@RequestMapping("/admin/job/search")
	public ModelAndView rdtSearch(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("job/searchList.admin");
		return mv;
	}
	
	@RequestMapping("/admin/job/detail")
	public ModelAndView rdtDetail(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.addObject("vo", vo);
		mv.setViewName("job/detail.admin");
		return mv;
	}

	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 등록
	*/
	@RequestMapping("/admin/api/job/registration")
	public ModelAndView registration(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", jobService.registration(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	* 변경
	*/
	@RequestMapping("/admin/api/job/modification")
	public ModelAndView modification(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", jobService.modification(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 * 삭제
	 */
	@RequestMapping("/admin/api/job/withdrawal")
	public ModelAndView withdrawal(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		mv.addObject("result", jobService.withdrawal(vo)); //
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	* 검색 List (10개씩)
	*/
	@RequestMapping("/admin/api/job/search/paging")
		public ModelAndView search(@ModelAttribute JobSearchVO vo, ModelAndView mv) throws Exception {
		List<JobVO> resList = jobService.searchList(vo);
		if (resList.size() > 0) {
			mv.addObject("result", resList);
			mv.addObject("totalCount", resList.get(0).getTotal_count());
		} else {
			mv.addObject("totalCount", 0);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	* All List
	*/
	@RequestMapping("/admin/api/job/all")
		public ModelAndView allSearchList(@ModelAttribute JobSearchVO vo, ModelAndView mv) throws Exception {
		List<JobVO> resList = jobService.allList();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	*  상세
	*/
	@RequestMapping("/admin/api/job/detail")
	public ModelAndView detail(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		JobVO result = jobService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	* 카테고리 사용 여부 변경
	*/
	@RequestMapping("/admin/api/job/code/update/useYN")
		public ModelAndView categoryUseYN(@ModelAttribute CommonCodeVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", commonCodeService.updateUseYN(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 등록
	*/
	@RequestMapping("/admin/api/job/code/registration")
		public ModelAndView codeRegistration(@ModelAttribute CommonCodeVO vo, ModelAndView mv) throws Exception {
		
		CommonCodeVO detailIdVO;
		// MASTER ID 별로 아이디 표현 방식이 틀리다.
		switch (vo.getMaster_id()) {
			case "M000002":
				detailIdVO = commonCodeService.selectDetailIdDescOne(vo);
				if ( detailIdVO == null || StringUtils.isEmpty(detailIdVO.getDetail_id()) ) {
					vo.setDetail_id("A");
				}
				else {
					
					int charId = (int)(detailIdVO.getDetail_id().toCharArray()[0]) + 1;
					String id = String.valueOf((char)charId);
					vo.setDetail_id(id);
				}
				break;
			case "M000003":
				detailIdVO = commonCodeService.selectDetailIdDescOne(vo);
				if ( detailIdVO == null || StringUtils.isEmpty(detailIdVO.getDetail_id()) ) {
					vo.setDetail_id(vo.getParent_id()+"-1");
				}
				else {
					String temp = detailIdVO.getDetail_id().substring(detailIdVO.getDetail_id().lastIndexOf('-')+1, detailIdVO.getDetail_id().length());
					int lastId = Integer.parseInt(temp) + 1;
					String id = detailIdVO.getDetail_id().substring(0,detailIdVO.getDetail_id().lastIndexOf('-')+1) + lastId;
					vo.setDetail_id(id);
				}
				break;
			case "M000004":
				detailIdVO = commonCodeService.selectDetailIdDescOne(vo);
				if ( detailIdVO == null || StringUtils.isEmpty(detailIdVO.getDetail_id()) ) {
					vo.setDetail_id(vo.getParent_id()+"-1");
				}
				else {
					String temp = detailIdVO.getDetail_id().substring(detailIdVO.getDetail_id().lastIndexOf('-')+1, detailIdVO.getDetail_id().length());
					int lastId = Integer.parseInt(temp) + 1;
					String id = detailIdVO.getDetail_id().substring(0,detailIdVO.getDetail_id().lastIndexOf('-')+1) + lastId;
					vo.setDetail_id(id);
				}
				break;
			case "M000006":
				detailIdVO = commonCodeService.selectDetailIdDescOne(vo);
				if ( detailIdVO == null || StringUtils.isEmpty(detailIdVO.getDetail_id()) ) {
					vo.setDetail_id("D000001");
				}
				else {
					String temp = detailIdVO.getDetail_id().substring(1, detailIdVO.getDetail_id().length());
					int lastId = Integer.parseInt(temp) + 1;
					String id = detailIdVO.getDetail_id().substring(0,1) + String.format("%06d", lastId);
					vo.setDetail_id(id);
				}
				break;
			case "M000013":
				// 13인 경우 자격증이다.
				// 자격증의 Detail ID는 Parent_ID 와 상관없이 숫자로 증가하는 코드이다.
				// 따라서 가장 최근의 Detail ID를 가져오기 위해서 Query 시에 Parent_ID를 NULL 로 해야 한다.
				// selectDetailIdDescOne 쿼리가 Parent_ID도 검색하게 되어 있다.
				String tempParent_Id = vo.getParent_id();
				vo.setParent_id("");
				detailIdVO = commonCodeService.selectDetailIdDescOne(vo);
				vo.setParent_id(tempParent_Id);
				// 퀴리 이후에 다시 원복한다.
				vo.setParent_id(tempParent_Id);
				if ( detailIdVO == null || StringUtils.isEmpty(detailIdVO.getDetail_id()) ) {
					vo.setDetail_id("D000001");
				}
				else {
					String temp = detailIdVO.getDetail_id().substring(1, detailIdVO.getDetail_id().length());
					int lastId = Integer.parseInt(temp) + 1;
					String id = detailIdVO.getDetail_id().substring(0,1) + String.format("%06d", lastId);
					vo.setDetail_id(id);
				}
				break;
			default :
				break;
		}
		
		mv.addObject( "result", commonCodeService.registration(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 * 정보 다운로드
	 */
	@RequestMapping("/admin/api/job/excelDownload")
	public void excelDownload(@ModelAttribute JobVO vo, HttpServletResponse response) throws Exception {
		//전체 회원 정보 
		List<JobVO> list = jobService.searchAllList(vo);
		
		// Make Excel File
		// 워크북 생성
	    Workbook wb = new HSSFWorkbook();
	    Sheet sheet = wb.createSheet("회원정보");
	    Row row = null;
	    Cell cell = null;
	    int rowNo = 0;


	    // 테이블 헤더용 스타일
	    CellStyle headStyle = wb.createCellStyle();
	    headStyle.setBorderTop(BorderStyle.THIN);
	    headStyle.setBorderBottom(BorderStyle.THIN);
	    headStyle.setBorderLeft(BorderStyle.THIN);
	    headStyle.setBorderRight(BorderStyle.THIN);

	    // 배경색 노란색
	    headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
	    headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	    // 데이터는 가운데 정렬합니다.
	    headStyle.setAlignment(HorizontalAlignment.CENTER);

	    // 데이터용 경계 스타일 테두리만 지정
	    CellStyle bodyStyle = wb.createCellStyle();
	    bodyStyle.setBorderTop(BorderStyle.THIN);
	    bodyStyle.setBorderBottom(BorderStyle.THIN);
	    bodyStyle.setBorderLeft(BorderStyle.THIN);
	    bodyStyle.setBorderRight(BorderStyle.THIN);

	    // 헤더 생성
	    row = sheet.createRow(rowNo++);
	    cell = row.createCell(0);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("생태계 분류");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("생태계 세부분류");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("직종분류");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("일자리명");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("요구학력");
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("직무 난이도");
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("해당기업1");
	    cell = row.createCell(7);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("해당기업2");
	    cell = row.createCell(8);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("해당기업3");
	    
	    // 데이터 부분 생성
	    for(JobVO jobVO : list) {
	        row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getCategory_name());

	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getCategory_detail_name());

	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getOccupation_name());
	        
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getName());
	        
	        cell = row.createCell(4);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getEducation_name());
	        
	        cell = row.createCell(5);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getDifficulty_name());
	        
	        cell = row.createCell(6);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getCompany_1());
	        
	        cell = row.createCell(7);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getCompany_2());
	        
	        cell = row.createCell(8);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(jobVO.getCompany_3());
	    }
	    
	    // 컨텐츠 타입과 파일명 지정
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=job_list.xls");

	    // 엑셀 출력
	    wb.write(response.getOutputStream());
	    wb.close();
	}	
	

	
	
}
