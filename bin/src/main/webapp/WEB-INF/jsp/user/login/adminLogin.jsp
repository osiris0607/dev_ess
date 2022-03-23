<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!doctype html>
<html lang="ko">
    <head>
        <meta charset="utf-8">
	<meta name="viewport" content="width=1200">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <!--<meta name="viewport" content="width=device-width, height=device-height, initial-scale=1">-->
    <base href="../" />
    <title>일자리 내비게이터 관리자페이지</title>
    <!-- include common vendor stylesheets & fontawesome -->    
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet" /> 
    <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap.css" />    
    <link rel="stylesheet" type="text/css" href="/assets/css/ace.css" />
    <!-- favicon -->
    <link rel="icon" type="image/png" href="/assets/favicon/favicon.png" />    
	<!--추가-->
	<link rel="stylesheet" type="text/css" href="/assets/css/font.css" />
	<link rel="stylesheet" type="text/css" href="/assets/css/style_anchorData.css" />
	<script src="/assets/js/jquery-1.12.3.min.js"></script>
  </head>

  <body>
      <div class="login-body-container">
        <div class="container">
            <div class="w80 clearfix" style="margin: auto;">
            <div class="left-box fl" style="width:50%">
                <img src="/assets/img/login-bg-2.jpg" alt="일자리내비게이터 로그인 이미지">
            </div><!--left-->
            <div class="right-box fl" style="width:50%">
                <div class="w80 locin_right_txt_c">
                    <div class="login-title">
                        <h4 class="text-dark-tp4 text-130"><i class="fa fa-unlock-alt text-orange-m1 mr-1"></i>
                       		관리자페이지 로그인
                        </h4>
                    </div>
                
                    <form autocomplete="off" class="form-row mt-4" action="/login" method="POST">
	                    <div class="form-group">
	                        <div class="d-flex align-items-center input-floating-label text-blue brc-blue-m2">
	                          <input type="text" class="form-control form-control-lg pr-4 shadow-none has-content" name="member_id" id="member_id" value="">
	                          <i class="fa fa-user text-grey-m2 ml-n4"></i>
	                          <label class="floating-label text-grey-l1 ml-n3" for="member_id">Username</label>
	                        </div>
	                    </div>

	                    <div class="form-group">
	                        <div class="d-flex align-items-center input-floating-label text-blue brc-blue-m2">
	                            <input type="password" class="form-control form-control-lg pr-4 shadow-none has-content" name="pwd" id="pwd" value="" >
	                            <i class="fa fa-key text-grey-m2 ml-n4"></i>
	                            <label class="floating-label text-grey-l1 ml-n3" for="pwd">Password</label>
	                        </div>
	                  	</div>

	                  	<div class="form-group">
		                    <label class="d-inline-block mt-3 mb-0 text-dark-l1 clearfix">
		                      <input type="checkbox" class="mr5 fl" id="id-remember-me"><span class="id_save_txt fl">ID 저장</span>
		                    </label>
	
	                    	<button type="submit" class="btn btn-primary btn-block px-4 btn-bold mt-2 mb-4 blue_btn">LOGIN</button>
	                    
	                    	<span class="ta_c">Copyright©KETEP.<br> All Rights Reserved.</span>
	                  	</div>
                	</form>
                </div>
            </div><!--right-->
            </div>
        </div><!--main-container-->
      </div><!--login-body-container-->
  </body>

</html>
