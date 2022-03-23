<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type='text/javascript'>

	var jobList; 

	$(document).ready(function() {
		searchAllList();
	});


	
	function searchAllList() {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/user/api/job/all'/>");
		comAjax.setCallback("searchListCB");
		comAjax.ajax();
	}

	function searchListCB(data) {
		jobList = data.result;
		
		serachListTab01(data);
		serachListTab02(data);
	}

	function serachListTab01(data) {
		var body = $("#ul_tab01_body");
		body.empty();
		
		var categoryId = "";
		var categoryDetailId = "";
		var index = 1;
		var isCategoryFirst = true;
		var isDetailFirst = true;
		var occurptionNextId = "D000001";
		var str = "";
		
		for ( var i=0; i<data.result.length; i++ ) {
			// 카테고리가 바꿜때 LI 생성
			
			if (data.result[i].category_id != categoryId) {
				categoryId = data.result[i].category_id;
				index = 1;
				isDetailFirst = true;
				categoryDetailId = "";
				occurptionNextId = "D000001";

				// 마지막에 </li> 를 닫아야 한다.
				if ( isCategoryFirst == true ) {
					isCategoryFirst = false;
				}
				else{
					str += "	</div>";
					str += "</li>";
				}

				// 실제 리스트
				str += "<li>";
				
			}

			// Detail 생성
			if (categoryDetailId != data.result[i].category_detail_id) {
				categoryDetailId = data.result[i].category_detail_id;
				if ( isDetailFirst == true ){
					isDetailFirst = false;
				}
				else {
					str += "</li>";
					occurptionNextId = "D000001";
				}
				if (index == 1) {
					index++;
					str += "<a href='javascript:void(0)' class='item-toggle'>" + data.result[i].category_id + ". " + unescapeHtml(data.result[i].category_name) + "</a>";
					str += "<div class='accodion-content item-hide'>";
					str += "	<ul class='sub_accodion'>";
					str += "		<li>";
				}
				else {
					str += "		<li>";
				}
					// 직업
					str += "			<a href='javascript:void(0)' class='sub_item-toggle'>" + data.result[i].category_detail_id + ". " + unescapeHtml(data.result[i].category_detail_name) + "</a>";
					str += "			<div class='sub_accodion-content sub_item-hide'>";
				str += "				<ul>";
				while (true) {
					// 맨 마지막 데이터는 없을 수 있다.
					if ( i >= data.result.length){
						break;
					}
					
					// D000001, D000002, D000003, D000004 순서대로 데이터가 나와야 한다.
					// 중간에 없으면 Blank로 나온다.
					if ( categoryDetailId == data.result[i].category_detail_id ) {
						if ( data.result[i].occupation == "D000001") {
							str += "	<li><a href='javascript:void(0)' onclick='detailPopup(\"" + data.result[i].job_id + "\");'><em  class='research'>연구</em>" + unescapeHtml(data.result[i].name) + "</a></li>";
						}
						else if ( data.result[i].occupation == "D000002") {
							str += "	<li><a href='javascript:void(0)' onclick='detailPopup(\"" + data.result[i].job_id + "\");'><em class='tech'>기술</em>" + unescapeHtml(data.result[i].name) + "</a></li>";
						}
						else if ( data.result[i].occupation == "D000003") {
							str += "	<li><a href='javascript:void(0)' onclick='detailPopup(\"" + data.result[i].job_id + "\");'><em class='func'>기능</em>" + unescapeHtml(data.result[i].name) + "</a></li>";
						}
						else {
							str += "	<li><a href='javascript:void(0)' onclick='detailPopup(\"" + data.result[i].job_id + "\");'><em class='service'>서비스</em>" + unescapeHtml(data.result[i].name) + "</a></li>";
						}
					}
					else {
						i--;
						break;
					}
					i++;
				}
				str += "			</ul>";
				str += "			</DIV>";
			}
			else {
				// 직업
				str += "			<ul>";
				while (true) {
					// 맨 마지막 데이터는 없을 수 있다.
					if ( i >= data.result.length){
						break;
					}
					
					// D000001, D000002, D000003, D000004 순서대로 데이터가 나와야 한다.
					// 중간에 없으면 Blank로 나온다.
					if ( categoryDetailId == data.result[i].category_detail_id && occurptionNextId == data.result[i].occupation ) {
						if ( occurptionNextId == "D000001") {
							str += "	<li><a href='javascript:void(0)' onclick='detailPopup(\"" + data.result[i].job_id + "\");'><em  class='research'>연구</em>" + unescapeHtml(data.result[i].name) + "</a></li>";
						}
						else if ( occurptionNextId == "D000002") {
							str += "	<li><a href='javascript:void(0)' onclick='detailPopup(\"" + data.result[i].job_id + "\");'><em class='tech'>기술</em>" + unescapeHtml(data.result[i].name) + "</a></li>";
						}
						else if ( occurptionNextId == "D000003") {
							str += "	<li><a href='javascript:void(0)' onclick='detailPopup(\"" + data.result[i].job_id + "\");'><em class='func'>기능</em>" + unescapeHtml(data.result[i].name) + "</a></li>";
						}
						else {
							str += "	<li><a href='javascript:void(0)' onclick='detailPopup(\"" + data.result[i].job_id + "\");'><em class='service'>서비스</em>" + unescapeHtml(data.result[i].name) + "</a></li>";
						}
					}
					else {
						if ( occurptionNextId == "D000001") {
							occurptionNextId = "D000002";
						}
						else if ( occurptionNextId == "D000002") {
							occurptionNextId = "D000003";
						}
						else if ( occurptionNextId == "D000003") {
							occurptionNextId = "D000004";
						}
						else {
							occurptionNextId = "D000001";
						}
						
						i--;
						break;
					}
					i++;
				}
				str += "		</ul>";
			}
		}

		str += "	</div>";
		str += "</li>";

		body.append(str);
	}

	function serachListTab02(data) {
		var body = $("#ul_tab02_body");
		body.empty();
		
		var researchHtml = "";
     	var techHtml ="";
      	var funcHtml = "";
      	var serviceHtml = "";

		$.each(data.result, function(key, value) {
			// 연구직
	 	  	if (value.occupation == "D000001" ) {
	 	  		researchHtml += "<li class='nobgi'>";
	 	  		researchHtml += "		<em>" + value.category_detail_name + " 분야</em>";
	 	  		researchHtml += "		<p><a href='javascript:void(0)' onclick='detailPopup(\"" + value.job_id + "\");'>" + value.name + "</a></p>";
	 	  		researchHtml += "</li>";
		  	}
			// 기술직
	 	 	if (value.occupation == "D000002" ) {
	 	 		techHtml += "<li class='nobgi'>";
	 	 		techHtml += "		<em>" + value.category_detail_name + " 분야</em>";
	 	 		techHtml += "		<p><a href='javascript:void(0)' onclick='detailPopup(\"" + value.job_id + "\");'>" + value.name + "</a></p>";
	 	 		techHtml += "</li>";
			}
			// 기능직
	 	 	if (value.occupation == "D000003" ) {
	 	 		funcHtml += "<li class='nobgi'>";
	 	 		funcHtml += "		<em>" + value.category_detail_name + " 분야</em>";
	 	 		funcHtml += "		<p><a href='javascript:void(0)' onclick='detailPopup(\"" + value.job_id + "\");'>" + value.name + "</a></p>";
	 	 		funcHtml += "</li>";			
			}
			// 서비스직
	 	 	if (value.occupation == "D000004" ) {
	 	 		serviceHtml += "<li class='nobgi'>";
	 	 		serviceHtml += "	<em>" + value.category_detail_name + " 분야</em>";
	 	 		serviceHtml += "	<p><a href='javascript:void(0)' onclick='detailPopup(\"" + value.job_id + "\");'>" + value.name + "</a></p>";
	 	 		serviceHtml += "</li>";
			}
		});

		var html = "";
		html += "<li>";
		html += "	<a href='javascript:void(0)' class='item-toggle'>연구직</a>";
		html += "	<div class='accodion-content item-hide'>";
		html += "		<ul class='sub_accodion'>";
		html += researchHtml;
		html += "		</ul>";
		html += "	</div>";
		html += "</li>";

		html += "<li>";
		html += "	<a href='javascript:void(0)' class='item-toggle'>기술직</a>";
		html += "	<div class='accodion-content item-hide'>";
		html += "		<ul class='sub_accodion'>";
		html += techHtml;
		html += "		</ul>";
		html += "	</div>";
		html += "</li>";

		html += "<li>";
		html += "	<a href='javascript:void(0)' class='item-toggle'>기능직</a>";
		html += "	<div class='accodion-content item-hide'>";
		html += "		<ul class='sub_accodion'>";
		html += funcHtml;
		html += "		</ul>";
		html += "	</div>";
		html += "</li>";

		html += "<li>";
		html += "	<a href='javascript:void(0)' class='item-toggle'>서비스직</a>";
		html += "	<div class='accodion-content item-hide'>";
		html += "		<ul class='sub_accodion'>";
		html += serviceHtml;
		html += "		</ul>";
		html += "	</div>";
		html += "</li>";

		body.append(html);
	}



	

	function getJobDetail(id) {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/user/api/job/detail'/>");
		comAjax.setCallback("getJobDetailCB");
		comAjax.addParam("job_id", id);
		comAjax.ajax();
	}

	function getJobDetailCB(data){
		
		var jobDetail = data.result;

		$("#name").text(jobDetail.name);
		$("#definition").text(unescapeHtml(jobDetail.definition));
		$("#code").text("코드분류 : " + jobDetail.category_id + ". " + jobDetail.category_name + " > "+ jobDetail.category_detail_id +". " + jobDetail.category_detail_name);
		$("#occupation").text(unescapeHtml(jobDetail.occupation_name));
		$("#education").text(jobDetail.education_name);

		// 난이도
		if ( jobDetail.difficulty == "D000001" ) {
			$("#level_info").text("고급");
		}
		else if ( jobDetail.difficulty == "D000002" ) {
			$("#level_info").text("중급");
		}
		else {
			$("#level_info").text("초급");
		}
		// 연봉
		$("#salary_level").text(jobDetail.salary_level + "만원");
		// 경력
		if ( jobDetail.career == "") {
			$("#career").text("없음");
		}
		else {
			$("#career").text(jobDetail.career);
		}
		// 학력 
		str = "";
		for (var i=0; i<jobDetail.return_major_list.length; i++){
			str += jobDetail.return_major_list[i].name + ",";
		}
		if ( str == "") {
			$("#major").text("없음");
		}
		else {
			$("#major").text(str.substring(0, str.length-1));
		}
		// 자격증 
		str = "";
		for (var i=0; i<jobDetail.return_license_list.length; i++){
			str += jobDetail.return_license_list[i].kor_name + ",";
		}
		if ( str == "") {
			$("#license").text("없음");
		}
		else {
			$("#license").text(str.substring(0, str.length-1));
		}
		// 지식
		$("#knowledge").html("<li>" + unescapeHtml(jobDetail.knowledge) + "</li>");
		// 기술
		$("#skill").html("<li>" + unescapeHtml(jobDetail.skill) + "</li>");
		// 태도
		$("#attitude").html("<li>" + unescapeHtml(jobDetail.attitude) + "</li>");
		// 대표기업
		str = "";

		// 차후에 이미지로 대체시에 사용 
		// 지금은 이름으로 대체
		/* if (jobDetail.company_1_name != "") {
			str += "<li><img src='data:image/png;base64," + jobDetail.company_1_logo +"' alt='" + jobDetail.company_1_name + "'></li>"
		}
		if (jobDetail.company_2_name != "") {
			str += "<li><img src='data:image/png;base64," + jobDetail.company_2_logo +"' alt='" + jobDetail.company_2_name + "'></li>"
		}
		if (jobDetail.company_3_name != "") {
			str += "<li><img src='data:image/png;base64," + jobDetail.company_3_logo +"' alt='" + jobDetail.company_3_name + "'></li>"
		} */

		if (jobDetail.company_1_name != "") {
			str += "<li>" + jobDetail.company_1_name + "</li>"
		}
		if (jobDetail.company_2_name != "") {
			str += "<li>" + jobDetail.company_2_name + "</li>"
		}
		if (jobDetail.company_3_name != "") {
			str += "<li>" + jobDetail.company_3_name + "</li>"
		}
		
		if ( str == "") {
			$("#companies").html("<li>없음</li>");
		}
		else {
			$("#companies").html(str);
		}
		// future_career
		str = "";
		if (jobDetail.future_career_1_name != "") {
			str += "<li>" + unescapeHtml(jobDetail.future_career_1_name) + "</li>"
		}
		if (jobDetail.future_career_2_name != "") {
			str += "<li>" + unescapeHtml(jobDetail.future_career_2_name) + "</li>"
		}
		if (jobDetail.future_career_3_name != "") {
			str += "<li>" + unescapeHtml(jobDetail.future_career_3_name) + "</li>"
		}
		if ( str == "") {
			$("#future_career").html("<li>없음</li>");
		}
		else {
			$("#future_career").html(str);
		}

		$('.popup, .mask').fadeIn(350);
	}
	

	function detailPopup(id) {
		getJobDetail(id);
	}

