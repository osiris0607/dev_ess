<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

  
<script type='text/javascript'>

	var suggestList;
		
	$(document).ready(function() {
		$("#registration").attr("disabled", true);

		searchList(1);
	});

	function searchList(pageNo) {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/admin/api/notification/suggest/all' />");
		comAjax.setCallback("searchListCB");
		comAjax.ajax();
	}


	function searchListCB(data) {
		var total = data.totalCount;
		var body = $("#list_body");
		body.empty();
		
		if (total == 0) {
			var str = "<tr>" + "<td colspan='5'>조회된 결과가 없습니다.</td>" + "</tr>";
			body.append(str);
		} else {
			var str = "";
			var index = 1;
			
			suggestList = data.result;
			$.each(suggestList, function(key, value) {
				str += "<tr>";
				str += "	<td>" + index + "</td>";
				str += "	<td>";
				str += "		<input type='text' class='form-control brc-on-focusd-inline-block c7' value='" + value.name + "' />";
				str += "	</td>";
				str += "	<td>";
				var mails = value.email.split("@");
				str += "		<input type='text' class='form-control brc-on-focusd-inline-block mr5 fl w46' value= '"+ mails[0] + "'/><span class='fl email-span'>@</span>";
				str += "		<input type='text' class='form-control brc-on-focusd-inline-block fl ml5 w46' value= '"+ mails[1] + "'/>";
				str += "	</td>";
				str += "	<td>";
				if ( value.use_yn == "Y" ) {
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input' type='radio' onclick='useYN(\"" + value.suggest_id + "\", \"" + index + "\")' value='Y' id='suggest_radio" + index + "_y' name='suggest_radio" + index + "' checked='checked' />";
					str += "	<label class='form-check-label'>사용</label>";
					str += "</div>";
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input' type='radio' value='N' onclick='useYN(\"" + value.suggest_id + "\", \"" + index + "\")' id='suggest_radio" + index + "_n' name='suggest_radio" + index + "' />";
					str += "	<label class='form-check-label'>사용 안함</label>";
					str += "</div>";
				}
				else {
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input' type='radio' value='Y' onclick='useYN(\"" + value.suggest_id + "\", \"" + index + "\")' id='suggest_radio" + index + "_y' name='suggest_radio" + index + "' />";
					str += "	<label class='form-check-label'>사용</label>";
					str += "</div>";
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input' type='radio' value='N' onclick='useYN(\"" + value.suggest_id + "\", \"" + index + "\")' id='suggest_radio" + index + "_n' name='suggest_radio" + index + "' checked='checked' />";
					str += "	<label class='form-check-label'>사용 안함</label>";
					str += "</div>";
				}
				str += "	</td>";
				str += "	<td>";
				str += "		<button class='suggest_del_btn gray_btn btn' name='delsuggestcell' onclick='withdrawal(\"" + value.suggest_id + "\");'>삭제</button>";
				str += "	</td>";
				str += "</tr>";

				index++;
			});
			body.append(str);
		}
	}


	function useYN(id, index) {
		var categoryId = $("input[name='suggest_radio" + index + "']:checked").attr("id");
		var flag = $("input[name='suggest_radio" + index + "']:checked").val();
		
		// 같은 버튼을 눌렀을 시 반응안토록 한다.
		var returnFlag = false;
		$.each(suggestList, function(indexEach, item) 
		{
			if ( item.suggest_id == id && item.use_yn == flag) 
			{
				returnFlag = true;
				return;
			}
		});
	
		if ( returnFlag == true ){
			return;
		}

		
		if (confirm('변경 하시겠습니까?')) {
			$.ajax({
			    type : "POST",
			    url : "/admin/api/notification/suggest/useYN",
			    data :{
			    	"suggest_id" : id,
			    	"use_yn" : flag
			    },
			    success : function(data) {
			        if (data.result > 0) {
			            alert("변경 되었습니다.");
			            $.each(suggestList, function(indexEach, item) 
	            		{
	            			if ( item.suggest_id == id) 
	            			{
	            				item.use_yn = flag;
	            				return;
	            			}
	            		});
			        } else {
			            alert("변경에 실패했습니다.");
			        }
			    },
			    error : function(err) {
		        	alert(err);
			    }
			});
		}
		else {
	
			if (flag == "Y") {
				$("#" + categoryId).prop('checked', false);
				$("#" + categoryId.slice(0, (categoryId.length-2)) + "_n" ).prop('checked', true);
			}
			else {
				$("#" + categoryId).prop('checked', false);
				$("#" + categoryId.slice(0, (categoryId.length-2)) + "_y" ).prop('checked', true);
			}
		}	
	}
	

	function withdrawal(suggstId) {
		var formData = new FormData();
		formData.append("suggest_id", suggstId);
		
		if (confirm('삭제 하시겠습니까?')) {
			$.ajax({
			    type : "POST",
			    url : "/admin/api/notification/suggest/withdrawal",
			    data : formData,
			    processData: false,
			    contentType: false,
			    mimeType: 'multipart/form-data',
			    success : function(data) {
			    	var jsonData = JSON.parse(data);
			        if (jsonData.result == 1) {
			            alert("삭제 되었습니다.");
			            location.reload();
			        } else {
			            alert(jsonData.result_msg);
			        }
			    },
			    error : function(err) {
			        alert(err.status);
			    }
			});
		}
	}
	

	function addCell(){
		$("#add_btn").attr("disabled", true);
		$("#registration").attr("disabled", false);

		var str = "";
		str += "<tr>";
		str += "	<td>번호</td>";
		str += "	<td>";
		str += "		<input type='text' class='form-control' placeholder='담당자명' id='name' title='담당자명'>";
		str += "	</td>";
		str += "	<td>";
		str += "		<input type='text' class='form-control brc-on-focusd-inline-block mr5 fl w46' placeholder='example' id='email_front' title='이메일'/><span class='fl'>@</span>";
		str += "		<input type='text' class='form-control brc-on-focusd-inline-block fl ml5 w46' placeholder='ketep.re.kr' id='email_back' title='이메일'/>";
		str += "	</td>";
		str += "	<td></td>";
		str += "	<td></td>";

		$("#suggest_info_table > tbody:last").append(str);
	}

	function registration() {
        var formData = new FormData();

		var chkVal = ["name", "email_front", "email_back"];
		for (var i = 0; i < chkVal.length; i++) 
		{
			if ($("#" + chkVal[i]).val() == "" ) {
				alert($("#" + chkVal[i]).attr("title") + "은(는) 필수입력입니다.");
				$("#" + chkVal[i]).focus();
				return false;
			}
		}

		formData.append("name", $("#name").val());
		formData.append("email", $("#email_front").val() + "@" + $("#email_back").val() );

		if (confirm('등록 하시겠습니까?')) {
			$.ajax({
			    type : "POST",
			    url : "/admin/api/notification/suggest/registration",
			    data : formData,
			    processData: false,
			    contentType: false,
			    mimeType: 'multipart/form-data',
			    success : function(data) {
			    	var jsonData = JSON.parse(data);
			        if (jsonData.result == 1) {
			            alert("등록 되었습니다.");
			            location.reload();
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
	<a  href="/admin/home/management"><i class="nav-icon fa fa-home mr5"></i>홈화면</a><span class="route_icon"></span>
	<a href="javascript:void(0)">알림/정보</a><span class="route_icon"></span>
	<a href="javascript:void(0)">건의하기</a>
</div>

<!--본문시작-->
<div class="sub-container container">
	<div class="sub-content">
<!--페이지 타이틀-->				
		<div class="page-header">
			<h3 class="page-title text-primary-d2 text-120">건의하기</h3>
		</div>
<!--리스트 검색 상단-->				
		<div class="sub-content-box bgc-white clearfix">
		    <div class="border_box clearfix">
                      <h4 class="clearfix title_icon2 fl"><img src="/assets/img/tend_icon.png" alt="건의하기 아이콘" class="mr5 password_icon fl" /><span class="fl title_icon2_txt">건의하기 메일 수신자 설정</span></h4>
                      <div class="fr">
                          <input type="button" class="blue_btn2 btn suggest_add_cell_btn" name="addcell" id="add_btn" value="추가" onclick="addCell();"/> 
                      </div>
                      <table class="table table-striped-primary table-borderless text-dark-m1 mb-0 suggest" id="suggest_info_table">
                          <caption>건의 하기 </caption>
                          <colgroup>
                              <col style="width: 10%" />
                              <col style="width: 15%" />
                              <col style="width: 45%" />
                              <col style="width: 20%" />
                              <col style="width: 10%" />
                          </colgroup>	
                          <thead>			
                             <tr class="bgc-primary-d2 text-white text-sm-center">
                                  <th>No.</th>
                                  <th>담당자</th>		
                                  <th>건의하기 수신 이메일 주소</th>
                                  <th>사용여부</th>	
                                  <th>&nbsp;</th>
                              </tr>
                          </thead>
                          <tbody class="text-sm-center" id="list_body"></tbody>						
                      </table>
                      <div class="suggest_btns">
                          <button class="blue_btn ok_back_btn btn" id="registration" onclick="registration();">저장</button>
                      </div>
                  </div>
		</div>
	</div>
</div>