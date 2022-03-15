package com.anchordata.webframework.controller.user.lecture;

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
import com.anchordata.webframework.service.notice.NoticeVO;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingService;
import com.anchordata.webframework.service.solar.training.TrainingVO;


@Controller("UserLectureController")
public class UserLectureController {
	
	
	@Autowired
	private TrainingService trainingService;
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private LicenseService licenseService;
	
	
	/**
	 * 온라인 강의
	 */
	@RequestMapping("/user/lecture/online")
	public ModelAndView rdtOnline(@ModelAttribute NoticeVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("lecture/online.user");
		return mv;
	}
	
	
	/**
	 * 온라인 강의 Mobile
	 */
	@RequestMapping("/user/lecture/onlineMobile")
	public ModelAndView rdtOnlineMobile(@ModelAttribute NoticeVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("lecture/onlineMobile.userSolarMobile");
		return mv;
	}
	
	/**
	 * 훈련 기관
	 */
	@RequestMapping("/user/lecture/training")
	public ModelAndView rdtTraining(@ModelAttribute NoticeVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("lecture/training.user");
		return mv;
	}
	
	/**
	 * 훈련 기관  Mobile
	 */
	@RequestMapping("/user/lecture/trainingMobile")
	public ModelAndView rdttrainingMobile(@ModelAttribute NoticeVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("lecture/trainingMobile.userSolarMobile");
		return mv;
	}
	
	/**
	 * 훈련 기관 상세
	 */
	@RequestMapping("/user/lecture/training/detail")
	public ModelAndView rdtDetail(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("lecture/trainingDetail.user");
		return mv;
	}
	
	/**
	 * 자격증
	 */
	@RequestMapping("/user/lecture/license")
	public ModelAndView rdtLicense(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.addObject("vo", vo);
		mv.setViewName("lecture/license.user");
		return mv;
	}
	
	/**
	 * 자격증 Mobile
	 */
	@RequestMapping("/user/lecture/licenseMobile")
	public ModelAndView rdtLicenseMobie(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.addObject("vo", vo);
		mv.setViewName("lecture/licenseMobile.userSolarMobile");
		return mv;
	}
	
	/**
	 * 자격증 상세
	 */
	@RequestMapping("/user/lecture/license/detail")
	public ModelAndView rdtLicenseDetail(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.addObject("vo", vo);
		mv.setViewName("lecture/licenseDetail.user");
		return mv;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	/**
	* 훈련 기관 검색 List (9개씩)
	*/
	@RequestMapping("/user/api/lecture/training/search/paging")
		public ModelAndView search(@ModelAttribute TrainingSearchVO vo, ModelAndView mv) throws Exception {
		List<TrainingVO> resList = trainingService.searchList(vo, 9);
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
	* 훈련 기관 검색 List All
	*/
	@RequestMapping("/user/api/lecture/training/search/all")
		public ModelAndView searchAll(@ModelAttribute TrainingSearchVO vo, ModelAndView mv) throws Exception {
		List<TrainingVO> resList = trainingService.searchAllList(vo);
		if (resList.size() > 0) {
			mv.addObject("result", resList);
			mv.addObject("totalCount", resList.size());
		} else {
			mv.addObject("totalCount", 0);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	*  훈련 기관 상세
	*/
	@RequestMapping("/user/api/lecture/training/search/detail")
	public ModelAndView detail(@ModelAttribute TrainingVO vo, ModelAndView mv) throws Exception {
		TrainingVO result = trainingService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 자격증 검색 List (10개씩)
	*/
	@RequestMapping("/user/api/lecture/license/search/paging")
		public ModelAndView search(@ModelAttribute LicenseSearchVO vo, ModelAndView mv) throws Exception {
		List<LicenseVO> resList = licenseService.searchList(vo, 9);
		
		// 검색된 Data Row의 갯수와 검색된 Data Row의 License Type별 갯수 전달해야 한다.
		int countByDataRow = licenseService.countByDataRow(vo);
		int countBylicenseType = licenseService.countBylicenseType(vo);
		
		if (resList.size() > 0) {
			mv.addObject("result", resList);
			mv.addObject("totalCount", countByDataRow);
			mv.addObject("totalCountByLicenseType", countBylicenseType);
		} else {
			mv.addObject("totalCount", 0);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 자격증 검색 List All
	*/
	@RequestMapping("/user/api/lecture/license/search/all")
		public ModelAndView searchAllList(@ModelAttribute LicenseSearchVO vo, ModelAndView mv) throws Exception {
		List<LicenseVO> resList = licenseService.searchAllList(vo);
		
		// 검색된 Data Row의 갯수와 검색된 Data Row의 License Type별 갯수 전달해야 한다.
		int countByDataRow = licenseService.countByDataRow(vo);
		int countBylicenseType = licenseService.countBylicenseType(vo);
		
		if (resList.size() > 0) {
			mv.addObject("result", resList);
			mv.addObject("totalCount", countByDataRow);
			mv.addObject("totalCountByLicenseType", countBylicenseType);
		} else {
			mv.addObject("totalCount", 0);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	*  자격증  상세
	*/
	@RequestMapping("/user/api/lecture/license/search/detail")
	public ModelAndView detail(@ModelAttribute LicenseVO vo, ModelAndView mv) throws Exception {
		LicenseVO result = licenseService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
}
