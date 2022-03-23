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
		comAjax.setUrl("<c:url value='/admin/api/notification/training/search/paging' />");
		comAjax.setCallback("searchListCB");
		comAjax.addParam("pageIndex", pageNo);
		comAjax.addParam("orderby", "REG_DATE DESC");

		comAjax.addParam("title", $("#search_text").val());
		comAjax.addParam("enforcement_agency", $("#search_text").val());
		comAjax.ajax();
	}


	function searchListCB(data) {
		var total = data.totalCount;
		var body = $("#list_body");
		body.empty();
		if (total == 0) {
			var str = "<tr>" + "<td colspan='3'>조회된 결과가 없습니다.</td>" + "</tr>";
			body.append(str);
		} else {
			var params = {
					divId : "pageNavi",
					pageIndex : "pageIndex",
					totalCount : total,
					eventName : "searchList"
				};

			gfnRenderPaging(params);


			$("#search_count").text(total);
			var index = 1;
			var str = "";
			$.each(data.result, function(key, value) {
				str += "<tr>";
				str += "	<td>" + index + "</td>";
				str += "	<td><a href='/admin/notification/training/detail?training_id=" + value.training_id + "'>" + value.title + "</a></td>";
				str += "	<td>"+ value.enforcement_agency + "</td>";
				str += "</tr>";

				index++;
			});
			body.append(str);
		}
	}


</script>
  
<!--페이지 루트-->
<div class="page-nation container">
	<a href="/admin/home/management"><i class="nav-icon fa fa-home mr5"></i>홈화면</a><span class="route_icon"></span>
	<a href="javascript:void(0)">알림/정보</a><span class="route_icon"></span>
	<a href="javascript:void(0)">훈련과정</a>
</div>

<!--본문시작-->
<div class="sub-container container">
	<div class="sub-content">
<!--페이지 타이틀-->				
		<div class="page-header">
			<h3 class="page-title text-primary-d2 text-120">훈련과정</h3>
		</div>
<!--리스트 검색 상단-->				
		<div class="sub-content-box bgc-white">
                  <!--리스트 상단 검색결과-->                
                  <div class="list_search_end_box clearfix">
                      <h4 class="fl">전체</h4>
                      <span class="list_search_results fl ml10" id="search_count">0</span><em class="fl mr10">건</em>
                      <div class="fr training_table_top_seach">
                          <input type="text" class="form-control brc-on-focusd-inline-block fl w60" placeholder="검색어를 입력하세요" id="search_text"/>    
                          <button class="blue_btn2 fl btn ml5" onclick="searchList(1);">검색</button>						
                          <a href="/admin/notification/training/registration" class="btn blue_btn fr">등록</a>
                      </div>

                  <!--리스트 상단 검색결과 테이블-->    
                     <table class="table table-striped-primary table-borderless">
                  <colgroup>
                      <col style="width: 10%" />
                      <col style="width: 70%" />
                      <col style="width: 20%" />
                  </colgroup>
                     <thead>
                         <tr class="bgc-primary-d2 text-white text-sm-center">
                            <th>번호</th>
                            <th>훈련과정명</th>
                            <th>시행기관</th>
                         </tr>
                    </thead>
                    <tbody class="bgc-primary-d2 text-sm-center" id="list_body">
                    </tbody>
                 </table> 
                 
                 <div class="Page_navigation">
					<div id="pageNavi"></div>
						<input type="hidden" id="pageIndex" name="pageIndex"/>
					</div>  
                </div>
		</div>    
	</div>
</div>
