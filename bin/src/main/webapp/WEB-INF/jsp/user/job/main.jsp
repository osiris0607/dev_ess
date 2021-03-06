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

		str += "<caption><span>??????????????? ????????? ????????? ??????</span></caption>";
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
		str += "		<th id='h1' colspan='2' class='top_class'>??????</th>";
		str += "		<th id='h2' class='top_job researcher'><span><img src='/assets/img/job_researcher_icon.png' alt='????????? ?????????'></span>?????????</th>";
		str += "		<th id='h3' class='top_job technical'><span><img src='/assets/img/job_technical_icon.png' alt='????????? ?????????'></span>?????????</th>";
		str += "		<th id='h4' class='top_job function'><span><img src='/assets/img/job_function_icon.png' alt='????????? ?????????'></span>?????????</th>";
		str += "		<th id='h5' class='top_job service_td'><span><img src='/assets/img/job_service_icon.png' alt='???????????? ?????????'></span>????????????</th>";
		str += "	<tr>";
		str += "</thead>";

		jobList = data.result;
		for ( var i=0; i<data.result.length; i++ ) {
			// ??????????????? ????????? tbody ??????
			if (data.result[i].category_id != categoryId) {
				categoryId = data.result[i].category_id;
				index = 1;
				isDetailFirst = true;
				categoryDetailId = "";
				occurptionNextId = "D000001";

				// ???????????? tbody??? ????????? ??????.
				if ( isCategoryFirst == true ) {
					isCategoryFirst = false;
				}
				else{
					str += "</tbody>";

					// ?????? ??????
					str += "<tbody class='none'>";
					str += "	<tr></tr>";
					str += "</tbody>";
				}

				// ?????? ?????????
				str += "<tbody>";
			}

			// Detail ??????
			if (categoryDetailId != data.result[i].category_detail_id) {
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

					if (data.result[i].category_id == "A") {
						str += "	<th class='leftRadius material' rowspan='3'>" + unescapeHtml(categoryName) + "</th>";
					}
					if (data.result[i].category_id == "B") {
						str += "	<th class='leftRadius production' rowspan='3'>" + unescapeHtml(categoryName) + "</th>";
					}
					if (data.result[i].category_id == "C") {
						str += "	<th class='leftRadius system' rowspan='3'>" + unescapeHtml(categoryName) + "</th>";
					}
					if (data.result[i].category_id == "D") {
						str += "	<th class='leftRadius business' rowspan='4'>" + unescapeHtml(categoryName) + "</th>";
					}
				}
				else {
					str += "<tr class='line_b'>";
				}


				var imageTag = "";
				if (data.result[i].category_detail_id == "A-1") {
					imageTag = "<img src='/assets/img/job_silicon_icon.png' alt='????????? ?????????' title='????????? ?????????' />";
					str += "<th class='tal material2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "A-2") {
					imageTag = "<img src='/assets/img/job_notsilicon_icon.png' alt='???????????? ?????????' title='???????????? ?????????' />";
					str += "<th class='tal material2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "A-3") {
					imageTag = "<img src='/assets/img/job_wafer_icon.png' alt='??????&middot;????????? ?????????' title='??????&middot;????????? ?????????' />";
					str += "<th class='tal material2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "B-1") {
					imageTag = "<img src='/assets/img/job_cell_icon.png' alt='??????&middot;??? ?????????' title='??? ?????????' />";
					str += "<th class='tal production2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "B-2") {
					imageTag = "<img src='/assets/img/job_module_icon.png' alt='?????? ?????????' title='?????? ?????????' />";
					str += "<th class='tal production2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "B-3") {
					imageTag = "<img src='/assets/img/job_equipment_icon.png' alt='?????? ?????????' title='?????? ?????????' />";
					str += "<th class='tal production2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "C-1") {
					imageTag = "<img src='/assets/img/job_part_icon.png' alt='???????????? ?????????' title='???????????? ?????????' />";
					str += "<th class='tal system2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "C-2") {
					imageTag = "<img src='/assets/img/job_facilities_icon.png' alt='???????????? ?????????' title='???????????? ?????????' />";
					str += "<th class='tal system2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "C-3") {
					imageTag = "<img src='/assets/img/job_recycling_icon.png' alt='???????????????&middot;???????????? ?????????' title='???????????????&middot;???????????? ?????????' />";
					str += "<th class='tal system2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "D-1") {
					imageTag = "<img src='/assets/img/job_consulting_icon.png' alt='????????????&middot;????????? ?????????' title='????????????&middot;????????? ?????????' />";
					str += "<th class='tal business2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "D-2") {
					imageTag = "<img src='/assets/img/job_consulting_icon.png' alt='??????&middot;?????? ?????????' title='??????&middot;?????? ?????????' />";
					str += "<th class='tal business2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "D-3") {
					imageTag = "<img src='/assets/img/job_operation_icon.png' alt='??????&middot;???????????? ?????????' title='??????&middot;???????????? ?????????' />";
					str += "<th class='tal business2'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}
				else if (data.result[i].category_detail_id == "D-4") {
					imageTag = "<img src='/assets/img/job_edu_icon.png' alt='?????? ?????????' title='?????? ?????????' />";
					str += "<th class='tal business2 edu_'><span>" + imageTag + "</span>" + unescapeHtml(data.result[i].category_detail_name) + "</th>";
				}

				
				// ??????
				
				str += "<td>";
				while (true) {

					// ??? ????????? ???????????? ?????? ??? ??????.
					if ( i >= data.result.length){
						break;
					}
					
					// D000001, D000002, D000003, D000004 ???????????? ???????????? ????????? ??????.
					// ????????? ????????? Blank??? ?????????.
					if ( categoryDetailId == data.result[i].category_detail_id && occurptionNextId == data.result[i].occupation ) {
						if ( occurptionNextId == "D000001") {
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
			else {
				// ??????
				str += "<td>";
				while (true) {
					
					if ( i >= data.result.length){
						break;
					}
					
					// D000001, D000002, D000003, D000004 ???????????? ???????????? ????????? ??????.
					// ????????? ????????? Blank??? ?????????.
					if ( categoryDetailId == data.result[i].category_detail_id && occurptionNextId == data.result[i].occupation ) {
						if ( occurptionNextId == "D000001") {
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
		$("#code").text("???????????? : " + jobDetail.category_id + ". " + jobDetail.category_name + " > "+ jobDetail.category_detail_id +". " + jobDetail.category_detail_name);
		$("#occupation").text(unescapeHtml(jobDetail.occupation_name));
		$("#education").text(jobDetail.education_name);

		var str = "";
		// ?????????
		$("#li_info").empty();
		if ( jobDetail.difficulty == "D000001" ) {
			str += "<span>??????</span><span>??????</span><span class='on'>??????</span>";
		}
		else if ( jobDetail.difficulty == "D000002" ) {
			str += "<span>??????</span><span  class='on'>??????</span><span>??????</span>";
		}
		else {
			str += "<span  class='on'>??????</span><span>??????</span><span>??????</span>";
		}
		str += "<em class='level'>?????????</em>"
		$("#li_info").append(str);
		// ??????
		$("#salary_level").text(jobDetail.salary_level + "??????");
		// ??????
		if ( jobDetail.career == "") {
			$("#career").text("??????");
		}
		else {
			$("#career").text(jobDetail.career);
		}
		// ?????? 
		str = "";
		for (var i=0; i<jobDetail.return_major_list.length; i++){
			str += jobDetail.return_major_list[i].name + ",";
		}
		if ( str == "") {
			$("#major").text("??????");
		}
		else {
			$("#major").text(str.substring(0, str.length-1));
		}
		// ????????? 
		str = "";
		for (var i=0; i<jobDetail.return_license_list.length; i++){
			str += jobDetail.return_license_list[i].kor_name + ",";
		}
		if ( str == "") {
			$("#license").text("??????");
		}
		else {
			$("#license").text(str.substring(0, str.length-1));
		}
		// ??????
		$("#knowledge").html("<li>" + unescapeHtml(jobDetail.knowledge) + "</li>");
		// ??????
		$("#skill").html("<li>" + unescapeHtml(jobDetail.skill) + "</li>");
		// ??????
		$("#attitude").html("<li>" + unescapeHtml(jobDetail.attitude) + "</li>");
		// ????????????
		str = "";

		// ????????? ???????????? ???????????? ?????? 
		// ????????? ???????????? ??????
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
			$("#companies").html("<li>??????</li>");
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
			$("#future_career").html("<li>??????</li>");
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
		  $('.popup .wrap').css({'height': '776px'}); //padding ??? 24px ??????
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
				<h2>ESS?????? ?????????</h2>
          			<span>ESS ????????? 86??? ???????????? ESS?????? ???????????? ???????????? ????????????<br />????????? ??? ????????? ????????? ?????? ???, ?????? ????????? ??? ??? ????????????.</span>
			</div>
		</div>
  	</section>

   <section>
   <h3 class="hidden">ESS?????? ?????????</h3>
     <div class="subWrap">
       <ul class="breadcrumb">
         <li><img src="/assets/img/btn/breadcrumb_home.png" alt="???"></li>
         <li>ESS?????? ?????????</li>
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
      <h1>????????? ??????</h1>
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
          <dt>????????????</dt>
          	<dd id="salary_level"></dd>
          <dt>????????????</dt>
          	<dd id="career"></dd>
          <dt>??????</dt>
          	<dd id="major"></dd>
          <dt>?????????</dt>
          	<dd id="license"></dd>
          <dt>????????????</dt>
          <dd>
            <p class="listTit">??????</p>
            <ul id="knowledge"></ul>
            <p class="listTit">??????</p>
            <ul id="skill"></ul>
            <p class="listTit">??????</p>
            <ul id="attitude"></ul>
          </dd>
        </dl>
      </div>
      <div class="sideRight">
        <h3>????????????</h3>
        <ul class="link" id="companies"></ul>
        <!-- ????????? ?????? ?????? ????????? ??? !!!!!!
         <ul class="banner" id="companies"></ul> -->
        <h3>?????? ?????? ??????</h3>
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
			  $('.popup .wrap').css({'height': '776px'}); //padding ??? 24px ??????
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



	//??????
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

