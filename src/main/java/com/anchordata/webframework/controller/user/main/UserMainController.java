package com.anchordata.webframework.controller.user.main;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("UserMainController")
public class UserMainController {
	
	
	/**
	 * Main
	 */
	@RequestMapping("/user/main/main")
	public ModelAndView rdtSolarMain(ModelAndView mv) throws Exception {
		mv.setViewName("main/main.user");
		return mv;
	}
	
	
	/**
	 * Main
	 */
	@RequestMapping("/user/mobile/main/mainMobile")
	public ModelAndView rdtMobileSolarMain(ModelAndView mv) throws Exception {
		mv.setViewName("main/mainMobile.userSolarMobile");
		return mv;
	}
}
