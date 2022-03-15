<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

  
<script type='text/javascript'>
	
	$(document).ready(function() {
	});

	function registration() {
        var formData = new FormData();

		var chkVal = ["question", "answer"];
		for (var i = 0; i < chkVal.length; i++) 
		{
			if ($("#" + chkVal[i]).val() == "" ) {
				alert($("#" + chkVal[i]).attr("title") + "은(는) 필수입력입니다.");
				$("#" + chkVal[i]).focus();
				return false;
			}
		}

		formData.append("question", $("#question").val());
		formData.append("answer", $("#answer").val());

		if (confirm('등록 하시겠습니까?')) {
			$.ajax({
			    type : "POST",
			    url : "/admin/api/notification/faq/registration",
			    data : formData,
			    processData: false,
			    contentType: false,
			    mimeType: 'multipart/form-data',
			    success : function(data) {
			    	var jsonData = JSON.parse(data);
			        if (jsonData.result == 1) {
			            alert("등록 되었습니다.");
			            location.href = "/admin/notification/faq/searchList";
			        } else {
			            alert("등록에 실패했습니다. 다시 시도해 주시기 바랍니다.");
			        }
			    },
			    error : function(err) {
			        alert(err.status);
			    }
			});
		}
	}

</script>
  
<!--페이지 루트-->
<div class="page-nation container">
	<a href="/admin/home/management"><i class="nav-icon fa fa-home mr5"></i>홈화면</a><span class="route_icon"></span>	
	<a href="/admin/notification/faq/searchList">알림/정보</a><span class="route_icon"></span>
	<a href="javascript:void(0);">FAQ</a>
</div>

<!--본문시작-->
<div class="sub-container container">
    
	<div class="sub-content">
<!--페이지 타이틀-->				
		<div class="page-header">
		    <h3 class="page-title text-primary-d2 text-120">FAQ</h3>
		</div>
		
		
<!--리스트 검색 상단-->				
		<div class="sub-content-box bgc-white">	
             <div class="content_wrap">
                  <div class="qna_wrap" id="cssmenu">
                      <div class="qna_list qna_top clearfix">
                          <div class="clearfix"><span class="fl fna_q w2 mr15">Q.</span>
                              <textarea class="w96 fl faq_data_disablednot" id="question" title="질의"></textarea></div>
                          <div class="clearfix"><span class="fl fna_a w2 mr15">A.</span>
                              <textarea class="w96 fl textarea_h faq_data_disablednot" id="answer" title="응답"></textarea></div>    
                      </div>
                  </div>
              </div>
              <div class="faq-view_btns">
                 <a href="javascript:void(0);" class="blue_btn ok_back_btn btn" onclick="registration();">등록</a>
                 <div class="fr">
                     <a href="/admin/notification/faq/searchList" class="gray_btn btn list_back_btn">목록</a>
                    
                 </div>
           	</div>
        </div>
	</div>
</div>
