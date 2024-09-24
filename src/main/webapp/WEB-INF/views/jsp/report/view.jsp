<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.jyes.www.vo.common.InformationVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	pageContext.setAttribute("crcn", "\r\n"); //Enter PC
	pageContext.setAttribute("cn", "\n"); //Enter APP
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<title>레포트</title>
    <link href="<%=path%>/css/report.css" rel="stylesheet" />
    <!-- Link Swiper's CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link href="<%=path%>/css/cms_report.css" rel="stylesheet" />
    <style type="text/css">
    .cms-report .img-71801 {
    	background: none;
	    width: 350px;
	    height: auto;
	}
	.cms-report .container-7 {
	    overflow-wrap: break-word;
	    font-family: 'Pretendard', 'Roboto Condensed';
	    font-weight: 400;
	    font-size: 16px;
	    line-height: 1.429;
	    color: rgba(0, 0, 0, 0.7);
	}
	.cms-report .container-31 {
	    overflow-wrap: break-word;
	    font-family: 'Pretendard', 'Roboto Condensed';
	    font-weight: 400;
	    font-size: 16px;
	    line-height: 1.857;
	    color: #242424;
	}
    </style>
    <!-- jQuery 포함 -->
    <script src="<%=path%>/js/jquery-1.12.4.min.js"></script>
    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script type="text/javascript">
    function dis() {
        if ($('#dis').css('display') == 'none') {
            $('#dis').show();
        } else {
            $('#dis').hide();
        }
    }
	
	$(document).ready(function() {
		// Initialize Swiper
	    var swiper = new Swiper(".mySwiper", {
	        slidesPerView: "auto",
	        spaceBetween: 30,
	        pagination: {
	            el: ".swiper-pagination",
	            clickable: true,
	        },
	    });
	    
	    console.log("view in");
	});
	</script>
