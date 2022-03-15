package com.anchordata.webframework.controller.admin.home;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.solar.job.JobVO;



@Controller("AdminHomeController")
public class AdminHomeController {
	
	@RequestMapping("/admin/home/management")
	public ModelAndView rdtManagement(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		mv.setViewName("home/management.admin");
		return mv;
	}
	
}
