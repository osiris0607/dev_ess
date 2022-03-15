package com.anchordata.webframework.controller.user.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("UserMemberController")
public class UserMemberController {
	
	@RequestMapping("/user/member/mypage")
	public ModelAndView mypage(ModelAndView mv) throws Exception {
		mv.setViewName("member/mypage.user");
		return mv;
	}
	
}
