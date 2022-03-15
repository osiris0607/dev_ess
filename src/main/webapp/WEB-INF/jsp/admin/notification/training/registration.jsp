<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authentication property="principal.username" var="member_id" />
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

  
<script type='text/javascript'>
	
	$(document).ready(function() {
	});

	function registration() {
        var formData = new FormData();

		var chkVal = ["title", "enforcement_agency", "address", "contact", "home_page"];
		for (var i = 0; i < chkVal.length; i++) 
		{
			if ($("#" + chkVal[i]).val() == "" ) {
				alert($("#" + chkVal[i]).attr("title") + "은(는) 필수입력입니다.");
				$("#" + chkVal[i]).focus();
				return false;
			}
		}

		formData.append("title", $("#title").val());
		formData.append("enforcement_agency", $("#enforcement_agency").val());
		formData.append("address", $("#address").val());
		formData.append("contact", $("#contact").val());
		formData.append("home_page", $("#home_page").val());

		formData.append("attach_file_description", $("#attach_file_description").val());

		if ( $("#attach_file")[0].files[0] != undefined && $("#attach_file")[0].files[0] != "") {
			formData.append("attach_file", $("#attach_file")[0].files[0]);
		}
		

		if (confirm('등록 하시겠습니까?')) {
			$.ajax({
			    type : "POST",
			    url : "/admin/api/notification/training/registration",
			    data : formData,
			    processData: false,
			    contentType: false,
			    mimeType: 'multipart/form-data',
			    success : function(data) {
			    	var jsonData = JSON.parse(data);
			        if (jsonData.result == 1) {
			            alert("등록 되었습니다.");
			            location.href = "/admin/notification/training/searchList";
			        } else {
			            alert("등록에 실패하였습니다. 다시 시도해 주시기 바랍니다.");
			        }
			    },
			    error : function(err) {
			        alert(err.status);
			    }
			});
		}
	}


	function execPostCode() {
		new daum.Postcode({
            oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
               var extraRoadAddr = ''; // 도로명 조합형 주소 변수

               // 법정동명이 있을 경우 추가한다. (법정리는 제외)
               // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
               if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                   extraRoadAddr += data.bname;
               }
               // 건물명이 있고, 공동주택일 경우 추가한다.
               if(data.buildingName !== '' && data.apartment === 'Y'){
                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
               }
               // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
               if(extraRoadAddr !== ''){
                   extraRoadAddr = ' (' + extraRoadAddr + ')';
               }
               // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
               if(fullRoadAddr !== ''){
                   fullRoadAddr += extraRoadAddr;
               }

               $("#zipcode").val(data.zonecode);
               $("#address").val(fullRoadAddr);
               
           }
        }).open();
	}
	

</script>
  
<!--페이지 루트-->
<div class="page-nation container">
	<a href="/admin/home/management"><i class="nav-icon fa fa-home mr5"></i>홈화면</a><span class="route_icon"></span>
	<a href="/admin/notification/trend/searchList">알림/정보</a><span class="route_icon"></span>
	<a href="javascript:void(0);">훈련과정</a>
</div>

<!--본문시작-->
<div class="sub-container container">
	<div class="sub-content">
<!--페이지 타이틀-->				
	<div class="page-header">
		<h3 class="page-title text-primary-d2 text-120">훈련과정</h3>
	</div>
<!--리스트 검색 상단-->				
	<div class="sub-content-box bgc-white">
                 <table class="job_write">
			<caption>훈련과정명</caption>
			<tbody>
			   <tr>							
					<th class="w20 jop_write_table_title"><span class="icon_box"><span class="necessary_icon">*</span>훈련과정명</span></th>
					<td class="w80">
						<input type="text" class="form-control brc-on-focusd-inline-block w90" id="title"/>                              
					</td>									
			   </tr>
			</tbody>
	   </table>
	   <table class="job_write">
			<caption>기관명</caption>
			<tbody>
			   <tr>							
					<th class="w20 jop_write_table_title"><span class="icon_box"><span class="necessary_icon">*</span>기관명</span></th>
					<td class="w80">
						<input type="text" class="form-control brc-on-focusd-inline-block w90" id="enforcement_agency" title="기관명"/>                              
					</td>									
			   </tr>
			</tbody>
	   </table>
	   <table class="job_write">
			<caption>기관정보 - 주소</caption>
			<tbody>
			   <tr>	
					<th rowspan="3" class="w20 jop_write_table_title">기관정보</th>
					
					<td class="w10 txt_r hm" style="padding-top:30px !important"><span class="icon_box"><span class="necessary_icon">*</span>주소</span></td>
					<td class="w80">
                        <input type="text" class="form-control brc-on-focusd-inline-block w87" name="address" id="address" title="주소" />
                        <button type="button" class="gray_btn mt5" onclick="execPostCode();"><i class="fa fa-search"></i>검색</button>
                    </td>										
			   </tr>
			
			   <tr>							
					<td class="w10 txt_r hm" style="padding-top:30px !important"><span class="necessary_icon">*</span>연락처</td>
					<td class="w80">
						<input type="text" class="form-control brc-on-focusd-inline-block w89" id="contact" title="연락처"/>                              
					</td>									
			   </tr>

	   
			   <tr>							
					<td class="w10 txt_r hm" style="padding-top:30px !important"><span class="necessary_icon">*</span>홈페이지</td>
						<td class="w80">
							<input type="text" class="form-control brc-on-focusd-inline-block w89" id="home_page" title="홈페이지"/>                              
						</td>									
				   </tr>
				</tbody>
		   </table>
           <table class="job_write">
           		<caption>첨부 파일</caption>				
                    	<tr>							
                       	<th rowspan="2" class="w20 jop_write_table_title">이미지</th>
                  	 	<td class="w18 txt_r">				
                         		 <span class="icon_box">기업 이미지</span>  
				 		</td>                           
						<td class="w80 txt_r">                
                            <div class="job_file_upload w87"> 
                                <div class="custom-file w100">
                                    <input type="file" class="custom-file-input custom-file-input-write-company" id="attach_file" name="attach_file">
                                    <label class="custom-file-label custom-control-label-write-company" for="attach_file" id="attach_file_name">선택된 파일 없음</label>
                                </div>
                            </div>                               
                     	 	</td>		
                    	</tr> 
                    	<tr>
                          <td class="w18 txt_r">이미지 설명</td>	
                          <td><input type="text" class="form-control brc-on-focusd-inline-block w87 fl" id="attach_file_description" placeholder="이미지 등록시 이미지 설명글을 넣어주세요."></td>	
                      </tr>                       					
            </table> 
		   
		   
		   <div class="industry-trend-view_btns">
                      <a href="javascript:void(0);" class="blue_btn ok_back_btn btn" onclick="registration();">등록</a>
                      <div class="fr">
                          <a href="/admin/notification/training/searchList" class="gray_btn btn list_back_btn">목록</a>
                      </div>
                 </div>
		</div>    
	</div>
</div>
