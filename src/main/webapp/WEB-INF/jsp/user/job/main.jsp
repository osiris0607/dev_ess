<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script type='text/javascript'>

	var jobList; 

	$(document).ready(function() {
		searchAllList();
	});


	function mouseOver(my) {
		var jobId = $(my).attr("id").replace("job_", "");
		var relativeId_1 = "";
		var relativeId_2 = "";
		var relativeId_3 = "";
		$.each(jobList, function(key, value) {
			if ( value.job_id == jobId) {
				relativeId_1 = value.future_career_1;
				relativeId_2 = value.future_career_2;
				relativeId_3 = value.future_career_3;
				return false;
			}
		});
		
		if ( gfn_isNull(relativeId_1) == false ) {
	 		var className = $("#job_" + relativeId_1).attr("class");
			if (className == "research") {
				$("#job_" + relativeId_1).attr("class", "research_active");
			}
			else if (className == "tech") {
				$("#job_" + relativeId_1).attr("class", "tech_active");
			}
			else if (className == "func") {
				$("#job_" + relativeId_1).attr("class", "func_active");
			}
			else if (className == "service") {
				$("#job_" + relativeId_1).attr("class", "service_active");
			} 
		}
		if ( gfn_isNull(relativeId_2) == false ) {
	 		var className = $("#job_" + relativeId_2).attr("class");
			if (className == "research") {
				$("#job_" + relativeId_2).attr("class", "research_active");
			}
			else if (className == "tech") {
				$("#job_" + relativeId_2).attr("class", "tech_active");
			}
			else if (className == "func") {
				$("#job_" + relativeId_2).attr("class", "func_active");
			}
			else if (className == "service") {
				$("#job_" + relativeId_2).attr("class", "service_active");
			} 
		}
		if ( gfn_isNull(relativeId_3) == false ) {
	 		var className = $("#job_" + relativeId_3).attr("class");
			if (className == "research") {
				$("#job_" + relativeId_3).attr("class", "research_active");
			}
			else if (className == "tech") {
				$("#job_" + relativeId_3).attr("class", "tech_active");
			}
			else if (className == "func") {
				$("#job_" + relativeId_3).attr("class", "func_active");
			}
			else if (className == "service") {
				$("#job_" + relativeId_3).attr("class", "service_active");
			}
		} 
	}

	function mouseOut(my) {
		var jobId = $(my).attr("id").replace("job_", "");
		var relativeId_1 = "";
		var relativeId_2 = "";
		var relativeId_3 = "";
		$.each(jobList, function(key, value) {
			if ( value.job_id == jobId) {
				relativeId_1 = value.future_career_1;
				relativeId_2 = value.future_career_2;
				relativeId_3 = value.future_career_3;
				return false;
			}
		});
		
		var originClass = $(my).attr("originClass");
		$(my).attr("class", originClass);
		if ( gfn_isNull(relativeId_1) == false ) {
			originClass = $("#job_" + relativeId_1).attr("originClass");
			$("#job_" + relativeId_1).attr("class", originClass);
		}
		if ( gfn_isNull(relativeId_2) == false ) {
			originClass = $("#job_" + relativeId_2).attr("originClass");
			$("#job_" + relativeId_2).attr("class", originClass);
		}
		if ( gfn_isNull(relativeId_3) == false ) {
			originClass = $("#job_" + relativeId_3).attr("originClass");
			$("#job_" + relativeId_3).attr("class", originClass);
		} 
	}
	
	function searchAllList() {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/user/api/job/all'/>");
		comAjax.setCallback("searchListCB");
		comAjax.ajax();
	}

	function searchListCB(data) {
		var body = $("#job_table");
		body.empty();
		
		var categoryId = "";
		var categoryDetailId = "";
		var index = 1;
		var isCategoryFirst = true;
		var isDetailFirst = true;
		var occurptionNextId = "D000001";
		var str = "";

		str += "<caption><span>태양관산업 직군별 일자리 안내</span></caption>";
		str += "<colgroup>";
		str += "	<col style='width:12%;'>";
		str += "	<col style='width:8%;'>";
		str += "	<col style='width:20%;'>";
		str += "	<col style='width:20%;'>";
		str += "	<col style='width:20%;'>";
		str += "	<col style='width:20%;'>";
		str += "<colgroup>";
		str += "<thead>";
		str += "	<tr>";
		str += "		<th id='h1' colspan='2' class='top_class'>구분</th>";
		str += "		<th id='h2' class='top_job researcher'><span><img src='/assets/img/job_researcher_icon.png' alt='연구직 이미지'></span>연구직</th>";
		str += "		<th id='h3' class='top_job technical'><span><img src='/assets/img/job_technical_icon.png' alt='기술직 이미지'></span>기술직</th>";
		str += "		<th id='h4' class='top_job function'><span><img src='/assets/img/job_function_icon.png' alt='기능직 이미지'></span>기능직</th>";
		str += "		<th id='h5' class='top_job service_td'><span><img src='/assets/img/job_service_icon.png' alt='서비스직 이미지'></span>서비스직</th>";
		str += "	<tr>";
		str += "</thead>";

		jobList = data.result;
		for ( var i=0; i<data.result.length; i++ ) {
			// 카테고리가 바꿜때 tbody 생성
			if (data.result[i].category_id != categoryId) {
				categoryId = data.result[i].category_id;
				index = 1;
				isDetailFirst = true;
				categoryDetailId = "";
				occurptionNextId = "D000001";

				// 마지막에 tbody를 닫아야 한다.
				if ( isCategoryFirst == true ) {
					isCategoryFirst = false;
				}
				else{
					str += "</tbody>";

					// 중간 공백
					str += "<tbody class='none'>";
					str += "	<tr></tr>";
					str += "</tbody>";
				}

				// 실제 리스트
				str += "<tbody>";
			}

			if (categoryDetailId != data.result[i].category_detail_id) { // D000002 부터 데이터가 없을 때
				categoryDetailId = data.result[i].category_detail_id;
				if ( isDetailFirst == true ){
					isDetailFirst = false;
				}
				else {
					if ( occurptionNextId != "D000001" ) {
						str += "<td>"
						str += "</td>"
					}
					str += "</tr>";
					occurptionNextId = "D000001";
				}
				if (index == 1) {
					index++;
					str += "<tr class='nobd line_b'>";
					var categoryStr = data.result[i].category_name.split(" ");
					var categoryName = "";
					for (var j=0; j<categoryStr.length; j++){
						if ( j == (categoryStr.length-1) ) {
							categoryName += categoryStr[j];
						}
						else {
							categoryName += categoryStr[j] + "<br\>";
						}
					}

					// 대분류 row span 수정
					if (data.result[i].category_id == "A") {
						str += "	<th class='leftRadius material' rowspan='3'>" + unescapeHtml(categoryName) + "</th>";
					}
					if (data.result[i].category_id == "B") {
						str += "	<th class='leftRadius production' rowspan='2'>" + unescapeHtml(categoryName) + "</th>";
					}
					if (data.result[i].category_id == "C") {
						str += "	<th class='leftRadius system' rowspan='3'>" + unescapeHtml(categoryName) + "</th>";
					}
					if (data.result[i].category_id == "D") {
						str += "	<th class='leftRadius business' rowspan='12'>" + unescapeHtml(categoryName) + "</th>";
					}
				}
				else {
					str += "<tr class='line_b'>";
				}

				// 중분류 row span 수정
				// 카테고리명 개행을 위해 name1+name2로 출력
				var imageTag = "";
				if (data.result[i].category_detail_id == "A-1") {
					imageTag = "<img src='/assets/img/job_silicon_icon.png' alt='실리콘 이미지' title='실리콘 이미지' />";
					var categoryDetailName = data.result[i].category_detail_name;
					var name1 = categoryDetailName.substr(0, 2);
					var name2 = categoryDetailName.substr(2);
					
					str += "<th class='tal material2' rowspan='2'><span>" + imageTag + "</span>" + unescapeHtml(name1) + "<br>" + unescapeHtml(name2) + "</th>";
				}
				else if (data.result[i].category_detail_id == "A-2") {
					imageTag = "<img src='/assets/img/job_notsilicon_icon.png' alt='비실리콘 이미지' title='비실리콘 이미지' />";
					str += "<th class='tal material2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "A-3") {
					imageTag = "<img src='/assets/img/job_wafer_icon.png' alt='잉곳&middot;웨이퍼 이미지' title='잉곳&middot;웨이퍼 이미지' />";
					str += "<th class='tal material2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "B-1") {
					imageTag = "<img src='/assets/img/job_cell_icon.png' alt='잉곳&middot;셀 이미지' title='셀 이미지' />";
					str += "<th class='tal production2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "B-2") {
					imageTag = "<img src='/assets/img/job_module_icon.png' alt='모듈 이미지' title='모듈 이미지' />";
					str += "<th class='tal production2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "B-3") {
					imageTag = "<img src='/assets/img/job_equipment_icon.png' alt='장비 이미지' title='장비 이미지' />";
					str += "<th class='tal production2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "C-1") {
					imageTag = "<img src='/assets/img/job_part_icon.png' alt='발전부품 이미지' title='발전부품 이미지' />";
					str += "<th class='tal system2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "C-2") {
					imageTag = "<img src='/assets/img/job_facilities_icon.png' alt='발전설비 이미지' title='발전설비 이미지' />";
					var categoryDetailName = data.result[i].category_detail_name;
					var name1 = categoryDetailName.substr(0, 4);
					var name2 = categoryDetailName.substr(4);
					str += "<th class='tal system2'><span>" + imageTag + "</span>" + unescapeHtml(name1) + "<br>" + unescapeHtml(name2) + "</th>";
				}
				else if (data.result[i].category_detail_id == "C-3") {
					imageTag = "<img src='/assets/img/job_recycling_icon.png' alt='리사이클링&middot;리파워링 이미지' title='리사이클링&middot;리파워링 이미지' />";
					imageTag = "<img src='/assets/img/job_facilities_icon.png' alt='발전설비 이미지' title='발전설비 이미지' />";
					var categoryDetailName = data.result[i].category_detail_name;
					var name1 = categoryDetailName.substr(0, 7);
					var name2 = categoryDetailName.substr(7);
					str += "<th class='tal system2'><span>" + imageTag + "</span>" + unescapeHtml(name1) + "<br>" + unescapeHtml(name2) + "</th>";
				}
				else if (data.result[i].category_detail_id == "D-1") {
					imageTag = "<img src='/assets/img/job_consulting_icon.png' alt='사업개발&middot;컨설팅 이미지' title='사업개발&middot;컨설팅 이미지' />";
					str += "<th class='tal business2' rowspan='4'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "D-2") {
					imageTag = "<img src='/assets/img/job_consulting_icon.png' alt='설계&middot;시공 이미지' title='설계&middot;시공 이미지' />";
					str += "<th class='tal business2' rowspan='2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "D-3") {
					imageTag = "<img src='/assets/img/job_operation_icon.png' alt='운영&middot;유지관리 이미지' title='운영&middot;유지관리 이미지' />";
					var categoryDetailName = data.result[i].category_detail_name;
					var name1 = categoryDetailName.substr(0, 4);
					var name2 = categoryDetailName.substr(4);
					str += "<th class='tal business2' rowspan='2'><span>" + imageTag + "</span>" + unescapeHtml(name1) + "<br>" + unescapeHtml(name2) + "</th>";
				}
				else if (data.result[i].category_detail_id == "D-4") {
					imageTag = "<img src='/assets/img/job_edu_icon.png' alt='교육 이미지' title='교육 이미지' />";
					str += "<th class='tal business2 edu_'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}

				
				// 직업
				str += "<td>";
				
				while (true) {
						
					// 맨 마지막 데이터는 없을 수 있다.
					if ( i >= data.result.length){
						break;
					}
					
					// D000001, D000002, D000003, D000004 순서대로 데이터가 나와야 한다.
					// 중간에 없으면 Blank로 나온다.
					if ( categoryDetailId == data.result[i].category_detail_id && occurptionNextId == data.result[i].occupation ) { // 한 row 데이터로 출력
						
						if ( occurptionNextId == "D000001") {
							str += "	<a href='javascript:void(0)' originClass='research' id='job_"+ data.result[i].job_id +"' onMouseOut='mouseOut(this);' onMouseOver='mouseOver(this);' class='research' onclick='detailPopup(\"" + data.result[i].job_id + "\");'>" + unescapeHtml(data.result[i].name) + "</a>";
						}
						else if ( occurptionNextId == "D000002") {
							str += "	<a href='javascript:void(0)' originClass='tech' id='job_"+ data.result[i].job_id +"' onMouseOut='mouseOut(this);' onMouseOver='mouseOver(this);' class='tech' onclick='detailPopup(\"" + data.result[i].job_id + "\");'>" + unescapeHtml(data.result[i].name) + "</a>";
						}
						else if ( occurptionNextId == "D000003") {
							str += "	<a href='javascript:void(0)' originClass='func' id='job_"+ data.result[i].job_id +"' onMouseOut='mouseOut(this);' onMouseOver='mouseOver(this);' class='func' onclick='detailPopup(\"" + data.result[i].job_id + "\");'>" + unescapeHtml(data.result[i].name) + "</a>";
						}
						else { // D000004
							str += "	<a href='javascript:void(0)' originClass='service' id='job_"+ data.result[i].job_id +"' onMouseOut='mouseOut(this);' onMouseOver='mouseOver(this);' class='service' onclick='detailPopup(\"" + data.result[i].job_id + "\");'>" + unescapeHtml(data.result[i].name) + "</a>";
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
				str += "</td>";
			}
			else {
				// 직업
				str += "<td>";
				while (true) {
					
					if ( i >= data.result.length){
						break;
					}
					
					// D000001, D000002, D000003, D000004 순서대로 데이터가 나와야 한다.
					// 중간에 없으면 Blank로 나온다.
					if ( categoryDetailId == data.result[i].category_detail_id && occurptionNextId == data.result[i].occupation ) {
						
						if ( occurptionNextId == "D000001") {
							// 소분류 tr, td 추가
							str += "<tr class='line_b'>";
							str += "<td>";
							str += "	<a href='javascript:void(0)' originClass='research' id='job_"+ data.result[i].job_id +"' onMouseOut='mouseOut(this);' onMouseOver='mouseOver(this);' class='research' onclick='detailPopup(\"" + data.result[i].job_id + "\");'>" + unescapeHtml(data.result[i].name) + "</a>";
						}
						else if ( occurptionNextId == "D000002") {
							str += "	<a href='javascript:void(0)' originClass='tech' id='job_"+ data.result[i].job_id +"' onMouseOut='mouseOut(this);' onMouseOver='mouseOver(this);' class='tech' onclick='detailPopup(\"" + data.result[i].job_id + "\");'>" + unescapeHtml(data.result[i].name) + "</a>";
						}
						else if ( occurptionNextId == "D000003") {
							str += "	<a href='javascript:void(0)' originClass='func' id='job_"+ data.result[i].job_id +"' onMouseOut='mouseOut(this);' onMouseOver='mouseOver(this);' class='func' onclick='detailPopup(\"" + data.result[i].job_id + "\");'>" + unescapeHtml(data.result[i].name) + "</a>";
						}
						else {
							str += "	<a href='javascript:void(0)' originClass='service' id='job_"+ data.result[i].job_id +"' onMouseOut='mouseOut(this);' onMouseOver='mouseOver(this);' class='service' onclick='detailPopup(\"" + data.result[i].job_id + "\");'>" + unescapeHtml(data.result[i].name) + "</a>";
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
				str += "</td>";
			}
		}
		
		str += "	</tr>";
		
		str += "</tbody>";

		console.log(str);

		body.append(str);
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

		var str = "";
		// 난이도
		$("#li_info").empty();
		if ( jobDetail.difficulty == "D000001" ) {
			str += "<span>초급</span><span>중급</span><span class='on'>고급</span>";
		}
		else if ( jobDetail.difficulty == "D000002" ) {
			str += "<span>초급</span><span  class='on'>중급</span><span>고급</span>";
		}
		else {
			str += "<span  class='on'>초급</span><span>중급</span><span>고급</span>";
		}
		str += "<em class='level'>난이도</em>"
		$("#li_info").append(str);
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
/* 		$('.mask').css({'display':'block'});
		$('.popup').css({'display':'block'});
		$(document.body).css({'position':'fixed','overflow': 'hidden'});
		var popHeight = $('.popup .wrap').height();
		if (popHeight > 800) {
		  $('.popup .wrap').css({'height': '776px'}); //padding 값 24px 제외
		  $('.popup .wrap .contents').css({'height': '722px'});
		} else {
		  $('.popup .wrap').css({'height': popHeight});
		} */
	}
	

	function detailPopup(id) {
		getJobDetail(id);
	}

</script>



<div id="wrap">
	<section>
		<div class="subVisual job">
			<div class="titleArea">
				<h2>ESS산업 일자리</h2>
          			<span>ESS 분야의 86개 일자리를 ESS산업 생태계와 직종별로 구분하여<br />확인할 수 있으며 일자리 클릭 시, 상세 내용을 볼 수 있습니다.</span>
			</div>
		</div>
  	</section>

   <section>
   <h3 class="hidden">ESS산업 일자리</h3>
     <div class="subWrap">
       <ul class="breadcrumb">
         <li><img src="/assets/img/btn/breadcrumb_home.png" alt="홈"></li>
         <li>ESS산업 일자리</li>
       </ul>

      <table class="tblJob job_table" id="job_table">
      </table>
    </div>
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
        <ul>
          <li>
            <h2 id="name"></h2>
            <p class="summary" id="definition"></p>
            <span class="codeDiv" id="code"></span>
            <em class="groupA" id="occupation"></em>
            <em class="groupB" id="education"></em>
          </li>
          <li id="li_info"></li>
        </ul>
      </div>
      <div class="sideLeft">
        <dl>
          <dt>평균연붕</dt>
          	<dd id="salary_level"></dd>
          <dt>필요경력</dt>
          	<dd id="career"></dd>
          <dt>전공</dt>
          	<dd id="major"></dd>
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
      <div class="sideRight">
        <h3>대표기업</h3>
        <ul class="link" id="companies"></ul>
        <!-- 배너로 변경 시에 사용할 것 !!!!!!
         <ul class="banner" id="companies"></ul> -->
        <h3>향후 연계 직업</h3>
        <ul class="link" id="future_career"></ul>
      </div>
    </div>
    <button class="btn_close"><i class="fa fa-times" aria-hidden="true"></i></button>
  </div>
</div>

<script>
/* 	$(function() {
		$('.open').on('click', function() {
			$('.mask').css({'display':'block'});
			$('.popup').css({'display':'block'});
			$(document.body).css({'position':'fixed','overflow': 'hidden'});
			var popHeight = $('.popup .wrap').height();
			if (popHeight > 800) {
			  $('.popup .wrap').css({'height': '776px'}); //padding 값 24px 제외
			  $('.popup .wrap .contents').css({'height': '722px'});
			} else {
			  $('.popup .wrap').css({'height': popHeight});
			}
		});
    
    	$('.btn_close').on('click', function() {
		    $('.mask').css({'display':'none'});
		    $('.popup').css({'display':'none'});
		    $(document.body).css({'position':'unset','overflow': 'auto'});
    	});
	}); */



	//팝업
    //----- OPEN
    $('.open').on('click', function(e)  {      
        $('.popup, .mask').fadeIn(350);
        e.preventDefault();
    });

    //----- CLOSE
    $('.btn_close').on('click', function(e)  {
        $('.popup, .mask').fadeOut(350);
        e.preventDefault();
    });	
	
</script>

