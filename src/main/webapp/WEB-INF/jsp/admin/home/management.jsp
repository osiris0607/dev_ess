<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

  
<script type='text/javascript'>

	var jobList = new Array();
	var futureRoad1 = "";
	var futureRoad2 = "";
	var futureRoad3 = "";
	var futureRoadChoiceNo = "";
	
	$(document).ready(function() {
	});

	//이미지 클릭시 보더값보이게
    function Change(){
      var key = user_clarification_type.value;
      if(key==1){
      document.all["b_1"].style.display="block";
      document.all["b_2"].style.display="none";
      document.all["b_3"].style.display="none";  
      /*document.documentElement.scrollTo({top: 0,
            left: 0,
            behavior: 'smooth'
        });*/
        document.documentElement.scrollTop= 0;  
      }
      
      if(key==2){
      document.all["b_1"].style.display="none";
      document.all["b_2"].style.display="block";
      document.all["b_3"].style.display="none"; 
        /*document.documentElement.scrollTo({top: 1000,
            left: 0,
            behavior: 'smooth'
        });*/
       document.documentElement.scrollTop= 1000;   
      }
      if(key==3){
      document.all["b_1"].style.display="none";
      document.all["b_2"].style.display="none";
      document.all["b_3"].style.display="block";
      /*document.documentElement.scrollTo({top: 1400,
            left: 0,
            behavior: 'smooth'
        });*/
      document.documentElement.scrollTop= 1400;    
      }
   } 


</script>
  

<!--페이지 루트-->
<div class="page-nation container">
    <a href="javascript:void(0)"><i class="nav-icon fa fa-home mr5"></i>홈화면</a><span class="route_icon"></span>
    <a href="javascript:void(0)">홈화면 관리</a>
</div>        
 
 
<!--본문시작-->      
<div class="home_wrap clearfix container">
    <div class="left_box fl">
        <img src="/assets/img/home_img.png" alt="사용자페이지 메인이미지">
        <div class="b_1" id="b_1">배너영역</div>
        <div class="b_2" id="b_2">유망일자리 소개</div>
        <div class="b_3" id="b_3">기업 소개</div>
    </div>
    <div class="right_box fl">
        <div class="search_contens_box">
            <ul>                      
                <li>
                <label for="user_clarification_type"><i class="fas fa-caret-right"></i>영역설명 선택</label><select name="user_clarification_type" id="user_clarification_type" class="user_clarification_type ace-select" onchange="Change()">
                    <option value="1" selected="selected">배너영역</option>
                    <!--<option value="2">유망 일자리 소개</option>
                    <option value="3">기업 소개</option> --> 
                 </select>                       
            </li>
                <li>
                <label for="user_clarification_whether_type"><i class="fas fa-caret-right"></i>사용 여부</label><select name="user_clarification_whether_type" id="user_clarification_whether_type" class="user_clarification_whether_type ace-select w60">
                    <option value="전체" selected="selected">전체</option>
                    <option value="사용">사용</option>
                    <option value="사용안함">사용안함</option>  
                </select>                       
            </li>                     
            </ul>
            <div class="btns_box clearfix">                   
                <button class="gray_btn">초기화</button>
                <button class="fl blue_btn2 mr5">조회</button>
            </div>                   
        </div><!--//search_contens_box-->
        <div class="search_contens_result_box">
           <!--배너영역-->
           <button class="fr blue_btn banner_btn_popup_open mt20_m10">배너 등록</button>
            <table>
                <caption>일자리 내비게이터</caption>
                <colgroup>
                    <col style="width: 10%" />
                    <col style="width: 50%" />
                    <col style="width: 20%" />
                    <col style="width: 20%" />
                </colgroup>
                <thead>
                    <tr>
                        <th scope="col">순번</th>
                        <th scope="col">배너명</th>
                        <th scope="col">이미지</th>
                        <th scope="col">사용여부</th>
                   </tr>
                </thead>
                <tbody>
                    <tr>
                         <td>1</td>
                         <td><a  href="javascript:void(0)" class="banner_btn_popup_open">일자리 소개 배너</a>
                         </td>
                         <td><a  href="javascript:void(0)" class="banner_btn_popup_open">123.jpg</a></td>
                         <td>사용</td>
                    </tr>
                    <tr>
                         <td>2</td>
                         <td><a  href="javascript:void(0)" class="banner_btn_popup_open">일자리 소개 배너</a>
                         </td>
                         <td><a  href="javascript:void(0)" class="banner_btn_popup_open">123.jpg</a></td>
                         <td>사용</td>
                    </tr>
                    <tr>
                         <td >3</td>
                         <td><a  href="javascript:void(0)" class="banner_btn_popup_open">일자리 소개 배너</a>
                         </td>
                         <td><a  href="javascript:void(0)" class="banner_btn_popup_open">123.jpg</a></td>
                         <td>사용</td>
                    </tr>
                    <tr>
                         <td>4</td>
                         <td><a  href="javascript:void(0)" class="banner_btn_popup_open">일자리 소개 배너</a>
                         </td>
                         <td><a  href="javascript:void(0)" class="banner_btn_popup_open">123.jpg</a></td>
                         <td>사용</td>
                    </tr>                           
                 </tbody>
            </table>
        </div>
    </div><!--//right_box-->
