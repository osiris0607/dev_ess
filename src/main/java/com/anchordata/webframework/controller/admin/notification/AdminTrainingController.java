package com.anchordata.webframework.controller.admin.notification;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.commonCode.CommonCodeService;
import com.anchordata.webframework.service.commonCode.CommonCodeVO;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingService;
import com.anchordata.webframework.service.solar.training.TrainingVO;


@Controller("AdminTrainingController")
public class AdminTrainingController {
	
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private TrainingService trainingService;
	
	@RequestMapping("/admin/notification/training/registration")
	public ModelAndView rdtLicenseRegistration(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
//		mv.addObject("headerMenuIndex", headerMenuIndex);
		mv.setViewName("notification/training/registration.admin");
		return mv;
	}
	
	@RequestMapping("/admin/notification/training/searchList")
	public ModelAndView rdtLicenseList(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		mv.setViewName("notification/training/searchList.admin");
		return mv;
	}
	
	@RequestMapping("/admin/notification/training/detail")
	public ModelAndView rdtDetail(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("notification/training/detail.admin");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 등록
	*/
	@RequestMapping("/admin/api/notification/training/registration")
	public ModelAndView registration(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", trainingService.registration(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 검색 List (10개씩)
	*/
	@RequestMapping("/admin/api/notification/training/search/paging")
		public ModelAndView search(@ModelAttribute TrainingSearchVO vo, ModelAndView mv) throws Exception {
		List<TrainingVO> resList = trainingService.searchList(vo);
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
	@RequestMapping("/admin/api/notification/training/all")
		public ModelAndView searchAll(@ModelAttribute TrainingSearchVO vo, ModelAndView mv) throws Exception {
		List<TrainingVO> resList = trainingService.allList();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	/**
	*  상세
	*/
	@RequestMapping("/admin/api/notification/training/search/detail")
	public ModelAndView detail(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		TrainingVO result = trainingService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 변경
	*/
	@RequestMapping("/admin/api/notification/training/modification")
	public ModelAndView modification(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		mv.addObject( "result", trainingService.modification(vo) );
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 * 삭제
	 */
	@RequestMapping("/admin/api/notification/training/withdrawal")
	public ModelAndView withdrawal(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		mv.addObject("result", trainingService.withdrawal(vo));
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	
	
	
}
