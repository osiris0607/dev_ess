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
	});

	function searchList(pageNo) {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/admin/api/notification/faq/search/paging' />");
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

			gfnRenderPaging(params);

			var str = "";
			$.each(data.result, function(key, value) {
				str += "<li>";
				str += "	<a href='/admin/notification/faq/detail?faq_id=" + value.faq_id + "' class='clearfix'>";
				str += "		<span class='fl fna_q mr10'>Q.</span><span class='qna_list_title_txt fl'>" + value.question + "</span><span class='qna_bullet fr'></span>";
				str += "	</a>";
				str += "</li>";
			});
			body.append(str);
		}
	}	
</script>
  
<!--페이지 루트-->
<div class="page-nation container">
	<a href="/admin/home/management"><i class="nav-icon fa fa-home mr5"></i>홈화면</a><span class="route_icon"></span>
	<a href="javascript:void(0)">알림/정보</a><span class="route_icon"></span>
	<a href="javascript:void(0)">FAQ</a>
</div>       
		

<!--본문시작-->
<div class="sub-container container">
	<div class="sub-content">
<!--페이지 타이틀-->				
		<div class="page-header">
			<h3 class="page-title text-primary-d2 text-120">
			FAQ
			</h3>
		</div>
<!--리스트 검색 상단-->				
		<div class="sub-content-box bgc-white">
          	<div class="faq-center_table_top_seach clearfix">
                  <select class="ace-select fl" id="selector_choice">	
                      <option value="">분류 선택</option>
                      <option value="C001">제목</option>
                      <option value="C002">내용</option>
                      <option value="C003">제목+내용</option>
                  </select>
                  <input type="text" class="form-control brc-on-focusd-inline-block fl ml5" placeholder="검색어를 입력하세요" id="search_text"/>        
                  <button class="blue_btn2 fl btn ml5" onclick="searchList(1);">검색</button>
                  <a href="/admin/notification/faq/registration" class="btn blue_btn fr">등록</a>
              </div>
              <!--리스트 상단 검색결과-->		
              <div class="fna_list_box">                    
                  <ul id="ul_body"></ul>
              </div>                        
        </div>
	</div>
</div> 
