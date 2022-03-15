package com.anchordata.webframework.controller.admin.notification;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.commonCode.CommonCodeService;
import com.anchordata.webframework.service.commonCode.CommonCodeVO;
import com.anchordata.webframework.service.solar.license.LicenseVO;
import com.anchordata.webframework.service.solar.trend.TrendSearchVO;
import com.anchordata.webframework.service.solar.trend.TrendService;
import com.anchordata.webframework.service.solar.trend.TrendVO;


@Controller("AdminTrendController")
public class AdminTrendController {
	
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private TrendService trendService;
	
	@RequestMapping("/admin/notification/trend/registration")
	public ModelAndView rdtLicenseRegistration(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
//		mv.addObject("headerMenuIndex", headerMenuIndex);
		mv.setViewName("notification/trend/registration.admin");
		return mv;
	}
	
	@RequestMapping("/admin/notification/trend/searchList")
	public ModelAndView rdtLicenseList(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		mv.setViewName("notification/trend/searchList.admin");
		return mv;
	}
	
	@RequestMapping("/admin/notification/trend/detail")
	public ModelAndView rdtDetail(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("notification/trend/detail.admin");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 등록
	*/
	@RequestMapping("/admin/api/notification/trend/registration")
	public ModelAndView registration(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", trendService.registration(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 검색 List (10개씩)
	*/
	@RequestMapping("/admin/api/notification/trend/search/paging")
		public ModelAndView search(@ModelAttribute TrendSearchVO vo, ModelAndView mv) throws Exception {
		List<TrendVO> resList = trendService.searchList(vo);
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
	@RequestMapping("/admin/api/notification/trend/all")
		public ModelAndView searchAll(@ModelAttribute TrendSearchVO vo, ModelAndView mv) throws Exception {
		List<TrendVO> resList = trendService.allList();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	/**
	*  상세
	*/
	@RequestMapping("/admin/api/notification/trend/search/detail")
	public ModelAndView detail(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		TrendVO result = trendService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 변경
	*/
	@RequestMapping("/admin/api/notification/trend/modification")
	public ModelAndView modification(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", trendService.modification(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 * 삭제
	 */
	@RequestMapping("/admin/api/notification/trend/withdrawal")
	public ModelAndView withdrawal(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		mv.addObject("result", trendService.withdrawal(vo));
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	
	
}
