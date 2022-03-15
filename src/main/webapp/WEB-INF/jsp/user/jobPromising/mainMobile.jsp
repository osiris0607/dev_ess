<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>


<script type='text/javascript'>

	$(document).ready(function() {
		searchList(1);
	});

	function searchList(pageNo) {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/user/api/jobTop/all' />");
		comAjax.setCallback("searchListCB");
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
			var str = "";
			$.each(data.result, function(key, value) {
				str += "<li>";
				str += "	<a href='javascript:void(0)' onclick='detailPopup(\"" + value.job_id + "\");'>";
				str += "		<h2>" + value.category_name + "<br />" + value.name + "</h2>";
				str += "		<img src='data:image/png;base64," + value.upload_file_image +"' alt='" + value.attach_file_description + "'>";
				str += "		<span class='b_bg'>&nbsp;</span>";
				str += "	</a>";
				str += "</li>";
			});
			body.append(str);
		}
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

		var str = "<img src='data:image/png;base64," + jobDetail.upload_file_image +"' alt='" + jobDetail.attach_file_description + "'>";
		$("#image_file").html(str);

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
		// keco
		$("#keco").html("<li>" + unescapeHtml(jobDetail.keco) + "</li>");
		


		// 대표기업
		str = "";

		// 대표기업
		str = "";
        str += "<h4>관련 기업</h4>";
        if (jobDetail.company_1_name != "") {
        	str += "<div class='coWrap'>";
        	str += "	<div class='inWrap'>";
        	str += "		<h5>" + jobDetail.company_1_name + "</h5>";
        	str += "		<ul class='normal'>";
        	str += "			<li>기업규모 : " + jobDetail.company_1_size + "</li>";
        	str += "			<li>위치 : " + unescapeHtml(jobDetail.company_1_address) + "</li>";
        	str += "			<li>주요 제품 및 서비스 : " + unescapeHtml(jobDetail.company_1_main_product) + "</li>";
        	str += "		</ul>";
        	str += "	</div>";
        	str += "	<div class='imgWrap'>";
        	str += "		<img src='data:image/png;base64," + jobDetail.company_1_logo +"' alt='" + jobDetail.company_1_name + "'>";
        	str += "	</div>";
        	str += "</div>";
		}
        else {
        	str += "<div class='coWrap'>";
        	str += "	<div class='inWrap'>";
        	str += "		<h5>없음</h5>";
        	str += "	</div>";
        	str += "</div>";
       	}
		if (jobDetail.company_2_name != "") {
        	str += "<div class='coWrap'>";
        	str += "	<div class='inWrap'>";
        	str += "		<h5>" + jobDetail.company_2_name + "</h5>";
        	str += "		<ul class='normal'>";
        	str += "			<li>기업규모 : " + jobDetail.company_2_size + "</li>";
        	str += "			<li>위치 : " + unescapeHtml(jobDetail.company_2_address) + "</li>";
        	str += "			<li>주요 제품 및 서비스 : " + unescapeHtml(jobDetail.company_2_main_product) + "</li>";
        	str += "		</ul>";
        	str += "	</div>";
        	str += "	<div class='imgWrap'>";
        	str += "		<img src='data:image/png;base64," + jobDetail.company_2_logo +"' alt='" + jobDetail.company_2_name + "'>";
        	str += "	</div>";
        	str += "</div>";
		}
		else {
        	str += "<div class='coWrap'>";
        	str += "	<div class='inWrap'>";
        	str += "		<h5>없음</h5>";
        	str += "	</div>";
        	str += "</div>";
		}
		if (jobDetail.company_3_name != "") {
        	str += "<div class='coWrap'>";
        	str += "	<div class='inWrap'>";
        	str += "		<h5>" + jobDetail.company_3_name + "</h5>";
        	str += "		<ul class='normal'>";
        	str += "			<li>기업규모 : " + jobDetail.company_3_size + "</li>";
        	str += "			<li>위치 : " + unescapeHtml(jobDetail.company_3_address) + "</li>";
        	str += "			<li>주요 제품 및 서비스 : " + unescapeHtml(jobDetail.company_3_main_product) + "</li>";
        	str += "		</ul>";
        	str += "	</div>";
        	str += "	<div class='imgWrap'>";
        	str += "		<img src='data:image/png;base64," + jobDetail.company_3_logo +"' alt='" + jobDetail.company_3_name + "'>";
        	str += "	</div>";
        	str += "</div>";
		}
		else {
        	str += "<div class='coWrap'>";
        	str += "	<div class='inWrap'>";
        	str += "		<h5>없음</h5>";
        	str += "	</div>";
        	str += "</div>";
		}
		$("#companies").html(str);
		

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

		$('#detail_popup').fadeIn(350);
		$('.mask').fadeIn(350);
	}
	

	function detailPopup(id) {
		getJobDetail(id);
	}
	
