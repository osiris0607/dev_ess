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
				str += "	<a href='/user/jobDictionary/detail?job_id=" + value.job_id + "' target='_blank'>";
				str += "		<h3>" + value.category_name + "<br />" + value.name + "</h3>";
				str += "		<img src='data:image/png;base64," + value.upload_file_image +"' alt='" + value.attach_file_description + "'>";
				str += "		<span class='b_bg'>&nbsp;</span>";
				str += "	</a>";
				str += "</li>";
			});
			body.append(str);
		}
	}

	

	
</script>

<div id="wrap">
    <section>
      <div class="subVisual promising">
        <div class="titleArea">
          <h2>ESS산업 유망 일자리</h2>
          <span>ESS산업 일자리 중 관련산업의 성장 가능성 및 파급효과 등을 고려하여<br /> 향후 유망성이 있다고 판단되는 유망 일자리에 대한 정보를 제공합니다.</span>
      </div>
    </section>

    <section>
      <div class="subWrap">
        <ul class="breadcrumb">
          <li><img src="/assets/img/btn/breadcrumb_home.png" alt="홈"></li>
          <li>ESS산업 유망 일자리</li>
        </ul>

        <ul class="jobList" id="ul_body">
        </ul>
    </section>
  </div>
