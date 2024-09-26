<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <% String path=request.getContextPath(); %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="utf-8" />
                <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
                <title>신규 리포트 등록</title>
                <link href="<%=path%>/css/style.css" rel="stylesheet" />
                <link href="<%=path%>/css/areport-mvp_style.css" rel="stylesheet" />
                <style type="text/css">
                    .areport-mvp-none .vector-3 {
                        position: absolute;
                        left: 259.5px;
                        top: 226px;
                        width: 440px;
                        height: 244.3px;
                    }

                    .areport-mvp-none .container-2 {
                        margin: 259px 669px 0 404px;
                        margin-bottom: 17px;
                        display: inline-block;
                        overflow-wrap: break-word;
                        font-family: var(--left-menu-font-family, 'Pretendard', 'Roboto Condensed');
                        font-weight: var(--left-menu-font-weight, 500);
                        font-size: var(--left-menu-font-size, 16px);
                        letter-spacing: var(--left-menu-letter-spacing, -0.2px);
                        color: #000000;
                    }
                </style>
                <!-- jQuery 포함 -->
                <script src="<%=path%>/js/jquery-1.12.4.min.js"></script>
                <script>
                    var currentPage = 1;		// 현재 페이지
                    var pageRow = 10;			// 페이지당 보여질 리스트 개수
                    var displayPage = 10;		// 클릭 가능한 페이지 수
                    var totalPages = 0;
                    $(document).ready(function () {

                        // 화면 진입 시, 리스트 불러오기
                        searchReports(currentPage, "", "");


                        $('#searchBtn').on('click', function (event) {
                            event.preventDefault(); // 폼 제출을 막음

                            var searchKeyword = $('#searchKeyword').val(); 	// 입력된 검색키워드를 가져옴
                            var searchKey = $('#searchKey').val(); 			// 선택된 검색 종류를 가져옴
                            $("#beforeSearchKeyword").val(searchKeyword); 	// 페이지 클릭 시, 이전 검색조건 그대로 가져가기위한 검색키워드 셋팅
                            $("#beforeSearchKey").val(searchKey);			// 페이지 클릭 시, 이전 검색 조건 그대로 가져가기위한 검색 종류 셋팅

                            currentPage = 1;

                            searchReports(currentPage, searchKey, searchKeyword); // 검색 버튼 클릭 시 1페이지부터 검색
                        });

                        // 엔터 키를 눌렀을 때 검색 버튼 클릭
                        $('#searchKeyword').on('keypress', function (event) {
                            if (event.key === 'Enter') {
                                event.preventDefault();

                                var searchKeyword = $('#searchKeyword').val(); 	// 입력된 검색키워드를 가져옴
                                var searchKey = $('#searchKey').val(); 			// 선택된 검색 종류를 가져옴
                                $("#beforeSearchKeyword").val(searchKeyword); 	// 페이지 클릭 시, 이전 검색조건 그대로 가져가기위한 검색키워드 셋팅
                                $("#beforeSearchKey").val(searchKey);			// 페이지 클릭 시, 이전 검색 조건 그대로 가져가기위한 검색 종류 셋팅

                                currentPage = 1;

                                searchReports(currentPage, searchKey, searchKeyword); // 검색 버튼 클릭 시 1페이지부터 검색
                            }
                        });

                        // 이벤트 위임을 사용하여 동적으로 생성된 요소에 클릭 이벤트 리스너 추가
                        /*
                      $(document).on('click', '.frame-1261152496', function() {
                          var storeId = $(this).data('store-id');
                          window.location.href = '/report/save/additional?storeId=' + storeId;
                      });
                        */
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

                        var beforeSearchKeyword = $("#beforeSearchKeyword").val();
                        var beforeSearchKey = $("#beforeSearchKey").val();
                        searchReports(currentPage, beforeSearchKey, beforeSearchKeyword);
                    }

                    function ajaxRequest(url, data, successCallback, errorCallback) {
                        $.ajax({
                            url: url,
                            method: 'POST',
                            contentType: 'application/json',
                            data: JSON.stringify(data),  // JSON 형식으로 데이터를 변환
                            success: function (response) {
                                if (typeof successCallback === 'function') {
                                    successCallback(response);
                                }
                            },
                            error: function (xhr, status, error) {
                                if (typeof errorCallback === 'function') {
                                    errorCallback(xhr, status, error);
                                }
                            }
                        });
                    }

                    function searchReports(page, searchKey, searchKeyword) {

                        console.log("searchKey : " + searchKey);
                        console.log("searchKeyword : " + searchKeyword);
                        var searchUrl = "/report/search";
                        var jsonItem = {
                            pageRow: pageRow,
                            page: page,
                            searchKey: searchKey,
                            searchKeyword: searchKeyword
                        }; // JSON 객체 생성

                        console.log(jsonItem);

                        // #listView의 내용을 비움
                        $('#listView').empty();

                        // ajaxRequest 함수를 사용하여 Ajax 요청 보내기
                        ajaxRequest(
                            searchUrl,
                            jsonItem,
                            function (response) {
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
                            var reportList = response.data.list || []; // null 체크 및 기본값 빈 배열
                            var reportHtml = '';
                            var reportListCount = response.data.count || 0;

                            var checkKeyword = $("#searchKeyword").val();

                            if (reportListCount > 0 || checkKeyword) {

                                $("#searchCount").text(reportListCount);

                                reportList.forEach(function (report) {
                                    reportHtml += "<div class='frame-1261152496'>" +
                                        "<div class='frame-1261152493'>" +
                                        "<div class='container-9'>" + (report.reportId || "") + "</div>" +
                                        // 리포트 목록 클릭 이벤트 제거
                                        //"<div class='container-10'><a href='#' onclick='reportInfo(" + (report.reportId || "") + ")'>" + (report.name || "") + "</a></div>" +
                                        "<div class='container-10'><a href='#'>" + (report.name || "") + "</a></div>" +
                                        "<div class='container-11'>" + (report.addr || "") + "</div>" +

                                        // 이미지 및 링크 부분 추가
                                        "<div class='frame-1261152506'>" +
                                        //report.url.replace("report.jyes.co.kr", "localhost:82")

                                        // "<a href='" + (report.url.replace("report.jyes.co.kr", "localhost:8080") || "") +
                                        // "?isPreview=Y' onclick='window.open(\"" + (report.url.replace("report.jyes.co.kr", "localhost:8080") || "") +
                                        // "?isPreview=Y\", \"_blank\", \"width=436, height=852\"); return false;'>" +

                                        //  "<a href='" + (report.url || "") + 
                                        //  "?isPreview=Y' onclick='window.open(\""+ (report.url || "") + 
                                        //  "?isPreview=Y\", \"_blank\", \"width=436, height=852\"); return false;'>" +

                                        "<a href='" + (report.url.replace("report.jyes.co.kr", "localhost:8080") || "") +
                                        "?isPreview=Y' onclick='window.open(\"" + (report.url.replace("report.jyes.co.kr", "localhost:3001/report") || "") +
                                        "?isPreview=Y\", \"_blank\", \"width=436, height=852\"); return false;'>" +
                                        // "?isPreview=Y\", \"_blank\", \"\"); return false;'>" +

                                        "<img class='vector' src='<%=path%>/assets/vectors/vector_42_x2.svg' />" +
                                        "</a>" +
                                        "</div>" +

                                        "<div class='container-12'>" + (report.regDate || "") + "</div>" +
                                        "<div class='container-13'>" + (report.vol || "") + "</div>" +  // 숫자 값으로 교체 필요

                                        // 스위치 부분 추가
                                        "<div class='wrapper'>" +
                                        "<input type='checkbox' id='switch_" + (report.reportId || "") + "' data-report='" + (report.reportId || "") + "' data-expose='" + (report.isExposed || "") + "' onclick='changeExposureReport(this)' " + (report.isExposed == "Y" ? "checked" : "") + " style='display:none;'>" +
                                        "<label for='switch_" + (report.reportId || "") + "' class='" + (report.isExposed == "Y" ? "switch_label_on" : "switch_label") + "'>" +
                                        "<span class='" + (report.isExposed == "Y" ? "onf_btn_on" : "onf_btn") + "'></span>" +
                                        "</label>" +
                                        "</div>" +

                                        "</div>" +
                                        "<div class='rectangle-161'></div>" +
                                        "</div>";
                                });

                                $('#listView').html(reportHtml); // 받은 데이터를 DOM에 추가

                                var searchedKeyword = $("#beforeSearchKeyword").val();

                                if (searchedKeyword != "") {
                                    $("#searchKeywordText").text(searchedKeyword);
                                    $("#searchResult").show();
                                } else {
                                    $("#searchResult").hide();
                                }

                                // 페이지네이션 설정
                                setPagination(response.data.count);

                            } else {
                                $("#zeroRow").empty();
                                var zeroCountHtml = "";
                                zeroCountHtml = "<div class='container-2'>" +
                                    "등록된 리포트가 없습니다. <br />" +
                                    "리포트 등록을 해주세요." +
                                    "</div>" +
                                    "<img class='vector-3' src='<%=path%>/assets/vectors/vector_31_x2.svg' />";
                                $("#zeroRow").append(zeroCountHtml);
                            }
                            $("#zeroRow").show();
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


                    function changeExposureReport(element) {

                        var reportId = $(element).attr('data-report');
                        var isExposed = $(element).attr('data-expose');

                        console.log(reportId);

                        var changeExposed = "";

                        if (isExposed == "Y") {
                            changeExposed = "N";
                        } else {
                            changeExposed = "Y";
                        }

                        var changeExposureUrl = "/report/change/expose";
                        var jsonItem = {
                            reportId: reportId,
                            isExposed: changeExposed
                        }; // JSON 객체 생성

                        console.log(jsonItem);

                        // ajaxRequest 함수를 사용하여 Ajax 요청 보내기
                        ajaxRequest(
                            changeExposureUrl,
                            jsonItem,
                            function (response) {
                                changeExposureSuccessCallback(response, element);
                            },
                            errorCallback
                        );

                    }

                    function changeExposureSuccessCallback(response, element) {
                        // 문자열인 경우 JSON으로 파싱
                        response = JSON.parse(response);

                        // response 객체의 구조를 콘솔에 출력
                        console.log('Parsed response:', response);
                        console.log('msg:', response.msg);
                        console.log('data:', response.data);

                        if (response.msg == "0000") {
                            if (response.data > 0) {
                                // Use the provided element to find the related label and span
                                var $checkbox = $(element);
                                var $label = $checkbox.next('label');
                                var $span = $label.find('span');

                                // Toggle class based on current state
                                if ($checkbox.is(':checked')) {
                                    $label.removeClass('switch_label').addClass('switch_label_on');
                                    $span.removeClass('onf_btn').addClass('onf_btn_on');
                                } else {
                                    $label.removeClass('switch_label_on').addClass('switch_label');
                                    $span.removeClass('onf_btn_on').addClass('onf_btn');
                                }
                            }
                        }
                    }

                    function errorCallback(xhr, status, error) {
                        // 실패 시 처리할 코드
                        console.error(error);
                    }

                    function popup() {
                        window.open("webview.html", "popup", "width=393, height=582, history=no, resizable=no, status=no, scrollbars=yes, menubar=no")
                    }

                    function reportInfo(reportId) {
                        window.location.href = "/report/info?reportId=" + reportId;
                    }
                </script>
            </head>

            <body>
                <div class="areport-mvp-none">
                    <div class="container">
                        <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
                        <div class="divider"></div>
                        <div class="title">
                            리포트 목록
                        </div>
                        <div class="areport-mvp" id="zeroRow" style="display: none;">
                            <div class="container-6">
                                <div class="selectBoxList">
                                    <!-- selectBox1 -->
                                    <div class="box">
                                        <div class="selectBox2 selectBox3">
                                            <select class="label select_renewal" id="searchKey" name="searchKey">
                                                <option value="">전체</option>
                                                <option value="A">Report_ID</option>
                                                <option value="B">매장이름</option>
                                                <option value="C">업종</option>
                                                <option value="D">주소</option>
                                            </select>
                                            <input type="hidden" id="beforeSearchKey" name="beforeSearchKey" />
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <div class="search-box" action="" method="get">
                                        <input class="search-txt" type="text" id="searchKeyword" name="searchKeyword"
                                            value="" placeholder="검색할 내용을 입력해 주세요." />
                                        <input type="hidden" id="beforeSearchKeyword" name="beforeSearchKeyword"
                                            value="" />
                                        <button class="search-btn" type="button" id="searchBtn">
                                            <img class="search-1" src="<%=path%>/assets/vectors/search.svg" />
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div class="container-25" id="searchResult" style="display: none;">
                                <span>‘</span><span id="searchKeywordText"></span><span>&#39;</span> <span>검색결과 :
                                </span><span id="searchCount"></span><span>건</span>
                            </div>
                            <div class="container-3">
                                <div class="frame-1261152495">
                                    <div class="frame-1261152492">
                                        <span class="report-id">
                                            Report_ID
                                        </span>
                                        <span class="tablehead name">
                                            이름
                                        </span>
                                        <span class="tablehead add">
                                            주소
                                        </span>
                                        <span class="tablehead link">
                                            링크 미리보기
                                        </span>
                                        <span class="tablehead day">
                                            등록일
                                        </span>
                                        <span class="tablehead uv">
                                            Vol
                                        </span>
                                        <span class="tablehead onf">
                                            게시중/멈춤
                                        </span>
                                    </div>

                                    <div class="rectangle-224">
                                    </div>
                                    <div id="listView">
                                    </div>
                                </div>
                            </div>
                            <div class="pagination_box">
                                <div class="pagination" id="pagination">
                                    <a href="#"><img class="previous" src="<%=path%>/assets/vectors/arrow.svg" />
                                        <span>previous</span></a>
                                    <a href="#" class="active">1</a>
                                    <a href="#"><span>Next</span> <img class="next"
                                            src="<%=path%>/assets/vectors/arrow.svg" /></a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
                </div>
            </body>

            </html>