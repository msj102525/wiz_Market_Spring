<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jyes.www.vo.common.InformationVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	InformationVo informationVo = (InformationVo) request.getAttribute("informationVo");
	String regDateStr = "";
	Date regDate = null;
	if(informationVo!=null&&informationVo.getRegDate()!=null&&!"".equals(informationVo.getRegDate())) {
		regDateStr = informationVo.getRegDate(); // String 형식의 날짜
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		    regDate = inputFormat.parse(regDateStr);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="nowDate" value="<%=new java.util.Date()%>" />
<c:set var="nowDate"><fmt:formatDate value="${nowDate}" pattern="yyyy-MM-dd HH:mm" /></c:set>
<c:set var="regDate" value="<%=regDate %>" />
<c:set var="regDate"><fmt:formatDate value="${regDate}" pattern="yyyy-MM-dd HH:mm" /></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레포트공통정보</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/jquery.Jcrop.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/tab.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/areport-mvp_style.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.jcrop.common.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript">
//이미지 세팅 시작
var image_create_count = 4; // 이미지 추가 개수
//크롭 세팅 ====
var form_selecter_name = "#searchFrm";
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
	infoImgTag = '<div class="info-img" style="background-repeat: no-repeat; background-size: 100% 100%; background-position: center center;background-image: url('+data+');">';
	infoImgTag += '<div class="info-img-use">';
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
	    $(this).find('.info-img').each(function(index) {
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
		if($(this).find(".info-img").length>=image_create_count) {
			$(this).find(".info-img-none").hide();
		} else {
			$(this).find(".info-img-none").show();
		}
		$(this).find(".info-img-none").off('click').on('click', function() {
			jCrop.fileUploadAction(index);
        });
	});
}
//크롭 이미지 콜백 함수
function fileCropCallback(data) {
	//data 크롭 이미지 데이터
	//크롭 이미지 데이터 UI 세팅
	$('#slideTogglebox_'+jCrop.file_index+' '+'.info-img').last().css({
        'background-image': 'url(' + data + ')',
        'background-repeat': 'no-repeat',
        'background-size': '100% 100%',
        'background-position': 'center center'
    });
}
//이미지 삭제
function delImage(parant_index, num, fileId) {
	var addCnt = 0;
	
	$("[id='"+form_selecter_name.slice(1)+"']").eq(parant_index).find('.info-img').each(function(index) {
		var dataValue = $(this).find('svg').attr("data-value");
		if (dataValue !== undefined && dataValue !== null && dataValue.trim() !== '') {
			addCnt++;
		}
	});
	
	//선택된 이미지 삭제
	$("[id='"+form_selecter_name.slice(1)+"']").eq(parant_index).find('.info-img').each(function(index) {
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
		var fileIdHtml = '<input type="hidden" name="fileId" value="'+fileId+'">';
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
	$('#title_temp').on('input', function() {
		setSaveBtn();
    });
	setSaveBtn();
});
function setSaveBtn() {
	if ($('#title_temp').val().trim() !== '') {
    	$('#btn_save').addClass('but2');
    	$('#btn_save').removeClass('but1');
        // 값이 있으면 버튼 활성화
        $('#btn_save').off("click").on("click",save);
    } else {
    	$('#btn_save').addClass('but1');
    	$('#btn_save').removeClass('but2');
        // 값이 없으면 버튼 비활성화
        $('#btn_save').off("click");
    }
}
//저장
function save() {
	var title_temp = $("#title_temp").val();
	var faq_title = $("#faqTitleHtml").attr("data-value");
	var title = (faq_title=="")?title_temp:faq_title;
	if(title==="") {
		alert("제목 입력")
		return;
	}
	$("#title").val(title);
	var form = $('#searchFrm');
	var fields = form.find('input,textarea');
	var formData = collectFormData(fields);
	$.ajax({
		url  : "<%=path %>/common/save/information/saveDataAjax",
		type : 'POST',
		data : formData,
		async : true,
		contentType : false,//formData 전송시 필수
		processData : false,//formData 전송시 필수
		dataType : "json",
		success : function(data) {
			alert("완료");
			var searchKeyword = $("#searchKeyword").val();
			var curpage = $("#curpage").val();
			var blockCount = $("#blockCount").val();
			moveUrl('<%=path%>/common/list/information/view?curpage='+curpage+'&blockCount='+blockCount+'&searchKeyword='+searchKeyword,'');
		}
	});
}
</script>
</head>
<body>
<form name="searchFrm" id="searchFrm" method="GET">
<!-- 공통정보 목록 파라미터 -->
<input type="hidden" name="searchKeyword" id="searchKeyword" value="<c:out value="${searchKeyword }" />">
<input type="hidden" name="curpage" id="curpage" value="<c:out value="${curpage }" />">
<input type="hidden" name="blockCount" id="blockCount" value="<c:out value="${blockCount }" />">

<!-- 공통정보 상세 페이지 파라미터 -->
<input type="hidden" name="commonInformationId" id="commonInformationId" value="<c:out value="${informationVo.commonInformationId}" />">
<input type="hidden" name="fileGroupId" id="fileGroupId" value="<c:out value="${informationVo.fileGroupId}" />">
<input type="hidden" name="title" id="title" value="<c:out value="${informationVo.title}" />">

<!-- <input type="hidden" name="fileId"> -->

<div id="crop_input_elements" style="display: none;"></div>

<div class="areport-mvp-none">
    <div class="container">
    	
        <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
        
        <div class="divider"></div>
        
        <div class="tab">
            
            <ul class="tabnav title">
                <li><a href="javascript:;" onclick="javascript:moveUrl('<%=path%>/common/information','');return false;" <c:if test="${topCode eq '0' or empty topCode }">class="active"</c:if>>공통정보 등록</a></li>
                <li><a href="javascript:;" onclick="javascript:moveUrl('<%=path%>/common/list/information/view','');return false;" <c:if test="${topCode eq '1' }">class="active"</c:if>>공통정보 목록</a></li>
            </ul>
            
            <div class="tabcontent">
                <div id="tab01" style="">
                
                    <div class="info">
                        <div class="tit">입력일 버전</div>
                        <c:choose>
                        <c:when test="${not empty informationVo.regDate}"><span class="info-day"><c:out value="${regDate }" /></span></c:when>
				        <c:otherwise><span class="info-day"><c:out value="${nowDate }" /></span></c:otherwise>
					    </c:choose>
                    </div>
                    <div class="info">
                        <div class="tit">이미지 등록</div>
                        
                        <!-- 이미지 세팅 -->
                        <div id="image_start_div" style="display: inline-block;">
	                        <c:forEach items="${fileVoList }" var="vo" varStatus="status">
	                        <div class="info-img" style="background-repeat: no-repeat; background-size: 100% 100%; background-position: center center;<c:if test="${not empty vo.url }">background-image: url(${vo.url });</c:if><c:if test="${empty vo.url }">display: none;</c:if>">
	                            <div class="info-img-use">
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
                        
                        <div class="info-img-none" style="display: none;">
                            <img src="<%=path%>/assets/vectors/image.svg">
                            <div class="info-img-txt">
								클릭하여 이미지를 등록해주세요.<br>
                                (최대 4장)<br>
                                (png, jpg, jpeg만 등록 가능합니다.)
                            </div>
                        </div>
                        
                    </div>
                    
                    <div class="info">
                        <div class="tit info-mid">제목</div>
                        <div class="container-6">
                            <div class="selectBoxList" style="display: none;">
                                <!-- selectBox1 -->
                                <div class="box">
                                    <div class="selectBox2 ">
                                        <button type="button" onclick="javascript:;" class="label">자주 사용하는 질문</button>
                                        <ul class="optionList" id="faqTitleHtml" data-value=""></ul>
                                    </div>
                                </div>
                            </div>
                            <span style="display: none;">or </span>
                            <div class="selectEnroll">
                                <input class="search-txt" style="background: #fff; float: none; padding: 10px;border-radius: 4px; border: 1px solid var(--line, #E4E4E4);" type="text" name="title_temp" id="title_temp" value="<c:out value="${informationVo.title}" />" placeholder="제목을 신규로 등록합니다(최대 50글자)">
                                <div class="new-but" style="display: none;">
                                    <span class="container-66" onclick="javascript:addFaqTitle();return false;">자주 사용하는 질문 추가</span>
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none">
                                        <path d="M19 12.998H13V18.998H11V12.998H5V10.998H11V4.99805H13V10.998H19V12.998Z" fill="white"></path>
                                    </svg>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="info">
                        <div class="tit">내용</div>
                        <textarea class="textarea" name="content" id="content" placeholder="내용을 입력해주세요.(최대 500글자)"><c:out value="${informationVo.content}" /></textarea>
                    </div>
                    
                    <div class="info">
                        <div class="info-but">
                            <div id="btn_del" class="but1" onclick="javascript:moveUrl('<%=path %>'+window.location.pathname+window.location.search,'Y');return false;">삭제</div>
                            <div id="btn_save" class="but1" onclick="javascript:return false;">저장</div>
                        </div>
                    </div>
                    
                </div>
			</div>
        </div><!--tab-->
    </div>
    
    <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />

</div>


</form>
</body>
</html>