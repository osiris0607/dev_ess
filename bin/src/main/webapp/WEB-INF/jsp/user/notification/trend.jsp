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
		comAjax.setUrl("<c:url value='/user/api/notification/trend/search/paging' />");
		comAjax.setCallback("searchListCB");
		comAjax.addParam("pageIndex", pageNo);
		comAjax.addParam("orderby", "TREND_ID DESC");

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
			$("#search_count").text("전체 0건");
			body.append(str);
		} else {
			var params = {
					divId : "pageNavi",
					pageIndex : "pageIndex",
					totalCount : total,
					eventName : "searchList"
				};

			gfnRenderPagingMain(params);

			$("#search_count").text("전체 " + total + "건");
			var index = 1;
			var str = "";
			$.each(data.result, function(key, value) {
				str += "<tr>";
				str += "	<td>" + index + "</td>";
				str += "	<td><a href='/user/notification/trend/detail?trend_id=" + value.trend_id + "'>" + value.title + "</a></td>";
				str += "	<td>"+ value.writer + "</td>";
				str += "	<td>"+ value.reg_date + "</td>";
				str += "	<td>"+ value.hits + "</td>";
				if (value.upload_file_name == null || value.upload_file_name == "") {
					str += "	<td><i></i></td>";
				}
				else {
					str += "    <td><img src='/assets/img/attach_file.png' alt=''></td>";
				}
				
				str += "</tr>";

				index++;
			});
			body.append(str);
		}
	}

</script>


<div id="wrap">
 <section>
    <div class="subVisual lecture">
      <div class="titleArea">
        <h2>자료실</h2>
          <span>ESS산업 및 연관산업 동향 등의 자료를 제공합니다.</span>
      </div>
    </div>
  </section>

  <section>
    <div class="subWrap">
      <ul class="breadcrumb">
        <li><img src="/assets/img/btn/breadcrumb_home.png" alt="홈"></li>
        <li>자료실&middot;게시판</li>
        <li>자료실</li>
      </ul>

      <div class="referenceWrap">
        <!-- <h3>자료실</h3> -->

        <fieldset>
          <legend><span>과정검색</span></legend>
          <ul class="searchWrap reference">
            <li class="result" id="search_count"></li>
            <li>
              <select name="" id="search_category">
                <option value="title" selected>제목</option>
                <option value="writer">작성자</option>
                <option value="reg_date">작성일</option>
              </select>
            </li>
            <li>
              <input type="text" placeholder="검색어" id="search_text">
            </li>
            <li>
              <button type="submit" onclick="searchList(1);">검색</button>
            </li>
          </ul>

          <table class="tbl">
            <caption><span>ESS산업 동향 자료 목록</span></caption>
            <colgroup>
              <col style="width:10%;">
              <col style="width:45%;">
              <col style="width:10%;">
              <col style="width:15%;">
              <col style="width:10%;">
              <col style="width:10%;">
            </colgroup>
            <thead>
              <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회</th>
                <th>첨부</th>
              </tr>
            </thead>
            <tbody class="ref" id="list_body"></tbody>
          </table>

          	<input type="hidden" id="pageIndex" name="pageIndex"/>
		    <div class="pagination" id="pageNavi">
		    </div>
        </fieldset>
      </div>
    </div>
  </section>
</div>
