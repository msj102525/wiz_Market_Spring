<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>신규 리포트 등록</title>
    <link href="<%=path%>/css/style.css" rel="stylesheet" />
    <link href="<%=path%>/css/areport-mvp_style.css" rel="stylesheet" />
    <!-- jQuery 포함 -->
    <script src="<%=path%>/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/common.js"></script>
    <script>
        $(document).ready(function() {
        	
        	$('#saveAndNextBtn').on('click', function(event) {
        		$(this).prop('disabled', true);
                console.log("saveAndNextBtn click");

                save();
            });
        	
        	$("#storeInfoViewBtn").on('click', function(event) {
        		$(this).prop('disabled', true);
        		var storeId = $("#storeId").val();
        		window.location.href = '/store/info?storeId='+storeId;
            });
        	
        	// 삭제버튼 ( 초기화 기능 아닌가? )
        	$("#resetBtn").on("click", function(event) {
                reset();
            });
        	
        	$("#goBackBtn").on("click", function(event){
        		$(this).prop('disabled', true);
        		
        		var reportId = $("#reportId").val();
                window.location.href = "/report/save/business/district/analysis?reportId=" + reportId;
        	});
        	
        });
        
        function ajaxJSONRequest(url, data, successCallback, errorCallback) {
            $.ajax({
                url: url,
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),  // JSON 형식으로 데이터를 변환
                success: function(response) {
                    if (typeof successCallback === 'function') {
                        successCallback(response);
                    }
                },
                error: function(xhr, status, error) {
                    if (typeof errorCallback === 'function') {
                        errorCallback(xhr, status, error);
                    }
                }
            });
        }
        
        function errorCallback(xhr, status, error) {
            // 실패 시 처리할 코드
            console.error(error);
        }
        
        function saveSuccessCallback(response){
        	// 문자열인 경우 JSON으로 파싱
            response = JSON.parse(response);
            
            if (response.msg == "0000") {
            	console.log(response.data);
               	if(response.data > 0){
               		// reportId를 쿼리 파라미터로 추가하여 페이지 이동
                    var reportId = $("#reportId").val();
                    window.location.href = "/report/save/information?reportId=" + reportId;
               	}
            	
            } else {
                // msg가 '0000'이 아닌 경우 처리
                console.error('Error: ' + response.msg);
            }
        }
        
        function save(){
        	console.log("save method");
        	var url = "/report/detail/save";
        	var jsonItem = valueCheckAndJsonItem();
        	
        	if(jsonItem){
        		ajaxJSONRequest(url, jsonItem, saveSuccessCallback, errorCallback);
        	}
        }
        
        function valueCheckAndJsonItem(){
        	
        	var jsonItem = {};
        	
        	// 값이 유효한지 체크하고, 특정 조건에 따라 함수 실행 중단
            function addIfValid(key, value) {
        		var result = true;
                if (value != null && value != "") {
                    jsonItem[key] = value;  // 값이 유효할 때만 객체에 추가
                }else{
                	result = false;
                }
                return result;
            }
        	
         	// 필수 필드 체크 및 객체 추가
            if (!addIfValid("reportId", $("#reportId").val())){}  													// 보고서 ID
            if (!addIfValid("reportDetailId", $("#reportDetailId").val())){}  										// 지역 세부 ID

            // 교통환경
            if (!addIfValid("subwayDistance", $("#subwayDistance").val())){}  										// 지하철 거리
            if (!addIfValid("busDistance", $("#busDistance").val())){}  											// 버스 정장 거리
            if (!addIfValid("roadDistance", $("#roadDistance").val())){}  											// 가장 가까운 도로 거리
            
            // GPTs
            jsonItem["gptsStrategy"] = $("#gptsStrategy").val();													// GPT's 전략수집
            jsonItem["gptsSlogan"] = $("#gptsSlogan").val();														// GPT's 슬로건
            
            console.log(jsonItem);
        	
        	return jsonItem;
        }
        
     	// 입력칸 리셋 메소드
        function reset(){
        	// 모든 input[type="text"] 요소를 선택하여 값을 빈 문자열로 설정
            $('input[type="text"]').val('');

            // 모든 textarea 요소를 선택하여 값을 빈 문자열로 설정
            $('textarea').val('');
        }
    </script>
