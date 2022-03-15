<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<script>
	$(document).ready(function() {
	});
	
	function submit() {
		var form = $("#frm")[0];
        var formData = new FormData(form);
        var sUrl = "";
        
		var chkVal = ["first_name", "last_name", "country", "company", "email", "message"];
		
		for (var i = 0; i < chkVal.length; i++) {
			if ($("#" + chkVal[i]).val() == "") {
				alert($("#" + chkVal[i]).attr("title") + "은(는) 필수입력입니다.");
				$("#" + chkVal[i]).focus();
				return false;
			}
		}
		
/* 		if ($("#upload_file").val() != "") {
			formData.set("upload_file", $("#upload_file")[0].files[0]);
		} */
		formData.set("first_name", $("#first_name").val());
		formData.set("last_name", $("#last_name").val());
		formData.set("country", $("#country").val());
		formData.set("company", $("#company").val());
		formData.set("phone", $("#phone").val() + $("#phone_num").val());
		formData.set("email", $("#email").val());
		formData.set("message", $("#message").val());
		
		if (confirm('저장하시겠습니까?')) {
			$.ajax({
			    type : "POST",
			    url : "/contact/mail",
			    data : formData,
			    processData: false,
			    contentType: false,
			    success : function(data) {
			        if (data.result == "SUCCESS") {
			            alert("Success");
			        } else {
			            alert("실패!! 관리자에게 문의해주세요.");
			        }
			        location.reload();
			    },
			    error : function(err) {
			        alert(err.status);
			    }
			});
		}
	}
</script>  
  
<div id="wrap">
 <section>
    <div class="subVisual lecture">
      <div class="titleArea">
        <h2>알림/정보</h2>
        생태계별 ESS산업 기업을 지도 형식으로 표시하여 지역별 ESS산업 기업의 분포 확인 가능
      </div>
    </div>
  </section>

  <section>
    <div class="subWrap">
      <ul class="breadcrumb">
        <li><img src="/assets/img/btn/breadcrumb_home.png" alt="홈"></li>
        <li>알림/정보</li>
        <li>건의하기</li>
      </ul>

      <div class="referenceWrap">
        <h3>건의하기</h3>

        <fieldset>
        	<form>
	          <legend><span>건의하기</span></legend>
	          <ul class="suggest">
	            <li><label for="email">이름</label><input type="text" id="name" placeholder="이름을 입력하세요"></li>
	            <li><label for="title">제목</label><input type="text" id="title" placeholder="제목을 입력하세요"></li>
	            <li><label for="contents">내용</label><input type="text" id="content" placeholder="내용을 입력하세요"></li>
	            <li><label for="attach">파일첨부</label><input class="fileName" value="" disabled="disabled"><input type="file" id="attach" class="upFile"><label for="attach" class="upBtn">파일찾기</label> </li>
	          </ul>
			</form>	
			
          <div class="btnWrap">
            <input type="submit" class="btn" value="제출하기" onclick="submit();"></input>
          </div>
          
          <script>
            $(function () {
              var fileTarget = $('.upFile');
              fileTarget.on('change', function(){
                  if(window.FileReader){
                      var filename = $(this)[0].files[0].name;
                  } else {
                      var filename = $(this).val().split('/').pop().split('\\').pop();
                  }
                  $(this).siblings('.fileName').val(filename);
              });
            });
          </script>
        </fieldset>
      </div>
    </div>
  </section>
</div>
