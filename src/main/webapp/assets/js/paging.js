/* divId : 페이징 태그가 그려질 div pageIndx : 현재 페이지 위치가 저장될 input 태그 id recordCount : 페이지당 레코드 수 totalCount : 전체 조회 건수 eventName : 페이징 하단의 숫자 등의 버튼이 클릭되었을 때 호출될 함수 이름 */ 
/*
divId : 페이징 태그가 그려질 div
pageIndx : 현재 페이지 위치가 저장될 input 태그 id
recordCount : 페이지당 레코드 수
totalCount : 전체 조회 건수 
eventName : 페이징 하단의 숫자 등의 버튼이 클릭되었을 때 호출될 함수 이름
*/
var gfv_pageIndex = null;
var gfv_eventName = null;
function gfnRenderPaging(params){
	var divId = params.divId; // 페이징이 그려질 div id
	gfv_pageIndex = params.pageIndex; // 현재 위치가 저장될 input 태그
	var totalCount = params.totalCount; // 전체 조회 건수
	
	var currentIndex = $("#"+params.pageIndex).val(); // 현재 위치
	if($("#"+params.pageIndex).length == 0 || gfn_isNull(currentIndex) == true){
		currentIndex = 1;
	}
	
	var recordCount = params.recordCount; // 페이지당 레코드 수
	if(gfn_isNull(recordCount) == true){
		recordCount = 10;
	}
	var totalIndexCount = Math.ceil(totalCount / recordCount); // 전체 인덱스 수
	gfv_eventName = params.eventName;
	
	$("#"+divId).empty();
	
	var preStr  = "<nav class='bgc-white mt-3 d-flex radius-1 position-center' aria-label='Page navigation example'>";
	 	preStr += "		<ul class='pagination nav-tabs-scroll is-scrollable p-2 mb-0'>";
	var postStr = "";
	var str = "";
	
	var first = (parseInt((currentIndex-1) / 10) * 10) + 1;
	var last = (parseInt(totalIndexCount/10) == parseInt(currentIndex/10)) ? totalIndexCount%10 : 10;
	if ( (currentIndex%10) == 0 ) {
		last = 10;
	}
	var prev = (parseInt((currentIndex-1)/10)*10) - 9 > 0 ? (parseInt((currentIndex-1)/10)*10) - 9 : 1; 
	var next = (parseInt((currentIndex-1)/10)+1) * 10 + 1 < totalIndexCount ? (parseInt((currentIndex-1)/10)+1) * 10 + 1 : totalIndexCount;
	
	if (totalIndexCount > 10) { // 전체 인덱스가 10이 넘을 경우, 맨앞, 앞 태그 작성
		preStr += "<li class='page-item mr-2'>"
		preStr += "		<a class='h-5 pt-25 page-link rounded-sm border-0 text-95' href='javascript:void(0);' onclick='_movePage("+prev+", "+ gfv_eventName +")'>"
		preStr += "			<i class='fa fa-angle-left mr-1'></i>Previous";
		preStr += "		</a>"
		preStr += "</li>"
//		preStr += "<a href='javascript:void(0);' class='pprev' onclick='_movePage("+prev+", "+ gfv_eventName +")'></a>";
//		preStr += "<li class='pagination__listitem'><a href='javascript:void(0);' class='pagination__link'onclick='_movePage("+"1"+", "+ gfv_eventName +")'>◀◀</a></li>" +
//				  "<li><a href='javascript:void(0);' class='pagination__link' onclick='_movePage("+prev+", "+ gfv_eventName +")'>◀</a></li>";
	} else if (totalIndexCount <=10 && totalIndexCount > 1) { // 전체 인덱스가 10보다 작을경우, 맨앞 태그 작성
		preStr += "<li class='page-item mr-2'>"
		preStr += "		<a class='h-5 pt-25 page-link rounded-sm border-0 text-95' href='javascript:void(0);' onclick='_movePage("+prev+", "+ gfv_eventName +")'>"
		preStr += "			<i class='fa fa-angle-left mr-1'></i>Previous";
		preStr += "		</a>"
		preStr += "</li>"
		
//		preStr += "<a href='javascript:void(0);' class='pprev' onclick='_movePage("+prev+", "+ gfv_eventName +")'></a>";
//		preStr += "<li class='page-item'><a href='javascript:void(0);' class='pprev' onclick='_movePage("+prev+", "+ gfv_eventName +")'></a></li>";
//		preStr += "<li class='pagination__listitem'><a href='javascript:void(0);' class='pagination__link' onclick='_movePage("+prev+", "+ gfv_eventName +")'>◀</a></li>";
	}
	
	if (totalIndexCount > 10) { // 전체 인덱스가 10이 넘을 경우, 맨뒤, 뒤 태그 작성
		postStr += "<li class='page-item'>"
		postStr += "		<a class='h-5 pt-25 page-link rounded-sm border-0 text-95' href='javascript:void(0);' onclick='_movePage("+next+", "+ gfv_eventName +")'>"
		postStr += "			Next<i class='fa fa-angle-right mr-1'></i>";
		postStr += "		</a>"
		postStr += "</li>"
//		postStr += "<li class='pagination__listitem'><a href='javascript:void(0);' class='pagination__link' onclick='_movePage("+next+", "+ gfv_eventName +")'>▶</a></li>" +
//				   "<li class='pagination__listitem'><a href='javascript:void(0);' class='pagination__link' onclick='_movePage("+totalIndexCount+", "+ gfv_eventName +")'>▶▶</a></li>";
	} else if (totalIndexCount <=10 && totalIndexCount > 1){ // 전체 인덱스가 10보다
		// 작을경우, 맨뒤 태그 작성
		postStr += "<li class='page-item'>"
		postStr += "		<a class='h-5 pt-25 page-link rounded-sm border-0 text-95' href='javascript:void(0);' onclick='_movePage("+next+", "+ gfv_eventName +")'>"
		postStr += "			Next<i class='fa fa-angle-right mr-1'></i>";
		postStr += "		</a>"
		postStr += "</li>"
//		postStr += "<a href='javascript:void(0);' class='nnext' onclick='_movePage("+next+", "+ gfv_eventName +")'></a>";
//		postStr += "<li class='page-item'><a href='javascript:void(0);' class='page-link' onclick='_movePage("+next+", "+ gfv_eventName +")'>▶▶</a></li>";
//		postStr += "<li class='pagination__listitem'><a href='javascript:void(0);' onclick='_movePage("+totalIndexCount+", "+ gfv_eventName +")'>▶</a></li>";
	}
	
	for (var i = first; i < (first+last); i++) {
		if (i != currentIndex) {
			str += "<li class='page-item mr-2'>";
			str += "	<a class='w-5 h-5 pt-2 page-link text-center rounded-circle' href='javascript:void(0);' onclick='_movePage("+i+", "+ gfv_eventName +")'>"+i+"</a>";
			str += "</li>";
		} else {
			str += "<li class='page-item mr-2 active'>";
			str += "	<a class='w-5 h-5 pt-2 page-link text-center rounded-circle' href='javascript:void(0);' onclick='_movePage("+i+", "+ gfv_eventName +")'>"+i+"</a>";
			str += "</li>";
		}
	}
	
	
	postStr += "		</ul>";
    postStr += "</nav>";
	
	var tempStr = preStr + str + postStr;
	$("#"+divId).append(preStr + str + postStr);
	
}

