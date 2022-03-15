package com.anchordata.webframework.controller.admin.notification;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.solar.suggest.SuggestService;
import com.anchordata.webframework.service.solar.suggest.SuggestVO;
import com.anchordata.webframework.service.solar.license.LicenseVO;


@Controller("AdminSuggestController")
public class AdminSuggestController {
	
	@Autowired
	private SuggestService suggestService;
	
	@RequestMapping("/admin/notification/suggest/management")
	public ModelAndView rdtManagement(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		mv.setViewName("notification/suggest/management.admin");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 등록
	*/
	@RequestMapping("/admin/api/notification/suggest/registration")
	public ModelAndView registration(@ModelAttribute SuggestVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", suggestService.registration(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 검색 List ALL
	*/
	@RequestMapping("/admin/api/notification/suggest/all")
		public ModelAndView searchAll(@ModelAttribute SuggestVO vo, ModelAndView mv) throws Exception {
		List<SuggestVO> resList = suggestService.allList();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 변경
	*/
	@RequestMapping("/admin/api/notification/suggest/modification")
	public ModelAndView modification(@ModelAttribute SuggestVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", suggestService.modification(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 * 삭제
	 */
	@RequestMapping("/admin/api/notification/suggest/withdrawal")
	public ModelAndView withdrawal(@ModelAttribute SuggestVO vo, ModelAndView mv) throws Exception {
		mv.addObject("result", suggestService.withdrawal(vo));
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 사용 여부 변경
	*/
	@RequestMapping("/admin/api/notification/suggest/useYN")
		public ModelAndView categoryUseYN(@ModelAttribute SuggestVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", suggestService.updateUseYN(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
}
