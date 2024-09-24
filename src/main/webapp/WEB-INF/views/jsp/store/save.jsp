<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>매장정보등록</title>
    <link rel="stylesheet" href="<%=path%>/css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/areport-mvp_style.css" />
    <link rel="stylesheet" href="<%=path%>/css/jquery-ui.css" />
    <!-- jQuery 포함 -->
    <script type="text/javascript" src="<%=path%>/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-ui.min.js"></script>
    <script type="text/javascript">
 	
    
    $(document).ready(function() {
    	setDatepicker();
    	
    	// 화면진입 시, 현재 시간 체크하여 입력일버전 기입
    	updateDateTime();
    	
    	// 1초마다 현재 시간 체크하여 입력일 변경
    	setInterval(updateDateTime, 1000);
    	
    	// 저장 버튼
    	$("#saveBtn").on("click", function(event) {
            event.preventDefault(); // 폼 제출을 막음

            save();
        });
    	
    	// 삭제버튼 ( 초기화 기능 아닌가? )
    	$("#resetBtn").on("click", function(event) {
            event.preventDefault(); // 폼 제출을 막음

            reset();
        });
    	
    	
    });
    function setDatepicker() {
        $(".new-datepicker").datepicker();
        $('.new-datepicker').click(function () {
            if ($('.ui-widget').css('display') == 'none') {
                $('.ui-widget').show();
            } 
        });
        $.datepicker.regional["ko"] = {
            closeText: "닫기",
            prevText: "이전달",
            nextText: "다음달",
            currentText: "오늘",
            monthNames: ["1월(JAN)", "2월(FEB)", "3월(MAR)", "4월(APR)", "5월(MAY)", "6월(JUN)", "7월(JUL)", "8월(AUG)", "9월(SEP)", "10월(OCT)", "11월(NOV)", "12월(DEC)"],
            monthNamesShort: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
            dayNames: ["일", "월", "화", "수", "목", "금", "토"],
            dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
            dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],
            changeMonth: true, // month 셀렉트박스 사용
            changeYear: true, // year 셀렉트박스 사용
            weekHeader: "Wk",
            dateFormat: "yy-mm-dd",
            firstDay: 0,
            isRTL: false,
            showMonthAfterYear: true,
            yearSuffix: ""
        };
        $.datepicker.setDefaults($.datepicker.regional["ko"]);
        // Today 버튼 코드 추가
        $.datepicker._gotoToday = function (id) {
        	$(id).datepicker('setDate', new Date());
            $(".ui-datepicker").hide().blur();
        };
    }
    
    function formatDate(date) {
        // 날짜와 시간 포맷
        var year = date.getFullYear();
        var month = String(date.getMonth() + 1).padStart(2, '0');
        var day = String(date.getDate()).padStart(2, '0');
        var hour = String(date.getHours()).padStart(2, '0');
        var minute = String(date.getMinutes()).padStart(2, '0');
        
        return year + '-' + month + '-' + day + ' ' + hour + ':' + minute;
    }

    function updateDateTime() {
        var now = new Date();
        var formattedDate = formatDate(now);
        var $newDayElement = $('#nowDate'); // 제이쿼리로 요소 선택

        // 현재 시간이 요소의 시간과 다르면 업데이트
        if ($newDayElement.text() !== formattedDate) {
            $newDayElement.text(formattedDate); // 제이쿼리로 텍스트 업데이트
        }
    }
    
    function ajaxRequest(url, data, successCallback, errorCallback) {
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
    
    function save(){
    	
    	var name = $("#name").val();
    	var startDate = $("#startDate").val();
    	
    	// 빈값 또는 null 체크
        if (!name || !startDate) {
            alert("매장 이름과 업종 시작일을 모두 입력해 주세요.");
            return false; // 메소드 종료
        }
    	
    	var url = "/store/save";
    	var jsonItem = {
    		name : name,
    		startDate : startDate
    	};
    	
    	// ajaxRequest 함수를 사용하여 Ajax 요청 보내기
        ajaxRequest(
       		url,
            jsonItem,
            function(response) {
       			saveSuccessCallback(response);
            },
            errorCallback
        );
    	
    }
    
    function saveSuccessCallback(response){
    	// 문자열인 경우 JSON으로 파싱
        response = JSON.parse(response);
        
        if (response.msg == "0000") {
        	console.log(response.data);
        	if(response.data > 0){
                window.location.href = '/store/list';
        	}
        	
        } else {
            // msg가 '0000'이 아닌 경우 처리
            console.error('Error: ' + response.msg);
        }
    }
    
    function errorCallback(xhr, status, error) {
        // 실패 시 처리할 코드
        console.error(error);
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
<form name="searchFrm" id="searchFrm" method="GET">

	<div class="areport-mvp-none">
	
        <div class="container">
            
            <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
            
            <div class="divider"></div>
            
            <div class="title">
                <u>매장 목록</u> > 매장 신규등록
            </div>
            
            <div class="areport-mvp">
            
                <div class="new">
                    <div class="tit">입력일 버전</div>
                    <span class="new-day" id="nowDate"></span>
                </div>
                
                <div class="new">
                    <div class="tit">매장이름</div> 
                    <div class="selectEnroll">
                    	<div class="search-box search-box">
                    		<input class="search-txt-disable" type="text" id="name" name="name" value=""/>
                    	</div>
                    </div>
                </div>
                
                <div class="new">
                    <div class="tit new-mid">업종 시작일</div>
                    <div class="selectBoxList">
                        <!-- selectBox1 -->
                        <div class="box">
                            <div class="selectBox2 " id="datepicker">
                                <input type="text" id="startDate" name="startDate" placeholder="날짜선택" class="label new-datepicker" value="">
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="new">
                    <div class="new-but-box">
                        <div class="but1" id="resetBtn" onclick="javascript:;">삭제</div>
                        <div class="but2" id="saveBtn" onclick="javascript:;">저장</div>
                    </div>
                </div>
                
            </div>

        </div>
        
        <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
        
    </div>
    
</form>
</body>
</html>
