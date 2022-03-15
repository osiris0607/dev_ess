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
		comAjax.setUrl("<c:url value='/admin/api/notification/trend/search/paging' />");
		comAjax.setCallback("searchListCB");
		comAjax.addParam("pageIndex", pageNo);
		comAjax.addParam("orderby", "REG_DATE DESC");

	    if ($("#search_category").val() == "reg_date" ) {
	    	var datatimeRegexp = /[0-9]{4}-[0-9]{2}-[0-9]{2}/;
	    	if ( !datatimeRegexp.test($("#search_text").val()) ) {
		        alert("날짜는 yyyy-mm-dd 형식으로 입력해야 합니다.");
		        return false;
		    }
	    }
		
		comAjax.addParam($("#search_category").val(), $("#search_text").val());
		comAjax.ajax();
	}


	function searchListCB(data) {
		var total = data.totalCount;
		var body = $("#list_body");
		body.empty();
		if (total == 0) {
			var str = "<tr>" + "<td colspan='6'>조회된 결과가 없습니다.</td>" + "</tr>";
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
				str += "	<td><a href='/admin/notification/trend/detail?trend_id=" + value.trend_id + "'>" + value.title + "</a></td>";
				str += "	<td>"+ value.writer + "</td>";
				str += "	<td>"+ value.reg_date + "</td>";
				str += "	<td>"+ value.hits + "</td>";
				if (value.upload_file_name == null || value.upload_file_name == "") {
					str += "	<td><i></i></td>";
				}
				else {
					str += "    <td><i class='fas fa-save'></i></td>";
				}
				
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
    <a href="javascript:void(0)">ESS산업 동향</a>
</div>

<!--본문시작-->
<div class="sub-container container">
	<div class="sub-content">
<!--페이지 타이틀-->				
		<div class="page-header">
			<h3 class="page-title text-primary-d2 text-120">
			ESS산업 동향
			</h3>
		</div>
<!--리스트 검색 상단-->				
		<div class="sub-content-box bgc-white">
                 <!--리스트 상단 검색결과-->                
                  <div class="list_search_end_box clearfix">
                      <h4 class="fl">전체</h4>
                      <span class="list_search_results fl ml10" id="search_count">0</span><em class="fl mr10">건</em>
                      <div class="fr industry-trend_table_top_seach clearfix">
                          <select class="area_write_table_txt w30 ace-select fl" id="search_category">
	                          <option value="title">제목</option>
	                          <option value="writer">작성자</option>
	                          <option value="reg_date">작성일</option>
                          </select>                        
                          <input type="text" class="form-control brc-on-focusd-inline-block fl ml5" placeholder="검색어를 입력하세요" id="search_text" />                        
                          <button class="blue_btn2 fl btn ml5" onclick="searchList(1);">검색</button>                            
                          <a href="/admin/notification/trend/registration" class="btn blue_btn fr">등록</a>
                      </div>
                      
                      <!--리스트 상단 검색결과 테이블-->    
                     <table class="table table-striped-primary table-borderless">
                      <colgroup>
                          <col style="width: 10%" />
                          <col style="width: 35%" />
                          <col style="width: 15%" />
                          <col style="width: 15%" />
                          <col style="width: 15%" />
                          <col style="width: 10%" />
                      </colgroup>
                         <thead>
                             <tr class="bgc-primary-d2 text-white text-sm-center">
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회</th>
                                <th>첨부</th>
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
