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
    <title>리포트 정보 등록</title>
	<link rel="stylesheet" href="<%=path%>/css/jquery.Jcrop.css" />
    <link rel="stylesheet" href="<%=path%>/css/jquery-ui.css" />
    <link rel="stylesheet" href="<%=path%>/css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/areport-mvp_style.css" />
    <!-- jQuery 포함 -->
    <script type="text/javascript" src="<%=path%>/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.Jcrop.js"></script>
	<script type="text/javascript" src="<%=path%>/js/jquery.jcrop.common.js"></script>
    <script type="text/javascript" src="<%=path%>/js/common.js"></script>
    <!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
    <%-- <script src="<%=path%>/js/jquery.min.js"></script> --%>
    <script type="text/javascript">
    
	 	//이미지 세팅 시작
	    var image_create_count = 4; // 이미지 추가 개수
		//크롭 세팅 ====
		var form_selecter_name = ".loadInformation";
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
			
			var selector;
			if (form_selecter_name.startsWith("#")) {
	    		selector = $("[id='"+form_selecter_name.slice(1)+"']");
	    	} else {
	    		selector = $(form_selecter_name);
	    	}
			
			selector.eq(jCrop.file_index).find('#image_start_div').append(infoImgTag);
			
			//이미지 붙이기(실제 페이지에 이미지 태그를 감싸는 부모 생성 필요)
			//$('#slideTogglebox_'+jCrop.file_index+' '+'#image_start_div').append(infoImgTag);
			//이미지 삭제 클릭
			setImageDeleteOnClick();
			//이미지 추가 버튼 보임/숨김 처리
			setImageAddButton();
		}
		//이미지 삭제 click 이벤트 추가
		function setImageDeleteOnClick() {
			var selector;
			if (form_selecter_name.startsWith("#")) {
	    		selector = $("[id='"+form_selecter_name.slice(1)+"']");
	    	} else {
	    		selector = $(form_selecter_name);
	    	}
			selector.each(function(parant_index) {
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
			var selector;
			if (form_selecter_name.startsWith("#")) {
	    		selector = $("[id='"+form_selecter_name.slice(1)+"']");
	    	} else {
	    		selector = $(form_selecter_name);
	    	}
			selector.each(function(index) {
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
			
			var selector;
			if (form_selecter_name.startsWith("#")) {
	    		selector = $("[id='"+form_selecter_name.slice(1)+"']");
	    	} else {
	    		selector = $(form_selecter_name);
	    	}
			
			selector.eq(parant_index).find('.new-img').each(function(index) {
				var dataValue = $(this).find('svg').attr("data-value");
				if (dataValue !== undefined && dataValue !== null && dataValue.trim() !== '') {
					addCnt++;
				}
			});
			
			//선택된 이미지 삭제
			selector.eq(parant_index).find('.new-img').each(function(index) {
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
				selector.eq(parant_index).append(fileIdHtml);
				
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
        	
        	$("#storeInfoViewBtn").on('click', function(event) {
        		$(this).prop('disabled', true);
        		var storeId = $("#storeId").val();
        		window.location.href = '/store/info?storeId='+storeId;
            });
        	
        	$('#saveAndRowAddBtn').on('click', function(event) {
        		$(this).prop('disabled', true);
                console.log("saveAndNextBtn click");
                $("#isIssued").val("N");
                saveAndRowAdd();
            });
        	
        	$("#issueBtn").on("click", function(event) {
        		$(this).prop('disabled', true);
                console.log("issueBtn click");
                $("#isIssued").val("Y");
                issue();
            });
        	
        	$("#faqTitleId").on("change",function(){
        		var title = $("#faqTitleId").val();
        		$("#newTitle").val(title);
        	});
        	
        	// 삭제버튼 ( 초기화 기능 아닌가? )
        	$("#resetBtn").on("click", function(event) {
                reset();
            });
        	
        	$("#goBackBtn").on("click", function(event){
        		$(this).prop('disabled', true);
        		
        		var reportId = $("#reportId").val();
        		window.location.href = "/report/save/detail?reportId=" + reportId;
        	});
        	
        });
        
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
        
        function errorCallback(xhr, status, error) {
            // 실패 시 처리할 코드
            console.error(error);
        }
        
        function saveSuccessCallback(response){
        	// 문자열인 경우 JSON으로 파싱
            response = JSON.parse(response);
            
            if (response.msg == "0000") {
            	console.log(response.data.result);
            	if(response.data.result > 0){
            		console.log(response.data.result);
                	if(response.data.result > 0){
                		
                		var informationHtml = "";
                		var fileHtml = "";
                		
                		// TODO : loadInformation 저장한 데이터 추가
                		var fileList = response.data.information.fileList || [];
                		
                		fileList.forEach(function(vo) {
                		    var displayStyle = vo.url ? 'background-image: url(' + vo.url + ');' : 'display: none;';
                		    fileHtml += "<div class='new-img' style='background-repeat: no-repeat; background-size: cover; background-position: center center; " + displayStyle + "'>" +
                		        "<div class='new-img-use'>" +
                		            "<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' viewBox='0 0 16 16' fill='none' data-value='" + vo.fileId + "'>" +
                		                "<g clip-path='url(#clip0_1_821)'>" +
                		                    "<path d='M9.52 1.33337C9.79984 1.33345 10.0726 1.42156 10.2995 1.58525C10.5265 1.74893 10.6962 1.97987 10.7847 2.24537L11.1467 3.33337H13.3333C13.5101 3.33337 13.6797 3.40361 13.8047 3.52864C13.9298 3.65366 14 3.82323 14 4.00004C14 4.17685 13.9298 4.34642 13.8047 4.47144C13.6797 4.59647 13.5101 4.66671 13.3333 4.66671L13.3313 4.71404L12.7533 12.8094C12.7173 13.3138 12.4915 13.7858 12.1214 14.1304C11.7513 14.475 11.2644 14.6666 10.7587 14.6667H5.24133C4.73564 14.6666 4.24874 14.475 3.87864 14.1304C3.50855 13.7858 3.28274 13.3138 3.24667 12.8094L2.66867 4.71337C2.66746 4.69785 2.66679 4.68228 2.66667 4.66671C2.48986 4.66671 2.32029 4.59647 2.19526 4.47144C2.07024 4.34642 2 4.17685 2 4.00004C2 3.82323 2.07024 3.65366 2.19526 3.52864C2.32029 3.40361 2.48986 3.33337 2.66667 3.33337H4.85333L5.21533 2.24537C5.3038 1.97977 5.47362 1.74874 5.70073 1.58505C5.92784 1.42136 6.20071 1.33331 6.48067 1.33337H9.52ZM11.998 4.66671H4.002L4.57667 12.714C4.58863 12.8822 4.66385 13.0395 4.78717 13.1545C4.9105 13.2694 5.07277 13.3333 5.24133 13.3334H10.7587C10.9272 13.3333 11.0895 13.2694 11.2128 13.1545C11.3362 13.0395 11.4114 12.8822 11.4233 12.714L11.998 4.66671ZM6.66667 6.66671C6.82996 6.66673 6.98756 6.72668 7.10958 6.83518C7.23161 6.94369 7.30956 7.09321 7.32867 7.25537L7.33333 7.33337V10.6667C7.33315 10.8366 7.26808 11.0001 7.15143 11.1236C7.03479 11.2472 6.87536 11.3215 6.70574 11.3315C6.53611 11.3414 6.36908 11.2863 6.23878 11.1772C6.10848 11.0681 6.02474 10.9134 6.00467 10.7447L6 10.6667V7.33337C6 7.15656 6.07024 6.98699 6.19526 6.86197C6.32029 6.73695 6.48986 6.66671 6.66667 6.66671ZM9.33333 6.66671C9.51014 6.66671 9.67971 6.73695 9.80474 6.86197C9.92976 6.98699 10 7.15656 10 7.33337V10.6667C10 10.8435 9.92976 11.0131 9.80474 11.1381C9.67971 11.2631 9.51014 11.3334 9.33333 11.3334C9.15652 11.3334 8.98695 11.2631 8.86193 11.1381C8.73691 11.0131 8.66667 10.8435 8.66667 10.6667V7.33337C8.66667 7.15656 8.73691 6.98699 8.86193 6.86197C8.98695 6.73695 9.15652 6.66671 9.33333 6.66671ZM9.52 2.66671H6.48L6.258 3.33337H9.742L9.52 2.66671Z' fill='black'></path>" +
                		                "</g>" +
                		                "<defs>" +
                		                    "<clipPath id='clip0_1_821'>" +
                		                        "<rect width='16' height='16' fill='white'></rect>" +
                		                    "</clipPath>" +
                		                "</defs>" +
                		            "</svg>" +
                		        "</div>" +
                		    "</div>";
                		});
                		
                		informationHtml = "<div class='loadInformation' id='loadInformation_"+ response.data.information.reportInformationId + "'>" +
                					"<input type='hidden' id='reportInformationId_"+ response.data.information.reportInformationId + "' name='reportInformationId' value='" + response.data.information.reportInformationId + "'>" +
                					'<input type="hidden" name="fileGroupId" value="'+ response.data.information.fileGroupId +'">' +
                					"<div class='new'>" +
			                	    "<div class='tit new-top'>이미지 등록</div>" +
			                	    "<div class='container-6'>" +
			                	        "<div id='image_start_div' style='display: inline-block;'>" +
			                	        "</div>" + fileHtml +
			                	        "<div class='new-img-none'>" +
			                	            "<img src='<%=path%>/assets/vectors/image.svg'>" +
			                	            "<div class='new-img-txt'>" +
			                	                "클릭하여 이미지를 등록해주세요.<br>" +
			                	                "(최대 4장)<br>" +
			                	                "(png, jpg, jpeg만 등록 가능합니다.)" +
			                	            "</div>" +
			                	        "</div>" +
			                	    "</div>" +
			                	"</div>" +
			                	"<div class='new'>" +
			                	    "<div class='tit'>제목</div>" +
			                	    "<div class='container-6'>" +
			                	        "<div class='selectEnroll'>" +
			                	            "<div class='search-box'>" +
			                	                "<input class='search-txt' type='text' id='title' name='title' placeholder='제목을 신규로 등록합니다(최대 50글자)' value='" + response.data.information.title + "'/>" +
			                	            "</div>" +
			                	        "</div>" +
			                	    "</div>" +
			                	"</div>" +
			                	"<div class='new'>" +
			                	    "<div class='tit new-top'>내용</div>" +
			                	    "<textarea class='textarea' placeholder='내용을 입력해주세요.(최대 500글자)' id='content' name='content' value=''>" + response.data.information.content + "</textarea>" + 
			                	"</div>" +
			                	"<div class='new'>" +
			                	    "<div class='new-but-box'>" +
			                	    "<div class='but3' id='delete_" + response.data.information.reportInformationId + "' onclick='deleteInformation(" + response.data.information.reportInformationId + ")'>삭제</div>" +
		                	        "<div class='but2' id='update_" + response.data.information.reportInformationId + "' onclick='updateInformation(" + response.data.information.reportInformationId + ")'>수정</div>" +
			                	    "</div>" +
			                	"</div>" +
			                	"<div class='divider_no_width'></div>" +
			                	"</div>";
                		
			            $("#loadInformation").append(informationHtml);
                		
                		// TODO : newInformation 초기화
                		// 1. 이미지쪽은 html 새로그리기
                		$("#newImageLine").empty();
                		var imageLine = 
                			'<div id="image_start_div" style="display: inline-block;"></div>' + 
                		    "<div class='new-img-none'>" +
                		    "<div class='img-div-line'>" +
                		    "<img src='<%=path%>/assets/vectors/image.svg' />" +
                		    "<div class='new-img-txt'>" +
                		    "클릭하여 이미지를 등록해주세요.<br />" +
                		    "(최대 4장)<br />" +
                		    "(png, jpg, jpeg만 등록 가능합니다.)" +
                		    "</div>" +
                		    "</div>" +
                		    "<input type='file' style='display:none;' />" +
                		    "</div>";
                		$("#newImageLine").append(imageLine);
                		
                		// 2. title, content 내용 초기화
                		$("#faqTitleId").val("");
                		$("#newTitle").val("");
                		$("#newContent").val("");
                		
                		//이미지 삭제 click 이벤트 추가
            			setImageDeleteOnClick();
            			//이미지 추가 버튼 보임/숨김 처리
            			setImageAddButton();
            			$('#newInformation [name="fileList"]').remove();
            			$('#newInformation [name="image_url"]').remove();
            			
            			$("#saveAndRowAddBtn").prop('disabled', false);
                	}
            	}
            	
            } else {
                // msg가 '0000'이 아닌 경우 처리
                console.error('Error: ' + response.msg);
            }
        }
        
        function issudSuccessCallback(response){
        	// 문자열인 경우 JSON으로 파싱
            response = JSON.parse(response);
            
            if (response.msg == "0000") {
            	console.log(response.data.result);
            	if(response.data.result > 0){
            		console.log(response.data.result);
                	if(response.data.result > 0){
                		
                		// TODO : 리포트 목록으로 이동
                		alert("리포트 발행 완료");
                		
                		window.location.href = '/report/list';
                	}
            	}
            	
            } else {
                // msg가 '0000'이 아닌 경우 처리
                console.error('Error: ' + response.msg);
            }
        }
        
        function updateSuccessCallback(response){
        	// 문자열인 경우 JSON으로 파싱
            response = JSON.parse(response);
            
            if (response.msg == "0000") {
            	console.log(response.data.result);
            	if(response.data > 0){
            		console.log(response.data);
                	if(response.data > 0){
                		// TODO : 리포트 목록으로 이동
                		var reportId = $("#reportId").val();
                		window.location.href = '/report/save/information?reportId='+reportId;
                	}
            	}
            	
            } else {
                // msg가 '0000'이 아닌 경우 처리
                console.error('Error: ' + response.msg);
            }
        }
        
        function deleteSuccessCallback(response){
        	// 문자열인 경우 JSON으로 파싱
            response = JSON.parse(response);
            
            if (response.msg == "0000") {
            	console.log(response.data.result);
            	if(response.data > 0){
            		console.log(response.data);
                	if(response.data > 0){
                		alert("삭제 완료");
                		
                		// TODO : 리포트 목록으로 이동
                		var reportId = $("#reportId").val();
                		window.location.href = '/report/save/information?reportId='+reportId;
                	}
            	}
            	
            } else {
                // msg가 '0000'이 아닌 경우 처리
                console.error('Error: ' + response.msg);
            }
        }

        function saveAndRowAdd(){
        	console.log("save and row add method");
        	var title = $("#newTitle").val();
        	
        	// 빈 문자열이나 null 체크
        	if (!title) {
        	    alert("제목을 입력해 주세요.");
        	    return false;
        	}
        	
        	var url = "/report/information/insert";
        	var form = $("#newInformation");
        	var fields = form.find('input, textarea, select');
        	var formData = collectFormData(fields);
        	
       		ajaxFormRequest(url, formData, saveSuccessCallback, errorCallback);
        }
        
        function issue(){
			console.log("issue method");
        	
			var url = "/report/information/insert";
        	var form = $("#newInformation");
        	var fields = form.find('input, textarea, select');
        	var formData = collectFormData(fields);
        	
       		ajaxFormRequest(url, formData, issudSuccessCallback, errorCallback);
        }
        
        function updateInformation(informationId){
        	$("#update_" + informationId).prop('disabled', true);
			console.log("updateInformation method");
			console.log(informationId);
        	
			var url = "/report/information/update";
        	var form = $("#loadInformation_"+informationId);
        	var fields = form.find('input, textarea, select');
        	var formData = collectFormData(fields);
        	
       		ajaxFormRequest(url, formData, updateSuccessCallback, errorCallback);
        }
        
        function deleteInformation(informationId){
        	$("#delete_" + informationId).prop('disabled', true);
			console.log("deleteInformation method");
			console.log(informationId);
        	
			var url = "/report/information/delete";
        	var jsonItem = {
        		reportInformationId : informationId
        	}
        	
        	if(jsonItem){
        		ajaxJSONRequest(url, jsonItem, deleteSuccessCallback, errorCallback);
        	}
        }
        
     	// 입력칸 리셋 메소드
        function reset(){
        	// TODO : newInformation 초기화
    		// 1. 이미지쪽은 html 새로그리기
    		$("#newImageLine").empty();
    		var imageLine = 
    			'<div id="image_start_div" style="display: inline-block;"></div>' + 
    		    "<div class='new-img-none'>" +
    		    "<div class='img-div-line'>" +
    		    "<img src='<%=path%>/assets/vectors/image.svg' />" +
    		    "<div class='new-img-txt'>" +
    		    "클릭하여 이미지를 등록해주세요.<br />" +
    		    "(최대 4장)<br />" +
    		    "(png, jpg, jpeg만 등록 가능합니다.)" +
    		    "</div>" +
    		    "</div>" +
    		    "<input type='file' style='display:none;' />" +
    		    "</div>";
    		$("#newImageLine").append(imageLine);
    		
    		// 2. title, content 내용 초기화
    		$("#faqTitleId").val("");
    		$("#newTitle").val("");
    		$("#newContent").val("");
    		
    		//이미지 삭제 click 이벤트 추가
			setImageDeleteOnClick();
			//이미지 추가 버튼 보임/숨김 처리
			setImageAddButton();
			$('#newInformation [name="fileList"]').remove();
			$('#newInformation [name="image_url"]').remove();
        }
    </script>
</head>
<body>
	<input type="hidden" id="reportId" name="reportId" value="${ reportInformation.view.reportId }"/>
	<input type="hidden" id="storeId" name="storeId" value="${ reportInformation.view.storeId }"/>
    <div class="areport-mvp-none">
        <div class="container">
            <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
            <div class="divider">
            </div>
            <div class="title">
                <u>신규 리포트 생성</u> > <u>${ reportInformation.view.name }</u> > 세부등록
            </div>
            <div class="areport-mvp">
                <div class="new">
                    <div class="tit">등록정보</div>
                    <span class="new-day">${ reportInformation.view.regDate }</span>
                    <span class="new-box-none">${ reportInformation.view.siNm }</span>
                    <span class="new-box-none">${ reportInformation.view.sggNm }</span>
                    <span class="new-box-none">
                    	<c:if test="${not empty reportInformation.view.emdNm}">
					        ${reportInformation.view.emdNm}
					    </c:if>
					    <c:if test="${empty reportInformation.view.emdNm}">
					        ${reportInformation.view.liNm}
					    </c:if>
                    </span>
                    <span class="new-box-none">${ reportInformation.view.mainCategoryName }</span>
                    <span class="new-box-none">${ reportInformation.view.subCategoryName }</span>
                    <button class="but2" id="storeInfoViewBtn" style="border:0px;">정보보기</button>
                </div>
                
                <div id="loadInformation">
	                <c:forEach var="information" items="${reportInformation.reportInformationList}" varStatus="status">
	                	<div class="loadInformation" id="loadInformation_${ information.reportInformationId }">
	                	<input type="hidden" id="reportInformationId_${ information.reportInformationId }" name="reportInformationId" value="${ information.reportInformationId }">
	                	<input type="hidden" name="fileGroupId" value="${ information.fileGroupId }">
	                	<div class="new">
	                    <div class="tit new-top">이미지 등록</div>
	                    <div class="container-6">
	                    	<div id="image_start_div" style="display: inline-block;">
	                        <c:forEach items="${information.fileList }" var="vo" varStatus="fileStatus">
	                        <div class="new-img" style="background-repeat: no-repeat; background-size: cover; background-position: center center;<c:if test="${not empty vo.url }">background-image: url(${vo.url });</c:if><c:if test="${empty vo.url }">display: none;</c:if>">
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
                        	<div class="new-img-none" style="display: none;">
	                            <img src="<%=path%>/assets/vectors/image.svg">
	                            <div class="new-img-txt">
									클릭하여 이미지를 등록해주세요.<br>
	                                (최대 4장)<br>
	                                (png, jpg, jpeg만 등록 가능합니다.)
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="new">
	                    <div class="tit">제목</div>
	                    <div class="container-6">
	                        <div class="selectEnroll">
	                            <div class="search-box">
	                                <input class="search-txt" type="text" id="title" name="title" placeholder="제목을 신규로 등록합니다(최대 50글자)" value="${ information.title }"/>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="new">
	                    <div class="tit new-top">내용</div>
	                    <textarea class="textarea" placeholder="내용을 입력해주세요.(최대 500글자)" id="content" name="content" value="">${ information.content }</textarea>
	                </div>
	                <div class="new">
	                    <div class="new-but-box">
	                        <div class="but3" id="delete_${ information.reportInformationId }" onclick="deleteInformation(${ information.reportInformationId })">삭제</div>
	                        <c:if test="${empty information.commonInformationId}">
						        <div class="but2" id="update_${ information.reportInformationId }" onclick="updateInformation(${ information.reportInformationId })">수정</div>
						    </c:if>
	                    </div>
	                </div>
	                <div class="divider_no_width"></div>
	                </div>
	                </c:forEach>
                </div>
                
                <div class="loadInformation" id="newInformation">
                	<input type="hidden" id="newReportId" name="reportId" value="${ reportInformation.view.reportId }" />
                	<input type="hidden" id="isIssued" name="isIssued" value="N" />
	                <div class="new">
	                    <div class="tit new-top">이미지 등록</div>
	                    <div class="container-6" id="newImageLine">
	                    	<div id="image_start_div" style="display: inline-block;"></div>
	                        <div class="new-img-none">
	                        	<div class="img-div-line">
		                            <img src="<%=path%>/assets/vectors/image.svg" />
		                            <div class="new-img-txt">
		                                클릭하여 이미지를 등록해주세요.<br />
		                                (최대 4장)<br />
		                                (png, jpg, jpeg만 등록 가능합니다.)
		                            </div>
	                            </div>
	                            <input type="file" style="display:none;" />
	                        </div>
	                    </div>
	                </div>
	                <div class="new">
	                    <div class="tit info-mid">제목</div>
	                    <div class="container-6">
	                        <%-- <div class="selectBoxList">
	                            <!-- selectBox1 -->
	                            <div class="box">
	                                <div class="selectBox3 search-box-red">
	                                    <select class="label select_renewal" id="faqTitleId" name="faqTitleId">
	                                    	<option value="">제목 선택</option>
	                                    	<c:forEach var="faq" items="${faqList}">
										        <option value="${faq.title}">${faq.title}</option>
										    </c:forEach>
	                                    </select>
	                                </div>
	                            </div>
	                        </div> --%>
	                        <!-- <span>or </span> -->
	                        <div class="selectEnroll">
	                            <div class="search-box search-box-red" action="" method="get">
	                                <input class="search-txt" type="text" placeholder="제목을 신규로 등록합니다(최대 50글자)" id="newTitle" name="title" value="" />
	                            </div>
	                        </div>
	                    </div>
	                </div>
	
	                <div class="new">
	                    <div class="tit new-top">내용</div>
	                    <textarea class="textarea" placeholder="내용을 입력해주세요.(최대 500글자)" id="newContent" name="content" value=""></textarea>
	                </div>
                </div>
                <div class="new">
                    <div class="center">
                        <div class="but2" id="saveAndRowAddBtn">
                            <span class="container-66">
                                추가 등록
                            </span>
                            <span class="icoutline-plus">
                                <img class="vector" src="<%=path%>/assets/vectors/vector_210_x2.svg" />
                            </span>
                        </div>
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
                        <button class="but2" id="issueBtn" style="border:0px;">저장 & 리포트 발행</button>
                    </div>
                </div>
            </div>

        </div>
        <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
    </div>
</body>
</html>
