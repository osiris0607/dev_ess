package com.anchordata.webframework.controller.admin.notification;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.commonCode.CommonCodeService;
import com.anchordata.webframework.service.commonCode.CommonCodeVO;
import com.anchordata.webframework.service.solar.license.LicenseSearchVO;
import com.anchordata.webframework.service.solar.license.LicenseService;
import com.anchordata.webframework.service.solar.license.LicenseVO;


@Controller("AdminLicenseController")
public class AdminLicenseController {
	
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private LicenseService licenseService;
	
	@RequestMapping("/admin/notification/license/registration")
	public ModelAndView rdtLicenseRegistration(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
//		mv.addObject("headerMenuIndex", headerMenuIndex);
		mv.setViewName("notification/license/registration.admin");
		return mv;
	}
	
	@RequestMapping("/admin/notification/license/searchList")
	public ModelAndView rdtLicenseList(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("notification/license/searchList.admin");
		return mv;
	}
	
	@RequestMapping("/admin/notification/license/detail")
	public ModelAndView rdtDetail(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.addObject("vo", vo);
		mv.setViewName("notification/license/detail.admin");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 등록
	*/
	@RequestMapping("/admin/api/notification/license/registration")
	public ModelAndView registration(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", licenseService.registration(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 검색 List (10개씩)
	*/
	@RequestMapping("/admin/api/notification/license/search/paging")
		public ModelAndView search(@ModelAttribute LicenseSearchVO vo, ModelAndView mv) throws Exception {
		List<LicenseVO> resList = licenseService.searchList(vo);
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
	@RequestMapping("/admin/api/notification/license/all")
		public ModelAndView searchAll(@ModelAttribute LicenseSearchVO vo, ModelAndView mv) throws Exception {
		List<LicenseVO> resList = licenseService.allList();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	/**
	*  상세
	*/
	@RequestMapping("/admin/api/notification/license/search/detail")
	public ModelAndView detail(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		LicenseVO result = licenseService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 변경
	*/
	@RequestMapping("/admin/api/notification/license/modification")
	public ModelAndView modification(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", licenseService.modification(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 * 삭제
	 */
	@RequestMapping("/admin/api/notification/license/withdrawal")
	public ModelAndView withdrawal(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		mv.addObject("result", licenseService.withdrawal(vo));
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	
	
}