</script>



  <div id="wrap">
    <header class="sub">
      <div class="headerSubWrap">
        <button class="prev" onclick="location.href='/user/mobile/main/mainMobile';"></button>
        <h1>ESS산업 일자리</h1>
      </div>
    </header>

    <section>
      <div class="tabs jobs">
        <ul class="tablist" data-hx="h2">
          <li class="tablist__item">
            <a href="#tab01" class="tablist__link tabs__standard__a">분야별</a>
          </li>
          <li class="tablist__item">
            <a href="#tab02" class="tablist__link ">직종별</a>
          </li>
        </ul>
        <div id="tab01" class="js-tabcontent">
          <div class="innerWrap">
            <ul class="accodion" id="ul_tab01_body">
            </ul>
          </div>
        </div>
        <div id="tab02" class="js-tabcontent">
          <div class="innerWrap">
            <ul class="accodion common"  id="ul_tab02_body">
              <!-- <li>
                <a href="javascript:void(0)" class="item-toggle">연구직</a>
                <div class="accodion-content item-hide">
                  <ul class="sub_accodion">
                    <li class="nobgi">
                      <em>실리콘 분야</em>
                      <p><a href="">실리콘 태양전지소재 연구원</a></p>
                    </li>
                    <li class="nobgi">
                      <em>비실리콘 분야</em>
                      <p><a href="">실리콘 태양전지소재 연구원</a></p>
                    </li>
                    <li class="nobgi">
                      <em>잉곳/웨이퍼 분야</em>
                      <p><a href="">실리콘 태양전지소재 연구원</a></p>
                    </li>
                  </ul>
                </div>
              </li>
              <li>
                <a href="javascript:void(0)" class="item-toggle">기술직</a>
                <div class="accodion-content item-hide">
                  <ul class="sub_accodion">
                    <li class="nobgi">
                      <em>셀분야</em>
                      <p><a href="">실리콘 태양전지소재 연구원</a></p>
                    </li>
                    <li class="nobgi">
                      <em>모듈 분야</em>
                      <p><a href="">실리콘 태양전지소재 연구원</a></p>
                    </li>
                    <li class="nobgi">
                      <em>장비 분야</em>
                      <p><a href="">실리콘 태양전지소재 연구원</a></p>
                    </li>
                  </ul>
                </div>
              </li>
              <li>
                <a href="javascript:void(0)" class="item-toggle">기능직</a>
                <div class="accodion-content item-hide">
                  <ul class="sub_accodion">
                    <li class="nobgi">
                      <em>실리콘 분야</em>
                      <p><a href="">실리콘 태양전지소재 연구원</a></p>
                    </li>
                  </ul>
                </div>
              </li>
              <li>
                <a href="javascript:void(0)" class="item-toggle">서비스직</a>
                <div class="accodion-content item-hide">
                  <ul class="sub_accodion">
                    <li class="nobgi">
                      <em>실리콘 분야</em>
                      <p><a href="">실리콘 태양전지소재 연구원</a></p>
                    </li>
                  </ul>
                </div>
              </li> -->
            </ul>
          </div>
        </div>
      </section>
    </section>
  </div>

  <!-- Layer -->
  <div class="mask"></div>

  <div class="popup">
    <div class="wrap">
      <div class="header">
        <h1>일자리 카드</h1>
      </div>

      <div class="contents">
        <div class="mainSec">
              <h2 id="name"></h2>
              <p class="summary" id="definition"></p>
              <span class="codeDiv" id="code"></span>
              <em class="groupA" id="occupation"></em>
              <em class="groupB" id="education"></em>
 			  <em class="level" id="level_info">고급</em>
        </div>
        <div class="infoWrap">
          <dl class="jobInfo">
            <dt>평균연봉</dt>
            	<dd id="salary_level"></dd>
            <dt>필요경력</dt>
            	<dd id="career"></dd>
            <dt>전공</dt>
            	<dd	id="major"></dd>
            <dt>자격증</dt>
            	<dd id="license"></dd>
            <dt>직무역량</dt>
            <dd>
              <p class="listTit">지식</p>
              <ul id="knowledge"></ul>
              <p class="listTit">기술</p>
              <ul id="skill"></ul>
              <p class="listTit">태도</p>
              <ul id="attitude"></ul>
            </dd>
          </dl>
        </div>
        <div class="infoWrap">
          <h3>대표기업</h3>
          <ul class="link" id="companies"></ul>
        <!-- 배너로 변경 시에 사용할 것 !!!!!!
         <ul class="banner" id="companies"></ul> -->
        </div>
        <div class="infoWrap">
          <h3>향후 연계 직업</h3>
          <ul class="link" id="future_career"></ul>
        </div>
      </div>
      <button class="btn_close">닫기</button>
    </div>
  </div>
  <script>
    $(function() {
      $('.open').on('click', function() {
        $('.mask').css({'display':'block'});
        $('.popup').css({'display':'block'});
      });
      $('.btn_close').on('click', function() {
        $('.mask').css({'display':'none'});
        $('.popup').css({'display':'none'});
      });
    });
  </script>
