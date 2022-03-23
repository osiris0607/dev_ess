<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

  
<script type='text/javascript'>

	var jobList;
	var jobTopList = new Array();

	$(document).ready(function() {
	
		// 연관 일자리 확인
		$("#job_popup_ok").click(function() {
			if (relativeJobChoiceNo == "1" ) {
				relativeJob_1 = $(":input:radio[name=jobOptions1]:checked").val();
				$("#job_result_1").val($(":input:radio[name=jobOptions1]:checked").attr("text"));
			}
			else if (relativeJobChoiceNo == "2" ) {
				relativeJob_2 = $(":input:radio[name=jobOptions1]:checked").val();
				$("#job_result_2").val($(":input:radio[name=jobOptions1]:checked").attr("text"));
			}
			else {
				relativeJob_3 = $(":input:radio[name=jobOptions1]:checked").val();
				$("#job_result_3").val($(":input:radio[name=jobOptions1]:checked").attr("text"));
			}
		});
	
		$("#job_search_text").on("keydown", function(key) {
	        //키의 코드가 13번일 경우 (13번은 엔터키)
	        if (key.keyCode == 13) {
	        	jobSearch();
	        }
	    });

		// 연관 일자리 Init
		getJobList();
		getJobTopList();
	});
	
	function getJobList() {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/admin/api/job/all' />");
		comAjax.setCallback("getJobListCB");
		comAjax.ajax();
	}
	
	function getJobListCB(data) {
		var body = $("#job_search_list");
		body.empty();
		if (data.result.length == 0) {
			var str = "조회된 결과가 없습니다.";
			body.append(str);
		} else {
			jobList = data.result;
			var str = "";
			$.each(jobList, function(key, value) {
				str += "<div class='form-check form-check-inline'>";
				str += "	<input class='form-check-input job_view_disabled' type='radio' name='jobOptions1' text='"+ unescapeHtml(value.name) + "' value='" + value.job_id + "'>";
				str += "	<label class='form-check-label'>" + unescapeHtml(value.name) + "</label>";
				str += "</div>";
			});
			body.append(str);
		}
	}

	function jobSearch() {
		var str = "";
		var body = $("#job_search_list");
		var serachWord = $("#job_search_text").val();
		body.empty();
		if ( serachWord == null || serachWord == "") {
			$.each(jobList, function(key, value) {
				str += "<div class='form-check form-check-inline'>";
				str += "	<input class='form-check-input job_view_disabled' type='radio' name='jobOptions1' text='"+ unescapeHtml(value.name) + "' value='" + value.job_id + "'>";
				str += "	<label class='form-check-label'>" + unescapeHtml(value.name) + "</label>";
				str += "</div>";
			});
		}
		else {
			$.each(jobList, function(key, value) {
				if (value.name.indexOf(serachWord) != -1 ) {
					str += "<div class='form-check form-check-inline'>";
					str += "	<input class='form-check-input job_view_disabled' type='radio' name='jobOptions1' text='"+ unescapeHtml(value.name) + "' value='" + value.job_id + "'>";
					str += "	<label class='form-check-label'>" + unescapeHtml(value.name) + "</label>";
					str += "</div>";
				}
			});
		}
		body.append(str);
	}


	function getJobTopList() {
		var comAjax = new ComAjax();
		comAjax.setUrl("<c:url value='/admin/api/jobTop/all' />");
		comAjax.setCallback("getJobTopListCB");
		comAjax.ajax();
	}
	
	function getJobTopListCB(data) {
		var body = $("#list_body");
		body.empty();
		var index = 1;
		if (data.result.length == 0) {
			for (index; index<=10; index++ ) {
				str += "<tr>"
					str += "	<td>"+ index +"</td>"
					str += "	<td><input type='text' class='form-control brc-on-focusd-inline-block' id='category_" + index + "'/></td>";
					str += "	<td><input type='text' class='form-control brc-on-focusd-inline-block' id='category_detail_" + index + "'/></td>";
					str += "	<td><input type='text' class='form-control brc-on-focusd-inline-block' id='occupation_" + index + "'/></td>";
					str += "	<td><input type='text' class='form-control brc-on-focusd-inline-block' id='name_" + index + "'/></td>";
					str += "	<td><button class='btn blue_btn2 open_popup1' onclick='searchPopup();'>검색</button></td>";
				str += "</tr>"
			}
			body.append(str);
		} else {
			jobTopList = data.result;
			var str = "";
			$.each(jobTopList, function(key, value) {
				str += "<tr>"
				str += "	<td>"+ index +"</td>"
				str += "	<td><span>[" + value.category_id + "] </span>" + unescapeHtml(value.category_name) + "</td>";
				str += "	<td><span>[" + value.category_detail_id + "] </span>" + unescapeHtml(value.category_detail_name) + "</td>";
				str += "	<td>" + unescapeHtml(value.occupation_name) + "</td>";
				str += "	<td>" + unescapeHtml(value.name) + "</td>";
				str += "	<td><button class='suggest_del_btn gray_btn btn job10delnotice_popup_open' name='deljobtencell' onclick='deletePopup(\"" + value.job_id + "\",\"" + value.name + "\");'>삭제</button></td>";
				str += "</tr>"
				index++;
			});

			// 유망 일자리가 10개보다 작으면 선택할 수 있도록 만든다.
			for (index; index<=10; index++ ) {
				str += "<tr>"
					str += "	<td>"+ index +"</td>"
					str += "	<td><input type='text' class='form-control brc-on-focusd-inline-block' id='category_" + index + "'/></td>";
					str += "	<td><input type='text' class='form-control brc-on-focusd-inline-block' id='category_detail_" + index + "'/></td>";
					str += "	<td><input type='text' class='form-control brc-on-focusd-inline-block' id='occupation_" + index + "'/></td>";
					str += "	<td><input type='text' class='form-control brc-on-focusd-inline-block' id='name_" + index + "'/></td>";
					str += "	<td><button class='btn blue_btn2 open_popup1' onclick='searchPopup();'>검색</button></td>";
				str += "</tr>"
			}
			body.append(str);
		}
	}

	function searchPopup() {
        var $jobtenpanel = $(".job10_popup_pannel1");
        var $jobtenContents = $jobtenpanel.find(".job10_popup_contents1");
        
		if ($jobtenContents.outerWidth() < $(document).width()) {
            $jobtenContents.css("margin-left", "-" + $jobtenContents.outerWidth() / 2 + "px");
        } else {
            $jobtenContents.css("left", "0px");
        }
        // 팝업 가운데 설정(세로)
        if ($jobtenContents.outerHeight() < $(document).height()) {
            $jobtenContents.css("margin-top", "-" + $jobtenContents.outerHeight() / 2 + "px");
        } else {
            $jobtenContents.css("top", "0px");
        }
        // 레이어 팝업 열기
        $jobtenpanel.fadeIn();
	}


	function confirm() {
		var jobId = $(":input:radio[name=jobOptions1]:checked").val();
		$.ajax({
		    type : "POST",
		    url : "/admin/api/jobTop/useYN",
		    data :{
		    	"job_id" : jobId,
		    	"ranking" : "Y"
		    },
		    success : function(data) {
		        if (data.result > 0) {
		            alert("선택 되었습니다.");
		            location.reload();
		        } else {
		            alert("선택 실패했습니다.");
		        }
		    },
		    error : function(err) {
	        	alert(err);
		    }
		});
	}

	
	function deletePopup(id, occupation) {
		$("#delete_name").text(occupation);
		$("#delete_name").val(id);
		var $jobTendelnoticepanel = $(".job10delnotice_popup_panel");
        var $jobTendelnoticepanelContents = $jobTendelnoticepanel.find(".job10delnotice_popup_contents");
        if ($jobTendelnoticepanelContents.outerWidth() < $(document).width()) {
            $jobTendelnoticepanelContents.css("margin-left", "-" + $jobTendelnoticepanelContents.outerWidth() / 2 + "px");
        } else {
            $jobTendelnoticepanelContents.css("left", "0px");
        }
        // 팝업 가운데 설정(세로)
        if ($jobTendelnoticepanelContents.outerHeight() < $(document).height()) {
            $jobTendelnoticepanelContents.css("margin-top", "-" + $jobTendelnoticepanelContents.outerHeight() / 2 + "px");
        } else {
            $jobTendelnoticepanelContents.css("top", "0px");
        }
        // 레이어 팝업 열기
        $jobTendelnoticepanel.fadeIn();
	}

	function withdrawal() {
		$.ajax({
		    type : "POST",
		    url : "/admin/api/jobTop/useYN",
		    data :{
		    	"job_id" : $("#delete_name").val(),
		    	"ranking" : "N"
		    },
		    success : function(data) {
		        if (data.result > 0) {
		            alert("삭제 되었습니다.");
		            location.reload();
		        } else {
		            alert("삭제 실패했습니다.");
		        }
		    },
		    error : function(err) {
	        	alert(err);
		    }
		});
	}
	
