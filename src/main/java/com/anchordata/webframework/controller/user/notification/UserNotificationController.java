package com.anchordata.webframework.controller.user.notification;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.service.solar.faq.FAQSearchVO;
import com.anchordata.webframework.service.solar.faq.FAQService;
import com.anchordata.webframework.service.solar.faq.FAQVO;
import com.anchordata.webframework.service.solar.training.TrainingSearchVO;
import com.anchordata.webframework.service.solar.training.TrainingVO;
import com.anchordata.webframework.service.notice.NoticeVO;
import com.anchordata.webframework.service.solar.trend.TrendSearchVO;
import com.anchordata.webframework.service.solar.trend.TrendService;
import com.anchordata.webframework.service.solar.trend.TrendVO;
import com.anchordata.webframework.service.uploadFile.UploadFileService;
import com.anchordata.webframework.service.uploadFile.UploadFileVO;


@Controller("UserNotificationController")
public class UserNotificationController {
	
	
	@Autowired
	private TrendService trendService;
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private FAQService faqService;
	
	
	/**
	 * ESS산업 동향 리스트
	 */
	@RequestMapping("/user/notification/trend")
	public ModelAndView rdtTrend(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		
		mv.addObject("vo", vo);
		mv.setViewName("notification/trend.user");
		return mv;
	}
	
	
	/**
	 * ESS산업 동향 리스트 Mobile
	 */
	@RequestMapping("/user/notification/trendMobile")
	public ModelAndView rdtTrendMobile(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		
		mv.addObject("vo", vo);
		mv.setViewName("notification/trendMobile.userSolarMobile");
		return mv;
	}
	
	
	/**
	 * ESS산업 동향 상세
	 */
	@RequestMapping("/user/notification/trend/detail")
	public ModelAndView rdtTrendDetail(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("notification/trendDetail.user");
		return mv;
	}
	
	
	/**
	 * ESS산업 동향 상세 모바일
	 */
	@RequestMapping("/user/notification/trend/detailMobile")
	public ModelAndView rdtTrendDetailMobile(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("notification/trendDetailMobile.userSolarMobile");
		return mv;
	}
	
	
	/**
	 * FAQ
	 */
	@RequestMapping("/user/notification/faq")
	public ModelAndView rdtFaq(@ModelAttribute NoticeVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("notification/faq.user");
		return mv;
	}
	
	
	/**
	 * FAQ Mobile
	 */
	@RequestMapping("/user/notification/faqMobile")
	public ModelAndView rdtFaqMobile(@ModelAttribute NoticeVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("notification/faqMobile.userSolarMobile");
		return mv;
	}
	
	
	/**
	 * 건의사항
	 */
	@RequestMapping("/user/notification/suggest")
	public ModelAndView rdtSuggest(@ModelAttribute NoticeVO vo, ModelAndView mv) throws Exception {
		mv.addObject("vo", vo);
		mv.setViewName("notification/suggest.user");
		return mv;
	}

	
	///////////////////////////////////////////////////////////////////////////
	// OPEN API 호출
	///////////////////////////////////////////////////////////////////////////
	
	/**
	* ESS산업 동향 검색 List (10개씩)
	*/
	@RequestMapping("/user/api/notification/trend/search/paging")
		public ModelAndView search(@ModelAttribute TrendSearchVO vo, ModelAndView mv) throws Exception {
		List<TrendVO> resList = trendService.searchList(vo);
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
	* ESS산업 동향 검색 List All
	*/
	@RequestMapping("/user/api/notification/trend/search/all")
		public ModelAndView searchAll(@ModelAttribute TrendSearchVO vo, ModelAndView mv) throws Exception {
		List<TrendVO> resList = trendService.searchAllList(vo);
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
	*  ESS산업 동향 검색 상세
	*/
	@RequestMapping("/user/api/notification/trend/search/detail")
	public ModelAndView detail(@ModelAttribute TrendVO vo, ModelAndView mv) throws Exception {
		TrendVO result = trendService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	 *  ESS산업 동향 이전글 다음글 검색
	 * @return
	 */
	@RequestMapping(value = "/user/api/trend/preNext")
	public ModelAndView previousNext(@ModelAttribute TrendSearchVO vo, ModelAndView mv) throws Exception {
		List<TrendVO> resList	= trendService.preNextList(vo); 
		
		Map<String,Object> returnMap = new HashMap<String, Object>();
		
		if ( resList.size() == 0 ) 
		{
			returnMap.put("pre", null);
			returnMap.put("next", null);
		}
		else if (resList.size() >= 2 ) {
			returnMap.put("next", resList.get(0));
			returnMap.put("pre", resList.get(1));
		}
		else {
			if ( Integer.parseInt(resList.get(0).getTrend_id()) < Integer.parseInt(vo.getTrend_id()) ) {
				returnMap.put("pre", null);
				returnMap.put("next", resList.get(0));
			}
			else {
				returnMap.put("pre", resList.get(0));
				returnMap.put("next", null);
			}
		}

		mv.addObject("result", returnMap);
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	/**
	*  ESS산업 동향 검색 첨부파일 다운로드
	* @return
	*/
	@RequestMapping(value = "/user/api/trend/download/{file_id}")
	public void downloadFile (@PathVariable("file_id") String fileId, HttpSession session, HttpServletResponse response) throws Exception {
		UploadFileVO UploadFileVO = new UploadFileVO();
		UploadFileVO.setFile_id(Integer.parseInt(fileId));
		UploadFileVO = uploadFileService.selectUploadFileContent(UploadFileVO);
		
		response.setContentType("application/octet-stream"); 
		response.setContentLength(UploadFileVO.getBinary_content().length); 
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(UploadFileVO.getName(),"UTF-8")+"\";"); 
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.getOutputStream().write(UploadFileVO.getBinary_content()); 
		response.getOutputStream().flush(); 
		response.getOutputStream().close(); 
	}
	
	
	/**
	* FAQ 검색 List (10개씩)
	*/
	@RequestMapping("/user/api/notification/faq/search/paging")
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
	* FAQ 검색 Lis All
	*/
	@RequestMapping("/user/api/notification/faq/search/all")
		public ModelAndView searchAllList(@ModelAttribute FAQSearchVO vo, ModelAndView mv) throws Exception {
		List<FAQVO> resList = faqService.searchAllList(vo);
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
	*  FAQ 상세
	*/
	@RequestMapping("/user/api/notification/faq/search/detail")
	public ModelAndView detail(@ModelAttribute FAQVO vo, ModelAndView mv) throws Exception {
		FAQVO result = faqService.detail(vo);
		mv.addObject("result", result);
		mv.setViewName("jsonView");
		return mv;
	}
}
