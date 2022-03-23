<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>




<!-- sidemenu -->
<div class="sidebar" style="display:block;">
  <button class="sideClose">X</button>
  <ul class="accodion">
    <li><a href="/user/mobile/main/mainMobile">홈</a></li>
    <li><a href="/user/mobile/job/mainMobile">ESS산업 일자리</a></li>
    <li><a href="/user/mobile/jobDictionary/mainMobile">ESS산업 일자리 사전</a></li>
    <li><a href="/user/jobPromising/mainMobile">ESS산업 유망 일자리</a></li>
    <li><a href="/user/company/mainMobile">ESS산업 기업지도</a></li>
    <li><a href="javascript:void(0)" class="item-toggle">교육&middot;자격증</a>
      <div class="accodion-content item-hide">
        <ul>            
	<li><a href="/user/lecture/onlineMobile">온라인 강의</a></li>
          <li><a href="/user/lecture/trainingMobile">훈련기관</a></li>
          <li><a href="/user/lecture/licenseMobile">자격증</a></li>
        </ul>
      </div>
    </li>
    <li><a href="javascript:void(0)" class="item-toggle">자료실&middot;게시판</a>
      <div class="accodion-content item-hide">
        <ul>
          <li><a href="/user/notification/trendMobile">자료실</a></li>
          <li><a href="/user/notification/faqMobile">게시판</a></li>
          <!--li><a href="suggest.html">건의하기</a></li-->
        </ul>
      </div>
    </li>
  </ul>
</div>