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
        		window.location.href = '/report/save/location/analysis?reportId=' + reportId
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
            	if(response.data > 0){
	        		// reportId를 쿼리 파라미터로 추가하여 페이지 이동
	                var reportId = $("#reportId").val();
	                window.location.href = "/report/save/detail?reportId=" + reportId;
	            	
	            } else {
	                // msg가 '0000'이 아닌 경우 처리
	                console.error('Error: ' + response.msg);
	            }
        	}
        }
        
        function save(){
        	console.log("save method");
        	var url = "/report/business/district/analysis/save";
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
            if (!addIfValid("businessDistrictAnalysisId", $("#businessDistrictAnalysisId").val())){}  				// 지역 상권 분석 ID
            if (!addIfValid("reportBusinessDistrictAnalysisId", $("#reportBusinessDistrictAnalysisId").val())){}  	// 리포트 지역 상권 분석 ID
            if (!addIfValid("areaId", $("#areaId").val())){}  														// 지역 ID
            if (!addIfValid("mainCategoryId", $("#mainCategoryId").val())){}  										// 대분류 category id
            if (!addIfValid("subCategoryId", $("#subCategoryId").val())){}  										// 중분류 category id

            // 해당 상권 정보
            if (!addIfValid("averageCost", $("#averageCost").val())){}  											// 평균 비용
            if (!addIfValid("averageEarning", $("#averageEarning").val())){}  										// 평균 수익
            if (!addIfValid("operateProfit", $("#operateProfit").val())){}  										// 영업 이익
            if (!addIfValid("operateExpense", $("#operateExpense").val())){}  										// 영업 비용

            // 업종 밀집도
            if (!addIfValid("allBusinessDensity", $("#allBusinessDensity").val())){}								// 전국 업종밀집도
            if (!addIfValid("sidoBusinessDensity", $("#sidoBusinessDensity").val())){}								// 시/도 업종밀집도
            if (!addIfValid("dongBusinessDensity", $("#dongBusinessDensity").val())){}								// 동 업종밀집도
            
            // 시장 규모
            if (!addIfValid("marketSalesScale", $("#marketSalesScale").val())){}									// 총 시장 규모
            if (!addIfValid("storeSalesScale", $("#storeSalesScale").val())){}										// 점포당 매출 규모
            if (!addIfValid("unitPrice", $("#unitPrice").val())){}													// 결제 단가
            if (!addIfValid("uses", $("#uses").val())){}															// 이용 건수

            // 이용 형태
            jsonItem["peakGenderCd"] = $("#peakGenderCd").val();													// 최고 이용 성별
            jsonItem["peakAgeRangeCd"] = $("#peakAgeRangeCd").val();												// 최고 이용 연령대
            jsonItem["peakWeekCd"] = $("#peakWeekCd").val();														// 최고 이용 요일
            jsonItem["peakTimeStartCd"] = $("#peakTimeStartCd").val();												// 최고 이용 시작 시간대
            jsonItem["peakTimeEndCd"] = $("#peakTimeEndCd").val();													// 최고 이용 종료 시간대
            
            console.log(jsonItem);
        	
        	return jsonItem;
        }
        
     	// 입력칸 리셋 메소드
        function reset(){
        	// 모든 input[type="text"] 요소를 선택하여 값을 빈 문자열로 설정
            $('input[type="text"]').val('');

            // 모든 select 요소를 선택하여 값을 빈 문자열로 설정
            $('select').val('');
        }
    </script>