</head>
<body>
	<c:choose>
    <c:when test="${ report.isExposed == 'Y' or param.isPreview == 'Y' }">
    <!-- is_exposed 값이 "Y"일 때 -->
    <div class=""></div>
    <div class="cms-report">
        <div class="status-bar">
            <div class="left-side">
                <svg xmlns="http://www.w3.org/2000/svg" width="55" height="21" viewBox="0 0 55 21" fill="none">
                    <path d="M16.8203 16.2563C19.5083 16.2563 21.105 14.1543 21.105 10.5947C21.105 9.25439 20.8486 8.12646 20.3579 7.25488C19.6475 5.8999 18.4243 5.16748 16.8789 5.16748C14.5791 5.16748 12.9531 6.71289 12.9531 8.88086C12.9531 10.917 14.418 12.3965 16.4321 12.3965C17.6699 12.3965 18.6733 11.8179 19.1714 10.8145H19.1934C19.1934 10.8145 19.2227 10.8145 19.23 10.8145C19.2446 10.8145 19.2959 10.8145 19.2959 10.8145C19.2959 13.2314 18.3804 14.6743 16.835 14.6743C15.9268 14.6743 15.2236 14.1763 14.9819 13.3779H13.0996C13.4146 15.1138 14.8867 16.2563 16.8203 16.2563ZM16.8862 10.895C15.6704 10.895 14.8062 10.0308 14.8062 8.82227C14.8062 7.64307 15.7144 6.74219 16.8936 6.74219C18.0728 6.74219 18.981 7.65771 18.981 8.85156C18.981 10.0308 18.0947 10.895 16.8862 10.895Z" fill="white" />
                    <path d="M24.1961 16.1538C24.8919 16.1538 25.368 15.6558 25.368 14.9966C25.368 14.3301 24.8919 13.8394 24.1961 13.8394C23.5076 13.8394 23.0242 14.3301 23.0242 14.9966C23.0242 15.6558 23.5076 16.1538 24.1961 16.1538ZM24.1961 10.6606C24.8919 10.6606 25.368 10.1699 25.368 9.51074C25.368 8.84424 24.8919 8.35352 24.1961 8.35352C23.5076 8.35352 23.0242 8.84424 23.0242 9.51074C23.0242 10.1699 23.5076 10.6606 24.1961 10.6606Z" fill="white" />
                    <path d="M32.2237 16H34.0328V14.0298H35.461V12.4331H34.0328V5.43115H31.3668C29.4991 8.24365 28.0123 10.5947 27.0602 12.3452V14.0298H32.2237V16ZM28.8106 12.3672C30.0411 10.1992 31.1397 8.46338 32.1505 6.96924H32.253V12.4771H28.8106V12.3672Z" fill="white" />
                    <path d="M39.4896 16H41.3793V5.43115H39.497L36.7357 7.36475V9.18115L39.3651 7.33545H39.4896V16Z" fill="white" />
                </svg>
            </div>
            <div class="right-side">
                <svg xmlns="http://www.w3.org/2000/svg" width="67" height="12" viewBox="0 0 67 12" fill="none">
                    <path opacity="0.35" d="M43 2.99992C43 1.8033 43.97 0.833252 45.1667 0.833252H61.8333C63.03 0.833252 64 1.8033 64 2.99992V8.99992C64 10.1965 63.03 11.1666 61.8333 11.1666H45.1667C43.97 11.1666 43 10.1965 43 8.99992V2.99992Z" stroke="white" />
                    <path opacity="0.4" d="M65.5 4V8C66.3047 7.66122 66.828 6.87313 66.828 6C66.828 5.12687 66.3047 4.33878 65.5 4Z" fill="white" />
                    <path d="M44.5 3.66659C44.5 2.93021 45.097 2.33325 45.8333 2.33325H61.1667C61.903 2.33325 62.5 2.93021 62.5 3.66659V8.33325C62.5 9.06963 61.903 9.66659 61.1667 9.66659H45.8333C45.097 9.66659 44.5 9.06963 44.5 8.33325V3.66659Z" fill="white" />
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M29.8309 2.60789C32.0468 2.60799 34.178 3.45943 35.7839 4.98623C35.9049 5.1041 36.0982 5.10261 36.2173 4.98289L37.3733 3.81623C37.4336 3.7555 37.4672 3.67325 37.4667 3.58767C37.4662 3.50209 37.4316 3.42024 37.3706 3.36023C33.1555 -0.67932 26.5057 -0.67932 22.2906 3.36023C22.2295 3.4202 22.1949 3.50202 22.1943 3.5876C22.1938 3.67318 22.2273 3.75546 22.2876 3.81623L23.4439 4.98289C23.563 5.1028 23.7564 5.10428 23.8773 4.98623C25.4834 3.45933 27.6148 2.60789 29.8309 2.60789ZM29.8309 6.40356C31.0484 6.40349 32.2225 6.85602 33.1249 7.67323C33.247 7.78921 33.4393 7.78669 33.5583 7.66756L34.7129 6.50089C34.7737 6.4397 34.8075 6.35668 34.8066 6.27042C34.8057 6.18415 34.7703 6.10184 34.7083 6.04189C31.9601 3.48551 27.7041 3.48551 24.9559 6.04189C24.8939 6.10184 24.8584 6.18419 24.8576 6.27049C24.8568 6.35678 24.8907 6.43979 24.9516 6.50089L26.1059 7.66756C26.2249 7.78669 26.4172 7.78921 26.5393 7.67323C27.4411 6.85656 28.6142 6.40406 29.8309 6.40356ZM32.1439 8.95738C32.1457 9.04388 32.1117 9.12729 32.0499 9.18789L30.0526 11.2036C29.9941 11.2628 29.9142 11.2961 29.8309 11.2961C29.7476 11.2961 29.6678 11.2628 29.6093 11.2036L27.6116 9.18789C27.5499 9.12724 27.5159 9.04381 27.5178 8.95731C27.5196 8.8708 27.557 8.78888 27.6213 8.73089C28.8968 7.65201 30.765 7.65201 32.0406 8.73089C32.1048 8.78893 32.1422 8.87087 32.1439 8.95738Z" fill="white" />
                    <path fill-rule="evenodd" clip-rule="evenodd" d="M16.167 0.666504H15.167C14.6147 0.666504 14.167 1.11422 14.167 1.6665V10.3332C14.167 10.8855 14.6147 11.3332 15.167 11.3332H16.167C16.7193 11.3332 17.167 10.8855 17.167 10.3332V1.6665C17.167 1.11422 16.7193 0.666504 16.167 0.666504ZM10.5003 2.99984H11.5003C12.0526 2.99984 12.5003 3.44755 12.5003 3.99984V10.3332C12.5003 10.8855 12.0526 11.3332 11.5003 11.3332H10.5003C9.94805 11.3332 9.50034 10.8855 9.50034 10.3332V3.99984C9.50034 3.44755 9.94805 2.99984 10.5003 2.99984ZM6.83365 5.33317H5.83365C5.28136 5.33317 4.83365 5.78089 4.83365 6.33317V10.3332C4.83365 10.8855 5.28136 11.3332 5.83365 11.3332H6.83365C7.38593 11.3332 7.83365 10.8855 7.83365 10.3332V6.33317C7.83365 5.78089 7.38593 5.33317 6.83365 5.33317ZM2.16699 7.33317H1.16699C0.614707 7.33317 0.166992 7.78089 0.166992 8.33317V10.3332C0.166992 10.8855 0.614707 11.3332 1.16699 11.3332H2.16699C2.71928 11.3332 3.16699 10.8855 3.16699 10.3332V8.33317C3.16699 7.78089 2.71928 7.33317 2.16699 7.33317Z" fill="white" />
                </svg>
            </div>
        </div>
        <div class="container-2">
            <div class="container-3" <c:if test="${not empty reportAdditional.fileList and reportAdditional.fileList.size() > 0}">style="background:url('${ reportAdditional.fileList[0].url }');  background-repeat: no-repeat; background-size: 100% 100%; background-position: center center;"</c:if>>
                <div class="container">
                    <div class="container-1">
                        <div class="weather">
                            <div class="f-7-cloud-rain-fill">
                                <img class="vector" src="<%=path%>/assets/vectors/vector_400_x2.svg" />
                            </div>
                            <span class="dust">
                                23℃ / <br />
                                미세먼지 좋음
                            </span>
                        </div>
                    </div>
                    <div class="report-tit">
                        <div class="title">
                            ${ report.name }
                        </div>
                        <div class="add">
                            ${ reportAdditional.addr } ${ reportAdditional.addrDetail }
                        </div>
                    </div>
                </div>
                <div class="rectangle-956659">
                </div>
            </div>
            <div class="top-block">
                <div class="advise" style="margin: 2px 6px 6px 0;">
                    <div class="vector-1">
                    </div>
                    <div class="tip-icon">
                        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" fill="none">
                            <path d="M19.1666 8.10416L18.4166 7.40832C18.2334 7.23336 18.0917 7.02916 17.9958 6.79584L17.9166 6.61248C17.8249 6.38332 17.7792 6.12916 17.7875 5.8792L17.8208 4.85836C17.8458 4.13748 17.5917 3.46249 17.1042 2.95835C16.6166 2.45417 15.9542 2.17918 15.2375 2.17918L14.1208 2.21249L14.0583 2.21668C13.8292 2.21668 13.6 2.17085 13.3875 2.08334L13.2042 2.00834C12.9708 1.90834 12.7666 1.76669 12.5917 1.58335L11.8958 0.833332C11.3916 0.295843 10.7208 0 10 0C9.28332 0 8.60832 0.295843 8.10832 0.833332L7.40832 1.58335C7.23748 1.76669 7.02916 1.90834 6.79584 2.00834L6.61668 2.08334C6.4 2.17085 6.175 2.21668 5.94164 2.21668L4.76248 2.17918C4.05 2.17918 3.38751 2.45417 2.89998 2.95835C2.41249 3.46249 2.15415 4.13748 2.17915 4.85836L2.21668 5.8792C2.22498 6.13336 2.17915 6.37916 2.08334 6.61248L2.00834 6.79584C1.91249 7.02916 1.76666 7.23336 1.58332 7.40832L0.833332 8.10416C0.295817 8.60836 0 9.27916 0 10C0 10.7208 0.295817 11.3917 0.833332 11.8917L1.58332 12.5917C1.76666 12.7625 1.91249 12.975 2.00834 13.2042L2.08334 13.3833C2.17915 13.6208 2.22498 13.8667 2.21668 14.1167L2.17915 15.1417C2.15415 15.8583 2.41249 16.5334 2.89998 17.0417C3.38751 17.5458 4.05 17.8208 4.76668 17.8208L5.88332 17.7834H5.94164C6.175 17.7834 6.4 17.8292 6.61668 17.9166L6.79584 17.9917C7.02916 18.0917 7.23332 18.2334 7.40832 18.4166L8.10832 19.1666C8.60832 19.7042 9.28332 20 10 20C10.7208 20 11.3916 19.7042 11.8958 19.1666L12.5917 18.4166C12.7666 18.2334 12.9708 18.0917 13.2042 17.9917L13.3875 17.9166C13.6 17.8292 13.825 17.7834 14.0583 17.7834L15.2375 17.8208C15.9542 17.8208 16.6166 17.5458 17.1042 17.0375C17.5917 16.5334 17.8458 15.8583 17.8208 15.1417L17.7875 14.1167C17.7792 13.8708 17.8249 13.6167 17.9166 13.3875L17.9958 13.2042C18.0875 12.975 18.2375 12.7625 18.4166 12.5917L19.1666 11.8917C19.7042 11.3917 20 10.7208 20 10C20 9.27916 19.7042 8.60836 19.1666 8.10416ZM10 16.6208C6.35 16.6208 3.37915 13.65 3.37915 10C3.37915 6.34584 6.35 3.37502 10 3.37502C13.6542 3.37502 16.6249 6.34584 16.6249 10C16.6249 13.65 13.6542 16.6208 10 16.6208Z" fill="#FBC645" />
                        </svg>
                    </div>
                    <span class="tip">
                        tip
                    </span>
                </div>
                <div class="container">
                    <div style="font-feature-settings: 'case' on; font-family: Pretendard; font-size: 18px; font-style: normal; font-weight: 400; line-height: 24px;">
                    	${fn:replace(fn:replace(reportAdditional.onlineUrl, crcn, '<br/>'), cn, '<br/>')}
                    </div>
                </div>
                <%-- <div class="container">
                    ${ reportDetail.gptsStrategy }
                </div> --%>
            </div>
            <c:forEach var="reportCommonInformation" items="${reportCommonInformaionList}">
                  	<c:choose>
                  		<c:when test="${reportCommonInformation.fileList != null && reportCommonInformation.fileList.size() > 1}">
                  		<div class="news" style="margin-bottom: 8px; min-height: 276px; height: auto;">
			                <div class="contents-header">
			                    <div class="container-28">
			                        <span class="container-29">
			                            ${ reportCommonInformation.title }
			                        </span>
			                    </div>
			                    <c:if test="${ reportCommonInformation.content != null }">
			                    	<div class="container-30">
				                        <span class="container-31">
				                        	${fn:replace(fn:replace(reportCommonInformation.content, crcn, '<br/>'), cn, '<br/>')}
				                        </span>
			                    	</div>
			                    </c:if>
			                </div>
			                <!-- Swiper -->
			                <div class="swiper mySwiper">
			                    <div class="swiper-wrapper">
			                    	<c:forEach var="reportCommonInformationFile" items="${reportCommonInformation.fileList}">
				                        <div class="swiper-slide"><div class="rectangle-956580" style="background:url('${reportCommonInformationFile.url}'); background-repeat: no-repeat; background-size: 100% 100%; background-position: center center;"></div></div>
			                        </c:forEach>
			                    </div>
			                    <div class="swiper-pagination"></div>
			                </div>
			            </div>
                  		</c:when>
                  		<c:when test="${reportCommonInformation.fileList != null && reportCommonInformation.fileList.size() == 1}">
				        <div class="block">
			                <div class="tit">
			                    <span class="container-6">
			                        ${ reportCommonInformation.title }
			                    </span>
			                </div>
			                <c:if test="${ not empty reportCommonInformation.content }">
		                    	<div class="sub">
				                    <span class="container-7">
				                        ${fn:replace(fn:replace(reportCommonInformation.content, crcn, '<br/>'), cn, '<br/>')}
				                    </span>
				                </div>
	                    	</c:if>
			                <div class="img-71801"><img width="100%" src="${reportCommonInformation.fileList[0].url}"></div>
			            </div>
				    	</c:when>
					    <c:otherwise>
					        <div class="card">
				                <div class="container-4">
				                    ${ reportCommonInformation.title }
				                </div>
				                <div class="container-5">
				                	${fn:replace(fn:replace(reportCommonInformation.content, crcn, '<br/>'), cn, '<br/>')}
				                </div>
				            </div>
					    </c:otherwise>
                  	</c:choose>
            </c:forEach>
            <c:forEach var="reportNonCommonInformaion" items="${reportNonCommonInformaionList}">
                  	<c:choose>
                  		<c:when test="${reportNonCommonInformaion.fileList != null && reportNonCommonInformaion.fileList.size() > 1}">
	                   		<div class="news" style="margin-bottom: 8px; min-height: 276px; height: auto;">
				                <div class="contents-header">
				                    <div class="container-28">
				                        <span class="container-29">
				                        	${ reportNonCommonInformaion.title }
				                        </span>
				                    </div>
				                    <c:if test="${ reportNonCommonInformaion.content != null }">
				                    	<div class="container-30">
					                        <span class="container-31">
					                        	${fn:replace(fn:replace(reportNonCommonInformaion.content, crcn, '<br/>'), cn, '<br/>')}
					                        </span>
				                    	</div>
			                    	</c:if>
				                </div>
				                <!-- Swiper -->
				                <div class="swiper mySwiper">
				                    <div class="swiper-wrapper">
					                    <c:forEach var="reportNonCommonInformaionFile" items="${reportNonCommonInformaion.fileList}">
					                        <div class="swiper-slide"><div class="rectangle-956580" style="background:url(${reportNonCommonInformaionFile.url}); background-repeat: no-repeat; background-size: 100% 100%; background-position: center center;"></div></div>
				                        </c:forEach>
				                    </div>
				                    <div class="swiper-pagination"></div>
				                </div>
				            </div>
                  		</c:when>
                  		<c:when test="${reportNonCommonInformaion.fileList != null && reportNonCommonInformaion.fileList.size() == 1}">
					        <div class="block">
				                <div class="tit">
				                    <span class="container-6">
				                        ${ reportNonCommonInformaion.title }
				                    </span>
				                </div>
				                <c:if test="${ not empty reportNonCommonInformaion.content }">
			                    	<div class="sub">
					                    <span class="container-7">
					                    	${fn:replace(fn:replace(reportNonCommonInformaion.content, crcn, '<br/>'), cn, '<br/>')}
					                    </span>
					                </div>
	                    		</c:if>
				                <div class="img-71801"><img width="100%" src="${reportNonCommonInformaion.fileList[0].url}"></div>
				            </div>
				    	</c:when>
					    <c:otherwise>
					        <div class="card">
				                <div class="container-4">
				                    ${ reportNonCommonInformaion.title }
				                </div>
				                <div class="container-5">
				                	${fn:replace(fn:replace(reportNonCommonInformaion.content, crcn, '<br/>'), cn, '<br/>')}
				                </div>
				            </div>
					    </c:otherwise>
                  	</c:choose>
            </c:forEach>
            
            <div class="footer">
                <div class="frame-1261152410">
                    <span class="wiz-market">
                        WIZ MARKET
                    </span>
                </div>
                <div class="frame-1261152409">
                    <span class="container-15">
                        이용 요금
                    </span>
                    <div class="line-33">
                    </div>
                    <span class="container-16">
                        프로필 수정
                    </span>
                    <div class="line-34">
                    </div>
                    <span class="container-17">
                        정보설정
                    </span>
                    <div class="line-35">
                    </div>
                    <span class="container-18">
                        1:1문의
                    </span>
                </div>
            </div>
        </div>
    </div>
    </c:when>
    <c:when test="${ report.isExposed == 'N' }">
    	  <!-- is_exposed 값이 "N"일 때 -->
    	   <div>
            <h1>이 페이지는 접근할 수 없습니다.</h1>
        </div>
    </c:when>
    <c:otherwise>
    	<!-- is_exposed가 "Y" 또는 "N"이 아닐 때 처리할 부분 (optional) -->
    	<div>
            <h1>잘못된 요청입니다.</h1>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>