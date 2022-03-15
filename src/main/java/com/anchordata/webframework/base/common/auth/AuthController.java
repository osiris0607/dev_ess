/*******************************************************************************
 *
 * Copyright 2019 namu C&D corp. All rights reserved.
 *
 * This is a proprietary software of namu C&D corp, and you may not use this file except in
 * compliance with license agreement with namu C&D corp. Any redistribution or use of this
 * software, with or without modification shall be strictly prohibited without prior written
 * approval of namu C&D corp, and the copyright notice above does not evidence any actual or
 * intended publication of such software.
 *
 *******************************************************************************/
package com.anchordata.webframework.base.common.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anchordata.webframework.base.common.Constants;
import com.anchordata.webframework.base.common.auth.service.AuthService;
import com.anchordata.webframework.base.common.auth.util.KakaoAuthProcess;
import com.anchordata.webframework.base.common.auth.vo.UserVO;
import com.anchordata.webframework.base.util.JsonStringParse;
import com.anchordata.webframework.base.util.naver.NaverLoginBO;
import com.anchordata.webframework.service.main.MainService;
import com.github.scribejava.core.model.OAuth2AccessToken;

@Controller("authController")
public class AuthController {

	@Autowired
	public AuthService authService;
	@Autowired
	public MainService mainService;
	

	private JsonStringParse jsonparse = new JsonStringParse();

	
	@Value("${spring.google.recaptcha.siteKey}")
	private String siteKey;
	@Value("${etc.login.kakao_redirection_url}")
	private String kakaoRdt;
	@Value("${etc.login.kakao_faq_redirection_url}")
	private String kakaoFaqRdt;
	@Value("${etc.login.kakao_policy_redirection_url}")
	private String kakaoPolicyRdt;
	@Value("${etc.login.kakao_actShare_redirection_url}")
	private String kakaoActShareRdt;
	

