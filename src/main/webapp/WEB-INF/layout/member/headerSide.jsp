<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



<script>
	$(document).ready(function() {
	
		$("#logout_btn").on("click", function(){
			if (confirm("로그아웃 하시겠습니까?")) {
				var comAjax = new ComAjax();
				comAjax.setUrl("<c:url value='/logoutJson' />");
				comAjax.setCallback("mainLoginCallback");
				comAjax.ajax();
			}
		});
	});

	function mainLoginCallback(data) {
		if (data.result = "SUCCESS") {
			alert("로그아웃되었습니다.")
			location.href = "/";
		}
	}
</script>

<!-- [S] Header -->
<header>
	<h1><a href="/"><img src="/assets/img/logo_01@2x.png" alt="경기도 청소년참여플랫폼 청출어람"></a></h1>
	<div class="gnb" id="nav">
		<ul role="menubar" class="menu2depth--allsub aui-allsub">
			<li class="on">
				<a href="#" class="dep1" role="menuitem"><span>소개</span></a>
				<ul role="menu">
					<li role="none"><a href="/user/introduction/youthPolicyParticipationCommittees" role="menuitem">청소년참여기구</a></li>
					<li role="none"><a href="/user/introduction/cheongChulEomram" role="menuitem">청출어람</a></li>
					<li role="none"><a href="/user/introduction/managementAgency" role="menuitem">운영기관</a></li>
				</ul>
	
			</li>
			<li class="">
				<a href="#" class="dep1" role="menuitem"><span>정보공유</span></a>
				<ul role="menu">
					<li role="none"><a href="/user/notice/list" role="menuitem">공지사항</a></li>
					<li role="none"><a href="/user/faq/list" role="menuitem">질의응답</a></li>
					<li role="none"><a href="/user/actShare/list" role="menuitem">활동공유</a></li>
				</ul>
	
			</li>
			<li><a href="#" class="dep1" role="menuitem"><span>正말正책</span></a>
				<ul role="menu">
					<li role="none"><a href="/user/policy/list" role="menuitem">정책제안</a></li>
					<li role="none"><a href="/user/policyResult/list" role="menuitem">제안결과</a></li>
				</ul>
			</li>
			<li class="cs"><a href="#" class="dep1" role="menuitem"><span>자료실</span></a>
				<ul role="menu">
					<li role="none"><a href="/user/pds/education/reportList" role="menuitem">교육관</a></li>
					<li role="none"><a href="/user/pds/promotion/reportList" role="menuitem">홍보관</a></li>
					<li role="none"><a href="/user/pds/news/reportList" role="menuitem">보도자료</a></li>
				</ul>
			</li>
			
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="admin"><a href="#" class="dep1" role="menuitem"><span>기본설정</span></a>
					<ul role="menu">
						<li role="none"><a href="/admin/settings/manageAccount" role="menuitem">계정관리</a></li>
						<li role="none"><a href="" role="menuitem">팝업창관리</a></li>
						<li role="none"><a href="" role="menuitem">배너관리</a></li>
						<li role="none"><a href="/admin/settings/manageMainPage" role="menuitem">홈설정</a></li>
					</ul>
				</li>
	    	</sec:authorize>
		</ul>
		<span class="floatBar"></span>
	</div>
	
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<button type="button" class="btn-primary btn-logout" id="logout_btn"><span>로그아웃</span></button>
    </sec:authorize>
	
	<sec:authorize access="!hasRole('ROLE_ADMIN')">
		<div class="family-link">
			<a href=""><img src="/assets/img/banner01.png" alt="새로운 경기 공정한 세상"></a>
			<a href=""><img src="/assets/img/banner02.png" alt="청소년활동진흥센터"></a>
		</div>
	</sec:authorize>
	
	<button type="button" class="mo-menu"><span>메뉴 펼치기</span></button>
	
<!-- <button type="button" class="top-util"><span>로그아웃</span></button> -->
</header>
<!-- [E] Header -->