</head>
<body>
	<input type="hidden" id="reportId" name="reportId" value="${ reportDetail.reportId }"/>
	<input type="hidden" id="storeId" name="storeId" value="${ reportDetail.storeId }"/>
	<input type="hidden" id="reportDetailId" name="reportDetailId" value="${ reportDetail.reportDetailId }">
    <div class="areport-mvp-none">
        <div class="container">
        <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
            <div class="divider">
            </div>
            <div class="title">
                <u>신규 리포트 생성</u> > <u>${ reportDetail.name }</u> > 세부등록
            </div>
            <div class="areport-mvp">
                <div class="new">
                    <div class="tit left">등록정보</div>
                    <span class="new-day" id="displayRegDate">${ reportDetail.regDate }</span>
                    <span class="new-box-none">${ reportDetail.siNm }</span>
                    <span class="new-box-none">${ reportDetail.sggNm }</span>
                    <span class="new-box-none"><c:if test="${not empty reportDetail.emdNm}">${reportDetail.emdNm}</c:if><c:if test="${empty reportDetail.emdNm}">${reportDetail.liNm}</c:if></span>
                    <span class="new-box-none">${ reportDetail.mainCategoryName }</span>
                    <span class="new-box-none">${ reportDetail.subCategoryName }</span>
                    <button class="but2" id="storeInfoViewBtn" style="border:0px;">정보보기</button>
                </div>
                <div class="container-26">
                    매장 정보등록
                </div>
                <div class="new">
                    <div class="tit2">교통환경</div>
                    <div>
                        <input class="nex-box-use" type="text" id="subwayDistance" name="subwayDistance" value="" placeholder="지하철 거리(m)<c:if test="${not empty reportDetail.subwayDistance}"> : ${reportDetail.subwayDistance}</c:if>" />
                        <input class="nex-box-use" type="text" id="busDistance" name="busDistance" value="" placeholder="버스 정류장 거리(m)<c:if test="${not empty reportDetail.busDistance}"> : ${reportDetail.busDistance}</c:if>" />
                        <input class="nex-box-use" type="text" id="roadDistance" name="roadDistance" value="" placeholder="가장 가까운 도로 거리(m)<c:if test="${not empty reportDetail.roadDistance}"> : ${reportDetail.roadDistance}</c:if>" />
                    </div>
                </div>
                <div class="new">
                    <div class="tit2">GPTs</div>
                    <div>
                        <textarea class="nex-box-use2" id="gptsStrategy" name="gptsStrategy" value="" placeholder="지역과 업종에 대한 전략 수립>">${reportDetail.gptsStrategy}</textarea>
                        <textarea class="nex-box-use2" id="gptsSlogan" name="gptsSlogan" value="" placeholder="매장 슬로건">${reportDetail.gptsSlogan}</textarea>
                    </div>
                </div>
                <div class="new">
                    <div class="new-but-box">
                        <button class="but1" id="resetBtn" style="border:0px;">삭제</button>
                        <button class="but1" id="goBackBtn" style="border:0px;">
                            <svg xmlns="http://www.w3.org/2000/svg" width="18" height="15" viewBox="0 0 18 15" fill="none">
                                <path d="M1.38221 5.93265L0.691105 6.60566L0 5.93265L0.691105 5.25964L1.38221 5.93265ZM18 13.5481C18 13.8005 17.897 14.0427 17.7137 14.2212C17.5304 14.3997 17.2817 14.5 17.0225 14.5C16.7632 14.5 16.5146 14.3997 16.3313 14.2212C16.148 14.0427 16.045 13.8005 16.045 13.5481H18ZM5.57869 11.3653L0.691105 6.60566L2.07331 5.25964L6.9609 10.0193L5.57869 11.3653ZM0.691105 5.25964L5.57869 0.5L6.9609 1.84603L2.07331 6.60566L0.691105 5.25964ZM1.38221 4.98072H11.1574V6.88458H1.38221V4.98072ZM18 11.6442V13.5481H16.045V11.6442H18ZM11.1574 4.98072C12.9722 4.98072 14.7126 5.68277 15.9958 6.93242C17.2791 8.18206 18 9.87695 18 11.6442H16.045C16.045 10.3819 15.53 9.17125 14.6134 8.27864C13.6968 7.38604 12.4536 6.88458 11.1574 6.88458V4.98072Z" fill="white" />
                            </svg> 뒤로가기
                        </button>
                        <button class="but2" id="saveAndNextBtn" style="border:0px;">저장&다음입력</button>
                    </div>
                </div>
            </div>

        </div>
        <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
    </div>
</body>
</html>