</head>
<body>
	<input type="hidden" id="reportId" name="reportId" value="${ businessDistrictAnalysis.reportId }"/>
	<input type="hidden" id="storeId" name="storeId" value="${ businessDistrictAnalysis.storeId }"/>
	<input type="hidden" id="businessDistrictAnalysisId" name="businessDistrictAnalysisId" value="${ businessDistrictAnalysis.businessDistrictAnalysisId }"/>
	<input type="hidden" id="reportBusinessDistrictAnalysisId" name="reportBusinessDistrictAnalysisId" value="${ businessDistrictAnalysis.reportBusinessDistrictAnalysisId }">
	<input type="hidden" id="areaId" name="areaId" value="${ businessDistrictAnalysis.areaId }" />
	<input type="hidden" id="mainCategoryId" name="mainCategoryId" value="${ businessDistrictAnalysis.mainCategoryId }" />
	<input type="hidden" id="subCategoryId" name="subCategoryId" value="${ businessDistrictAnalysis.subCategoryId }" />
    <div class="areport-mvp-none">
        <div class="container">
            <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
            <div class="divider">
            </div>
            <div class="title">
                <u>신규 리포트 생성</u> > <u>${ businessDistrictAnalysis.name }</u> > 세부등록
            </div>
            <div class="areport-mvp">
                <div class="new">
                    <div class="tit left">등록정보</div>
                    <span class="new-day">${ businessDistrictAnalysis.regDate }</span>
                    <span class="new-box-none">${ businessDistrictAnalysis.siNm }</span>
                    <span class="new-box-none">${ businessDistrictAnalysis.sggNm }</span>
                    <span class="new-box-none"><c:if test="${not empty businessDistrictAnalysis.emdNm}">${businessDistrictAnalysis.emdNm}</c:if><c:if test="${empty businessDistrictAnalysis.emdNm}">${businessDistrictAnalysis.liNm}</c:if></span>
                    <span class="new-box-none">${ businessDistrictAnalysis.mainCategoryName }</span>
                    <span class="new-box-none">${ businessDistrictAnalysis.subCategoryName }</span>
                    <button class="but2" id="storeInfoViewBtn" style="border:0px;">정보보기</button>
                </div>
                <div class="container-26">
                    %<u><c:if test="${not empty businessDistrictAnalysis.emdNm}">
					        ${businessDistrictAnalysis.emdNm}
					    </c:if>
					    <c:if test="${empty businessDistrictAnalysis.emdNm}">
					        ${businessDistrictAnalysis.liNm}
					    </c:if></u> <u>${ businessDistrictAnalysis.mainCategoryName }</u> > <u>${ businessDistrictAnalysis.subCategoryName }</u>
                </div>
                <div class="new">
                    <div class="tit2">해당상권 정보</div>
                    <div>
                        <input class="nex-box-use" type="text" id="averageCost" name="averageCost" value="" placeholder="평균비용(원 &#8361;)<c:if test="${not empty businessDistrictAnalysis.averageCost}"> : ${businessDistrictAnalysis.averageCost}</c:if>" />
                        <input class="nex-box-use" type="text" id="averageEarning" name="averageEarning" value="" placeholder="평균수익(원 &#8361;)<c:if test="${not empty businessDistrictAnalysis.averageEarning}"> : ${businessDistrictAnalysis.averageEarning}</c:if>" />
                        <input class="nex-box-use" type="text" id="operateProfit" name="operateProfit" value="" placeholder="영업이익(%)<c:if test="${not empty businessDistrictAnalysis.operateProfit}"> : ${businessDistrictAnalysis.operateProfit}</c:if>" />
                        <input class="nex-box-use" type="text" id="operateExpense" name="operateExpense" value="" placeholder="영업비용(%)<c:if test="${not empty businessDistrictAnalysis.operateExpense}"> : ${businessDistrictAnalysis.operateExpense}</c:if>" />
                    </div>
                </div>
                <div class="new">
                    <div class="tit2">업종 밀집도</div>
                    <div>
                        <input class="nex-box-use" type="text" id="allBusinessDensity" name="allBusinessDensity" value="" placeholder="전국 업종밀집도(%)<c:if test="${not empty businessDistrictAnalysis.allBusinessDensity}"> : ${businessDistrictAnalysis.allBusinessDensity}</c:if>" />
                        <input class="nex-box-use" type="text" id="sidoBusinessDensity" name="sidoBusinessDensity" value="" placeholder="시/도 업종밀집도(%)<c:if test="${not empty businessDistrictAnalysis.sidoBusinessDensity}"> : ${businessDistrictAnalysis.sidoBusinessDensity}</c:if>" />
                        <input class="nex-box-use" type="text" id="dongBusinessDensity" name="dongBusinessDensity" value="" placeholder="동 업종밀집도(%)<c:if test="${not empty businessDistrictAnalysis.dongBusinessDensity}"> : ${businessDistrictAnalysis.dongBusinessDensity}</c:if>" />
                    </div>
                </div>
                <div class="new">
                    <div class="tit2">시장규모</div>
                    <div>
                        <input class="nex-box-use" type="text" id="marketSalesScale" name="marketSalesScale" value="" placeholder="총 시장규모(원 &#8361;)<c:if test="${not empty businessDistrictAnalysis.marketSalesScale}"> : ${businessDistrictAnalysis.marketSalesScale}</c:if>" />
                        <input class="nex-box-use" type="text" id="storeSalesScale" name="storeSalesScale" value="" placeholder="점포당 매출규모(원 &#8361;)<c:if test="${not empty businessDistrictAnalysis.storeSalesScale}"> : ${businessDistrictAnalysis.storeSalesScale}</c:if>" />
                        <input class="nex-box-use" type="text" id="unitPrice" name="unitPrice" value="" placeholder="결제단가(원 &#8361;)<c:if test="${not empty businessDistrictAnalysis.unitPrice}"> : ${businessDistrictAnalysis.unitPrice}</c:if>" />
                        <input class="nex-box-use" type="text" id="uses" name="uses" value="" placeholder="이용건수<c:if test="${not empty businessDistrictAnalysis.uses}"> : ${businessDistrictAnalysis.uses}</c:if>" />
                    </div>

                </div>
                <div class="new">
                    <div class="tit2">이용 형태</div>
                    <div class="selectBoxList">
                        <!-- selectBox1 -->
                        <div class="box">
                            <div class="selectBox3">
                                <select class="label select_renewal" id="peakGenderCd" name="peakGenderCd">
	                                <option value="">최고 이용성별</option>
                                	<c:forEach var="gender" items="${genderList}">
								        <option value="${gender.detailCd}" 
								            <c:if test="${gender.detailCd == businessDistrictAnalysis.peakGenderCd}">
								                selected
								            </c:if>>
								            ${gender.detailCdName}
								        </option>
								    </c:forEach>
							    </select>
                            </div>
                        </div>
                    </div>
                    <div class="selectBoxList">
                        <!-- selectBox1 -->
                        <div class="box">
                            <div class="selectBox3">
                                <select class="label select_renewal" id="peakAgeRangeCd" name="peakAgeRangeCd">
	                                <option value="">최고 이용 연령대</option>
                                	<c:forEach var="ageGroup" items="${ageGroupList}">
								        <option value="${ageGroup.detailCd}" 
								            <c:if test="${ageGroup.detailCd == businessDistrictAnalysis.peakAgeRangeCd}">
								                selected
								            </c:if>>
								            ${ageGroup.detailCdName}
								        </option>
								    </c:forEach>
							    </select>
                            </div>
                        </div>
                    </div>
                    <div class="selectBoxList">
                        <!-- selectBox1 -->
                        <div class="box">
                            <div class="selectBox3">
                                <select class="label select_renewal" id="peakWeekCd" name="peakWeekCd">
	                                <option value="">최고 이용 요일</option>
	                               	<c:forEach var="week" items="${weekList}">
								        <option value="${week.detailCd}" 
								            <c:if test="${week.detailCd == businessDistrictAnalysis.peakWeekCd}">
								                selected
								            </c:if>>
								            ${week.detailCdName}
								        </option>
								    </c:forEach>
							    </select>
                            </div>
                        </div>
                    </div>
                    <div class="selectBoxList">
                        <!-- selectBox1 -->
                        <div class="box">
                            <div class="selectBox3">
                                <select class="label select_renewal" id="peakTimeStartCd" name="peakTimeStartCd">
	                                <option value="">최고 이용 시간대_시작</option>
                                	<c:forEach var="time" items="${timeList}">
								        <option value="${time.detailCd}" 
								            <c:if test="${time.detailCd == businessDistrictAnalysis.peakTimeStartCd}">
								                selected
								            </c:if>>
								            ${time.detailCdName}
								        </option>
								    </c:forEach>
							    </select>
                            </div>
                        </div>
                    </div>
                    <div class="selectBoxList">
                        <!-- selectBox1 -->
                        <div class="box">
                            <div class="selectBox3">
                                <select class="label select_renewal" id="peakTimeEndCd" name="peakTimeEndCd">
	                                <option value="">최고 이용 시간대_종료</option>
                                	<c:forEach var="time" items="${timeList}">
								        <option value="${time.detailCd}" 
								            <c:if test="${time.detailCd == businessDistrictAnalysis.peakTimeEndCd}">
								                selected
								            </c:if>>
								            ${time.detailCdName}
								        </option>
								    </c:forEach>
							    </select>
                            </div>
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
                        <button class="but2" id="saveAndNextBtn" style="border:0px;">저장&다음입력</button>
                    </div>
                </div>
            </div>

        </div>
        <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
    </div>
</body>
</html>