</div>


<!--팝업-->
<!--배너관리 팝업-->
<div class="banner_popup_panel">
	<div class="banner_popup_bg"></div>
	<div class="banner_popup_contents">
		<div class="banner_popuphead_box">
			<h2 class="fl">배너 관리</h2>
			<a  href="javascript:void(0)" id="banner_btn_popup_close" class="fr"><i class="fas fa-times"></i></a>
		</div><!--banner_popuphead_box-->
		<div class="banner_popuptxt_box">
			<table class="popup_table">
				<colgroup>
				   <col style="width: 30%" />
				   <col style="width: 70%" />
				</colgroup>
				<tr>
					<th>배너명</th>
					<td>추천 일자리 배너</td>
				</tr>
				<tr>
					<th>이미지</th>
					<td>
						<div class="banner_popupimg_box">
							<div class="banner_popupimg_contain">
								<div class="banner_popupimg"><a  href="" class="banner_popup_file_img">    <img src="/assets/img/mainimg.jpg" alt="배너 팝업 이미지" id="target_img" />       </a>
								<label ><span class="popup_file_name">123.jpg<!--a href="javascript:void(0)" class="banner_img_del"><i class="fas fa-times color-b"></i></a--></span> </label>
									<form name="signform" method="POST" ENCTYPE="multipart/form-data">
								
										<input type="file" id="file" name="file" style="display:none;" onchange="changeValue(this)" />
										<input type="hidden" name = "target_url">
									</form>
								</div>
								<div class="popup_file_name">
									
								</div>
							</div>                        
						</div>
						<label class="dn">파일 업로드</label>
						<input type="file" name="uploadFile" id="uploadFile" class="file_gray_btn " multiple />					
						<div id="preview"></div>                 
					</td>
				</tr>
				<tr>
					<th>URL</th>
					<td><label class="dn">job_name</label><input type="text" class="form-control brc-on-focus d-inline-block w100" id="job_name" /></td>
				</tr>
				<tr>
					<th>사용 여부</th>
					<td>
					  <div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="inlineRadioOptions" checked />
						<label class="form-check-label">사용</label>
					  </div>
					  <div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="inlineRadioOptions" value="" />
						<label class="form-check-label">사용 안함</label>
					 </div>						  
				   </td>
				</tr>
				<tr>
					<th>등록자</th>
					<td>홍길동</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td>2020-12-31</td>
				</tr>                        
			</table>
		</div><!--banner_popuptxt_box-->
		<div class="banner_popup_btns">
			<button class="blue_btn">수정</button>
			<button class="blue_btn">삭제</button>
			<button class="gray_btn banner_btn_popup_close2">취소</button> 
		</div><!--banner_popup_btns-->
	</div><!--banner_popup_contents-->       
</div><!--banner_popup_panel-->
<!--//배너관리 팝업-->
