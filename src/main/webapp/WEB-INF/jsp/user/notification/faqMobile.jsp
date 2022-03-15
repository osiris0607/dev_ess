<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

  
<script type='text/javascript'>

		
	$(document).ready(function() {
		$("#search_text").on("keydown", function(key) {
            //키의 코드가 13번일 경우 (13번은 엔터키)
            if (key.keyCode == 13) {
            	searchList(1);
            }
        });

		searchList(1);

		// accodion event
  	  	$(document).on("click", ".faq-item-toggle",  function () {
		    $(this).parent().siblings(".on").toggleClass("on").attr("aria-expanded", "false");
		    $(this).parent().siblings().children(".item-hide").slideUp("fast");
		    $(this).parent().toggleClass("on").attr("aria-expanded", "true");
		    $(this).siblings(".item-hide").stop("true", "true").slideToggle("fast");
	  	}); 

	});

 	function accodionInit() {
		$(".accodion-content").css("display", "none");
		$(".accodion li.first").addClass("on");
		$(".accodion li.first .accodion-content").css("display", "block");
	}

	function searchList(pageNo) {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/user/api/notification/faq/search/all' />");
		comAjax.setCallback("searchListCB");

		if ( $("#selector_choice").val() == "C001" ) {
			comAjax.addParam("question", $("#search_text").val());
		} 
		else if ( $("#selector_choice").val() == "C002" ) {
			comAjax.addParam("answer", $("#search_text").val());
		} 
		else if ( $("#selector_choice").val() == "C003" ) {
			comAjax.addParam("question", $("#search_text").val());
			comAjax.addParam("answer", $("#search_text").val());
		}
		
		comAjax.ajax();
	}


	function searchListCB(data) {
		var total = data.totalCount;
		var body = $("#ul_body");
		body.empty();
		if (total == 0) {
			var str = "<li>조회된 결과가 없습니다.</li>";
			body.append(str);
		} else {
			var str = "";
			$.each(data.result, function(key, value) {
				str += "<li>"
				str += "	<a href='javascript:void(0)' class='faq-item-toggle'>" + value.question + "</a>";
				str += "	<div class='accodion-content item-hide'>";
				str += "		<p>" +value.answer + "</p>";
				str += "	</div>";
				str += "</li>";
			});
			body.append(str);
		}

		accodionInit();
	}	
</script>



<div id="wrap">
  	<header class="sub">
	    <div class="headerSubWrap">
	      	<button class="prev" onclick="location.href='/user/mobile/main/mainMobile';"></button>
	      	<h1>게시판</h1>
	    </div>
  	</header>

	<section>
	    <div class="innerWrap search">
	      	<fieldset>
	        	<legend><span>자주하는 질문 검색</span></legend>
	            	<select name="" id="selector_choice">
			             <option value="C001" selected>제목</option>
			             <option value="C002">내용</option>
			             <option value="C003">제목+내용</option>
	           		 </select>
	        	<div class="inputWrap">
	         		<input type="text" placeholder="검색어를 입력하세요." id="search_text">
	         	 	<button type="submit" onclick="searchList(1);">검색</button>
	        	</div>
	      	</fieldset>
	      <ul class="accodion faq" id="ul_body">
	      </ul>
	    </div>
  	</section>
</div>
