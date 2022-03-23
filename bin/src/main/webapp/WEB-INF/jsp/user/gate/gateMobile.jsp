<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0">
<meta name="title" content="에너지 산업 Job-Navi">
<meta name="keywords" content="에너지 산업 Job-Navi">
<meta name="description" content="에너지 산업 Job-Navi 사이트 입니다.">
<meta property="og:type" content="website">
<meta property="og:title" content="에너지 산업 Job-Navi">
<meta property="og:site_name" content="에너지 산업 Job-Navi">
<meta property="og:url" content="http://sunjunghee.com/project/agipung_m/html/index.html">
<meta property="og:image" content="/assets/mobile/images/job_navi_index_bg.png" />
<meta property="og:description" content="에너지 산업 Job-Navi 사이트 입니다.">
<title>KETEP</title>
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
<link rel="Stylesheet" href="/assets/mobile/css/font-awesome.css" media="all" type="text/css" />

<script>
  var ver = Math.floor((Math.random() * 1000));
  document.write('<link rel="stylesheet" href="/assets/mobile/css/style.css?V=' + ver + '" type="text/css" />');
</script>
<script src="/assets/mobile/js/jquery-3.3.1.min.js"></script>
<script src="/assets/mobile/js/swiper.js"></script>

</head>

<body>
  <div id="wrap" class="main index_wrap">
    <header>
    <div class="headerWrap">
      <a href="solar_home.html">
        <h1><span>KETEP</span></h1>
      </a>
	  <h2>에너지산업 Job-Navi</h2>
	 </div>
  </header>   

  <section>
    <div class="job_nabi_box">
		<div class="job_nabi_box_top_txt">
			<h3>에너지산업<br /><span>일자리 내비게이터</span></h3>
			<p>에너지 분야로의 취업· 경력개발을 위해<br />에너지산업의 종합적인 일자리 정보를 제공하는 플랫폼</p>
		</div>

		<ul class="clearfix">
			<li><a href="/user/mobile/main/mainMobile" class="energyclass_menu_sun" title="ESS"><span>ESS</span><span class="img"><img src="/assets/mobile/images/job_navi_index_icon1_off.png" alt="ESS" /></span></a></li>
			<li><a href="javascript:alert('개발중입니다. 현재는 ESS 산업 일자리 내비게이터만 이용이 가능합니다.');" class="energyclass_menu_wind" title="풍력"><span>풍력</span><span class="img"><img src="/assets/mobile/images/job_navi_index_icon2_off.png" alt="풍력"  /></span></a></li>
			<li><a href="javascript:alert('개발중입니다. 현재는 ESS 산업 일자리 내비게이터만 이용이 가능합니다.');" class="energyclass_menu_battery" title="수소&middot;연료전지"><span>수소&middot;연료전지</span><span class="img"><img src="/assets/mobile/images/job_navi_index_icon3_off.png" alt="수소" /></span></a></li>
			<li><a href="javascript:alert('개발중입니다. 현재는 ESS 산업 일자리 내비게이터만 이용이 가능합니다.');" class="energyclass_menu_ess" title="ESS"><span>ESS</span><span class="img"><img src="/assets/mobile/images/job_navi_index_icon4_off.png" alt="ESS"  /></span></a></li>
			<li><a href="javascript:alert('개발중입니다. 현재는 ESS 산업 일자리 내비게이터만 이용이 가능합니다.');" class="energyclass_menu_efficiency" title="에너지효율"><span>에너지효율</span><span class="img"><img src="/assets/mobile/images/job_navi_index_icon5_off.png" alt="에너지효율"  /></span></a></li>
			<li><a href="javascript:alert('개발중입니다. 현재는 ESS 산업 일자리 내비게이터만 이용이 가능합니다.');" class="energyclass_menu_power" title="지능형전력망"><span>지능형전력망</span><span class="img"><img src="/assets/mobile/images/job_navi_index_icon6_off.png" alt="지능형전력망" /></span></a></li>
		</ul>
	</div>  
  </section>
 

  <section>
    <div class="energy_navi_box">
		<img src="/assets/mobile/images/job_navi_index_bg.png" alt="내비게이터 이미지" />
		<div class="energy_job_navi_box clearfix">
			<h2 class="fl job_navi_bottom_txt" style="margin-left: 1%;width:20%;margin-right: 1%;">energy <span class="yellow_txt">job-navi</span></h2>
			<div class="fl" style="width:75%;margin-right: 3%;">
				<div class="s">
					<label for="energy_job_class" class="hidden">에너지 분야 선택</label>			
					<select name="energy_job_class" id="energy_job_class" >
						<option value="에너지 분야 선택" selected="">에너지 분야 선택</option>
						<option value="지능형 전력망">지능형 전력망</option>
						<option value="에너지 효율">에너지 효율</option>
						<option value="Ess">Ess</option>
						<option value="태양광">태양광</option>
						<option value="풍력">풍력</option>
						<option value="수소·연료전지">수소·연료전지</option>
					</select>
				</div>
				<div class="search_box">
					<label for="energy_job_search" class="hidden">에너지 분야 검색</label>
					<input type="text" id="energy_job_search" placeholder="키워드 검색">
					<button type="submit" title="검색" class="energy_job_search_btn">검색</button>
				</div>
			</div>
		</div>
	</div>

  </section>

  </div>

  
 

  <script>
	$(function(){
        $('.job_nabi_box li .energyclass_menu_sun').click(function(){
            $('.energyclass_menu_sun .img img').attr('src','/assets/mobile/images/job_navi_index_icon1_on.png');
			$('.energyclass_menu_efficiency .img img').attr('src','/assets/mobile/images/job_navi_index_icon5_off.png');
			$('.energyclass_menu_ess .img img').attr('src','/assets/mobile/images/job_navi_index_icon4_off.png');
			$('.energyclass_menu_power .img img').attr('src','/assets/mobile/images/job_navi_index_icon6_off.png');
			$('.energyclass_menu_wind .img img').attr('src','/assets/mobile/images/job_navi_index_icon2_off.png');
			$('.energyclass_menu_battery .img img').attr('src','/assets/mobile/images/job_navi_index_icon3_off.png');
        });
		$('.job_nabi_box li .energyclass_menu_wind').click(function(){
            $('.energyclass_menu_wind .img img').attr('src','/assets/mobile/images/job_navi_index_icon2_on.png');
			$('.energyclass_menu_efficiency .img img').attr('src','/assets/mobile/images/job_navi_index_icon5_off.png');
			$('.energyclass_menu_ess .img img').attr('src','/assets/mobile/images/job_navi_index_icon4_off.png');
			$('.energyclass_menu_power .img img').attr('src','/assets/mobile/images/job_navi_index_icon6_off.png');
			$('.energyclass_menu_sun .img img').attr('src','/assets/mobile/images/job_navi_index_icon1_off.png');
			$('.energyclass_menu_battery .img img').attr('src','/assets/mobile/images/job_navi_index_icon3_off.png');
        });
		$('.job_nabi_box li .energyclass_menu_battery').click(function(){
            $('.energyclass_menu_battery .img img').attr('src','/assets/mobile/images/job_navi_index_icon3_on.png');
			$('.energyclass_menu_efficiency .img img').attr('src','/assets/mobile/images/job_navi_index_icon5_off.png');
			$('.energyclass_menu_ess .img img').attr('src','/assets/mobile/images/job_navi_index_icon4_off.png');
			$('.energyclass_menu_power .img img').attr('src','/assets/mobile/images/job_navi_index_icon6_off.png');
			$('.energyclass_menu_sun .img img').attr('src','/assets/mobile/images/job_navi_index_icon1_off.png');
			$('.energyclass_menu_wind .img img').attr('src','/assets/mobile/images/job_navi_index_icon2_off.png');
        });
		$('.job_nabi_box li .energyclass_menu_ess').click(function(){
            $('.energyclass_menu_ess .img img').attr('src','/assets/mobile/images/job_navi_index_icon4_on.png');
			$('.energyclass_menu_efficiency .img img').attr('src','/assets/mobile/images/job_navi_index_icon5_off.png');
			$('.energyclass_menu_battery .img img').attr('src','/assets/mobile/images/job_navi_index_icon3_off.png');
			$('.energyclass_menu_power .img img').attr('src','/assets/mobile/images/job_navi_index_icon6_off.png');
			$('.energyclass_menu_sun .img img').attr('src','/assets/mobile/images/job_navi_index_icon1_off.png');
			$('.energyclass_menu_wind .img img').attr('src','/assets/mobile/images/job_navi_index_icon2_off.png');
        });
		$('.job_nabi_box li .energyclass_menu_efficiency').click(function(){
            $('.energyclass_menu_efficiency .img img').attr('src','/assets/mobile/images/job_navi_index_icon5_on.png');
			$('.energyclass_menu_ess .img img').attr('src','/assets/mobile/images/job_navi_index_icon4_off.png');
			$('.energyclass_menu_battery .img img').attr('src','/assets/mobile/images/job_navi_index_icon3_off.png');
			$('.energyclass_menu_power .img img').attr('src','/assets/mobile/images/job_navi_index_icon6_off.png');
			$('.energyclass_menu_sun .img img').attr('src','/assets/mobile/images/job_navi_index_icon1_off.png');
			$('.energyclass_menu_wind .img img').attr('src','/assets/mobile/images/job_navi_index_icon2_off.png');
        });
		$('.job_nabi_box li .energyclass_menu_power').click(function(){
            $('.energyclass_menu_power .img img').attr('src','/assets/mobile/images/job_navi_index_icon6_on.png');
			$('.energyclass_menu_ess .img img').attr('src','/assets/mobile/images/job_navi_index_icon4_off.png');
			$('.energyclass_menu_battery .img img').attr('src','/assets/mobile/images/job_navi_index_icon3_off.png');
			$('.energyclass_menu_efficiency .img img').attr('src','/assets/mobile/images/job_navi_index_icon5_off.png');
			$('.energyclass_menu_sun .img img').attr('src','/assets/mobile/images/job_navi_index_icon1_off.png');
			$('.energyclass_menu_wind .img img').attr('src','/assets/mobile/images/job_navi_index_icon2_off.png');
        });

    })
  </script>

  
</body>

</html>