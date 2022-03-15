package com.anchordata.webframework.controller.user.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller("UserLoginController")
public class UserLoginController {
	
	@RequestMapping("/user/login/adminLogin")
	public ModelAndView adminLogin(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) throws Exception {
		mv.setViewName("login/adminLogin");
		return mv;
	}
	
	
	/**
	 *  로그인 Success
	 */
	@RequestMapping("/user/login/success")
	public String loginSuccess() {
		return "member/loginSuccess.user";
	}
	
	/**
	 * 로그인 Fail
	 * 
	 * @return
	 */
	@RequestMapping("/user/login/fail")
	public String loginFail() {
		return "member/loginFail.user";
	}
	
	
}
