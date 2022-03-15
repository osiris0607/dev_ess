package com.anchordata.webframework.controller.admin.notification;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.solar.faq.FAQSearchVO;
import com.anchordata.webframework.service.solar.faq.FAQService;
import com.anchordata.webframework.service.solar.faq.FAQVO;


@Controller("AdminFAQController")
public class AdminFAQController {
	
	@Autowired
	private FAQService faqService;
	
	@RequestMapping("/admin/notification/faq/registration")
	public ModelAndView rdtLicenseRegistration(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		mv.setViewName("notification/faq/registration.admin");
		return mv;
	}
	
	@RequestMapping("/admin/notification/faq/searchList")
	public ModelAndView rdtLicenseList(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		mv.setViewName("notification/faq/searchList.admin");
		return mv;
	}
	
	@RequestMapping("/admin/notification/faq/detail")
	public ModelAndView rdtDetail(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("notification/faq/detail.admin");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 등록
	*/
	@RequestMapping("/admin/api/notification/faq/registration")
	public ModelAndView registration(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", faqService.registration(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 검색 List (10개씩)
	*/
	@RequestMapping("/admin/api/notification/faq/search/paging")
		public ModelAndView search(@ModelAttribute FAQSearchVO vo, ModelAndView mv) throws Exception {
		List<FAQVO> resList = faqService.searchList(vo);
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
	@RequestMapping("/admin/api/notification/faq/all")
		public ModelAndView searchAll(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		List<FAQVO> resList = faqService.allList();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	/**
	*  상세
	*/
	@RequestMapping("/admin/api/notification/faq/search/detail")
	public ModelAndView detail(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		FAQVO result = faqService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 변경
	*/
	@RequestMapping("/admin/api/notification/faq/modification")
	public ModelAndView modification(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", faqService.modification(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 * 삭제
	 */
	@RequestMapping("/admin/api/notification/faq/withdrawal")
	public ModelAndView withdrawal(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		mv.addObject("result", faqService.withdrawal(vo));
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	
	
}
