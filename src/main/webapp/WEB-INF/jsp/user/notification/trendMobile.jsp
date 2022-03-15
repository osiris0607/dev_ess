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
		comAjax.setUrl("<c:url value='/user/api/notification/trend/search/all' />");
		comAjax.setCallback("searchListCB");

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
			var str = "<tr>" + "<td colspan='2'>조회된 결과가 없습니다.</td>" + "</tr>";
			$("#search_count").text("전체 0건");
			body.append(str);
		} else {

			$("#search_count").text("전체 " + total + "건");
			var str = "";
			$.each(data.result, function(key, value) {
				str += "<tr>";
				str += "	<td><a href='/user/notification/trend/detailMobile?trend_id=" + value.trend_id + "'>" + value.title + "</a></td>";
				str += "	<td>"+ value.reg_date + "</td>";
				str += "</tr>";

			});
			body.append(str);
		}
	}

</script>


<div id="wrap">
    <header class="sub">
      <div class="headerSubWrap">
        <button class="prev" onclick="location.href='/user/mobile/main/mainMobile';"></button>
        <h1>자료실</h1>
      </div>
    </header>

  <section>
      <div class="innerWrap search">
        <fieldset>
          <legend><span>ESS 산업동향 검색</span></legend>
          <div class="inputWrap">
          	<select name="" id="search_category">
                <option value="title" selected>제목</option>
                <option value="reg_date">작성일</option>
            </select>
            <input type="text" placeholder="검색어를 입력하세요." id="search_text">
            <button type="submit" onclick="searchList(1);">검색</button>
          </div>
        </fieldset>
        <div class="result">
          <div class="searchResult">
            <p class="resultNum" id="search_count"></p>
            <table class="tbl trend">
              <caption><span>ESS산업 동향 목록</span></caption>
              <colgroup>
                <col style="width:70%">
                <col style="width:30%">
              </colgroup>
              <thead>
                <tr>
                  <th scope="col">제목</th>
                  <th scope="col">작성일</th>
                </tr>
              </thead>
              <tbody id="list_body"></tbody>
            </table>
            <button type="button" class="more" id="more_btn">10개 더보기</button>
          </div>
        </div>
      </div>
    </section>
</div>
