package com.anchordata.webframework.controller.user.company;

import java.util.List;

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


@Controller("UserCompanyController")
public class UserCompanyController {
	
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private CompanyService companyService;
	
	/**
	 * Main
	 */
	@RequestMapping("/user/company/main")
	public ModelAndView rdtSolarCompanyMain(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("company/main.user");
		return mv;
	}
	
	/**
	 * Main Mobile
	 */
	@RequestMapping("/user/company/mainMobile")
	public ModelAndView rdtSolarCompanyMainMobile(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("company/mainMobile.userSolarMobile");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 검색 List (10개씩)
	*/
	@RequestMapping("/user/api/company/search/paging")
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
	@RequestMapping("/user/api/company/search/all")
		public ModelAndView searchAllList(@ModelAttribute CompanySearchVO vo, ModelAndView mv) throws Exception {
		List<CompanyVO> resList = companyService.searchAllList(vo);
		mv.addObject("totalCount", resList.size());
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	* ALL
	*/
	@RequestMapping("/user/api/company/all")
		public ModelAndView allList(@ModelAttribute CompanySearchVO vo, ModelAndView mv) throws Exception {
		List<CompanyVO> resList = companyService.allList();
		mv.addObject("totalCount", resList.size());
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	* 검색 : 지역별 Company 갯수
	*/
	@RequestMapping("/user/api/company/search/region")
		public ModelAndView searchRegion(@ModelAttribute CompanySearchVO vo, ModelAndView mv) throws Exception {
		List<CompanyVO> resList = companyService.searchAllList(vo);
		List<CompanyVO> regionCountList = companyService.regionCount(vo);
		if (resList.size() > 0) {
			mv.addObject("result", resList);
			mv.addObject("result_region", regionCountList);
			mv.addObject("totalCount", resList.size());
		} else {
			mv.addObject("totalCount", 0);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	/**
	*  상세
	*/
	@RequestMapping("/user/api/company/detail")
	public ModelAndView detail(@ModelAttribute CompanyVO vo, ModelAndView mv) throws Exception {
		CompanyVO result = companyService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
}
