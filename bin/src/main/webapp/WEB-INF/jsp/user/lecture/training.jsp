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
		comAjax.setUrl("<c:url value='/user/api/lecture/training/search/paging' />");
		comAjax.setCallback("searchListCB");
		comAjax.addParam("pageIndex", pageNo);
		comAjax.addParam("orderby", "TRAINING_ID DESC");

		comAjax.addParam($("#search_category").val(), $("#search_text").val());
		comAjax.ajax();
	}


	function searchListCB(data) {
		var total = data.totalCount;
		var body = $("#ul_body");
		body.empty();
		$("#pageNavi").empty();
		if (total == 0) {
			var str = "조회된 결과가 없습니다.";
			body.append(str);
		} else {
			var params = {
					divId : "pageNavi",
					pageIndex : "pageIndex",
					totalCount : total,
					eventName : "searchList"
				};

			gfnRenderPagingMain(params);

			$("#search_count").text(total);
			var str = "";
			$.each(data.result, function(key, value) {
				str += "<li>";
				str += "	<h3 class='jobTitlearea'>" + value.title + "</h3>";
				str += "	<div class='imgWrap'>";
				str += "		<img src='data:image/png;base64," + value.upload_file_image +"' alt='" + value.attach_file_description + "'>";
				str += "	</div>";
				str += "	<dl>";
				str += "		<dt>주소</dt>";
				str += "			<dd>" + value.address + "</dd>";
				str += "		<dt>연락처</dt>";
				str += "			<dd>" + value.contact + "</dd>";
				str += "		<dt>홈페이지</dt>";
				str += "			<dd><a href='" + value.home_page + "' target='_blank'>" + value.home_page + "</a></dd>";
				str += "	</dl>";
				str += "</li>";
			});
			body.append(str);
		}
	}


</script>



<div id="wrap">
 <section>
    <div class="subVisual lecture">
      <div class="titleArea">
      	<h2>훈련기관</h2>
          <span>ESS산업 일자리 관련 훈련기관 정보를 제공합니다.</span>
      </div>
    </div>
  </section>

  <section>
    <div class="subWrap">
      <ul class="breadcrumb">
        <li><img src="/assets/img/btn/breadcrumb_home.png" alt="홈"></li>
        <li>교육&middot;자격증</li>
        <li>훈련기관</li>
      </ul>

      <fieldset>
        <legend><span>과정검색</span></legend>
        <ul class="searchWrap reference">
          <li class="result">전체 <em id="search_count">0</em>건</li>
          <li>
          	<select name="" id="search_category">
              <option value="title" selected>훈련과정명</option>
              <option value="enforcement_agency">기관명</option>
            </select>
          </li>
          <li>
            <input type="text" placeholder="훈련과정을 검색해 보세요." id="search_text">
          </li>
          <li>
            <button type="submit" onclick="searchList(1);">검색</button>
          </li>
        </ul>

        <ul class="course" id="ul_body"></ul>

        <input type="hidden" id="pageIndex" name="pageIndex"/>
	    <div class="pagination" id="pageNavi"></div>
      </fieldset>
    </div>
  </section>
</div>