function _movePage(value, eventName){
	$("#"+gfv_pageIndex).val(value);
	if(typeof(eventName) == "function"){
		eventName(value);
	}
	else {
		eval(eventName + "(value);");
	}
}






function gfnRenderPagingMain(params){
	var divId = params.divId; // 페이징이 그려질 div id
	gfv_pageIndex = params.pageIndex; // 현재 위치가 저장될 input 태그
	var totalCount = params.totalCount; // 전체 조회 건수
	
	var currentIndex = $("#"+params.pageIndex).val(); // 현재 위치
	if($("#"+params.pageIndex).length == 0 || gfn_isNull(currentIndex) == true){
		currentIndex = 1;
	}
	
	var recordCount = params.recordCount; // 페이지당 레코드 수
	if(gfn_isNull(recordCount) == true){
		recordCount = 9;
	}
	var totalIndexCount = Math.ceil(totalCount / recordCount); // 전체 인덱스 수
	gfv_eventName = params.eventName;
	
	$("#"+divId).empty();
	
	
/*    <ul>
  	<li><button class="prev [no]"><i class="fa fa-angle-left" aria-hidden="true"></i></button></li>
    <li><a href="" class="loca">1</a></li>
    <li><a href="">2</a></li>
    <li><a href="">3</a></li>
    <li><a href="">4</a></li>
    <li><a href="">5</a></li>
    <li><a href="">6</a></li>
    <li><a href="">7</a></li>
    <li><a href="">8</a></li>
    <li><a href="">9</a></li>
    <li><a href="">10</a></li>
    <li><button class="next [no]"><i class="fa fa-angle-right" aria-hidden="true"></i></button></li>
  </ul>*/
	
	var preStr = "<ul>";
	var postStr = "";
	var str = "";
	
	
	var first = (parseInt((currentIndex-1) / 10) * 10) + 1;
	var last = (parseInt(totalIndexCount/10) == parseInt(currentIndex/10)) ? totalIndexCount%10 : 10;
	if ( (currentIndex%10) == 0 ) {
		last = 10;
	}
	var prev = (parseInt((currentIndex-1)/10)*10) - 9 > 0 ? (parseInt((currentIndex-1)/10)*10) - 9 : 1; 
	var next = (parseInt((currentIndex-1)/10)+1) * 10 + 1 < totalIndexCount ? (parseInt((currentIndex-1)/10)+1) * 10 + 1 : totalIndexCount;
	
	
	
//	var first = (parseInt((currentIndex-1) / 9) * 9) + 1;
//	var last = (parseInt(totalIndexCount/9) == parseInt(currentIndex/9)) ? totalIndexCount%9 : 9;
//	var prev = (parseInt((currentIndex-1)/9)*9) - 8 > 0 ? (parseInt((currentIndex-1)/9)*9) - 8 : 1; 
//	var next = (parseInt((currentIndex-1)/9)+1) * 9 + 1 < totalIndexCount ? (parseInt((currentIndex-1)/9)+1) * 8 + 1 : totalIndexCount;
	
	
	if (totalIndexCount > 10) { // 전체 인덱스가 10이 넘을 경우, 맨앞, 앞 태그 작성
		preStr += "<li><button class='prev [no]' onclick='_movePage("+prev+", "+ gfv_eventName +")'><i class='fa fa-angle-left' aria-hidden='true'></i></button></li>";
//		preStr += "<li class='pagination__listitem'><a href='javascript:void(0);' class='pagination__link'onclick='_movePage("+"1"+", "+ gfv_eventName +")'>◀◀</a></li>" +
//				  "<li><a href='javascript:void(0);' class='pagination__link' onclick='_movePage("+prev+", "+ gfv_eventName +")'>◀</a></li>";
	} else if (totalIndexCount <=10 && totalIndexCount > 1) { // 전체 인덱스가 10보다
		// 작을경우, 맨앞 태그 작성
		preStr += "<li><button class='prev [no]' onclick='_movePage("+prev+", "+ gfv_eventName +")'><i class='fa fa-angle-left' aria-hidden='true'></i></button></li>";
//		preStr += "<li class='page-item'><a href='javascript:void(0);' class='pprev' onclick='_movePage("+prev+", "+ gfv_eventName +")'></a></li>";
//		preStr += "<li class='pagination__listitem'><a href='javascript:void(0);' class='pagination__link' onclick='_movePage("+prev+", "+ gfv_eventName +")'>◀</a></li>";
	}
	
	if (totalIndexCount > 10) { // 전체 인덱스가 10이 넘을 경우, 맨뒤, 뒤 태그 작성
		postStr += "<li><button class='next [no]' onclick='_movePage("+next+", "+ gfv_eventName +")'><i class='fa fa-angle-right' aria-hidden='true'></i></button></li>";
//		postStr += "<li class='pagination__listitem'><a href='javascript:void(0);' class='pagination__link' onclick='_movePage("+next+", "+ gfv_eventName +")'>▶</a></li>" +
//				   "<li class='pagination__listitem'><a href='javascript:void(0);' class='pagination__link' onclick='_movePage("+totalIndexCount+", "+ gfv_eventName +")'>▶▶</a></li>";
	} else if (totalIndexCount <=10 && totalIndexCount > 1){ // 전체 인덱스가 10보다
		// 작을경우, 맨뒤 태그 작성
		postStr += "<li><button class='next [no]' onclick='_movePage("+next+", "+ gfv_eventName +")'><i class='fa fa-angle-right' aria-hidden='true'></i></button></li>";
//		postStr += "<li class='page-item'><a href='javascript:void(0);' class='page-link' onclick='_movePage("+next+", "+ gfv_eventName +")'>▶▶</a></li>";
//		postStr += "<li class='pagination__listitem'><a href='javascript:void(0);' onclick='_movePage("+totalIndexCount+", "+ gfv_eventName +")'>▶</a></li>";
	}
	
	for (var i = first; i < (first+last); i++) {
		if (i != currentIndex) {
			str += "<li><a href='javascript:void(0);' onclick=\"_movePageMain("+i+", "+ gfv_eventName +")\">"+i+"</a></li>";
		} else {
			str += "<li><a href='javascript:void(0);' class='loca' onclick=\"_movePageMain("+i+", "+ gfv_eventName +")\">"+i+"</a></li>";
		}
	}
	
	postStr += "</ul>";
	var tempStr = preStr + str + postStr;
	$("#"+divId).append(preStr + str + postStr);
	
}

function _movePageMain(value, eventName){
	$("#"+gfv_pageIndex).val(value);
	if(typeof(eventName) == "function"){
		eventName(value);
	}
	else {
		eval(eventName + "(value);");
	}
}