</script>

<div id="wrap">
  <header class="sub">
    <div class="headerSubWrap">
      <button class="prev" onclick="location.href='/user/mobile/main/mainMobile';"></button>
      <h1>ESS산업 유망 일자리 </h1>
    </div>
  </header>

  <section class="job_list_box">
    <ul class="jobList" id="ul_body"></ul>
  </section>
</div>



<!-- Layer -->
  <div class="mask"></div>
  <!-- 상세보기 -->
  <div id="detail_popup" class="popup">
    <div class="wrap">
      <div class="header">
        <h1>일자리 카드</h1>
      </div>

      <div class="contents">
        <div class="boxWrap">
          <div class="inBox">
            <h2 id="name"></h2>
            <p class="summary" id="definition"></p>
            <p class="code" id="code"></p>
            <ul class="level">
              <li><em class="box">업무 난이도</em></li>
              <li><em class="on" id="level_info"></em></li>
            </ul>
          </div>
          
        </div>
        <div class="imgBox" id="image_file">
		  <!--380*254-->
        </div>
        <div class="boxWrap dt">
          <h3>직무자격</h3>
          <dl class="qualification">
            <dt>요구학력</dt>
            	<dd id="education"></dd>
            <dt>필요경력</dt>
            	<dd id="career"></dd>
            <dt>급여수준</dt>
            	<dd id="salary_level"></dd>
            <dt>전공</dt>
            	<dd id="major"></dd>
            <dt class="last">자격증</dt>
            	<dd id="license"></dd>

          </dl>
        </div>
        <div class="boxWrap">
          <h3>직무능력</h3>
          <ul class="ability">
            <li>
              <h4>지식</h4>
	              <ul id="knowledge">
	              </ul>
            </li>
            <li>
              <h4>기술</h4>
	              <ul id="skill">
	              </ul>
            </li>
            <li>
              <h4>태도</h4>
	              <ul id="attitude">
	              </ul>
            </li>
          </ul>
        </div>
        <div class="boxWrap">
          <h4>관련 일자리</h4>
          <ul class="normal" id="future_career">
          </ul>
        </div>
        <div class="boxWrap">
          <h4>한국고용직업 분류</h4>
          <ul class="normal" id="keco">
            <li>[1521]금속〮재료공학 기술자 및 연구원</li>
          </ul>
        </div>
        
        <div class="boxWrap" id="companies"></div>
        
      </div>
      <button class="btn_close">닫기</button>
    </div>
  </div>
  
  
    <script>
    $(function () {
      $('.open').on('click', function () {
        var id = $(this).attr("id-data");
        $('#' + id).fadeIn();
        // $('.mask').css({'display':'block'});
        $(document.body).css({'overflow': 'hidden'});
      });
      $('.btn_close').on('click', function () {
        var id = $(this).parent('div').parent('div').attr('id');
        $('#' + id).css({'display':'none'});
        $('.mask').css({'display':'none'});
        $(document.body).css({'overflow': 'auto'});
      });
    });
  </script>