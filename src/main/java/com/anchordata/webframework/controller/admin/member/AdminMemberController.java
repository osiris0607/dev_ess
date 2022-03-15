package com.anchordata.webframework.controller.admin.member;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.member.MemberSearchVO;
import com.anchordata.webframework.service.member.MemberService;
import com.anchordata.webframework.service.member.MemberVO;

@Controller("AdminMemberController")
public class AdminMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/admin/member/password")
	public ModelAndView rdtPassword(@ModelAttribute MemberVO vo, ModelAndView mv) throws Exception {
		mv.setViewName("member/password.admin");
		return mv;
	}
	
	@RequestMapping("/admin/member/management")
	public ModelAndView rdtManagement(@ModelAttribute MemberVO vo, ModelAndView mv) throws Exception {
		mv.setViewName("member/management.admin");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 *  비밀번호 변경
	 */
	@RequestMapping(value = "/admin/api/member/changePwd", method = { RequestMethod.POST })
	public ModelAndView changePwd(@ModelAttribute MemberVO vo, HttpSession session, ModelAndView mv) throws Exception {
		Boolean result = memberService.checkMemberPwd(vo);
		
		if (result == false) {
			mv.addObject("result", "현재 비밀번호가 일치하지 않습니다. 확인 후 다시 시도해 주시기 바랍니다.");
		} else {
			int pwdResult = memberService.changePwd(vo);
			if ( pwdResult > 0 ) {
				mv.addObject("result", "패스워드 변경에 성공했습니다.");
			}
			else {
				mv.addObject("result", "패스워드 변경에 실패했습니다. 다시 시도 바랍니다.");
			}
		}

		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 *  관리자가 계정 생성
	 */
	@RequestMapping(value = "/admin/api/member/registration", method = { RequestMethod.POST })
	public ModelAndView signUp(@ModelAttribute MemberVO vo, HttpSession session, ModelAndView mv) throws Exception {
		
		int result = memberService.registration(vo);
		if (result == 404)
		{
			mv.addObject("result_msg", "이미 사용중인 아이디입니다. 다른 아이디를 선택해 주십시요.");
		}
		
		if (result == 405)
		{
			mv.addObject("result_msg", "이미 사용중인 이메일 입니다.");
		}
		
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	 *  검색
	 */
	@RequestMapping("/admin/api/member/searchList")
	public ModelAndView searchName(@ModelAttribute MemberSearchVO vo, HttpSession session, ModelAndView mv) throws Exception {
		List<MemberVO> resList = memberService.adminSearchList(vo); 
		
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
	* 사용 여부 변경
	*/
	@RequestMapping("/admin/api/member/useYN")
		public ModelAndView categoryUseYN(@ModelAttribute MemberVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", memberService.updateAdminUseYN(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	* 비밀번호 초기화
	*/
	@RequestMapping("/admin/api/member/initPassword")
		public ModelAndView initPassword(@ModelAttribute MemberVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", memberService.initPassword(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	
	
	
	
	
	
	
}
