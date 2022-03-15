<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

  
<script type='text/javascript'>

	var serachList;
	var serachListCount = 1;
		
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
		comAjax.setUrl("<c:url value='/user/api/lecture/training/search/all' />");
		comAjax.setCallback("searchListCB");

		comAjax.addParam($("#search_category").val(), $("#search_text").val());
		comAjax.ajax();
	}


	function searchListCB(data) {
		var total = data.totalCount;
		var body = $("#ul_body");
		body.empty();
		if (total == 0) {
			var str = "조회된 결과가 없습니다.";
			body.append(str);
		} else {

			$("#search_count").text(total);
			serachList = data.result;;
			serachListCount = 1;
			
			var str = "";
			$.each(data.result, function(key, value) {
				str += "<li>";
				str += "	<h3>" + unescapeHtml(value.title) + "</h3>";
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

				// 10개씩만 보여준다.
				if ( (serachListCount%10) == 0 ) {
					return false;
				}
				serachListCount++;
			});
			
			if ( serachListCount >= serachList.length) {
				$("#more_btn").hide();
			}
			else {
				$("#more_btn").show();
			}
			
			body.append(str);
		}
	}

	function serachListMore() {

		var body = $("#ul_body");

		var str = "";
		for ( var i=0; i<serachList.length ; i++) {
			str += "<li>";
			str += "	<h3>" + unescapeHtml(serachList[i+serachListCount].title) + "</h3>";
			str += "	<div class='imgWrap'>";
			str += "		<img src='data:image/png;base64," + serachList[i+serachListCount].upload_file_image +"' alt='" + serachList[i+serachListCount].attach_file_description + "'>";
			str += "	</div>";
			str += "	<dl>";
			str += "		<dt>주소</dt>";
			str += "			<dd>" + serachList[i+serachListCount].address + "</dd>";
			str += "		<dt>연락처</dt>";
			str += "			<dd>" + serachList[i+serachListCount].contact + "</dd>";
			str += "		<dt>홈페이지</dt>";
			str += "			<dd><a href='" + serachList[i+serachListCount].home_page + "' target='_blank'>" + serachList[i+serachListCount].home_page + "</a></dd>";
			str += "	</dl>";
			str += "</li>";

			// 10개씩만 보여준다.
			if ( i >= 9 || (i+serachListCount) >= (serachList.length-1) ) {
				break;
			}
		}
		serachListCount = serachListCount + 10;

		if ( serachListCount >= serachList.length) {
			$("#more_btn").hide();
		}
		else {
			$("#more_btn").show();
		}
		
		body.append(str);
	}

</script>



<div id="wrap">
    <header class="sub">
      <div class="headerSubWrap">
        <button class="prev" onclick="location.href='/user/mobile/main/mainMobile';"></button>
        <h1>훈련기관</h1>
      </div>
    </header>

  <section>
      <div class="innerWrap search">
        <fieldset>
          <legend><span>국가자격 검색</span></legend>
          <div class="inputWrap">
          	<select name="" id="search_category">
              <option value="title" selected>훈련과정명</option>
              <option value="enforcement_agency">기관명</option>
            </select>
            <input type="text" placeholder="훈련과정을 검색해 보세요." id="search_text">
            <button type="submit" onclick="searchList(1);">검색</button>
          </div>
        </fieldset>
      </div>

      <div class="innerWrap bg">
        <p class="noc">검색결과 <em id="search_count">0</em>건</p>
        <div class="courseWrap">
          <ul class="course" id="ul_body">
          </ul>
          <button type="button" class="more" id="more_btn" onclick="serachListMore();">10개 더보기</button>
        </div>
      </div>
    </section>
  </div>