</script>

  
<!--페이지 루트-->
<div class="page-nation container">
    <a href="/admin/home/management"><i class="nav-icon fa fa-home mr5"></i>홈화면</a><span class="route_icon"></span>
    <a href="javascript:void(0)">유망 일자리</a><span class="route_icon"></span>
    <a href="javascript:void(0)">유망 일자리 등록</a>
</div>
      
<!--본문시작-->
<div class="sub-container container">
	<div class="sub-content">
<!--페이지 타이틀-->				
		<div class="page-header">
			<h3 class="page-title text-primary-d2 text-120">유망 일자리 등록</h3>
		</div>
		
<!--리스트 검색 상단 합계-->				
		<div class="sub-content-box bgc-white">
            <table class="table table-striped-primary table-borderless text-dark-m1 mb-0 suggest" id="responsive-table">
                <caption>유망일자리</caption>
                <colgroup>
                    <col style="width: 10%" />
                    <col style="width: 15%" />
                    <col style="width: 20%" />
                    <col style="width: 15%" />
                    <col style="width: 30%" />
                    <col style="width: 15%" />
                </colgroup>	
                <thead>			
                <tr class="bgc-primary-d2 text-white text-sm-center">						
                    <th>번호</th>
                    <th>생태계 분류</th>
                    <th>생태계 세부분류</th>
                    <th>직종 분류</th>
                    <th>일자리명</th>
                    <th>&nbsp;</th>
                </tr> 
                </thead>
                <tbody class="text-sm-center" id="list_body"></tbody>						
            </table>
	    </div>			
    </div>
