<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

  
<script type='text/javascript'>

	var memberList;
		
	$(document).ready(function() {
		$("#registration_btn").attr("disabled", true);

		searchList(1);
	});

	function searchList(pageNo) {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/admin/api/member/searchList' />");
		comAjax.setCallback("searchListCB");
		comAjax.addParam("pageIndex", pageNo);
		comAjax.addParam("orderby", "REG_DATE DESC");
		comAjax.ajax();
	}

	function searchListCB(data) {
		var total = data.totalCount;
		var body = $("#list_body");
		body.empty();

		$("#add_btn").attr("disabled", false);
		$("#registration_btn").attr("disabled", true);
		
		if (total == 0) {
			var str = "<tr>" + "<td colspan='5'>조회된 결과가 없습니다.</td>" + "</tr>";
			body.append(str);
		} else {
			var str = "";
			var index = 1;
			var params = {
					divId : "pageNavi",
					pageIndex : "pageIndex",
					totalCount : total,
					eventName : "searchList"
				};

			gfnRenderPaging(params);
			
			memberList = data.result;
			$.each(memberList, function(key, value) {
				str += "<tr>";
				str += "	<td>" + index + "</td>";
				str += "	<td>";
				str += "		<input type='text' class='form-control c7 clear_input1' value='" + value.name + "' />";
				str += "	</td>";
				str += "	<td>";
				str += "		<input type='text' class='form-control c7 clear_input1' value='" + value.member_id + "' />";
				str += "	</td>";
				str += "	<td>";
				if ( value.admin_use_yn == "Y" ) {
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input' type='radio' onclick='useYN(\"" + value.member_id + "\", \"" + index + "\")' value='Y' id='member_radio" + index + "_y' name='member_radio" + index + "' checked='checked' />";
					str += "	<label class='form-check-label'>사용</label>";
					str += "</div>";
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input' type='radio' onclick='useYN(\"" + value.member_id + "\", \"" + index + "\")' value='N' id='member_radio" + index + "_n' name='member_radio" + index + "' />";
					str += "	<label class='form-check-label'>사용 안함</label>";
					str += "</div>";
				}
				else {
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input' type='radio' value='Y' onclick='useYN(\"" + value.member_id + "\", \"" + index + "\")' id='member_radio" + index + "_y' name='member_radio" + index + "' />";
					str += "	<label class='form-check-label'>사용</label>";
					str += "</div>";
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input' type='radio' value='N' onclick='useYN(\"" + value.member_id + "\", \"" + index + "\")' id='member_radio" + index + "_n' name='member_radio" + index + "' checked='checked' />";
					str += "	<label class='form-check-label'>사용 안함</label>";
					str += "</div>";
				}
				str += "	</td>";
				str += "	<td>";
				str += "		<button class='suggest_del_btn gray_btn btn' name='delaccountcell' onclick='initPassword(\"" + value.member_id + "\")'>초기화</button>";
				str += "	</td>";
				str += "</tr>";

				index++;
			});
			body.append(str);
		}
	}

	function initPassword(id) {
		if (confirm('초기화 하시겠습니까?')) {
			$.ajax({
			    type : "POST",
			    url : "/admin/api/member/initPassword",
			    data :{
			    	"member_id" : id,
			    },
			    success : function(data) {
			        if (data.result > 0) {
			            alert("초기화 되었습니다.");
			        } else {
			            alert("비밀번호 초기화에 실패했습니다. 다시 시도해 주시기 바랍니다.");
			        }
			    },
			    error : function(err) {
		        	alert(err);
			    }
			});
		}
	}

	function useYN(id, index) {
		var categoryId = $("input[name='member_radio" + index + "']:checked").attr("id");
		var flag = $("input[name='member_radio" + index + "']:checked").val();
		
		// 같은 버튼을 눌렀을 시 반응안토록 한다.
		var returnFlag = false;
		$.each(memberList, function(indexEach, item) 
		{
			if ( item.member_id == id && item.admin_use_yn == flag) 
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
			    url : "/admin/api/member/useYN",
			    data :{
			    	"member_id" : id,
			    	"admin_use_yn" : flag
			    },
			    success : function(data) {
			        if (data.result > 0) {
			            alert("변경 되었습니다.");
			            $.each(memberList, function(indexEach, item) 
	            		{
	            			if ( item.member_id == id) 
	            			{
	            				item.admin_use_yn = flag;
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

	function registration() {
		var chkVal = ["name", "member_id", "pwd"];
		for (var i = 0; i < chkVal.length; i++) 
		{
			if ($("#" + chkVal[i]).val() == "" ) {
				alert($("#" + chkVal[i]).attr("title") + "은(는) 필수입력입니다.");
				$("#" + chkVal[i]).focus();
				return false;
			}
		}

		var idReg = /^[A-za-z]/g;
		var temp = $("#member_id").val();
		if(idReg.test($("#member_id").val()) == false){
			alert("아이디는 영문자로 시작하는 영문자 또는 숫자이어야 합니다.");
		 	return;
		}

		var base64newPwd = btoa($("#pwd").val());
		if (confirm('등록 하시겠습니까?')) {
			$.ajax({
			    type : "POST",
			    url : "/admin/api/member/registration",
			    data :{
			    	"name" : $("#name").val(),
			    	"member_id" : $("#member_id").val(),
			    	"pwd" : base64newPwd,
			    },
			    success : function(data) {
				    if (data.result == 404) {
				    	alert(data.result_msg);
				    }
				    else {
				    	alert("등록 되었습니다");
				    	location.reload();
				    }
			    },
			    error : function(err) {
		        	alert(err);
			    }
			});
		}
	}
	

	function addCell(){
		$("#add_btn").attr("disabled", true);
		$("#registration_btn").attr("disabled", false);
		
		var str = "";
		str += "<tr>";
		str += "	<td>번호</td>";
		str += "	<td>";
		str += "		<input type='text' class='form-control' id='name' title='사용자' placeholder='이름을 입력하세요' />";
		str += "	</td>";
		str += "	<td>";
		str += "		<input type='text' class='form-control brc-on-focusd-inline-block mr5' id='member_id' title='아이디' placeholder='아이디를 입력하세요' />";
		str += "	</td>";
		str += "	<td></td>";
		str += "	<td>"
		str += "		<input type='password' class='form-control w60' style='display:inline-block' id='pwd' title='비밀번호' placeholder='비밀번호 입력' />"
		str += "	</td>";
		str += "</tr>";
		
		$("#account_info_table > tbody:last").append(str);
	}


</script>
  
<!--페이지 루트-->
<div class="page-nation container">
	<a href="/admin/home/management"><i class="nav-icon fa fa-home mr5"></i>홈화면</a><span class="route_icon"></span>
	<a href="javascript:void(0)">관리자</a><span class="route_icon"></span>
	<a href="javascript:void(0)">계정 관리</a>
</div>

<!--본문시작-->
<div class="sub-container container">
	<div class="sub-content">
<!--페이지 타이틀-->				
		<div class="page-header">
			<h3 class="page-title text-primary-d2 text-120">계정 관리</h3>
		</div>
<!--리스트 검색 상단-->				
		<div class="sub-content-box bgc-white">
		    <div class="box_bg">
                   <h4 class="clearfix title_icon"><img src="/assets/img/account_icon.png" class="mr5 password_icon fl" alt="자물쇠 아이콘" /><span class="fl txt">계정 관리 </span></h4>
                   <p class="border_box_p gray_p">비밀번호를 초기화 하시면 초기화된 비밀번호는 <span style="color:#2874d0">'1234'</span> 입니다.</p>
                   <div class="border_top_box">
                       <div class="fr">
                           <input type="button" class="blue_btn2 btn suggest_add_cell_btn fl" onclick="addCell();" name="account_addcell" id="add_btn" value="추가"> 
                           <input type="button" class="blue_btn btn fl ml5" id="registration_btn" onclick="registration();" value="저장"> 
                       </div>
                       <!--리스트 상단 검색결과-->                
                       <div class="list_search_end_box clearfix">
                           <!--리스트 상단 검색결과 테이블-->    
                           <table class="table table-striped-primary table-borderless text-dark-m1 mb-0" id="account_info_table">
                               <thead>
                                   <tr class="bgc-primary-d2 text-white text-sm-center">
                                     <th>순번</th>
                                     <th>사용자</th>
                                     <th>아이디</th>
                                     <th>계정권한 유지</th>
                                     <th>비밀번호 초기화</th>				 
                                   </tr>
                               </thead>
                               <tbody class="bgc-primary-d2 text-sm-center" id="list_body"></tbody>
                           </table> 

                           <div class="Page_navigation">
								<div id="pageNavi"></div>
								<input type="hidden" id="pageIndex" name="pageIndex"/>
		  					</div>        
                       </div>
                   </div>
               </div>
           </div>
	</div>
</div>       