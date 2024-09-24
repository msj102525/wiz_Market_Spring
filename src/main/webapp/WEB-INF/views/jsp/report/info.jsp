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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css" />
    <link href="<%=path%>/css/cms_report.css" rel="stylesheet" />
    <link href="<%=path%>/css/report.css" rel="stylesheet" />
    <link rel="stylesheet" as="style" crossorigin href="https://cdn.jsdelivr.net/gh/orioncactus/pretendard@v1.3.9/dist/web/variable/pretendardvariable.min.css" />
    <script src="<%=path%>/js/jquery-1.12.4.min.js"></script>
    <!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
    <script>
        $(document).ready(function() {
        	
        	$("#updateBtn").on("click", function(event){
        		event.preventDefault(); // 폼 제출을 막음
        		
        		var reportId = $("#reportId").val();
                window.location.href = '/report/save/additional?reportId=' + reportId;
        	});
        	
        	$("#deleteBtn").on("click", function(event){
        		event.preventDefault();
        		
        		deleteReport();
        		
        	});
        	
        	$("#goBackBtn").on("click", function(event){
        		event.preventDefault(); // 폼 제출을 막음
        		
        		window.location.href = "/report/list";
        	});
        	
        	<!-- Initialize Swiper -->
       		var swiper = new Swiper(".mySwiper", {
       			slidesPerView : "auto",
       			spaceBetween : 30,
       			pagination : {
       				el : ".swiper-pagination",
       				clickable : true,
       			},
       		});         	
        });
        
        function deleteSuccessCallback(response){
        	// 문자열인 경우 JSON으로 파싱
            response = JSON.parse(response);
            
            if (response.msg == "0000") {
            	console.log(response.data);
            	if(response.data > 0){
            		alert("리포트 삭제 되었습니다.");
            		
            		window.location.href = "/report/list";
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
        
        function deleteReport(){
        	var reportId = $("#reportId").val();
			
        	var url = "/report/delete";
        	var jsonItem = {
       			reportId : reportId
        	}
        	
        	ajaxJSONRequest(url, jsonItem, deleteSuccessCallback, errorCallback);
        }
    </script>
</head>
<body>
	<input type="hidden" id="reportId" name="reportId" value="${ report.reportId }" />
	<div class="areport-mvp-none">
		<div class="container">
			<c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
			<div class="divider"></div>
			<div class="title">
				<div class="tit">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 16 16" fill="none">
                        <path d="M16 7H3.83L9.42 1.41L8 0L0 8L8 16L9.41 14.59L3.83 9H16V7Z" fill="black" />
                    </svg>
					<div>참조은병원</div>
					<div class="tit-vol">Vol.12</div>
					<div class="tit-day">2024-06-08 13:34</div>
				</div>
				<div class="add">
					<p>경기도 광주시 광주대로45</p>
				</div>
			</div>

			<div class="areport-mvp">
				<div class="container-3">
					<div class="cms-report">
						<div class="profile container">
							<div class="mobile-top">
								<div class="time">9:41</div>
								<div class="right-side">
									<svg xmlns="http://www.w3.org/2000/svg" width="68" height="12" viewBox="0 0 68 12" fill="none">
                                    <path opacity="0.35" d="M43.2764 2.99992C43.2764 1.8033 44.2464 0.833252 45.443 0.833252H62.1097C63.3063 0.833252 64.2764 1.8033 64.2764 2.99992V8.99992C64.2764 10.1965 63.3063 11.1666 62.1097 11.1666H45.443C44.2464 11.1666 43.2764 10.1965 43.2764 8.99992V2.99992Z" stroke="white" />
                                    <path opacity="0.4" d="M65.7764 4V8C66.5811 7.66122 67.1044 6.87313 67.1044 6C67.1044 5.12687 66.5811 4.33878 65.7764 4Z" fill="white" />
                                    <path d="M44.7764 3.66659C44.7764 2.93021 45.3733 2.33325 46.1097 2.33325H61.443C62.1794 2.33325 62.7764 2.93021 62.7764 3.66659V8.33325C62.7764 9.06963 62.1794 9.66659 61.443 9.66659H46.1097C45.3733 9.66659 44.7764 9.06963 44.7764 8.33325V3.66659Z" fill="white" />
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M30.1063 2.60789C32.3222 2.60799 34.4534 3.45943 36.0593 4.98623C36.1803 5.1041 36.3736 5.10261 36.4927 4.98289L37.6487 3.81623C37.709 3.7555 37.7426 3.67325 37.7421 3.58767C37.7416 3.50209 37.707 3.42024 37.646 3.36023C33.4309 -0.67932 26.7811 -0.67932 22.566 3.36023C22.5049 3.4202 22.4703 3.50202 22.4697 3.5876C22.4692 3.67318 22.5027 3.75546 22.563 3.81623L23.7193 4.98289C23.8384 5.1028 24.0318 5.10428 24.1527 4.98623C25.7588 3.45933 27.8902 2.60789 30.1063 2.60789ZM30.1063 6.40356C31.3238 6.40349 32.4979 6.85602 33.4003 7.67323C33.5224 7.78921 33.7147 7.78669 33.8337 7.66756L34.9883 6.50089C35.0491 6.4397 35.0829 6.35668 35.082 6.27042C35.0811 6.18415 35.0457 6.10184 34.9837 6.04189C32.2355 3.48551 27.9795 3.48551 25.2313 6.04189C25.1693 6.10184 25.1338 6.18419 25.133 6.27049C25.1322 6.35678 25.1661 6.43979 25.227 6.50089L26.3813 7.66756C26.5003 7.78669 26.6926 7.78921 26.8147 7.67323C27.7165 6.85656 28.8896 6.40406 30.1063 6.40356ZM32.4193 8.95738C32.4211 9.04389 32.3871 9.12729 32.3253 9.18789L30.328 11.2036C30.2694 11.2628 30.1896 11.2961 30.1063 11.2961C30.023 11.2961 29.9432 11.2628 29.8847 11.2036L27.887 9.18789C27.8253 9.12724 27.7913 9.04381 27.7932 8.95731C27.795 8.8708 27.8324 8.78888 27.8967 8.73089C29.1722 7.65201 31.0404 7.65201 32.316 8.73089C32.3802 8.78893 32.4176 8.87087 32.4193 8.95738Z" fill="white" />
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M16.4429 0.666504H15.4429C14.8906 0.666504 14.4429 1.11422 14.4429 1.6665V10.3332C14.4429 10.8855 14.8906 11.3332 15.4429 11.3332H16.4429C16.9952 11.3332 17.4429 10.8855 17.4429 10.3332V1.6665C17.4429 1.11422 16.9952 0.666504 16.4429 0.666504ZM10.7762 2.99984H11.7762C12.3285 2.99984 12.7762 3.44755 12.7762 3.99984V10.3332C12.7762 10.8855 12.3285 11.3332 11.7762 11.3332H10.7762C10.2239 11.3332 9.7762 10.8855 9.7762 10.3332V3.99984C9.7762 3.44755 10.2239 2.99984 10.7762 2.99984ZM7.10954 5.33317H6.10954C5.55725 5.33317 5.10954 5.78089 5.10954 6.33317V10.3332C5.10954 10.8855 5.55725 11.3332 6.10954 11.3332H7.10954C7.66182 11.3332 8.10954 10.8855 8.10954 10.3332V6.33317C8.10954 5.78089 7.66182 5.33317 7.10954 5.33317ZM2.44287 7.33317H1.44287C0.890586 7.33317 0.442871 7.78089 0.442871 8.33317V10.3332C0.442871 10.8855 0.890586 11.3332 1.44287 11.3332H2.44287C2.99516 11.3332 3.44287 10.8855 3.44287 10.3332V8.33317C3.44287 7.78089 2.99516 7.33317 2.44287 7.33317Z" fill="white" />
                                	</svg>
								</div>
							</div>
							
							<div class="weather">
								<div class="f-7-cloud-rain-fill">
									<img class="vector" src="assets/vectors/vector_400_x2.svg" />
								</div>
								<span class="dust"> 22℃ / <br /> 미세먼지 좋음
								</span>
							</div>

							<div class="report-tit">
								<div class="title">일차3.5숙성고기</div>
								<div class="add">서울시 영등포구 당산동 171-19 102호 드림시드아파트 상가</div>
							</div>
						</div>

						<div class="top-block">
							<div class="advise">
								<div class="vector-1"></div>
								<div class="tip-icon">
									<svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 20 20" fill="none">
                            			<path d="M19.1666 8.10416L18.4166 7.40832C18.2334 7.23336 18.0917 7.02916 17.9958 6.79584L17.9166 6.61248C17.8249 6.38332 17.7792 6.12916 17.7875 5.8792L17.8208 4.85836C17.8458 4.13748 17.5917 3.46249 17.1042 2.95835C16.6166 2.45417 15.9542 2.17918 15.2375 2.17918L14.1208 2.21249L14.0583 2.21668C13.8292 2.21668 13.6 2.17085 13.3875 2.08334L13.2042 2.00834C12.9708 1.90834 12.7666 1.76669 12.5917 1.58335L11.8958 0.833332C11.3916 0.295843 10.7208 0 10 0C9.28332 0 8.60832 0.295843 8.10832 0.833332L7.40832 1.58335C7.23748 1.76669 7.02916 1.90834 6.79584 2.00834L6.61668 2.08334C6.4 2.17085 6.175 2.21668 5.94164 2.21668L4.76248 2.17918C4.05 2.17918 3.38751 2.45417 2.89998 2.95835C2.41249 3.46249 2.15415 4.13748 2.17915 4.85836L2.21668 5.8792C2.22498 6.13336 2.17915 6.37916 2.08334 6.61248L2.00834 6.79584C1.91249 7.02916 1.76666 7.23336 1.58332 7.40832L0.833332 8.10416C0.295817 8.60836 0 9.27916 0 10C0 10.7208 0.295817 11.3917 0.833332 11.8917L1.58332 12.5917C1.76666 12.7625 1.91249 12.975 2.00834 13.2042L2.08334 13.3833C2.17915 13.6208 2.22498 13.8667 2.21668 14.1167L2.17915 15.1417C2.15415 15.8583 2.41249 16.5334 2.89998 17.0417C3.38751 17.5458 4.05 17.8208 4.76668 17.8208L5.88332 17.7834H5.94164C6.175 17.7834 6.4 17.8292 6.61668 17.9166L6.79584 17.9917C7.02916 18.0917 7.23332 18.2334 7.40832 18.4166L8.10832 19.1666C8.60832 19.7042 9.28332 20 10 20C10.7208 20 11.3916 19.7042 11.8958 19.1666L12.5917 18.4166C12.7666 18.2334 12.9708 18.0917 13.2042 17.9917L13.3875 17.9166C13.6 17.8292 13.825 17.7834 14.0583 17.7834L15.2375 17.8208C15.9542 17.8208 16.6166 17.5458 17.1042 17.0375C17.5917 16.5334 17.8458 15.8583 17.8208 15.1417L17.7875 14.1167C17.7792 13.8708 17.8249 13.6167 17.9166 13.3875L17.9958 13.2042C18.0875 12.975 18.2375 12.7625 18.4166 12.5917L19.1666 11.8917C19.7042 11.3917 20 10.7208 20 10C20 9.27916 19.7042 8.60836 19.1666 8.10416ZM10 16.6208C6.35 16.6208 3.37915 13.65 3.37915 10C3.37915 6.34584 6.35 3.37502 10 3.37502C13.6542 3.37502 16.6249 6.34584 16.6249 10C16.6249 13.65 13.6542 16.6208 10 16.6208Z" fill="#FBC645" />
                        			</svg>
								</div>
								<span class="tip"> tip </span>
							</div>
							<div class="container">
								<ul>
									<li>오늘은 비가 내립니다. ‘우산증정 이벤트'나 ‘무료 핫 음료'를 제공해보세요.</li>
									<li>‘비오는 날 일차3.5숙성고기' 라는 해시태그로 사진이나 리뷰를 인스타그램에 올리는 사람에게
										10%할인 이벤트를 해보세요.</li>
									<li>비오는 날에는 우산 거치대, 밀대를 준비해서 매장이 쾌적하게 유지되도록 해주세요.</li>
									<li>오늘은 손님이 많지 않을 수 있어요. 배달비 무료 서비스를 시간 한시적으로 해보세요. 많은
										사람들이 배달음식을 찾고있어요. <span>배민 바로가기 ></span>
									</li>
								</ul>
							</div>
						</div>

						<div class="block-2">
							<div class="frame-12611524141">
								<div class="container-20">입지분석</div>
								<div class="container-21">서울시 영등포구 당산2동</div>
								<div class="container-22">‘전자정부 상권정보&#39; 2024년 1월</div>
								<p class="container-23">
									<span class="container-23-sub">8.05 <span class="red">좋음</span></span>
								</p>
								<div class="container-24">전반적으로 사업하기 용이한 좋은 입지 조건을 지니고
									있습니다.</div>
								<div class="frame-12611524151">
									<p class="container-25">
										전국기준 = <span class="green">100%</span>
									</p>
									<div class="container-26">참고) 서초4동(강남역) = 9.03 / 10</div>
								</div>
							</div>
						</div>

						<div class="card">
							<div class="container-4">고객은 이 지역(당산동) 고깃집에서 이런걸 원해요!</div>
							<ul class="container-5">
								<li>독특한 메뉴</li>
								<li>특이하고 재미있는 서빙방식</li>
								<li>고기상태와 질</li>
								<li>친절하고 적극적인 직원</li>
								<li>가성비, 개성있는 인테리어</li>
							</ul>
						</div>

						<div class="block">
							<div class="tit">
								<span class="container-6"> 배달 이용 현황 </span>
							</div>
							<div class="sub">
								<span class="container-7"> 오늘은 배달주문이 보통인 날입니다. 배달앱 리뷰를 보며
									고객을 이해하세요. </span>
							</div>
							<div class="img-71801"></div>
						</div>

						<div class="news">
							<div class="contents-header">
								<div class="container-28">
									<span class="container-29"> 영등포동 유동인구 </span>
								</div>
								<div class="container-30">
									<span class="container-31"> 영등포동 유동인구는 354명/일 입니다. </span>
								</div>
							</div>
							<!-- Swiper -->
							<div class="swiper mySwiper">
								<div class="swiper-wrapper">
									<div class="swiper-slide">
										<div class="rectangle-956580"></div>
									</div>
									<div class="swiper-slide">
										<div class="rectangle-956580"></div>
									</div>
									<div class="swiper-slide">
										<div class="rectangle-956580"></div>
									</div>
									<div class="swiper-slide">
										<div class="rectangle-956580"></div>
									</div>
								</div>
								<div class="swiper-pagination"></div>
							</div>
						</div>

						<div class="block-2">
							<div class="frame-12611524141">
								<div class="container-20">상권분석</div>
								<div class="container-21">서울시 영등포구 당산2동</div>
								<div class="container-22">‘전자정부 상권정보&#39; 2024년 1월</div>
								<p class="container-23">
									<span class="container-23-sub">8.05 <span class="red">좋음</span></span>
								</p>
								<div class="container-24">전반적으로 사업하기 용이한 좋은 입지 조건을 지니고
									있습니다.</div>
								<div class="frame-12611524151">
									<p class="container-25">
										전국기준 = <span class="green">100%</span>
									</p>
									<div class="container-26">참고) 서초4동(강남역) = 9.03 / 10</div>
								</div>
							</div>
						</div>

						<div class="card-1">
							<div class="container-27">돼지고기구이, 갈비구이 순입니다.</div>
							<div class="image-116"></div>
						</div>

						<div class="news">
							<div class="contents-header">
								<div class="container-28">
									<span class="container-29"> 영등포동 유동인구 </span>
								</div>
								<div class="container-30">
									<span class="container-31"> 영등포동 유동인구는 354명/일 입니다. </span>
								</div>
							</div>
							<!-- Swiper -->
							<div class="swiper mySwiper">
								<div class="swiper-wrapper">
									<div class="swiper-slide">
										<div class="rectangle-956580"></div>
									</div>
									<div class="swiper-slide">
										<div class="rectangle-956580"></div>
									</div>
									<div class="swiper-slide">
										<div class="rectangle-956580"></div>
									</div>
									<div class="swiper-slide">
										<div class="rectangle-956580"></div>
									</div>
								</div>
								<div class="swiper-pagination"></div>
							</div>
						</div>
					</div>
					<div class="frame-1261152411">
						<div class="frame-1261152410">
							<span class="wiz-market"> WIZ MARKET </span>
						</div>
						<div class="frame-1261152409">
							<span class="container-104"> 이용 요금 </span>
							<div class="line-33"></div>
							<span class="container-105"> 프로필 수정 </span>
							<div class="line-34"></div>
							<span class="container-106"> 정보설정 </span>
							<div class="line-35"></div>
							<span class="container-107"> 1:1문의 </span>
						</div>
						<span class="container-108"> 서비스 시작일 : 2024-04-15 </span>
					</div>
				</div>

				<div class="but-box">
					<div class="but-box-w">
						<div class="but1" id="goBackBtn">
							<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 18 15" fill="none">
                               <path d="M1.38221 5.93265L0.691105 6.60566L0 5.93265L0.691105 5.25964L1.38221 5.93265ZM18 13.5481C18 13.8005 17.897 14.0427 17.7137 14.2212C17.5304 14.3997 17.2817 14.5 17.0225 14.5C16.7632 14.5 16.5146 14.3997 16.3313 14.2212C16.148 14.0427 16.045 13.8005 16.045 13.5481H18ZM5.57869 11.3653L0.691105 6.60566L2.07331 5.25964L6.9609 10.0193L5.57869 11.3653ZM0.691105 5.25964L5.57869 0.5L6.9609 1.84603L2.07331 6.60566L0.691105 5.25964ZM1.38221 4.98072H11.1574V6.88458H1.38221V4.98072ZM18 11.6442V13.5481H16.045V11.6442H18ZM11.1574 4.98072C12.9722 4.98072 14.7126 5.68277 15.9958 6.93242C17.2791 8.18206 18 9.87695 18 11.6442H16.045C16.045 10.3819 15.53 9.17125 14.6134 8.27864C13.6968 7.38604 12.4536 6.88458 11.1574 6.88458V4.98072Z" fill="white" />
                           </svg>
							뒤로가기
						</div>
						<div class="but3" id="deleteBtn">삭제</div>
						<div class="but2" id="updateBtn">수정</div>
					</div>
				</div>
			</div>
			<c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
		</div>
	</div>
</body>
</html>