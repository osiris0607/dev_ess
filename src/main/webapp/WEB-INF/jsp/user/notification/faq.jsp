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
	  	$(document).on("click", ".item-toggle",  function () {
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
		comAjax.setUrl("<c:url value='/user/api/notification/faq/search/paging' />");
		comAjax.setCallback("searchListCB");
		comAjax.addParam("pageIndex", pageNo);
		comAjax.addParam("orderby", "REG_DATE DESC");

		var temp = $("#selector_choice").val();
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
			var params = {
					divId : "pageNavi",
					pageIndex : "pageIndex",
					totalCount : total,
					eventName : "searchList"
				};

			gfnRenderPagingMain(params);

			var str = "";
			$.each(data.result, function(key, value) {
				str += "<li>";
				str += "	<a href='javascript:void(0)' class='item-toggle'>" + value.question + "</a>";
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
 	<section>
      <div class="subVisual lecture notice_top_bg">
        <div class="titleArea">
          <h2>게시판</h2>
          <span>ESS산업 일자리 내비게이터 관련 소통 창구입니다.</span>
        </div>
      </div>
    </section>

  <section>
    <div class="subWrap">
      <ul class="breadcrumb">
        <li><img src="/assets/img/btn/breadcrumb_home.png" alt="홈"></li>
       	<li>자료실&middot;게시판</li>
        <li>게시판</li>
      </ul>

      <div class="referenceWrap">
        <!-- <h3>게시판</h3> -->

        <fieldset>
          <legend><span>과정검색</span></legend>
          <ul class="searchWrap fr">
            <li>
              <select name="" id="selector_choice">
	              <option value="C001" selected>제목</option>
	              <option value="C002">내용</option>
	              <option value="C003">제목+내용</option>
              </select>
            </li>
            <li>
              <input type="text" placeholder="검색어" id="search_text">
            </li>
            <li>
              <button type="submit" onclick="searchList(1);">검색</button>
            </li>
          </ul>
        </fieldset>

        <ul class="accodion" id="ul_body"></ul>
      </div>
   		<input type="hidden" id="pageIndex" name="pageIndex"/>
	   <div class="pagination" id="pageNavi">
	   </div>
    </div>
  </section>
</div>

  
<script src="/assets/js/common.js"></script>
<script src="/assets/js/jquery-accessible-tabs.js"></script>
