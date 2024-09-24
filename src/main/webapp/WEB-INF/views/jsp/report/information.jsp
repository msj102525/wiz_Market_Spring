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
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레포트공통정보</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/jquery.Jcrop.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/tab.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/areport-mvp_style.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/report.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.jcrop.common.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<form name="searchFrm" id="searchFrm" method="GET">

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
	        
	            <div class="profile">
	            
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
	                
	                <div class="tit">
	                    <span>참조은병원</span>
	                </div>
	                
	                <div class="profile-img">
	                    <div class="profile-bottom"></div>
	                    <div class="top-100">
	                        <div class="image-350"></div>
	                        <div class="profile-bar">
	                            <div class="profile-grade">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="25" height="24" viewBox="0 0 25 24" fill="none">
	                                    <path d="M23.1513 5.25V11.25C23.1513 11.5484 23.0328 11.8345 22.8218 12.0455C22.6108 12.2565 22.3247 12.375 22.0263 12.375C21.728 12.375 21.4418 12.2565 21.2308 12.0455C21.0199 11.8345 20.9013 11.5484 20.9013 11.25V7.96875L13.8223 15.0487C13.7177 15.1536 13.5936 15.2368 13.4568 15.2936C13.3201 15.3504 13.1735 15.3796 13.0254 15.3796C12.8773 15.3796 12.7307 15.3504 12.594 15.2936C12.4572 15.2368 12.333 15.1536 12.2285 15.0487L9.27633 12.0938L3.32226 18.0459C3.11092 18.2573 2.82428 18.376 2.52539 18.376C2.2265 18.376 1.93986 18.2573 1.72851 18.0459C1.51717 17.8346 1.39844 17.5479 1.39844 17.2491C1.39844 16.9502 1.51717 16.6635 1.72851 16.4522L8.47851 9.70219C8.58303 9.59731 8.70722 9.51409 8.84397 9.45731C8.98071 9.40053 9.12732 9.3713 9.27539 9.3713C9.42345 9.3713 9.57006 9.40053 9.70681 9.45731C9.84356 9.51409 9.96775 9.59731 10.0723 9.70219L13.0263 12.6562L19.3076 6.375H16.0263C15.728 6.375 15.4418 6.25647 15.2308 6.0455C15.0199 5.83452 14.9013 5.54837 14.9013 5.25C14.9013 4.95163 15.0199 4.66548 15.2308 4.4545C15.4418 4.24353 15.728 4.125 16.0263 4.125H22.0263C22.3247 4.125 22.6108 4.24353 22.8218 4.4545C23.0328 4.66548 23.1513 4.95163 23.1513 5.25Z" fill="#FF0000" />
	                                </svg>
	                                <span>6.8 / <b>6.4pt</b></span>
	                            </div>
	                            <div class="profile-add">
	                                <div>경기도 광주시 광주대로45</div>
	                            </div>
	                            <div class="profile-bar-img"></div>
	                        </div>
	                    </div>
	                </div>
	                
	            </div>
	            
	            <div class="dailyweek">
	                <div class="sns">
	                    <div class="timelinebar">
	                        <div class="rectangle-956531">
	                        </div>
	                        <div class="rectangle-956646">
	                        </div>
	                    </div>
	                    <div class="sns-txt">
	                        <div class="tit">소셜 홍보성과</div>
	                        <div class="sns-add">
	                            <svg xmlns="http://www.w3.org/2000/svg" width="25" height="24" viewBox="0 0 25 24" fill="none">
	                                <path d="M23.1513 12.7501V18.7501C23.1513 19.0484 23.0328 19.3346 22.8218 19.5456C22.6108 19.7565 22.3247 19.8751 22.0263 19.8751H16.0263C15.728 19.8751 15.4418 19.7565 15.2308 19.5456C15.0199 19.3346 14.9013 19.0484 14.9013 18.7501C14.9013 18.4517 15.0199 18.1656 15.2308 17.9546C15.4418 17.7436 15.728 17.6251 16.0263 17.6251H19.3076L13.0263 11.3438L10.0723 14.2988C9.96775 14.4037 9.84356 14.4869 9.70681 14.5437C9.57006 14.6005 9.42345 14.6297 9.27539 14.6297C9.12732 14.6297 8.98071 14.6005 8.84397 14.5437C8.70722 14.4869 8.58303 14.4037 8.47851 14.2988L1.72851 7.54883C1.51717 7.33748 1.39844 7.05084 1.39844 6.75195C1.39844 6.60396 1.42759 6.45741 1.48422 6.32069C1.54086 6.18396 1.62387 6.05972 1.72851 5.95508C1.83316 5.85043 1.9574 5.76742 2.09412 5.71078C2.23085 5.65415 2.3774 5.625 2.52539 5.625C2.82428 5.625 3.11092 5.74373 3.32226 5.95508L9.27633 11.9063L12.2304 8.95133C12.3349 8.84645 12.4591 8.76323 12.5958 8.70645C12.7326 8.64967 12.8792 8.62044 13.0273 8.62044C13.1753 8.62044 13.3219 8.64967 13.4587 8.70645C13.5954 8.76323 13.7196 8.84645 13.8241 8.95133L20.9013 16.0313V12.7501C20.9013 12.4517 21.0199 12.1656 21.2308 11.9546C21.4418 11.7436 21.728 11.6251 22.0263 11.6251C22.3247 11.6251 22.6108 11.7436 22.8218 11.9546C23.0328 12.1656 23.1513 12.4517 23.1513 12.7501Z" fill="#0066FF" />
	                            </svg>
	                            <span>Instagram @chamhospital</span>
	                        </div>
	                        <div class="sns-table">
	                            <div class="container-7">
	                                <span class="winnies">팔로워</span>
	                                <span class="container-8">459</span>
	                            </div>
	                            <div class="container-7">
	                                <span class="winnies">
	                                    업로드
	                                </span>
	                                <span class="container-8">
	                                    265
	                                </span>
	                            </div>
	                            <div class="container-7">
	                                <span class="winnies">
	                                    참여율
	                                </span>
	                                <span class="container-8">
	                                    2.45%
	                                </span>
	                            </div>
	                            <div class="container-7">
	                                <span class="winnies">
	                                    평균활동
	                                </span>
	                                <span class="container-8">
	                                    6.45
	                                </span>
	                            </div>
	                        </div>
	                        <div class="sns-sub">
	                            <span>인스타그램 활동은 미비하며 참여율이 낮습니다. 소셜 활성화를 위해 고객이 참여할 수 있는 콘텐츠나 광고를 활용해보시기 바랍니다.</span>
	                        </div>
	                    </div>
	                </div>
	                <div class="sns-but">
	                    <div class="container-16">
	                        <div class="frame-185572">
	                            <span class="container-17">리포트 정보 블록 추가 등록 +</span>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">공통정보: <span>신한은행, 자영업자.소상공인 3067억 원 상생지원 한다 / 2024-01월 01주차</span></div>
	                    <div class="right-side">
	                        <div class="but4">블록제거</div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">공통정보: <span>혹한기 매장 대비책 안내 공지</span></div>
	                    <div class="right-side">
	                        <div class="but4">블록제거</div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">우리 매장은 이럴때 결제가 가장 활발해요.</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">소셜 빅데이터 분석</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="sns-box">
	                    <div class="sns-keyword">연관어 비교</div>
	                    <div class="sns-keyword">긍.부정 분석</div>
	                    <div class="sns-keyword">‘광주'지역과 ‘참조은병원' 연관어 비교</div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">2024년 3월 5째 주 소식</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">기업법률 상담이 필요하신가요? 편하게 물어보세요. </div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="image-117"></div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">최근 뜨는 점포업종은?</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="analysis">
	                <div class="container-42">입지분석<br /></div>
	                <div class="container-43">경기도 광주시 경안동</div>
	                <div class="container-44">‘전자정부 상권정보&#39; 2024년 1월</div>
	                <div class="container-45">4.43</div>
	                <div class="container-46">전반적으로 사업하기 약간 낮은 입지 조건을 지니고 있습니다.</div>
	                <div class="frame-1261152415">
	                    <span class="container-48">전국기준=</span>
	                    <span class="container-47">100%</span>
	                    <span class="container-48">참고) 역삼1동 = 8.41 / 10</span>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">성별/ 연령대 분포</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">학생 / 직장인 분포</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">시간대별 분포</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">아파트 세대 수<div class="sns-keyword">447 세대</div></div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">평당(3.3㎡) 아파트 실거래가</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">교통환경</div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="analysis">
	                <div class="container-42">상권분석<br /></div>
	                <div class="container-43">경기도 광주시 경안동</div>
	                <div class="container-44">‘전자정부 상권정보&#39; 2024년 1월</div>
	                <div class="container-45">4.09</div>
	                <div class="container-46">상권지수는 낮은 편이며 사업 영역을 확장시킬 필요가 있습니다.</div>
	                <div class="frame-1261152415">
	                    <span class="container-48">전국기준=</span>
	                    <span class="container-47">100%</span>
	                    <span class="container-48">참고) 서초4동(강남역) = 9.03 / 10</span>
	                </div>
	            </div>
	            
	            <div class="image-121"></div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">참조은 병원 인근 유동인구<div class="tit-sub">일평균<span>27,242명</span></div></div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div>
	                    <img src="<%=path%>/assets/vectors/image_126.png" />
	                </div>
	                <div class="sns-box">
	                    <div class="popul">최근 1년 유동인구 추이</div>
	                    <div class="popul">유동인구 분석</div>
	                    <div class="popul">유동인구 요일/시간대 분포</div>
	                </div>
	            </div>
	            
	            <div class="dailyweek">
	                <div class="dailyweek-box">
	                    <div class="tit">지역명(주소), 업종을 입력해주세요.<div class="search">광주대로45, 병원</div></div>
	                    <div class="right-side">
	                        <div class="scroll">
	                            <div class="scroll-aorrw">
	                                <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15" viewBox="0 0 10 10" fill="none">
	                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M4.58443 9.19185C4.34035 8.94777 4.34035 8.55204 4.58443 8.30796L7.89248 4.99991L4.58443 1.69185C4.34035 1.44777 4.34035 1.05204 4.58443 0.807964C4.8285 0.563886 5.22423 0.563886 5.46831 0.807964L9.21831 4.55796C9.33552 4.67517 9.40137 4.83415 9.40137 4.99991C9.40137 5.16567 9.33552 5.32464 9.21831 5.44185L5.46831 9.19185C5.22423 9.43593 4.8285 9.43592 4.58443 9.19185Z" fill="#A2A2A2" />
	                                </svg>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="frame-1261152411">
	                <div class="frame-1261152410">
	                    <span class="wiz-market">WIZ MARKET</span>
	                </div>
	                <div class="frame-1261152409">
	                    <span class="container-104">이용 요금</span>
	                    <div class="line-33"></div>
	                    <span class="container-105">프로필 수정</span>
	                    <div class="line-34"></div>
	                    <span class="container-106">정보설정</span>
	                    <div class="line-35"></div>
	                    <span class="container-107">1:1문의</span>
	                </div>
	                <span class="container-108">서비스 시작일 : 2024-04-15</span>
	            </div>
	            
	        </div>
	        
	        <div class="but-box">
	            <div class="but-box-w">
	                <div class="but1">
	                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 18 15" fill="none">
	                        <path d="M1.38221 5.93265L0.691105 6.60566L0 5.93265L0.691105 5.25964L1.38221 5.93265ZM18 13.5481C18 13.8005 17.897 14.0427 17.7137 14.2212C17.5304 14.3997 17.2817 14.5 17.0225 14.5C16.7632 14.5 16.5146 14.3997 16.3313 14.2212C16.148 14.0427 16.045 13.8005 16.045 13.5481H18ZM5.57869 11.3653L0.691105 6.60566L2.07331 5.25964L6.9609 10.0193L5.57869 11.3653ZM0.691105 5.25964L5.57869 0.5L6.9609 1.84603L2.07331 6.60566L0.691105 5.25964ZM1.38221 4.98072H11.1574V6.88458H1.38221V4.98072ZM18 11.6442V13.5481H16.045V11.6442H18ZM11.1574 4.98072C12.9722 4.98072 14.7126 5.68277 15.9958 6.93242C17.2791 8.18206 18 9.87695 18 11.6442H16.045C16.045 10.3819 15.53 9.17125 14.6134 8.27864C13.6968 7.38604 12.4536 6.88458 11.1574 6.88458V4.98072Z" fill="white" />
	                    </svg> 뒤로가기
	                </div>
	                <div class="but3">삭제</div>
	                <div class="but2">수정</div>
	            </div>
	        </div>
	        
	    </div>
    </div>
            
	<c:import url="/WEB-INF/views/jsp/layout/left.jsp" />

</div>


</form>
</body>
</html>