	// 구글 Callback호출 메소드
	@RequestMapping(value = "/authCallback", method = { RequestMethod.GET, RequestMethod.POST })
	public String googleCallback(Authentication auth, HttpSession session, ModelMap model, HttpServletRequest request)
			throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object pricipal = authentication.getPrincipal();
		// 겟프린시펄... 떼문에 이렇게 함
		if (pricipal != null) {
			if (auth != null) {
				if (pricipal instanceof UserVO) {
					UserVO user = (UserVO) pricipal;
					if (StringUtils.equals("S", user.getAuthLevel())) {
						return "redirect:/admin";
					}
				} else {
					authService.setAccountInfoGoogle(auth);
				}
			}

			return "main/main.user";
		}
		return "redirect:/main";
	}

	@RequestMapping("/")
	public ModelAndView index(Authentication auth, HttpSession session, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, ModelAndView mv) throws Exception {

		System.out.println("index !!!!");

		String userAgent = request.getHeader("user-agent");
		boolean mobile1 = userAgent.matches( ".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
		boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*"); 
		
		
		if ( request.getServerName().contains("jobnavim.ketep.re.kr") || mobile1 || mobile2) {
			mv.setViewName("redirect:http://jobnavim.ketep.re.kr/gateMobile");
		}
		else {
			mv.setViewName("redirect:/gate");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/gate")
	public ModelAndView indexGate(Authentication auth, HttpSession session, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, ModelAndView mv) throws Exception {

		System.out.println("gate !!!!");
		
		// 방문자 수 계산
		// Cookie를 이용하여 최대한 중복 방문자를 막는다.
		Cookie[] cookies = request.getCookies();
		
		
		// 비교하기 위해 새로운 쿠키
        Cookie viewCookie = null;
 
        // 쿠키가 있을 경우 
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌 
                if (cookies[i].getName().equals("cookie_youth_starter")) { 
                    System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
                    viewCookie = cookies[i];
                }
            }
        }
                
        // 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
        if (viewCookie == null) {    
            System.out.println("cookie 없음");
            
            // 쿠키 값 - 오늘 날짜.
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date toDay = new Date();
            String toDate = mSimpleDateFormat.format( toDay );
            // 쿠키 생성(이름, 값)
            Cookie newCookie = new Cookie("cookie_youth_starter", toDate );
            // 쿠키 추가
            response.addCookie(newCookie);
            //조회수 증가
            mainService.addVisitCounter();
        }
        // viewCookie가 null이 아닐경우 쿠키 값인 날짜비교. 날짜가 지났으면 Counter 증가. 
        else {
            // 쿠키 값 받아옴.
            String value = viewCookie.getValue();
            // 오늘 날짜
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date toDay = new Date();
            String toDate = mSimpleDateFormat.format( toDay );
            // 날짜 비교
            // 날짜가 다르면 Couter 증가.
            // 오늘 날짜를 새로운 쿠키 값으로 저장 후 전송
            if (StringUtils.equals(value, toDate) == false ) {
            	// 쿠키 생성(이름, 값)
                Cookie newCookie = new Cookie("cookie_youth_starter", toDate );
                // 쿠키 추가
                response.addCookie(newCookie);
                //조회수 증가
                mainService.addVisitCounter();
            }
        }
		
		mv.setViewName("user/gate");
		return mv;
	}		

	@RequestMapping(value = "/gateMobile")
	public ModelAndView indexGateMobile(Authentication auth, HttpSession session, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, ModelAndView mv) throws Exception {

		System.out.println("gateMobile !!!!");
		
		// 방문자 수 계산
		// Cookie를 이용하여 최대한 중복 방문자를 막는다.
		Cookie[] cookies = request.getCookies();
		
		
		// 비교하기 위해 새로운 쿠키
        Cookie viewCookie = null;
 
        // 쿠키가 있을 경우 
        if (cookies != null && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                // Cookie의 name이 cookie + reviewNo와 일치하는 쿠키를 viewCookie에 넣어줌 
                if (cookies[i].getName().equals("cookie_youth_starter")) { 
                    System.out.println("처음 쿠키가 생성한 뒤 들어옴.");
                    viewCookie = cookies[i];
                }
            }
        }
                
        // 만일 viewCookie가 null일 경우 쿠키를 생성해서 조회수 증가 로직을 처리함.
        if (viewCookie == null) {    
            System.out.println("cookie 없음");
            
            // 쿠키 값 - 오늘 날짜.
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date toDay = new Date();
            String toDate = mSimpleDateFormat.format( toDay );
            // 쿠키 생성(이름, 값)
            Cookie newCookie = new Cookie("cookie_youth_starter", toDate );
            // 쿠키 추가
            response.addCookie(newCookie);
            //조회수 증가
            mainService.addVisitCounter();
        }
        // viewCookie가 null이 아닐경우 쿠키 값인 날짜비교. 날짜가 지났으면 Counter 증가. 
        else {
            // 쿠키 값 받아옴.
            String value = viewCookie.getValue();
            // 오늘 날짜
            SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
            Date toDay = new Date();
            String toDate = mSimpleDateFormat.format( toDay );
            // 날짜 비교
            // 날짜가 다르면 Couter 증가.
            // 오늘 날짜를 새로운 쿠키 값으로 저장 후 전송
            if (StringUtils.equals(value, toDate) == false ) {
            	// 쿠키 생성(이름, 값)
                Cookie newCookie = new Cookie("cookie_youth_starter", toDate );
                // 쿠키 추가
                response.addCookie(newCookie);
                //조회수 증가
                mainService.addVisitCounter();
            }
        }
		
		mv.setViewName("user/gateMobile");
		return mv;
	}	
	

	/*
	 * @GetMapping("/member/login") public String login(Authentication auth,
	 * HttpSession session, ModelMap model, HttpServletRequest request) {
	 * 
	 * System.out.println("login!!!!!");
	 * 
	 * if (auth != null && auth.isAuthenticated()) {
	 * 
	 * return "main/main.user"; } else {
	 * 
	 * Cookie[] cookies = request.getCookies(); // 荑좏궎瑜� �꽌踰꾩뿉�꽌 �슂泥��븳�떎. String
	 * remember_me = ""; if (cookies != null) { for (int i = 0; i < cookies.length;
	 * i++) { if (cookies[i].getName().equals("remember_me")) { remember_me =
	 * cookies[i].getValue(); break; } } model.addAttribute("remember_me",
	 * remember_me); }
	 * 
	 * NaverLoginBO naverLoginBO = new NaverLoginBO(); String naverAuthUrl =
	 * naverLoginBO.getAuthorizationUrl(session); model.addAttribute("naverAuthUrl",
	 * naverAuthUrl);
	 * 
	 * google recaptcha model.addAttribute("siteKey", siteKey);
	 * 
	 * return "member/login.user"; } }
	 */

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/main";
	}

	@RequestMapping(value = "/logoutJson")
	public ModelAndView loout(HttpServletRequest request, HttpServletResponse response, ModelAndView mv)
			throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}

		mv.addObject("result", Constants.SUCCESS);
		mv.setViewName("jsonView");
		return mv;
	}

	@RequestMapping("/login/kakao")
	public ModelAndView loginKakao(@RequestParam HashMap<String, String> paramMap, ModelAndView mv,	HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		String redirectUrl = kakaoRdt;
		String accessToken  = KakaoAuthProcess.getAccessToken(paramMap.get("code").toString(), redirectUrl);
		HashMap<String, Object> userInfo = KakaoAuthProcess.getKakaoUser(accessToken);
		
	    if (userInfo.get("email") == null) {
	    	mv.addObject("result", Constants.FAIL);
	    	mv.setViewName("login/kakaoLogin");
	    }
	    else {
			// Kakao 계정 로그인
	    	UserVO vo = new UserVO();
	    	// Email 설징
	    	vo.setEmail(userInfo.get("email").toString());
	    	// 권한 설정
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_MEMBER"));
			vo.setAuthorities(authorities);
	    	// 계정 등록
			KakaoAuthProcess.setKakaoIdIntoAccount(vo);
	    	
			mv.addObject("result", Constants.SUCCESS);
	    	mv.setViewName("main/main.user");
	    }

		return mv;
	}
	
	
	
	@RequestMapping("/kakaoLogin")
	public ModelAndView kakaoLogin(@RequestParam HashMap<String, String> paramMap, ModelAndView mv,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		String targetUrl = "";
		JsonNode profile = getKakaoUserInfo(paramMap.get("access_token").toString());
		System.out.println(profile);
		UserVO vo = changeData(profile);

		/*
		 * 데이터컴은 그냥 이메일 없이 로그인시키는 걸로... if (StringUtils.equals(vo.getEmailAuthYn(), "N")
		 * ) { targetUrl = "verifyEmail"; } else { targetUrl = "main"; }
		 */
		targetUrl = "main";
		// 얻어온정보고 로그인시키고
		authService.setAccountInfoKakao(vo);

		mv.addObject("profile", vo);
		mv.addObject("targetUrl", targetUrl);
		mv.setViewName("jsonView");
		return mv;
	}
	

	
	public static JsonNode getKakaoUserInfo(String autorizeCode) {
		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";

		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);

		// add header
		post.addHeader("Authorization", "Bearer " + autorizeCode);
		JsonNode returnNode = null;

		try {

			final HttpResponse response = client.execute(post);
			final int responseCode = response.getStatusLine().getStatusCode();
			System.out.println("\nSending 'POST' request to URL : " + RequestUrl);
			System.out.println("Response Code : " + responseCode);

			// JSON
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(response.getEntity().getContent());

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clear resources
		}

		return returnNode;
	}

	public UserVO changeData(JsonNode userInfo) {
		UserVO vo = new UserVO();
		vo.setUserKey(userInfo.path("id").asText());

		if (userInfo.path("kaccount_email_verified").asText().equals("true")) {
			vo.setEmail(userInfo.path("kaccount_email").asText());
			vo.setEmailAuthYn("Y");
		} else {
			vo.setEmail("");
			vo.setEmailAuthYn("N");
		}

		JsonNode properties = userInfo.path("properties");
		if (properties.has("nickname")) {
			vo.setName(properties.path("nickname").asText());
		}

		vo.setSnsDivi(Constants.KAKAO);

		return vo;
	}

	@RequestMapping(value = "/naverLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws Exception {
		System.out.println("네이버 callback");
		NaverLoginBO naverLoginBO = new NaverLoginBO();
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		String apiResult = naverLoginBO.getUserProfile(oauthToken);
		System.out.println(naverLoginBO.getUserProfile(oauthToken).toString());
		System.out.println("result" + apiResult);

		authService.setAccountInfoNaver(jsonparse.stringToJson(apiResult, "response"));

		return "main/main.user";
	}
}
