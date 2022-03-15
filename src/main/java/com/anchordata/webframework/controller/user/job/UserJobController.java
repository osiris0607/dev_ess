package com.anchordata.webframework.controller.user.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.commonCode.CommonCodeService;
import com.anchordata.webframework.service.commonCode.CommonCodeVO;
import com.anchordata.webframework.service.solar.job.JobSearchVO;
import com.anchordata.webframework.service.solar.job.JobService;
import com.anchordata.webframework.service.solar.job.JobVO;


@Controller("UserJobController")
public class UserJobController {
	
	@Autowired
	private CommonCodeService commonCodeService;
	@Autowired
	private JobService jobService;
	
	/**
	 * 일자리 Main
	 */
	@RequestMapping("/user/job/main")
	public ModelAndView rdtSolarJobMain(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("job/main.user");
		return mv;
	}
	
	/**
	 * 일자리 Mobile Main
	 */
	@RequestMapping("/user/mobile/job/mainMobile")
	public ModelAndView rdtSolarMobileJobMain(ModelAndView mv) throws Exception {
		mv.setViewName("job/mainMobile.userSolarMobile");
		return mv;
	}
	
	
	/**
	 * 일자리 사전  Main
	 */
	@RequestMapping("/user/jobDictionary/main")
	public ModelAndView rdtSearchList(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("jobDictionary/main.user");
		return mv;
	}
	
	/**
	 * 일자리 사전 Mobile Main
	 */
	@RequestMapping("/user/mobile/jobDictionary/mainMobile")
	public ModelAndView rdtSolarMobileJobDicMain(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		List<CommonCodeVO> result = commonCodeService.selectListAllUseYN();
		mv.addObject("commonCode", result);
		mv.setViewName("jobDictionary/mainMobile.userSolarMobile");
		return mv;
	}
	
	/**
	 * 일자리 사전 상세
	 */
	@RequestMapping("/user/jobDictionary/detail")
	public ModelAndView rdtSolarJobDictionaryDetail(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("jobDictionary/detail.user");
		return mv;
	}
	
	
	/**
	 * 유망 일자리
	 */
	@RequestMapping("/user/jobPromising/main")
	public ModelAndView rdtSolarMobileJobPromisingMain(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("jobPromising/main.user");
		return mv;
	}
	
	/**
	 * 유망 일자리 모바일
	 */
	@RequestMapping("/user/jobPromising/mainMobile")
	public ModelAndView rdtSolarMobileJobPromisingMobileMain(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("jobPromising/mainMobile.userSolarMobile");
		return mv;
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	
	/**
	* All List. not paging
	*/
	@RequestMapping("/user/api/job/all")
		public ModelAndView allList(@ModelAttribute JobSearchVO vo, ModelAndView mv) throws Exception {
		List<JobVO> resList = jobService.allList();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}	
	
	
	/**
	* All Search List. not paging
	*/
	@RequestMapping("/user/api/job/search/all")
		public ModelAndView allSearchList(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		List<JobVO> resList = jobService.searchAllList(vo);
		mv.addObject("totalCount", resList.size());
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	* 검색 List (9개씩)
	*/
	@RequestMapping("/user/api/job/search/paging")
		public ModelAndView search(@ModelAttribute JobSearchVO vo, ModelAndView mv) throws Exception {
		List<JobVO> resList = jobService.searchList(vo, 9);
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
	*  상세
	*/
	@RequestMapping("/user/api/job/detail")
	public ModelAndView detail(@ModelAttribute JobVO vo, ModelAndView mv) throws Exception {
		JobVO result = jobService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	/**
	* 유망 일자리 All List
	*/
	@RequestMapping("/user/api/jobTop/all")
		public ModelAndView allJobTopSearchList(@ModelAttribute JobSearchVO vo, ModelAndView mv) throws Exception {
		List<JobVO> resList = jobService.allListRank();
		mv.addObject("result", resList);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
}