</div>   
      
      
<!--검색 팝업-->
    <div class="job10_popup_pannel1">
        <div class="job10_popup_bg1"></div>
        <div class="job10_popup_contents1">
            <div class="job10_popuphead_box1">
                <h2 class="fl">유망일자리 등록 </h2>
                <a href="javascript:void(0)" id="job10_btn_popup_close1" class="fr"  ><i class="fas fa-times"></i></a>
            </div><!--specialism_popuphead_box-->
            <div class="job10_popuptxt_box1 ta_c">
				<div class="job10jop_popuptxt_box1">
					<input type="text" class="form-control w84 fl mr5" id="job_search_text" placeholder="검색어을 입력하세요">
					<input type="button" class="gray_btn btn job10_search_btn1" onclick='jobSearch();' value="검색">
				</div>
                <div class="job10_popuptxt_box_box1" id="job_search_list"></div>  
            </div>        
            <div class="job10_popup_btns1">
                <button class="blue_btn job10_popup_close1" id="job10_popup_close1" onclick="confirm();">확인</button> 
            </div><!--job10_popup_btns-->
        </div><!--job10_popup_contents-->  
    </div>
<!--삭제 알림 팝업-->
    <div class="job10delnotice_popup_panel">
		<div class="job10delnotice_popup_bg"></div>
		<div class="job10delnotice_popup_contents" style="margin-left: -355px; margin-top: 0px;">
			<div class="job10delnotice_popuphead_box">
				<h2 class="fl">알림</h2>
				<a href="javascript:void(0)" id="job10delnotice_btn_popup_close" class="fr"><i class="fas fa-times"></i></a>
			</div><!--banner_popuphead_box-->
			<div class="job10delnotice_popuptxt_box">
				<p><span class="text_b" id="delete_name"></span> 을(를)<br /> 유망일자리 목록에서 삭제합니다.</p>
			</div><!--banner_popuptxt_box-->
			<div class="job10delnotice_popup_btns">
				<button class="gray_btn job10delnotice_btn_popup_close">취소</button>
				<button class="blue_btn job10delnotice_btn_popup_close jobtenresult_del" name="deljobtencellok" onclick="withdrawal()">확인</button>
				 
			</div>
		</div>   
	</div>
   <!--//end 팝업-->      