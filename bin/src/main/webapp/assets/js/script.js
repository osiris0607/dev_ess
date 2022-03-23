/*-----------------------------------------------------------홈화면--------------------------------------------------------------*/

//---------------------------login-icon--------------------------
 $('.login-icon').hover(function() {
     $(".logout_txt").css('display','blcok');
 })
//------------------------------------------------------------------
//--------------------------홈화면 배너 관리 팝업------------------------------------------------------------------------------------
    $(document).ready(function() { 
        var $panel = $(".banner_popup_panel");
        var $panelContents = $panel.find(".banner_popup_contents");
        $("#banner_btn_popup_open, .banner_btn_popup_open").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($panelContents.outerWidth() < $(document).width()) {
                $panelContents.css("margin-left", "-" + $panelContents.outerWidth() / 2 + "px");
            } else {
                $panelContents.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($panelContents.outerHeight() < $(document).height()) {
                $panelContents.css("margin-top", "-" + $panelContents.outerHeight() / 2 + "px");
            } else {
                $panelContents.css("top", "0px");
            }
            // 레이어 팝업 열기
            $panel.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#banner_btn_popup_close, .banner_btn_popup_close2").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $panel.find(".banner_popup_bg").on("click", popupClose);
        function popupClose(e) {
            $panel.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });

//------------------------------------------------------------------------------------------------------------------------------


//--------------------------배너 등록 팝업 - 첨부파일----------------------------------------------------------------------------------
    $(document).ready(function (e){
        $("input[type='file']").change(function(e){

      //div 내용 비워주기
      $('#preview').empty();

      var files = e.target.files;
      var arr =Array.prototype.slice.call(files);

      //업로드 가능 파일인지 체크
      for(var i=0;i<files.length;i++){
        if(!checkExtension(files[i].name,files[i].size)){
          return false;
        }
      }
      preview(arr);
    });//file change

    function checkExtension(fileName,fileSize){

      var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
      var maxSize = 20971520;  //20MB

      if(fileSize >= maxSize){
        alert('파일 사이즈 초과');
        $("input[type='file']").val("");  //파일 초기화
        return false;
      }

      if(regex.test(fileName)){
        alert('업로드 불가능한 파일이 있습니다.');
        $("input[type='file']").val("");  //파일 초기화
        return false;
      }
      return true;
    }

    function preview(arr){
      arr.forEach(function(f){
        //파일명이 길면 파일명...으로 처리
        var fileName = f.name;
        if(fileName.length > 10){
          fileName = fileName.substring(0,7)+"...";
        }
        //div에 이미지 추가
        var str = '<div style="display: inline-flex; padding: 10px;"><li>';
        str += '<span>'+fileName+'</span><br>';

        //이미지 파일 미리보기
        if(f.type.match('image.*')){
          var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
          reader.onload = function (e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
            //str += '<button type="button" class="delBtn" value="'+f.name+'" style="background: red">x</button><br>';
            str += '<img src="'+e.target.result+'" title="'+f.name+'" width=100 height=100 />';
            str += '</li></div>';
            $(str).appendTo('#preview');
          } 
          reader.readAsDataURL(f);
        }else{
          str += '<img src="/resources/img/fileImg.png" title="'+f.name+'" width=60 height=60 />';
          $(str).appendTo('#preview');
        }
      });//arr.forEach
    }
    });
    
    // 배너등록 팝업 첨부파일
        $('#target_img').click(function (e) {
            document.signform.target_url.value = document.getElementById( 'target_img' ).src;
            e.preventDefault();
            $('#file').click();
        }); 
//------------------------------------------------------------------------------------------------------------------------------

//-----------------------------------------------배너 등록 갯수 초과 팝업--------------------------------------------------------------
    $(document).ready(function() { 
        var $panel = $(".banner_warning_popup_panel");
        var $panelContents = $panel.find(".banner_warning_popup_contents");
        $("#banner_warning_intro_open, .banner_warning_intro_open").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($panelContents.outerWidth() < $(document).width()) {
                $panelContents.css("margin-left", "-" + $panelContents.outerWidth() / 2 + "px");
            } else {
                $panelContents.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($panelContents.outerHeight() < $(document).height()) {
                $panelContents.css("margin-top", "-" + $panelContents.outerHeight() / 2 + "px");
            } else {
                $panelContents.css("top", "0px");
            }
            // 레이어 팝업 열기
            $panel.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#banner_warning_btn_popup_close, .banner_warning_btn_popup_close").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $panel.find(".banner_warning_popup_bg").on("click", popupClose);
        function popupClose(e) {
            $panel.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });
    
 
	$('.banner_img_del').click(function(){
		$('.banner_popupimg_contain').hide();            
	});        
   
// -----------------------------------------------------------------------------------------------------------------------------

/*----------------------------일자리 검색 view---------------------*/
//--------------------------------------------------------디세이블 해제-------------------------------------------------------

    $('.job_retouch_btn').click( function() {
        if( $(this).val() == '수정' ) {
            $(this).val('수정완료');
            $('.job_view_disabled').removeAttr('disabled');
            $('.job_view_disabled-none').css('display', 'block');
        }
        else {
            $(this).val('수정');
            $('.job_view_disabled').prop('disabled', true);
            $('.job_view_disabled-none').css('display', 'none');
        }
    });

//-------------------------------------------------------------------
//--------------------------------------------------------select 값 인풋박스에 넘기기-------------------------------------------------------   
//생태계 분류
    function getSelectValuea(frm)
        {
         frm.job_view_form1_textValue.value = frm.mainCategory.options[frm.mainCategory.selectedIndex].text;
        }
//생태계 세부 분류
    function getSelectValueb(frm)
        {
         frm.job_view_form2_textValue.value = frm.subCategory.options[frm.subCategory.selectedIndex].text;
        }
//직종 분류
    function getSelectValuec(frm)
        {
         frm.job_view_form3_textValue.value = frm.subCategory2.options[frm.subCategory2.selectedIndex].text;
        }
//-----------------------------------------------------------------
/*----------------------------------------일자리 등록 페이지-------------------------------------------------------------------------*/
    //전공
    /*전공 팝업 열기*/
    $(document).ready(function() { 
        var $panel = $(".specialism_popup_pannel");
        var $panelContents = $panel.find(".specialism_popup_contents");
        $("#specialism_popup_btn, .specialism_popup_btn").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($panelContents.outerWidth() < $(document).width()) {
                $panelContents.css("margin-left", "-" + $panelContents.outerWidth() / 2 + "px");
            } else {
                $panelContents.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($panelContents.outerHeight() < $(document).height()) {
                $panelContents.css("margin-top", "-" + $panelContents.outerHeight() / 2 + "px");
            } else {
                $panelContents.css("top", "0px");
            }
            // 레이어 팝업 열기
            $panel.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#specialism_popup_close, .specialism_popup_close").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $panel.find(".specialism_popup_bg, #specialism_btn_popup_close").on("click", popupClose);
        function popupClose(e) {
            $panel.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });

//-------------------------------------------------------------------------------------------------------------------------------
   
    
/*---------------------------------------------------전공 체크박스 input에 결과값 넘기기---------------------------------------------------*/
    function specialism_getCheckboxValue()  {
      // 선택된 목록 가져오기
      var query = 'input[name="specialism"]:checked';
      var selectedEls = 
          document.querySelectorAll(query);
      // 선택된 목록에서 value 찾기
      var result = '';
        for(var i=0; i<selectedEls.length; i++){
        result  +=  selectedEls[i].getAttribute('text') + ', ' ;
      };        
     
      // 출력
      document.getElementById('specialism_result').value
        = result.substr(0,result.length -2);        
    }


    /* 전공 텍스트박스 초기화 */
    function specialismclearInputdel(){
        var specialism_del = document.getElementsByClassName('specialism_result');
            for(var i=0; i<specialism_del.length; i++){
                specialism_del[i].value = '';
                specialism_del[i].placeholder = '';
            }
       
        /* 체크박스 지우는 부분*/ 
        var query = 'input[name="specialism"]:checked';
        var selectedEls = document.querySelectorAll(query);
            for(var i=0; i<selectedEls.length; i++){
                selectedEls[i].checked = false; 
            }
     }

//--------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------자격증-------------------------------------------------------------------------
/*----------------------------------일자리 > 일자리 등록 > 직무자격 > 자격증 팝업 열기---------------------------------------------------*/
    $(document).ready(function() { 
        var $panel = $(".certificate_popup_pannel");
        var $panelContents = $panel.find(".certificate_popup_contents");
        $("#certificate_popup_btn, .certificate_popup_btn").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($panelContents.outerWidth() < $(document).width()) {
                $panelContents.css("margin-left", "-" + $panelContents.outerWidth() / 2 + "px");
            } else {
                $panelContents.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($panelContents.outerHeight() < $(document).height()) {
                $panelContents.css("margin-top", "-" + $panelContents.outerHeight() / 2 + "px");
            } else {
                $panelContents.css("top", "0px");
            }
            // 레이어 팝업 열기
            $panel.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#certificate_popup_close, .certificate_close").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $panel.find(".certificate_popup_bg, #certificate_btn_popup_close").on("click", popupClose);
        function popupClose(e) {
            $panel.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });
    
//--------------------------------------------------------------------------------------------------------------------------------- 



/*---------------------------------------------------자격증 체크박스 input 결과값 넘기기---------------------------------------------------*/
    function getCheckboxValue()  {
      // 선택된 목록 가져오기
      var query1 = 'input[name="certificate"]:checked';
      var selectedEls1 = 
          document.querySelectorAll(query1);
      // 선택된 목록에서 value 찾기
      var result = '';
      for(var i=0; i<selectedEls1.length; i++){
        result  +=  selectedEls1[i].getAttribute('text') + ', ' ;
      };
        
      // 출력
    /*document.getElementById('certificate_popuptxt_box_txt').value
    = result;    
        */
      document.getElementById('certificate_result').value
        = result.substr(0,result.length -2);
    }


    /* 자격증 텍스트박스 지우는 부분 - 초기화 */
    function certificateclearInputdelet(){
        /*var certificate_del = document.getElementsByClassName('certificate_result2');
                for(var i=0; i<certificate_del.length; i++){
                certificate_del[i].value = ''; 
                } 
          */      
        var certificate_del = document.getElementsByClassName('certificate_result');
                for(var i=0; i<certificate_del.length; i++){
                certificate_del[i].value = '';
                certificate_del[i].placeholder = '';    
                }
    /* 체크박스 지우는 부분 */
        var query1 = 'input[name="certificate"]:checked';
        var selectedEls10 = document.querySelectorAll(query1);
            for(var i=0; i<selectedEls10.length; i++){
                selectedEls10[i].checked = false; 
            }
    }



    /*셀렉트 박스 선택시 값에 따라 전체 값 체크박스 목록 보이게*/
    function selectForm(frm){ 
      	  var hiddenForms = document.getElementById("allForms"); 
     	  theForm = hiddenForms.getElementsByTagName("ul"); 
         for(i=0; i<theForm.length; i++){ 
         theForm[i].style.display = "none"; 
      } 
      if (theForm[frm-1]){ 
        theForm[frm-1].style.display = "block"; 
      } 
    } 
//-----------------------------------------------------------------------------------------------------------------------------------------

    /*textarea.autosize
        $("textarea.autosize").on('keydown keyup', function () {
          $(this).height(1).height( $(this).prop('scrollHeight')+12 );	
        });

*/
//---------------------------------------------------향후진로----------------------------------------------------------------------------------
//-------------------------향후진로1------------------------------
   $(document).ready(function() { 
        var $futurea = $(".future_road_popupbox");
        var $futureContentsa = $futurea.find(".future_road_popup_contents");
        $("#future_btn_popup_open, .future_btn_popup_open").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($futureContentsa.outerWidth() < $(document).width()) {
                $futureContentsa.css("margin-left", "-" + $futureContentsa.outerWidth() / 2 + "px");
            } else {
                $futureContentsa.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($futureContentsa.outerHeight() < $(document).height()) {
                $futureContentsa.css("margin-top", "-" + $futureContentsa.outerHeight() / 2 + "px");
            } else {
                $futureContentsa.css("top", "0px");
            }
            // 레이어 팝업 열기
            $futurea.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $(".future_road_btn_popup_close, .future_road_popup_close").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $futurea.find(".future_road_popup_bg").on("click", popupClose);
        function popupClose(e) {
            $futurea.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });
    

    //----------------향후진로2--------------------------------
   $(document).ready(function() { 
        var $futureb = $(".future_road_popupbox2");
        var $futureContentsb = $futureb.find(".future_road_popup_contents2");
        $("#future_btn_popup_open2, .future_btn_popup_open2").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($futureContentsb.outerWidth() < $(document).width()) {
                $futureContentsb.css("margin-left", "-" + $futureContentsb.outerWidth() / 2 + "px");
            } else {
                $futureContentsb.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($futureContentsb.outerHeight() < $(document).height()) {
                $futureContentsb.css("margin-top", "-" + $futureContentsb.outerHeight() / 2 + "px");
            } else {
                $futureContentsb.css("top", "0px");
            }
            // 레이어 팝업 열기
            $futureb.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#future_road_btn_popup_close2").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $futureb.find(".future_road_popup_bg2").on("click", popupClose);
        function popupClose(e) {
            $futureb.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });

    //---------향후진로3----------------------
   $(document).ready(function() { 
        var $futurec = $(".future_road_popupbox3");
        var $futureContentsc = $futurec.find(".future_road_popup_contents3");
        $("#future_btn_popup_open3, .future_btn_popup_open3").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($futureContentsc.outerWidth() < $(document).width()) {
                $futureContentsc.css("margin-left", "-" + $futureContentsc.outerWidth() / 2 + "px");
            } else {
                $futureContentsc.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($futureContentsc.outerHeight() < $(document).height()) {
                $futureContentsc.css("margin-top", "-" + $futureContentsc.outerHeight() / 2 + "px");
            } else {
                $futureContentsc.css("top", "0px");
            }
            // 레이어 팝업 열기
            $futurec.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#future_road_btn_popup_close3").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $futurec.find(".future_road_popup_bg3").on("click", popupClose);
        function popupClose(e) {
            $futurec.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });
//------------------------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------첨부파일----------------------------------------------------------------------------------
   //일자리 검색 view
    $(document).ready(function(){
        $(".custom-file-input-view").on("change", function() {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".job_view_custom-control-label-view").addClass("selected").html(fileName);
        });
    });

   //일자리 등록
    $(document).ready(function(){
        $(".job_write_custom-file-input").on("change", function() {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".job_write_custom-file-label").addClass("selected").html(fileName);
        });
    });

//------------------------------------------------------------------------------------------------------------------------------------------


/*-------------------------------------------------------일자리 코드 관리 페이지------------------------------------------------------------------*/
 //--------------------------------------------------------------------탭 ------------------------------------------------------------------  
    $(document).ready(function() {
        $('.code-manage_tab_btns a').on('click',function(){
    		temp=$('.code-manage_tab_btns a').index($(this));
    		$(this).addClass('active').siblings().removeClass('active');
    		$('ul.bx>li').eq(temp).show().siblings().hide();
    	});

    	$('.tab a').click(function(){
            n=$('.tab a').index($(this));
            $('.tab li').eq(n).addClass('active')   //내가 원하는 버튼누를때 누르는 해당 버튼색on(버튼색깔바뀜)//
                .siblings().removeClass('active');
            $('.content div').eq(n).show()
                .siblings().hide()
    	});    	
    });

//--------------------------------------------------------------------------------------------------------------------------------------------

/*---------------------------------------------------테이블 행추가------------------------------------------------------------------------------*/
    //생태계 관리 탭
        //라디오버튼 비활성화
        $("#adiodisabled").attr( "disabled" );
/*---------------------------------------------------생태계 관리 탭 추가 버튼---------------------------------------------------------------------*/
        $(document).on("click","input[name=addStaff]",function(){       
            var addStaffText =     '<tr name="trStaff">'+
                '    <td>'+'</td>'+
                '    <td>'+
                '        <input type="text" class="form-control" name="staff_name">'+
                '    </td>'+'<td>'+'</td>'+
                '</tr>';

            var trHtml = $( "tr[name=trStaff]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출
            trHtml.after(addStaffText); //마지막 trStaff명 뒤에 붙인다.

            var button_joinus = document.getElementById('add_cell_btn');
                button_joinus.disabled = true;

        });

        //생태계 관리 탭 삭제 버튼
        $(document).on("click","button[name=delStaff]",function(){
            var trHtml = $(this).parent().parent();
            trHtml.remove(); //tr 테그 삭제
        });
    
//--------------------------------------------------------------------------------------------------------------------------------------------    
   
    


    //생태계 세부 관리 탭
//---------------------------------------------------생태계 세부 관리 탭 추가 버튼---------------------------------------------------------------------
        $(document).on("click","input[name=addStaff2]",function(){

            var addStaffText =     '<tr name="trStaff2">'+
                '    <td>' + '코드' + '</td>'+
                '    <td>' + '생태계명' + '</td>'+
                '    <td>' + '코드' + '</td>'+
                '    <td class="">'+
                '        <input type="text" class="form-control" name="staff_name2">'+
                '    </td>'+'<td>'+'</td>'+
                '</tr>';

            var trHtml = $( "tr[name=trStaff2]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출        
            trHtml.after(addStaffText); //마지막 trStaff명 뒤에 붙인다.
/*            var button_joinus2 = document.getElementById('add_cell_btn2');
                button_joinus2.disabled = true;*/
        });

        //생태계 세부 관리 탭 삭제 버튼
        $(document).on("click","button[name=delStaff2]",function(){

            var trHtml = $(this).parent().parent();        
            trHtml.remove(); //tr 테그 삭제

        });
//-------------------------------------------------------------------------------------------------------------------------------------------------------

    //직종 관리 탭
//---------------------------------------------------직종 관리 탭 버튼 추가------------------------------------------------------------------------------------
        $(document).on("click","input[name=addStaff3]",function(){

            var addStaffText =     '<tr name="trStaff3">'+
                '    <td>'+'코드'+'</td>'+
                '    <td>'+'생태계명'+'</td>'+
                '    <td>'+'코드'+'</td>'+
                '    <td>'+'생태계 세부명'+'</td>'+
                '    <td>'+'코드'+'</td>'+
                '    <td class="">'+
                '        <input type="text" class="form-control" name="staff_name3">'+
                '    </td>'+'<td>'+'</td>'+
                '</tr>';

            var trHtml = $( "tr[name=trStaff3]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출
            trHtml.after(addStaffText); //마지막 trStaff명 뒤에 붙인다.
/*            var button_joinus3 = document.getElementById('add_cell_btn3');
                button_joinus3.disabled = true;*/
        });

        //직종 관리 탭 삭제 버튼
        $(document).on("click","input[name=delStaff3]",function(){   
            var trHtml = $(this).parent().parent();        
            trHtml.remove(); //tr 테그 삭제        
        });
//------------------------------------------------------------------------------------------------------------------------------------------------------------
    //전공 관리 탭
//--------------------------------------------------------------------전공 관리 탭 버튼 추가----------------------------------------------------------------------- 
        var arrInput = new Array(0);
        var arrInputValue = new Array(0);

        function addInput() {
          arrInput.push(arrInput.length);
          arrInputValue.push("");
          display();
        }

        function display() {
          document.getElementById('parah').innerHTML="";
          for (intI=0;intI<arrInput.length;intI++) {
            document.getElementById('parah').innerHTML+=createInput(arrInput[intI], arrInputValue[intI]);
          }
        }

        function saveValue(intId,strValue) {
          arrInputValue[intId]=strValue;
        }  

        function createInput(id,value) {
          return "<input type='text' class=form-control brc-on-focusd-inline-block w83 id='test " + id +"' onChange='javascript:saveValue("+ id +",this.value)' value='"+ 
          value +"'>";
        }

        function deleteInput() {
          if (arrInput.length > 0) { 
             arrInput.pop(); 
             arrInputValue.pop();
          }
          display(); 
        }
//----------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------전공관리 - 추가 disabled------------------------------------
        $(document).on("click","input[name=addStaff4]",function(){
            var button_joinus4 = document.getElementById('add_cell_btn4');
                button_joinus4.disabled = true;
        });
//-----------------------------------------------------------------------------------------------------------------------------
    //자격증 관리 탭
//--------------------------------------------------------------------자격증 관리 탭 추가 버튼---------------------------------------
        $(document).on("click","input[name=addStaff5]",function(){

            var addStaffText =     '<tr name="trStaff5">'+
                '    <td>'+'자격증 분류명'+'</td>'+
                '    <td>'+
                '        <input type="text" class="form-control" name="staff_name5">'+
                '    </td>'+'<td>'+'</td>'+
                '</tr>';

            var trHtml = $( "tr[name=trStaff5]:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출
            trHtml.after(addStaffText); //마지막 trStaff명 뒤에 붙인다.
            var button_joinus5 = document.getElementById('add_cell_btn5');
                button_joinus5.disabled = true;
        });
//-----------------------------------------------------------------------------------------------------------------------------
//--------------------------------------------------------------------자격증 관리 탭 삭제 버튼----------------------------------------

        $(document).on("click","button[name=delStaff5]",function(){
            var trHtml = $(this).parent().parent();
            trHtml.remove(); //tr 테그 삭제
        });

//--------------------------------------------------------------------------------------------------------------------------------

/*----------------------------기업검색 view---------------------*/
//--------------------------------------------------------디세이블 해제-------------------------------------------------------

    $('.company_retouch_btn').click( function() {
        if( $(this).val() == '수정' ) {
            $(this).val('수정완료');
            $('.company_view_disabled').removeAttr('disabled');
            $('.company_view_disabled-none').css('display', 'block');
            $('.company_view_disabled-none-m').css('display', 'block');
        }
        else {
            $(this).val('수정');
            $('.company_view_disabled').prop('disabled', true);
            $('.company_view_disabled-none').css('display', 'none');
            $('.company_view_disabled-none-m').css('display', 'none');
        }
    });
//-------------------------------------------------------------------
//-----------------------기업 등록-------------------------------------------
    
    
  //--------------------------------------------------- 해당기업1 팝업---------------------------------------------------*/
  $(document).ready(function() { 
      var $jobcompanywritea = $(".jobcompany_popupbox1");
      var $jobcompanywriteContentsa = $jobcompanywritea.find(".jobcompany_job_popup_contents1");
      $(".job_company_future_btn_popup_open1").on("click", function(e) {
          // 팝업 가운데 설정(가로)
          if ($jobcompanywriteContentsa.outerWidth() < $(document).width()) {
              $jobcompanywriteContentsa.css("margin-left", "-" + $jobcompanywriteContentsa.outerWidth() / 2 + "px");
          } else {
              $jobcompanywriteContentsa.css("left", "0px");
          }
          // 팝업 가운데 설정(세로)
          if ($jobcompanywriteContentsa.outerHeight() < $(document).height()) {
              $jobcompanywriteContentsa.css("margin-top", "-" + $jobcompanywriteContentsa.outerHeight() / 2 + "px");
          } else {
              $jobcompanywriteContentsa.css("top", "0px");
          }
          // 레이어 팝업 열기
          $jobcompanywritea.fadeIn();
      });

      // 팝업 닫기 이벤트 정의
      $("#jobcompany_btn_popup_close1, .jobcompany_popup_close1").on("click", popupClose);
      // 팝업 배경 클릭 이벤트 정의
      $jobcompanywritea.find(".company_job_popup_bg3").on("click", popupClose);
      function popupClose(e) {
          $jobcompanywritea.fadeOut();
          // 이벤트 기본 동작 중단
          e.preventDefault();
      }
  });
    
    
    
//-------------------------연관 일자리1------------------------------
   $(document).ready(function() { 
        var $companywritea = $(".company_job_popupbox");
        var $companywriteContentsa = $companywritea.find(".company_job_popup_contents");
        $(".company_job_btn_popup_open").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($companywriteContentsa.outerWidth() < $(document).width()) {
                $companywriteContentsa.css("margin-left", "-" + $companywriteContentsa.outerWidth() / 2 + "px");
            } else {
                $companywriteContentsa.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($companywriteContentsa.outerHeight() < $(document).height()) {
                $companywriteContentsa.css("margin-top", "-" + $companywriteContentsa.outerHeight() / 2 + "px");
            } else {
                $companywriteContentsa.css("top", "0px");
            }
            // 레이어 팝업 열기
            $companywritea.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#company_job_btn_popup_close, .company_job_popup_close").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $companywritea.find(".company_job_popup_bg").on("click", popupClose);
        function popupClose(e) {
            $companywritea.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });
    
    
  //----------------연관 일자리2--------------------------------
    $(document).ready(function() { 
        var $companywriteb = $(".company_job_popupbox2");
        var $companywriteContentsb = $companywriteb.find(".company_job_popup_contents2");
        $(".company_job_btn_popup_open2").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($companywriteContentsb.outerWidth() < $(document).width()) {
                $companywriteContentsb.css("margin-left", "-" + $companywriteContentsb.outerWidth() / 2 + "px");
            } else {
                $companywriteContentsb.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($companywriteContentsb.outerHeight() < $(document).height()) {
                $companywriteContentsb.css("margin-top", "-" + $companywriteContentsb.outerHeight() / 2 + "px");
            } else {
                $companywriteContentsb.css("top", "0px");
            }
            // 레이어 팝업 열기
            $companywriteb.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#company_job_btn_popup_close2, .company_job_popup_close2").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $companywriteb.find(".company_job_popup_bg2").on("click", popupClose);
        function popupClose(e) {
            $companywriteb.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });

    //---------연관 일자리3----------------------
    $(document).ready(function() { 
        var $companywritec = $(".company_job_popupbox3");
        var $companywriteContentsc = $companywritec.find(".company_job_popup_contents3");
        $(".company_job_btn_popup_open3").on("click", function(e) {
            // 팝업 가운데 설정(가로)
            if ($companywriteContentsc.outerWidth() < $(document).width()) {
                $companywriteContentsc.css("margin-left", "-" + $companywriteContentsc.outerWidth() / 2 + "px");
            } else {
                $companywriteContentsc.css("left", "0px");
            }
            // 팝업 가운데 설정(세로)
            if ($companywriteContentsc.outerHeight() < $(document).height()) {
                $companywriteContentsc.css("margin-top", "-" + $companywriteContentsc.outerHeight() / 2 + "px");
            } else {
                $companywriteContentsc.css("top", "0px");
            }
            // 레이어 팝업 열기
            $companywritec.fadeIn();
        });

        // 팝업 닫기 이벤트 정의
        $("#company_job_btn_popup_close3, .company_job_popup_close3").on("click", popupClose);
        // 팝업 배경 클릭 이벤트 정의
        $companywritec.find(".company_job_popup_bg3").on("click", popupClose);
        function popupClose(e) {
            $companywritec.fadeOut();
            // 이벤트 기본 동작 중단
            e.preventDefault();
        }
    });
//------------------------------------------------------------------------------------------------------------------------------------------
  
//--------------------------------------기업검색 등록 및 뷰 첨부파일---------------------------------

    $(document).ready(function(){
        $(".custom-file-input-write-company").on("change", function() {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".custom-control-label-write-company").addClass("selected").html(fileName);
        });
    });
//-----------------------------------------------------------------------------------------------------------------------------------

 /*input*/
/*---------------------------------------input_maslength------------------------------------------------------------------------*/
    function maxLengthCheck(object){
        if (object.value.length > object.maxLength){
          object.value = object.value.slice(0, object.maxLength);
        }    
     }
//-------------------------------------------------------------------------------------------------------------------------------

    /*input 3자리 콤마*/
//----------------------------------------------------------------------3자리 단위마다 콤마 생성----------------------------------------
    function addCommas(x) {
        return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    }
    $(".onlynumber:text").on("keyup", function() {
        $(this).val(addCommas($(this).val().replace(/[^0-9]/g,"")));
    });
//-------------------------------------------------------------------------------------------------------------------------------



    /*자격증 체크박스 바로 보여주기
    function ggg()  {
      // 선택된 목록 가져오기
      const query = 'input[name="certificate"]:checked';
      const selectedEls = 
          document.querySelectorAll(query);

      // 선택된 목록에서 value 찾기
      let result = '';
      selectedEls.forEach((els) => {
        result += els.value + ', ';
          
      });
            
      // 출력
      document.getElementById('certificate_result2').value = result.substr(0,result.length -2);
        
        
    }
*/


/*------------------------------------------------------------faq------------------------------------------------------------------*/
     //접었다펼쳤다
    /*$('.qna_wrap>li>a').click(function(e){
        e.preventDefault()
        $('.qna_wrap ul:visible').slideUp(200).parent().removeClass('active');
        $(this).next(':hidden').slideDown(200).parent().addClass('active');       
        })*/

//--------------------------------------------------------testare디세이블 해제-------------------------------------------------------

    $('.faq_ok_back_btn').click( function() {
        if( $(this).val() == '수정' ) {
            $(this).val('수정완료');
            $('.faq_data_disablednot').removeAttr('disabled');
        }
        else {
            $(this).val('수정');
            $('.faq_data_disablednot').prop('disabled', true);
        }
    });

//-----------------------------------------------------------------------------------------------------------------------------------
/*------------------------------------------------------알림/정보 태양관산업 동향-------------------------------------------------------------------------*/
    $('.industry-trend-view_btns .data_ok_back_btn').click( function() {
        if( $(this).val() == '수정' ) {
            $(this).val('수정완료');
            $('.textarea_disablednot').removeAttr('disabled');
            $('.industry-trend_upload_view_disabled-none').css('display', 'block');
        }
        else {
            $(this).val('수정');
            $('.textarea_disablednot').prop('disabled', true);
            $('.industry-trend_upload_view_disabled-none').css('display', 'none');
        }
    });
//-------------------------------------------------------------------

//--------------------------------------알림/정보 태양관산업 동향 뷰 첨부파일---------------------------------

    $(document).ready(function(){
        $(".industry-trend-view-file-input-view-company").on("change", function() {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".industry-trend-view-label").addClass("selected").html(fileName);
        });
    });
//-----------------------------------------------------------------------------------------------------------------------------------
/*------------------------------------------------------자격증-------------------------------------------------------------------------*/
    $('.education-data_retouch_btn').click( function() {
        if( $(this).val() == '수정' ) {
            $(this).val('수정완료');
            $('.education-data-disabled').removeAttr('disabled');
        }
        else {
            $(this).val('수정');
            $('.education-data-disabled').prop('disabled', true);
        }
    });
//-------------------------------------------------------------------
/*------------------------------------------------------건의하기-------------------------------------------------------------------------*/
/*--------------------------------------------------------행 추가 버튼--------------------------------------------------------*/
        $(document).on("click","input[name=addcell]",function(){       
            var addStaffText =     '<tr name="trStaff">'+
                '    <td>'+'번호'+'</td>'+
                '    <td>'+
                '        <input type="text" class="form-control" placeholder="담당자명" name="staff_name">'+
                '    </td>'+'<td>'+'<input type="text" class="form-control brc-on-focusd-inline-block mr5 fl w46" placeholder="example" /><span class="fl">@</span><input type="text" class="form-control brc-on-focusd-inline-block fl ml5 w46" placeholder="ketep.re.kr" />'+'</td>'+'<td>'+'</td>'+'<td>'+'</td>'+
                '</tr>';

            var trHtml = $( "tr:last" ); //last를 사용하여 trStaff라는 명을 가진 마지막 태그 호출
            trHtml.after(addStaffText); //마지막 trStaff명 뒤에 붙인다.

           /* var button_joinus = document.getElementById('add_cell_btn');
                button_joinus.disabled = true;*/

        });


        //생태계 관리 탭 삭제 버튼
        $(document).on("click","button[name=delsuggestcell]",function(){
            var trHtml = $(this).parent().parent();
            trHtml.remove(); //tr 테그 삭제
        });
//--------------------------------------------------------------------------------------------------------------------------------------
        
        
/*---------------------10대 일자리 동향----------------------*/
  //분류값 선택 팝업
   $(document).ready(function() { 
      var $jobtenpanel = $(".job10_popup_pannel1");
      var $jobtenContents = $jobtenpanel.find(".job10_popup_contents1");
      $(".open_popup1").on("click", function(e) {
          // 팝업 가운데 설정(가로)
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
      });

      // 팝업 닫기 이벤트 정의
      $(".job10_popup_close1, #job10_btn_popup_close1").on("click", popupClose);
      // 팝업 배경 클릭 이벤트 정의
      $jobtenpanel.find(".job10_popup_bg1").on("click", popupClose);
      function popupClose(e) {
          $jobtenpanel.fadeOut();
          // 이벤트 기본 동작 중단
              e.preventDefault();
          }
      });
  //-------------------------------------------------------------------
  //삭제 알림 팝업
    $(document).ready(function() { 
      var $jobTendelnoticepanel = $(".job10delnotice_popup_panel");
      var $jobTendelnoticepanelContents = $jobTendelnoticepanel.find(".job10delnotice_popup_contents");
      $("#job10delnotice_open, .job10delnotice_popup_open").on("click", function(e) {
          // 팝업 가운데 설정(가로)
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
      });

      // 팝업 닫기 이벤트 정의
      $("#job10delnotice_btn_popup_close, .job10delnotice_btn_popup_close").on("click", popupClose);
      // 팝업 배경 클릭 이벤트 정의
      $jobTendelnoticepanel.find(".job10delnotice_popup_bg").on("click", popupClose);
      function popupClose(e) {
          $jobTendelnoticepanel.fadeOut();
          // 이벤트 기본 동작 중단
              e.preventDefault();
          }
      });
  //-----------------------------------------------------------------
  /*해당 버튼 삭제*/
  $(document).on("click","button[name=deljobtencell]",function(){
      $(this).parent().parent().addClass('add_jobten_txt');
  });
  $(document).on("click",".jobtenresult_del",function(){
     $('.add_jobten_txt').remove();
  })
