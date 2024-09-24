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
    <title>매장목록</title>
    <link rel="stylesheet" href="<%=path%>/css/style.css" />
    <link rel="stylesheet" href="<%=path%>/css/areport-mvp_style.css" />
    <!-- jQuery 포함 -->
    <script type="text/javascript" src="<%=path%>/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript">
    var currentPage = 1;		// 현재 페이지
    var pageRow = 10;			// 페이지당 보여질 리스트 개수
    var displayPage = 10;		// 클릭 가능한 페이지 수
    var totalPages = 0;
    $(document).ready(function() {
    	
        $('#searchBtn').on('click', function(event) {
            event.preventDefault(); // 폼 제출을 막음

            var searchText = $('#name').val(); // 입력된 검색어를 가져옴
            $("#beforeName").val(searchText); // 페이지 클릭 시, 이전 검색조건 그대로 가져가기위한 검색키워드 셋팅
            
            currentPage = 1;
            
            searchStores(currentPage, searchText); // 검색 버튼 클릭 시 1페이지부터 검색
        });
        
     	// 엔터 키를 눌렀을 때 검색 버튼 클릭
        $('#name').on('keypress', function(event) {
            if (event.key === 'Enter') {
            	event.preventDefault();
            	var searchText = $('#name').val(); // 입력된 검색어를 가져옴
                $("#beforeName").val(searchText); // 페이지 클릭 시, 이전 검색조건 그대로 가져가기위한 검색키워드 셋팅
                
                currentPage = 1;
                
                searchStores(currentPage, searchText); // 검색 버튼 클릭 시 1페이지부터 검색
            }
        });
     	
     	// 이벤트 위임을 사용하여 동적으로 생성된 요소에 클릭 이벤트 리스너 추가
        $(document).on('click', '.frame-1261152496', function() {
            var storeId = $(this).data('store-id');
            window.location.href = '/store/info?storeId=' + storeId;
        });
     	
        searchStores('1', ''); // 최초 실행
    });
    
    function navigatePage(page) {
    	if ($(event.target).hasClass('disabled-link')) {
            return; // disabled-link 클래스를 가진 경우 동작하지 않음
        }
        
        if (page == 'previous') {
            if (currentPage > 1) {
                currentPage -= 1;
            }
        } else if (page == 'next') {	
            if (currentPage < totalPages) {
                currentPage += 1;
            }
        } else {
            currentPage = page;
        }
        
        var beforeSearchText = $("#beforeName").val();
        searchStores(currentPage, beforeSearchText);
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
    
    function searchStores(page, searchText) {

    	console.log("searchKeyword : " + searchText);
        var searchUrl = "<%=path%>/store/search";
        var jsonItem = { 
        	searchKeyword : searchText,
            page : page,
            pageRow : pageRow
        }; // JSON 객체 생성

        // #listView의 내용을 비움
        $('#listView').empty();

        // ajaxRequest 함수를 사용하여 Ajax 요청 보내기
        ajaxRequest(
            searchUrl,
            jsonItem,
            function(response) {
            	searchSuccessCallback(response, page);
            },
            listErrorCallback
        );
    }
    
    function searchSuccessCallback(response) {
    	// 문자열인 경우 JSON으로 파싱
        response = JSON.parse(response);
        
        // response 객체의 구조를 콘솔에 출력
        console.log('Parsed response:', response);
        console.log('msg:', response.msg);
        console.log('data:', response.data);
        
        if (response.msg == "0000") {
            var storeList = response.data.list || []; // null 체크 및 기본값 빈 배열
            var storeHtml = '';

            storeList.forEach(function(store) {
                storeHtml += "<div class='frame-1261152496' data-store-id='" + (store.storeId || "") + "'>" +
                             "<div class='frame-1261152493'>" +
                             "<div class='container-9'>" + (store.storeId || "") + "</div>" +
                             "<div class='container-10'>" + (store.name || "") + "</div>" +
                             "<div class='container-11'>" + (store.addr || "") + "</div>" +
                             "<div class='container-12'>" + (store.regDate.slice(0, 16) || "") + "</div>" +
                             "</div>" +
                             "<div class='rectangle-161'></div>" +
                             "</div>";
            });

            $('#listView').html(storeHtml); // 받은 데이터를 DOM에 추가

            // 페이지네이션 설정
            setPagination(response.data.count);
        } else {
            // msg가 '0000'이 아닌 경우 처리
            console.error('Error: ' + response.msg);
        }
    }
    
    function listErrorCallback(xhr, status, error) {
        // 실패 시 처리할 코드
        console.error(error);
    }
    
    function setPagination(totalCount) {
    	totalPages = Math.ceil(totalCount / pageRow);

        // 페이지 범위 계산
        var startPage = Math.max(1, currentPage - Math.floor(displayPage / 2));
        var endPage = Math.min(totalPages, startPage + displayPage - 1);

        // 만약 페이지 범위가 전체 페이지 수보다 적다면, 범위를 조정합니다.
        if (endPage - startPage + 1 < displayPage) {
            startPage = Math.max(1, endPage - displayPage + 1);
        }

        var paginationHtml = "<a href='#' onclick='navigatePage(\"previous\")'><img class='previous' src='<%=path%>/assets/vectors/arrow.svg' /><span>previous</span></a>";

        for (var i = startPage; i <= endPage; i++) {
            paginationHtml += "<a href='#' class='page-link " + (i === currentPage ? 'active' : '') + "' onclick='navigatePage(" + i + ")'>" + i + "</a>";
        }

        paginationHtml += "<a href='#' onclick='navigatePage(\"next\")'><span>Next</span><img class='next' src='<%=path%>/assets/vectors/arrow.svg' /></a>";

        // 기존 내용 지우기
        $('#pagination').empty();

        // 새로운 내용 추가하기
        $('#pagination').html(paginationHtml);
    }
    </script>
</head>
<body>
	
	<div class="areport-mvp-none">
        
        <div class="container">
        
            <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
            
            <div class="divider"></div>
            
            <div class="title">매장목록</div>
            
            <div class="areport-mvp">
            
                <div class="container-6">
                    <div>
						<form class="search-box" style="width:390px;" name="searchFrm" id="searchFrm" method="GET">
                        <input class="search-txt" style="width:330px;" type="text" id="name" name="name" placeholder="검색어를 입력해주세요. (Store_ID, 이름, 주소)">
                        <input type = "hidden" id="beforeName" name="beforeName" value=""/>
                        <button class="search-btn" id="searchBtn" type="button">
                            <img class="search-1" src="<%=path%>/assets/vectors/search.svg">
                        </button>
						</form>
                    </div>
                    <div>
                        <div class="new-but m-14" style="width:114px;" onclick="window.location.href='/store/new';">
                            <span class="container-66">신규매장 등록</span>
                            <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none">
                                <path d="M19 12.998H13V18.998H11V12.998H5V10.998H11V4.99805H13V10.998H19V12.998Z" fill="white"></path>
                            </svg>
                        </div>
                    </div>
                </div>
                
                <!-- <div class="container-25" id="searchName">‘크린토피아' 검색결과 : 2건</div> -->
                
                <div class="container-3">
                    <div class="frame-1261152495">
                    
                    	<!-- 리스트 헤더 -->
                        <div class="frame-1261152492">
                            <span class="report-id">Store_ID</span>
                            <span class="tablehead name">이름
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none">
                                    <path d="M7 10L12 15L17 10H7Z" fill="black"></path>
                                </svg>
                            </span>
                            <span class="tablehead add">주소</span>
                            <span class="tablehead day">등록일</span>
                        </div>

                        <div class="rectangle-224"></div>
                        
                        <div id="listView"></div>
                        
                    </div>
                </div>
                
                <div class="pagination_box">
                    <div class="pagination" id="pagination"></div>
                </div>
                
            </div>
        </div>
        
        <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
        
    </div>
</body>
</html>
