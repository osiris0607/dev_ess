package com.anchordata.webframework.controller.admin.company;


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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.commonCode.CommonCodeService;
import com.anchordata.webframework.service.commonCode.CommonCodeVO;
import com.anchordata.webframework.service.solar.company.CompanySearchVO;
import com.anchordata.webframework.service.solar.company.CompanyService;
import com.anchordata.webframework.service.solar.company.CompanyVO;


@Controller("AdminCompanyController")
public class AdminCompanyController {
	
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/admin/company/registration")
	public ModelAndView rdtLicenseRegistration(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
//		mv.addObject("headerMenuIndex", headerMenuIndex);
		mv.setViewName("company/registration.admin");
		return mv;
	}
	
	@RequestMapping("/admin/company/searchList")
	public ModelAndView rdtLicenseList(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("company/searchList.admin");
		return mv;
	}
	
	@RequestMapping("/admin/company/detail")
	public ModelAndView rdtDetail(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.addObject("vo", vo);
		mv.setViewName("company/detail.admin");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 등록
	*/
	@RequestMapping("/admin/api/company/registration")
	public ModelAndView registration(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", companyService.registration(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 검색 List (10개씩)
	*/
	@RequestMapping("/admin/api/company/search/paging")
		public ModelAndView search(@ModelAttribute CompanySearchVO vo, ModelAndView mv) throws Exception {
		List<CompanyVO> resList = companyService.searchList(vo);
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
	* 검색 List ALL
	*/
	@RequestMapping("/admin/api/company/all")
		public ModelAndView searchAll(@ModelAttribute CompanySearchVO vo, ModelAndView mv) throws Exception {
		List<CompanyVO> resList = companyService.allList();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	/**
	*  상세
	*/
	@RequestMapping("/admin/api/company/detail")
	public ModelAndView detail(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		CompanyVO result = companyService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 변경
	*/
	@RequestMapping("/admin/api/company/modification")
	public ModelAndView modification(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", companyService.modification(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 * 삭제
	 */
	@RequestMapping("/admin/api/company/withdrawal")
	public ModelAndView withdrawal(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		mv.addObject("result", companyService.withdrawal(vo)); //
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 * 정보 다운로드
	 */
	@RequestMapping("/admin/api/company/excelDownload")
	public void excelDownload(@ModelAttribute CompanySearchVO vo, HttpServletResponse response) throws Exception {
		//전체 회원 정보 
		List<CompanyVO> list = companyService.searchAllList(vo);
		
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
	    cell.setCellValue("업체 규모");
	    cell = row.createCell(1);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("지역1");
	    cell = row.createCell(2);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("지역2");
	    cell = row.createCell(3);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("회사명");
	    cell = row.createCell(4);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("표준산업분류");
	    cell = row.createCell(5);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("고용인수");
	    cell = row.createCell(6);
	    cell.setCellStyle(headStyle);
	    cell.setCellValue("주력제품/서비스");
	    
	    // 데이터 부분 생성
	    for(CompanyVO companyVO : list) {
	        row = sheet.createRow(rowNo++);
	        cell = row.createCell(0);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(companyVO.getSize_name());

	        cell = row.createCell(1);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(companyVO.getRegion_name());

	        cell = row.createCell(2);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(companyVO.getRegion_detail_name());
	        
	        cell = row.createCell(3);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(companyVO.getName());
	        
	        cell = row.createCell(4);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(companyVO.getKsic_name());
	        
	        cell = row.createCell(5);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(companyVO.getStaff_number());
	        
	        cell = row.createCell(6);
	        cell.setCellStyle(bodyStyle);
	        cell.setCellValue(companyVO.getMain_product());
	    }
	    
	    // 컨텐츠 타입과 파일명 지정
	    response.setContentType("ms-vnd/excel");
	    response.setHeader("Content-Disposition", "attachment;filename=company_list.xls");

	    // 엑셀 출력
	    wb.write(response.getOutputStream());
	    wb.close();
	}	
}
