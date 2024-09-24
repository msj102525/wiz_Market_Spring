<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <title>매장정보상세보기</title>
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/jquery.Jcrop.css" />
    <link rel="stylesheet" href="<%=path%>/css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/areport-mvp_style.css" />
    <link rel="stylesheet" href="<%=path%>/css/jquery-ui.css" />
    <!-- jQuery 포함 -->
    <script type="text/javascript" src="<%=path%>/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.Jcrop.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.jcrop.common.js"></script>
	<script type="text/javascript" src="<%=path%>/js/common.js"></script>
	<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript">
    var mainCategoryList;
	var subCategoryList;
	var onlineUrlList;
    
	//이미지 세팅 시작
    var image_create_count = 4; // 이미지 추가 개수
	//크롭 세팅 ====
	var form_selecter_name = "#loadInformation";
	var file_input_name = "fileList"; // 파일 input 이름
	var crop_data_input_name = "image_url"; // 크롭 된 파일 데이터 input 이름
	var crop_data_create_url = "<%=path %>/common/getJCropImageData"; // 이미지 크롭 URL
	let jCrop;
	$(document).ready(function() {
    	//크롭 세팅 ====
		jCrop = new Jcrop(form_selecter_name, file_input_name, crop_data_input_name, crop_data_create_url, fileSelectCallback, fileCropCallback);
		//크롭 기본 객체 생성
		jCrop.init();
		//이미지 삭제 click 이벤트 추가
		setImageDeleteOnClick();
		//이미지 추가 버튼 보임/숨김 처리
		setImageAddButton();
		//크롭 세팅 ====
	});
	//파일 이미지 선택 콜백 함수
	function fileSelectCallback(data) {
		//e.target.result 이미지 데이터
		//파일 선택시 이미지 UI 세팅
		var infoImgTag ="";
		infoImgTag = '<div class="new-img new-img new-img2" style="background-repeat: no-repeat; background-size: 100% 100%; background-position: center center;background-image: url('+data+');">';
		infoImgTag += '<div class="new-img-use">';
		infoImgTag += '<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none" data-value="">';
		infoImgTag += '<g clip-path="url(#clip0_1_821)">';
		infoImgTag += '<path d="M9.52 1.33337C9.79984 1.33345 10.0726 1.42156 10.2995 1.58525C10.5265 1.74893 10.6962 1.97987 10.7847 2.24537L11.1467 3.33337H13.3333C13.5101 3.33337 13.6797 3.40361 13.8047 3.52864C13.9298 3.65366 14 3.82323 14 4.00004C14 4.17685 13.9298 4.34642 13.8047 4.47144C13.6797 4.59647 13.5101 4.66671 13.3333 4.66671L13.3313 4.71404L12.7533 12.8094C12.7173 13.3138 12.4915 13.7858 12.1214 14.1304C11.7513 14.475 11.2644 14.6666 10.7587 14.6667H5.24133C4.73564 14.6666 4.24874 14.475 3.87864 14.1304C3.50855 13.7858 3.28274 13.3138 3.24667 12.8094L2.66867 4.71337C2.66746 4.69785 2.66679 4.68228 2.66667 4.66671C2.48986 4.66671 2.32029 4.59647 2.19526 4.47144C2.07024 4.34642 2 4.17685 2 4.00004C2 3.82323 2.07024 3.65366 2.19526 3.52864C2.32029 3.40361 2.48986 3.33337 2.66667 3.33337H4.85333L5.21533 2.24537C5.3038 1.97977 5.47362 1.74874 5.70073 1.58505C5.92784 1.42136 6.20071 1.33331 6.48067 1.33337H9.52ZM11.998 4.66671H4.002L4.57667 12.714C4.58863 12.8822 4.66385 13.0395 4.78717 13.1545C4.9105 13.2694 5.07277 13.3333 5.24133 13.3334H10.7587C10.9272 13.3333 11.0895 13.2694 11.2128 13.1545C11.3362 13.0395 11.4114 12.8822 11.4233 12.714L11.998 4.66671ZM6.66667 6.66671C6.82996 6.66673 6.98756 6.72668 7.10958 6.83518C7.23161 6.94369 7.30956 7.09321 7.32867 7.25537L7.33333 7.33337V10.6667C7.33315 10.8366 7.26808 11.0001 7.15143 11.1236C7.03479 11.2472 6.87536 11.3215 6.70574 11.3315C6.53611 11.3414 6.36908 11.2863 6.23878 11.1772C6.10848 11.0681 6.02474 10.9134 6.00467 10.7447L6 10.6667V7.33337C6 7.15656 6.07024 6.98699 6.19526 6.86197C6.32029 6.73695 6.48986 6.66671 6.66667 6.66671ZM9.33333 6.66671C9.51014 6.66671 9.67971 6.73695 9.80474 6.86197C9.92976 6.98699 10 7.15656 10 7.33337V10.6667C10 10.8435 9.92976 11.0131 9.80474 11.1381C9.67971 11.2631 9.51014 11.3334 9.33333 11.3334C9.15652 11.3334 8.98695 11.2631 8.86193 11.1381C8.73691 11.0131 8.66667 10.8435 8.66667 10.6667V7.33337C8.66667 7.15656 8.73691 6.98699 8.86193 6.86197C8.98695 6.73695 9.15652 6.66671 9.33333 6.66671ZM9.52 2.66671H6.48L6.258 3.33337H9.742L9.52 2.66671Z" fill="black" />';
		infoImgTag += '</g>';
		infoImgTag += '<defs>';
		infoImgTag += '<clipPath id="clip0_1_821">';
		infoImgTag += '<rect width="16" height="16" fill="white"></rect>';
		infoImgTag += '</clipPath>';
		infoImgTag += '</defs>';
		infoImgTag += '</svg>';
		infoImgTag += '</div>';
		infoImgTag += '</div>';
		
		$("[id='"+form_selecter_name.slice(1)+"']").eq(jCrop.file_index).find('#image_start_div').append(infoImgTag);
		
		//이미지 붙이기(실제 페이지에 이미지 태그를 감싸는 부모 생성 필요)
		//$('#slideTogglebox_'+jCrop.file_index+' '+'#image_start_div').append(infoImgTag);
		//이미지 삭제 클릭
		setImageDeleteOnClick();
		//이미지 추가 버튼 보임/숨김 처리
		setImageAddButton();
	}
	//이미지 삭제 click 이벤트 추가
	function setImageDeleteOnClick() {
		$("[id='"+form_selecter_name.slice(1)+"']").each(function(parant_index) {
		    $(this).find('.new-img').each(function(index) {
		        var dataValue = $(this).find('svg').attr("data-value");
		        $(this).find('svg').off('click').on('click', function() {
		            delImage(parant_index, index, dataValue);
		        });
		    });
		});
	}
	//이미지 추가 버튼 보임/숨김 처리
	function setImageAddButton() {
		$("[id='"+form_selecter_name.slice(1)+"']").each(function(index) {
			if($(this).find(".new-img").length>=image_create_count) {
				$(this).find(".new-img-none").hide();
			} else {
				$(this).find(".new-img-none").show();
			}
			$(this).find(".new-img-none").off('click').on('click', function() {
				jCrop.fileUploadAction(index);
	        });
		});
	}
	//크롭 이미지 콜백 함수
	function fileCropCallback(data) {
		//data 크롭 이미지 데이터
		//크롭 이미지 데이터 UI 세팅
		$('#slideTogglebox_'+jCrop.file_index+' '+'.new-img').last().css({
	        'background-image': 'url(' + data + ')',
	        'background-repeat': 'no-repeat',
	        'background-size': '100% 100%',
	        'background-position': 'center center'
	    });
	}
	//이미지 삭제
	function delImage(parant_index, num, fileId) {
		var addCnt = 0;
		
		$("[id='"+form_selecter_name.slice(1)+"']").eq(parant_index).find('.new-img').each(function(index) {
			var dataValue = $(this).find('svg').attr("data-value");
			if (dataValue !== undefined && dataValue !== null && dataValue.trim() !== '') {
				addCnt++;
			}
		});
		
		//선택된 이미지 삭제
		$("[id='"+form_selecter_name.slice(1)+"']").eq(parant_index).find('.new-img').each(function(index) {
			if(num === index) {
				$(this).remove();
				return;
			}
		});
		
		//삭제 버튼 click 재 할당
		setImageDeleteOnClick();
		
		//아이디 있는 경우
		if (fileId !== undefined && fileId !== null && fileId.trim() !== '') {
			//아이디가 있는 이미지 삭제시 삭제 아이디 input 생성
			var fileIdHtml = '<input type="hidden" name="deleteFileIdList" value="'+fileId+'">';
			$("[id='"+form_selecter_name.slice(1)+"']").eq(parant_index).append(fileIdHtml);
			
		//아이디 없는 경우
		} else {
			//아이디가 없는 이미지 삭제 - 생성 되어있는 file_input, crop_data_input 삭제할 index 넘겨서 삭제
			jCrop.delImage(num-addCnt);
		}
		
		//이미지 추가 버튼 보임/숨김 처리
		setImageAddButton();
	}
	//이미지 세팅 종료
	
    $(document).ready(function() {
    	setDatepicker();
    	
    	// 업종 시작일 변경 할 때, 경과일 계산 및 변경
    	$(".new-datepicker").on('change', function(){
    		var inputDate = $(".new-datepicker").val();
    		calculateDays(inputDate);
    	});
    	
    	// sales 입력 필드의 값이 변경될 때마다 호출되는 함수
        $('input[id^="sales_"]').on('input', function() {
            var index = $(this).attr('id').split('_')[1]; // ID에서 인덱스 추출
            updateSalesText(index); // 수정된 함수 호출
        });
    	
        $('input[id^="onlineUrl_"]').on('input', function(){
        	var index = $(this).attr('id').split('_')[1]; // ID에서 인덱스 추출
    		var onlineUrlValue = $("#onlineUrl_"+index).val();
    		if(onlineUrlValue){
    			$("#linkCheckBtn_"+index).show();
    		}else{
    			$("#linkCheckBtn_"+index).hide();
    		}
    	});
    	
    	// 매장 되돌아가기 버튼 클릭
    	$("#returnBtn").on('click', function(){
    		returnToPage();
    	});
    	// 매장 삭제 버튼 클릭
    	$("#removeBtn").on('click', function(){
    		removeStore();
    	});
    	
    	// 매장 수정 버튼 클릭
    	$("#saveBtn").on('click', function(){
    		saveStore();
    	});

        // salesText를 업데이트하는 함수
        function updateSalesText(index) {
            var value = $('#sales_' + index).val(); // 입력된 값 가져오기
            var formattedValue = formatNumber(value); // 값 포맷팅
           
            // 관련 salesText 업데이트
            $('#salesText_' + index).text(formattedValue);
        }
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
    
  	//주소
    function searchDaumPostcode(index) {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                //참고 가이드
                //https://postcode.map.daum.net/guide 

                var addr = "";

                addr += "우편 번호 : ";
                addr += data.zonecode;
                addr += "\n";
                addr += "지번 : ";
                addr += data.roadAddress;
                addr += "\n";
                addr += "도로명 : ";
                addr += data.jibunAddress;
                
                alert(addr);
                
                $("#addr_" + index).val(data.roadAddress);
                $("#siNm_" + index).val(data.sido);
                $("#sggNm_" + index).val(data.sigungu);
                $("#emdNm_" + index).val(data.bname2);
                $("#liNm_" + index).val(data.bname1);
                $("#rn_" + index).val(data.roadname);
            }
        }).open();
    }
  	
    // 리포트 수정버튼
    function saveReport(index){
    	console.log(index+"번째 수정 버튼 클릭");
    	var url = "/report/update";
    	var form = $("#slideTogglebox_"+index);
    	var fields = form.find('input, textarea, select');
    	var formData = collectFormData(fields);
    	
    	ajaxFormRequest(url, formData, saveReportSuccessCallback, errorCallback);
    }
    
    // 리포트 수정 버튼 성공 콜백
    function saveReportSuccessCallback(response){
    	// 문자열인 경우 JSON으로 파싱
        response = JSON.parse(response);
        
        if (response.msg == "0000") {
        	console.log(response.data);
        	if(response.data > 0){
        		moveUrl('<%=path %>'+window.location.pathname+window.location.search,'Y');
        		// reportId를 쿼리 파라미터로 추가하여 페이지 이동
                alert("리포트 수정 완료");
        	}
        	
        } else {
            // msg가 '0000'이 아닌 경우 처리
            console.error('Error: ' + response.msg);
        }
    }
    
    // 리포트 취소 버튼
    function rollbackReport(index){
    	console.log(index+"번째 취소 버튼 클릭");
    	
    	$("#onlineUrlCategoryCd_" + index).val($("#beforeOnlineUrlCategoryCd_"+index).val());
    	$("#onlineUrl_" + index).val($("#beforeOnlineUrl_"+index).val());
    	$("#sales_" + index).val($("#beforeSales_"+index).val());
    	
    }
    
    // 매장 되돌아가기 버튼
    function returnToPage(){
    	window.location.href = '/store/list';
    }
    
    // 매장 삭제 버튼
    function removeStore(){
    	console.log("매장 삭제 버튼 클릭");
    	var url = "/store/delete";
    	var jsonItem = { 
            storeId : $("#storeId").val()
        }; 
    	
    	console.log(jsonItem);
    	ajaxJSONRequest(url, jsonItem, removeStoreSuccessCallback, errorCallback);
    }
    
 // 매장 저장 성공 콜백 함수
    function removeStoreSuccessCallback(response){
    	// 문자열인 경우 JSON으로 파싱
        response = JSON.parse(response);
        
        if (response.msg == "0000") {
        	console.log(response.data);
        	if(response.data > 0){
        		// reportId를 쿼리 파라미터로 추가하여 페이지 이동
                alert("매장 삭제 완료");
                window.location.href = '/store/list';
        	}
        	
        } else {
            // msg가 '0000'이 아닌 경우 처리
            console.error('Error: ' + response.msg);
        }
    }
    
    // 매장 저장 버튼
    function saveStore(){
    	console.log("매장 수정 버튼 클릭");
    	var url = "/store/save";
    	var jsonItem = { 
            storeId : $("#storeId").val(),
            startDate : $("#startDate").val()
        };
    	
    	console.log(jsonItem);
    	ajaxJSONRequest(url, jsonItem, saveStoreSuccessCallback, errorCallback);
    	window.location.href = '/store/list';
    }
    
    // 매장 저장 성공 콜백 함수
    function saveStoreSuccessCallback(){
    	// 문자열인 경우 JSON으로 파싱
        response = JSON.parse(response);
        
        if (response.msg == "0000") {
        	console.log(response.data);
        	if(response.data > 0){
                
        		// reportId를 쿼리 파라미터로 추가하여 페이지 이동
                alert("매장 수정 완료");
                window.location.href = '/store/list';
        	}
        	
        } else {
            // msg가 '0000'이 아닌 경우 처리
            console.error('Error: ' + response.msg);
        }
    }
    
    function ajaxFormRequest(url, formData, successCallback, errorCallback) {
        $.ajax({
            url: url,
            method: 'POST',
            data: formData,
            contentType: false,
            processData: false,
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
    
 	// 숫자를 한글 단위로 포맷팅하는 함수
    function formatNumber(value) {
        var number = parseFloat(value.replace(/,/g, '')); // 콤마 제거 및 숫자로 변환
        if (isNaN(number)) {
            return '0';
        }
        return numberToKorean(number); // 한글 포맷팅
    }

 	// 숫자를 한글 단위로 포맷팅하는 함수
    function numberToKorean(number) {
        if (number === 0) return '0';
        
        var units = ['', '만', '억', '조', '경'];
        var unitNames = ['', '십', '백', '천'];
        var result = '';

        var unitIndex = 0;
        while (number > 0) {
            var part = number % 10000;
            if (part > 0) {
                result = formatPart(part) + (units[unitIndex] ? units[unitIndex] : '') + result;
            }
            number = Math.floor(number / 10000);
            unitIndex++;
        }

        return result ? result + '' : '0';
    }

    // 4자리 숫자를 한글로 포맷팅하는 함수
    function formatPart(part) {
        var unitNames = ['', '십', '백', '천'];
        var result = '';
        var digit;

        for (var i = 0; i < 4; i++) {
            digit = part % 10;
            if (digit > 0) {
                result = digit + unitNames[i] + result;
            }
            part = Math.floor(part / 10);
        }

        return result;
    }
    
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
    
    function fetchCategoryLists(){
    	console.log("카테고리 불러오기");
    	var url = "/common/category/lists";
    	var jsonItem = {};
    	
    	ajaxJSONRequest(url, jsonItem, categorySettingSuccessCallback, errorCallback);
    }
    
    function fetchCodeOnlineLists(){
    	console.log("온라인 URL 불러오기");
    	var url = "/common/code/online/lists";
		var jsonItem = {};
    	
		ajaxJSONRequest(url, jsonItem, onlineUrlSettingSuccessCallback, errorCallback);
    }
    
    function categorySettingSuccessCallback(response){
    	// 문자열인 경우 JSON으로 파싱
        response = JSON.parse(response);
        
        if (response.msg == "0000") {
        	mainCategoryList = response.data.mainCategoryList;
        	subCategoryList = response.data.subCategoryList;
        	
        } else {
            // msg가 '0000'이 아닌 경우 처리
            console.error('Error: ' + response.msg);
        }
    }
    
    function onlineUrlSettingSuccessCallback(response){
    	// 문자열인 경우 JSON으로 파싱
        response = JSON.parse(response);
        
        if (response.msg == "0000") {
        	response.data.onlineUrlList
        	
        } else {
            // msg가 '0000'이 아닌 경우 처리
            console.error('Error: ' + response.msg);
        }
    }
    
    function errorCallback(xhr, status, error) {
        // 실패 시 처리할 코드
        console.error(error);
    }
    
 	// 날짜 계산 함수
    function calculateDays(inputDate) {
        // 사용자가 입력한 날짜 가져오기
        var selectedDate = inputDate;

        // 날짜가 입력되지 않은 경우 처리
        if (!selectedDate) {
            $("#daysDifference").text("날짜를 선택해주세요.");
            return;
        }

        // 선택한 날짜와 현재 날짜를 Date 객체로 변환
        var selectedDateObj = new Date(selectedDate);
        var currentDateObj = new Date();

     	// 날짜 차이 계산
        var years = currentDateObj.getFullYear() - selectedDateObj.getFullYear();
        var months = currentDateObj.getMonth() - selectedDateObj.getMonth();
        var days = currentDateObj.getDate() - selectedDateObj.getDate();

        // 월과 일 보정
        if (days < 0) {
            months--;
            days += new Date(currentDateObj.getFullYear(), currentDateObj.getMonth(), 0).getDate(); // 이전 달의 일 수
        }
        if (months < 0) {
            years--;
            months += 12;
        }

        // 결과 문자열 생성
        var result = "";
        if (years > 0) {
            result += years + "년 ";
        }
        if (months > 0) {
            result += months + "개월 ";
        }
        if (days > 0 || (years === 0 && months === 0)) { // 최소 하루라도 경과했으면 일도 표시
            result += days + "일";
        }

        // 결과 출력
        $("#daysDifference").text(result.trim() || "0일");
    }
    
  	//이력 상세
    function setSlide(index) {
        $('#slideTogglebox_' + index).slideToggle();
        console.log('#btn_'+ index + "  닫음");
        
        // Toggle 버튼 코드 추가
        $("#slideTogglebox_" + index).show();
        $("#slideTogglebox_" + index, this).slideToggle("fast");
        if ($("#btn_" + index).find(".up").css("display") == "none") {
        	$("#btn_" + index).find(".down").hide();
        } else {
        	$("#btn_" + index).find(".down").show();
        }
        $("#btn_" + index).find(".up").toggle();
        
    }
  	
    function linkCheck(index){
    	var linkUrl = $("#onlineUrl_"+index).val();
    	window.open(linkUrl, '_blank', 'width=600,height=400');
    };
    </script>
</head>
<body>
<div name="searchFrm" id="searchFrm" method="GET">

	<input type="hidden" id="storeId" name="storeId" value="${ store.storeId }" />
	<div class="areport-mvp-none">
	
        <div class="container">
            
            <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
            
            <div class="divider"></div>
            
            <div class="title">
                <u>매장 목록</u> > 뱃고동 압구정점
            </div>
            
            <div class="areport-mvp" style="display: flex; flex-direction: column;">
            	
                <div class="new">
                    <div class="tit">입력일 버전</div>
                    <span class="new-day">${ store.regDate }</span>
                </div>
                
                <div class="new">
                    <div class="tit">매장이름</div>
                    <div class="selectEnroll">
                    	<div class="search-box search-box">
                    		<input class="search-txt-disable" type="text" value="${ store.name }" readonly />
                    	</div>
                    </div>
                </div>
                
                <div class="new">
                    <div class="tit new-mid">업종 시작일</div>
                    <div class="selectBoxList">
                        <!-- selectBox1 -->
                        <div class="box">
                            <div class="selectBox2 ">
                                <input type="text" id="startDate" name="startDate" placeholder="날짜선택" class="label new-datepicker" value="${ store.startDate }">
                            </div>
                        </div>
                    </div>
                    <span class="day-sub" id="daysDifference">${ store.displayStartDateDayPass }</span>
                    <span id=""> 경과</span>
                </div>
                
                <div class="new-bottom16" style="float: left;">
                    <div class="tit new-mid">리포트 이력</div>
                </div>
                
                <c:forEach var="report" items="${reportList}" varStatus="status">
	                <div class="selectBoxList resumelist" style="padding-left: 5px;">
	                    <div class="resume">
	                        <!-- selectBox1 -->
	                        <div class="box">
	                            <div id="btn_${status.index}" data-value="${ report.reportId }" onclick="javascript:setSlide(${status.index});return false;" class="modify-Box">
	                                <div class="modify-tit">${report.regDate}</div>
	                                <div class="toggle-btn">
	                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17" viewBox="0 0 16 17" fill="none" class="up">
	                                        <path d="M8.00008 11.8333L13.3334 5.16659H2.66675L8.00008 11.8333Z" fill="#656565" />
	                                    </svg>
	                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="17" viewBox="0 0 16 17" fill="none" class="down">
	                                        <path d="M8.00016 5.16675L2.66683 11.8334H13.3335L8.00016 5.16675Z" fill="#656565" />
	                                    </svg>
	                                </div>
	                            </div>
	
	                        </div>
	                    </div>
	                </div>
	                
	                <div id="slideTogglebox_${status.index}" style="display: none;">
	                	<div id="loadInformation">
	                	<input type="hidden" id="reportId_${status.index}" name="reportId" value="${ report.reportId }"/>
	                	<input type="hidden" id="fileGroupId" name="fileGroupId" value="${ report.fileGroupId }"/>
	                	<input type="hidden" id="reportAdditionalId_${status.index}" name="reportAdditionalId" value="${ report.reportAdditionalId }"/>
	                	<input type="hidden" id="beforeOnlineUrlCategoryCd_${status.index}" value="${ report.onlineUrlCategoryCd }"/>
	                	<input type="hidden" id="beforeOnlineUrl_${status.index}" value="${ report.onlineUrl }"/>
	                	<input type="hidden" id="beforeSales_${status.index}" value="${ report.sales }"/>
	                    <div class="Toggle-new">
	                        <div class="tit">업종분류</div>
	                        <div class="container-7">
	                        
	                        	<div class="selectBoxList">
	                        		<div class="box">
	                        			<div class="selectBox3 search-box-red">
				                        	<select id="" name="mainCategoryId" class="select_renewal" disabled>
				                        		<c:forEach var="mainCategory" items="${ mainCategoryList }">
						                        	<option value="${ mainCategory.categoryId }" <c:if test="${report.mainCategoryId == mainCategory.categoryId}">selected</c:if>>${ mainCategory.categoryName }</option>
						                    	</c:forEach>
				                        	</select>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	
	                        	<div class="selectBoxList">
	                        		<div class="box">
	                        			<div class="selectBox3 search-box-red">
				                        	<select id="" name="" class="select_renewal" disabled>
				                        		<option value="한식">한식</option>
				                        		<c:forEach var="subCategory" items="${ subCategoryList }">
						                        	<option value="${ subCategoryId.categoryId }" <c:if test="${report.subCategoryId == subCategory.categoryId}">selected</c:if>>${ subCategory.categoryName }</option>
						                    	</c:forEach>
				                        	</select>
	                        			</div>
	                        		</div>
	                        	</div>
	                        	
	                        </div>
	                    </div>
	                    <div class="Toggle-new">
	                        <div class="tit">주소등록</div>
	                        <div class="container-7">
	                            <div class="new-input-box">
	                            	<div class="search-box search-box-red">
	                            		<input class="search-txt" type="text" id="" name="addr" value="${report.addr}" disabled>
	                            	</div>
	                            </div>
	                            <button class="but2" onclick="javascript:searchDaumPostcode(${status.index});return false;" style="border:0px;" disabled>검색</button>
	                            <div class="new-input-box">
	                            	<div class="search-box search-box-red  w-710">
	                            	<input class="search-txt" type="text" id="" name="addrDetail" value="${report.addrDetail}" disabled>
	                                </div>
	                            </div>
	                            <input type="hidden" id="siNm_${status.index}" name="siNm" value="${ report.siNm }">
			                    <input type="hidden" id="sggNm_${status.index}" name="sggNm" value="${ report.sggNm }">
			                    <input type="hidden" id="emdNm_${status.index}" name="emdNm" value="${ report.emdNm }">
			                    <input type="hidden" id="liNm_${status.index}" name="liNm" value="${ report.liNm }">
			                    <input type="hidden" id="rn_${status.index}" name="rn" value="${ report.rn }">
	                        </div>
	                    </div>
	                    <div class="Toggle-new">
	                        <div class="tit">온라인URL</div>
	                        <div class="selectBoxList">
	                            <!-- selectBox1 -->
	                            <div class="box">
	                                <div class="selectBox3">
				                        <select class="select_renewal" id="onlineUrlCategoryCd_${status.index}" name="onlineUrlCategoryCd">
				                      		<option value="">종류선택</option>
				                      		<c:forEach var="onlineUrl" items="${ onlineUrlList }">
						                        <option value="${ onlineUrl.detailCd }" <c:if test="${report.onlineUrlCategoryCd == onlineUrl.detailCd}">selected</c:if>>${ onlineUrl.detailCdName }</option>
						                    </c:forEach>
				                      	</select>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="selectEnroll">
	                            <div class="search-box new-width">
	                                <input class="search-txt" type="text" id="onlineUrl_${status.index}" name="onlineUrl" value="${ report.onlineUrl }">
		                            <c:if test="${not empty report.onlineUrl}">
        							<a id="linkCheckBtn_${status.index}" onclick="linkCheck(${status.index})">링크 확인</a>
	    							</c:if>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="Toggle-new">
	                        <div class="tit">월평균 매출</div>
	                        <div class="new-input-box">
	                            <div class="search-box">
	                                <input class="search-txt" type="text" id="sales_${status.index}" name="sales" value="${ report.sales }"><span style="align-content: center;" id="salesText_${status.index}" name="salesText_${status.index}">${ report.displaySales }</span>
	                            </div>
	                        </div>
	                        <span>원</span>
	                    </div>
	                    <div class="Toggle-new">
	                        <div class="tit new-top">이미지 등록</div>
	                        <div class="container-6">
	                        <c:forEach var="file" items="${report.fileList}" varStatus="fileIndex">
	                        	<input type="hidden" name="fileId" valaue="${ file.fileId }" />
	                        </c:forEach>
	                        	<!-- 이미지 세팅 -->
		                        <div id="image_start_div" style="display: inline-block;">
			                        <c:forEach items="${report.fileList }" var="vo" varStatus="fileIndex">
			                        <div class="img new-img new-img2" style="background-repeat: no-repeat; background-size: 100% 100%; background-position: center center;<c:if test="${not empty vo.url }">background-image: url(${vo.url });</c:if><c:if test="${empty vo.url }">display: none;</c:if>">
			                            <div class="new-img-use">
			                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none" data-value="<c:out value="${vo.fileId}" />">
			                                    <g clip-path="url(#clip0_1_821)">
			                                        <path d="M9.52 1.33337C9.79984 1.33345 10.0726 1.42156 10.2995 1.58525C10.5265 1.74893 10.6962 1.97987 10.7847 2.24537L11.1467 3.33337H13.3333C13.5101 3.33337 13.6797 3.40361 13.8047 3.52864C13.9298 3.65366 14 3.82323 14 4.00004C14 4.17685 13.9298 4.34642 13.8047 4.47144C13.6797 4.59647 13.5101 4.66671 13.3333 4.66671L13.3313 4.71404L12.7533 12.8094C12.7173 13.3138 12.4915 13.7858 12.1214 14.1304C11.7513 14.475 11.2644 14.6666 10.7587 14.6667H5.24133C4.73564 14.6666 4.24874 14.475 3.87864 14.1304C3.50855 13.7858 3.28274 13.3138 3.24667 12.8094L2.66867 4.71337C2.66746 4.69785 2.66679 4.68228 2.66667 4.66671C2.48986 4.66671 2.32029 4.59647 2.19526 4.47144C2.07024 4.34642 2 4.17685 2 4.00004C2 3.82323 2.07024 3.65366 2.19526 3.52864C2.32029 3.40361 2.48986 3.33337 2.66667 3.33337H4.85333L5.21533 2.24537C5.3038 1.97977 5.47362 1.74874 5.70073 1.58505C5.92784 1.42136 6.20071 1.33331 6.48067 1.33337H9.52ZM11.998 4.66671H4.002L4.57667 12.714C4.58863 12.8822 4.66385 13.0395 4.78717 13.1545C4.9105 13.2694 5.07277 13.3333 5.24133 13.3334H10.7587C10.9272 13.3333 11.0895 13.2694 11.2128 13.1545C11.3362 13.0395 11.4114 12.8822 11.4233 12.714L11.998 4.66671ZM6.66667 6.66671C6.82996 6.66673 6.98756 6.72668 7.10958 6.83518C7.23161 6.94369 7.30956 7.09321 7.32867 7.25537L7.33333 7.33337V10.6667C7.33315 10.8366 7.26808 11.0001 7.15143 11.1236C7.03479 11.2472 6.87536 11.3215 6.70574 11.3315C6.53611 11.3414 6.36908 11.2863 6.23878 11.1772C6.10848 11.0681 6.02474 10.9134 6.00467 10.7447L6 10.6667V7.33337C6 7.15656 6.07024 6.98699 6.19526 6.86197C6.32029 6.73695 6.48986 6.66671 6.66667 6.66671ZM9.33333 6.66671C9.51014 6.66671 9.67971 6.73695 9.80474 6.86197C9.92976 6.98699 10 7.15656 10 7.33337V10.6667C10 10.8435 9.92976 11.0131 9.80474 11.1381C9.67971 11.2631 9.51014 11.3334 9.33333 11.3334C9.15652 11.3334 8.98695 11.2631 8.86193 11.1381C8.73691 11.0131 8.66667 10.8435 8.66667 10.6667V7.33337C8.66667 7.15656 8.73691 6.98699 8.86193 6.86197C8.98695 6.73695 9.15652 6.66671 9.33333 6.66671ZM9.52 2.66671H6.48L6.258 3.33337H9.742L9.52 2.66671Z" fill="black"></path>
			                                    </g>
			                                    <defs>
			                                        <clipPath id="clip0_1_821">
			                                            <rect width="16" height="16" fill="white"></rect>
			                                        </clipPath>
			                                    </defs>
			                                </svg>
			                            </div>
			                        </div>
			                        </c:forEach>
		                        </div>
	                        	
	                            <div class="info-img-none new-img-none">
	                                <img src="<%=path%>/assets/vectors/image.svg" />
	                                <div class="new-img-txt">
										클릭하여 이미지를 등록해주세요.<br />
	                                    (최대 4장)<br />
	                                    (png, jpg, jpeg만 등록 가능합니다.)
	                                </div>
	                            </div>
	                            
	                        </div>
	                    </div>
	                    <div class="new">
	                        <div class="new-but-box">
	                            <div class="but1" onclick="javascript:rollbackReport(${status.index});return false;">취소</div>
	                            <div class="but2" onclick="javascript:saveReport(${status.index});return false;">수정</div>
	                        </div>
	                    </div>
	                    </div>
	                </div>
	                </c:forEach>
                
                <div class="new" style="clear: both;">
                    <div class="new-but-box">
                        <div class="but1" id="returnBtn">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 18 15" fill="none">
                                <path d="M1.38221 5.93265L0.691105 6.60566L0 5.93265L0.691105 5.25964L1.38221 5.93265ZM18 13.5481C18 13.8005 17.897 14.0427 17.7137 14.2212C17.5304 14.3997 17.2817 14.5 17.0225 14.5C16.7632 14.5 16.5146 14.3997 16.3313 14.2212C16.148 14.0427 16.045 13.8005 16.045 13.5481H18ZM5.57869 11.3653L0.691105 6.60566L2.07331 5.25964L6.9609 10.0193L5.57869 11.3653ZM0.691105 5.25964L5.57869 0.5L6.9609 1.84603L2.07331 6.60566L0.691105 5.25964ZM1.38221 4.98072H11.1574V6.88458H1.38221V4.98072ZM18 11.6442V13.5481H16.045V11.6442H18ZM11.1574 4.98072C12.9722 4.98072 14.7126 5.68277 15.9958 6.93242C17.2791 8.18206 18 9.87695 18 11.6442H16.045C16.045 10.3819 15.53 9.17125 14.6134 8.27864C13.6968 7.38604 12.4536 6.88458 11.1574 6.88458V4.98072Z" fill="white" />
                            </svg> 뒤로가기
                        </div>
                        <div class="but3" id="removeBtn">삭제</div>
                        <div class="but2" id="saveBtn">수정</div>
                    </div>
                </div>
                
            </div>

        </div>
            
        <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
        
    </div>
    
</div>
</body>
</html>
