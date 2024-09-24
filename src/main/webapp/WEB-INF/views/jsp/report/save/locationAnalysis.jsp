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
        	
        	// 저장&다음입력 버튼 클릭 이벤트
        	$('#saveAndNextBtn').on('click', function(event) {
                $(this).prop('disabled', true);

                save();
            });
        	
        	// 정보보기 버튼 클릭 이벤트
        	$("#storeInfoViewBtn").on('click', function(event) {
        		$(this).prop('disabled', true);
        		
        		var storeId = $("#storeId").val();
        		window.location.href = '/store/info?storeId='+storeId;
            });
        	
        	// 삭제버튼 ( 초기화 기능 아닌가? )
        	$("#resetBtn").on("click", function(event) {
        		$(this).prop('disabled', true);

                reset();
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
            	console.log(response.data);
            	if(response.data > 0){
            		console.log(response.data);
               		// reportId를 쿼리 파라미터로 추가하여 페이지 이동
                    var reportId = $("#reportId").val();
                    window.location.href = "/report/save/business/district/analysis?reportId=" + reportId;
            	}
            	
            } else {
                // msg가 '0000'이 아닌 경우 처리
                console.error('Error: ' + response.msg);
            }
        }
        
        function save(){
        	console.log("save method");
        	var url = "/report/location/analysis/save";
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
            if (!addIfValid("reportId", $("#reportId").val())){}  									// 보고서 ID
            if (!addIfValid("locationAnalysisId", $("#locationAnalysisId").val())){}  				// 지역 입지 분석 ID
            if (!addIfValid("reportLocationAnalysisId", $("#reportLocationAnalysisId").val())){}  	// 리포트 지역 입지 분석 ID
            if (!addIfValid("areaId", $("#areaId").val())){}  										// 지역 ID

            // 아파트 정보
            if (!addIfValid("aprtmentHousehold", $("#aprtmentHousehold").val())){}  				// 세대 수
            if (!addIfValid("aprtmentTransactionPrice", $("#aprtmentTransactionPrice").val())){}  	// 실 거래가
            if (!addIfValid("aprtmentResidence", $("#aprtmentResidence").val())){}  				// 아파트 거주인구 비율

            // 유동인구
            if (!addIfValid("flowPopulation", $("#flowPopulation").val())){}  						// 일 평균 유동인구 수
            jsonItem["flowAgeRangeCd"] = $("#flowAgeRangeCd").val();								// 유동인구 연령대
            jsonItem["flowTimeStartCd"] = $("#flowTimeStartCd").val();								// 유동인구 최대 시작 시간대
            jsonItem["flowTimeEndCd"] = $("#flowTimeEndCd").val();									// 유동인구 최대 종료 시간대

            // 인구정보
            if (!addIfValid("residencePopulation", $("#residencePopulation").val())){}				// 주거 인구 수
            if (!addIfValid("workPopulation", $("#workPopulation").val())){} 						// 직장 인구 수
            if (!addIfValid("household", $("#household").val())){}									// 세대 수
            if (!addIfValid("business", $("#business").val())){}									// 업소 수
            if (!addIfValid("income", $("#income").val())){}										// 소득

            // 배달 정보
            jsonItem["deliveryGenderCd"] = $("#deliveryGenderCd").val();							// 배달 주 이용 성별
            jsonItem["deliveryAgeRangeCd"] = $("#deliveryAgeRangeCd").val();						// 배달 주 이용 연령대
            jsonItem["deliveryWeekCd"] = $("#deliveryWeekCd").val();								// 배달 주 이용 요일
            jsonItem["deliveryTimeStartCd"] = $("#deliveryTimeStartCd").val();						// 배달 주 이용 시작 시간대
            jsonItem["deliveryTimeEndCd"] = $("#deliveryTimeEndCd").val();							// 배달 주 이용 종료 시간대
            jsonItem["deliveryMethodCd"] = $("#deliveryMethodCd").val();							// 배달 주 이용 배달 앱
            
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
	<input type="hidden" id="reportId" name="reportId" value="${ locationAnalysis.reportId }"/>
	<input type="hidden" id="storeId" name="storeId" value="${ locationAnalysis.storeId }"/>
	<input type="hidden" id="locationAnalysisId" name="locationAnalysisId" value="${ locationAnalysis.locationAnalysisId }"/>
	<input type="hidden" id="reportLocationAnalysisId" name="reportLocationAnalysisId" value="${ locationAnalysis.reportLocationAnalysisId }">
	<input type="hidden" id="areaId" name="areaId" value="${ locationAnalysis.areaId }" />
    <div class="areport-mvp-none">
        <div class="container">
            <c:import url="/WEB-INF/views/jsp/layout/header.jsp" />
            <div class="divider">
            </div>
            <div class="title">
                <u>신규 리포트 생성</u> > ${ locationAnalysis.name } > 세부등록
            </div>
            <div class="areport-mvp">
                <div class="new">
                    <div class="tit left">등록정보</div>
                    <span class="new-day" id="displayRegDate">${ locationAnalysis.regDate }</span>
                    <span class="new-box-none">${ locationAnalysis.siNm }</span>
                    <span class="new-box-none">${ locationAnalysis.sggNm }</span>
                    <span class="new-box-none"><c:if test="${not empty locationAnalysis.emdNm}">${locationAnalysis.emdNm}</c:if><c:if test="${empty locationAnalysis.emdNm}">${locationAnalysis.liNm}</c:if></span>
                    <span class="new-box-none">${ locationAnalysis.mainCategoryName }</span>
                    <span class="new-box-none">${ locationAnalysis.subCategoryName }</span>
                    <button class="but2" id="storeInfoViewBtn" style="border:0px;">정보보기</button>
                </div>
                <div class="container-26">
                    %<c:if test="${not empty locationAnalysis.emdNm}">
					        ${locationAnalysis.emdNm}
					    </c:if>
					    <c:if test="${empty locationAnalysis.emdNm}">
					        ${locationAnalysis.liNm}
					    </c:if> 입지분석 정보등록
                </div>
                <div class="new">
                    <div class="tit2">아파트 정보</div>
                    <div>
                    	<input class="nex-box-use" type="text" id="aprtmentHousehold" name="aprtmentHousehold" value="" placeholder="세대수 <c:if test="${not empty locationAnalysis.aprtmentHousehold}">: ${locationAnalysis.aprtmentHousehold}</c:if><c:if test="${empty locationAnalysis.aprtmentHousehold}">입력(개)</c:if>"/>
                        <input class="nex-box-use" type="text" id="aprtmentTransactionPrice" name="aprtmentTransactionPrice" value="" placeholder="실 거래가 <c:if test="${not empty locationAnalysis.aprtmentTransactionPrice}"> : ${locationAnalysis.aprtmentTransactionPrice}</c:if><c:if test="${empty locationAnalysis.aprtmentTransactionPrice}">입력(원)</c:if>"/>
                        <input class="nex-box-use" type="text" id="aprtmentResidence" name="aprtmentResidence" value="" placeholder="아파트 거주인구 비율 <c:if test="${not empty locationAnalysis.aprtmentResidence}">(%) : ${locationAnalysis.aprtmentResidence}</c:if><c:if test="${empty locationAnalysis.aprtmentResidence}">입력(%)</c:if>"/>
                    </div>
                </div>
                <div class="new">
                    <div class="tit2">유동인구 정보</div>
                    <div>
                    	<input class="nex-box-use" type="text" id="flowPopulation" name="flowPopulation" value="" placeholder="일 평균 유동인구 수 <c:if test="${not empty locationAnalysis.flowPopulation}">: ${locationAnalysis.flowPopulation}</c:if><c:if test="${empty locationAnalysis.flowPopulation}">입력(명)</c:if>">
                        <div class="selectBoxList">
                            <!-- selectBox1 -->
                            <div class="box">
                                <div class="selectBox3">
                                    <select class="label select_renewal" id="flowAgeRangeCd" name="flowAgeRangeCd">
                                    	<option value="">최다 연령대</option>
                                    	<c:forEach var="ageGroup" items="${ageGroupList}">
									        <option value="${ageGroup.detailCd}" 
									            <c:if test="${ageGroup.detailCd == locationAnalysis.flowAgeRangeCd}">
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
                                    <select class="label select_renewal" id="flowTimeStartCd" name="flowTimeStartCd">
                                    	<option value="">최다 시간대 시작</option>
                                    	<c:forEach var="time" items="${timeList}">
									        <option value="${time.detailCd}" 
									            <c:if test="${time.detailCd == locationAnalysis.flowTimeStartCd}">
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
                                	<input type="hidden" id="flowTimeEndCdSelect" name="flowTimeEndCdSelect" value="${locationAnalysis.flowTimeEndCd}"/>
                                    <select class="label select_renewal" id="flowTimeEndCd" name="flowTimeEndCd">
                                    	<option value="">최다 시간대 종료</option>
                                    	<c:forEach var="time" items="${timeList}">
									        <option value="${time.detailCd}" 
									            <c:if test="${time.detailCd == locationAnalysis.flowTimeEndCd}">
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
                </div>
                <div class="new">
                    <div class="tit2">인구정보</div>
                    <div>
                    	<input class="nex-box-use" type="text" id="residencePopulation" name="residencePopulation" value="" placeholder="주거인구 수 <c:if test="${not empty locationAnalysis.residencePopulation}">: ${locationAnalysis.residencePopulation}</c:if><c:if test="${empty locationAnalysis.residencePopulation}">입력(명)</c:if>"/>
                    	<input class="nex-box-use" type="text" id="workPopulation" name="workPopulation" value="" placeholder="직장인구 수 <c:if test="${not empty locationAnalysis.workPopulation}">: ${locationAnalysis.workPopulation}</c:if><c:if test="${empty locationAnalysis.workPopulation}">입력(명)</c:if>"/>
                        <input class="nex-box-use" type="text" id="household" name="household" value="" placeholder="세대 수 <c:if test="${not empty locationAnalysis.household}">: ${locationAnalysis.household}</c:if><c:if test="${empty locationAnalysis.household}">입력(개)</c:if>"/>
                        <input class="nex-box-use" type="text" id="business" name="business" value="" placeholder="업소 수 <c:if test="${not empty locationAnalysis.business}">: ${locationAnalysis.business}</c:if><c:if test="${empty locationAnalysis.business}">입력(개)</c:if>"/>
                        <input class="nex-box-use" type="text" id="income" name="income" value="" placeholder="소득 <c:if test="${not empty locationAnalysis.income}">(원 &#8361;) : ${locationAnalysis.income}</c:if><c:if test="${empty locationAnalysis.income}">입력(원 &#8361;)</c:if>"/>
                    </div>

                </div>
                <div class="new">
                    <div class="tit2">배달정보</div>
                    <div class="selectBoxList">
                        <!-- selectBox1 -->
                        <div class="box">
                            <div class="selectBox3">
                            	<input type="hidden" id="deliveryGenderCdSelect" name="deliveryGenderCdSelect" value="${ locationAnalysis.deliveryGenderCd }">
                                <select class="label select_renewal" id="deliveryGenderCd" name="deliveryGenderCd">
                                	<option value="">주 이용성별</option>
                                	<c:forEach var="gender" items="${genderList}">
								        <option value="${gender.detailCd}" 
								            <c:if test="${gender.detailCd == locationAnalysis.deliveryGenderCd}">
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
                            	<input type="hidden" id="deliveryAgeRangeCdSelect" name="deliveryAgeRangeCdSelect" value="${ locationAnalysis.deliveryAgeRangeCd }">
                                <select class="label select_renewal" id="deliveryAgeRangeCd" name="deliveryAgeRangeCd">
                                	<option value="">주 이용 연령대</option>
                                	<c:forEach var="ageGroup" items="${ageGroupList}">
								        <option value="${ageGroup.detailCd}" 
								            <c:if test="${ageGroup.detailCd == locationAnalysis.deliveryAgeRangeCd}">
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
                            	<input type="hidden" id="deliveryWeekCdSelect" name="deliveryWeekCdSelect" value="${ locationAnalysis.deliveryWeekCd }">
                                <select class="label select_renewal" id="deliveryWeekCd" name="deliveryWeekCd">
                                	<option value="">주 이용 요일</option>
                                	<c:forEach var="week" items="${weekList}">
								        <option value="${week.detailCd}" 
								            <c:if test="${week.detailCd == locationAnalysis.deliveryWeekCd}">
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
                            	<input type="hidden" id="deliveryTimeStartCdSelect" name="deliveryTimeStartCdSelect" value="${ locationAnalysis.deliveryTimeStartCd }">
                                <select class="label select_renewal" id="deliveryTimeStartCd" name="deliveryTimeStartCd">
                                	<option value="">주 이용 시간대 시작</option>
                                	<c:forEach var="time" items="${timeList}">
								        <option value="${time.detailCd}" 
								            <c:if test="${time.detailCd == locationAnalysis.deliveryTimeStartCd}">
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
                            	<input type="hidden" id="deliveryTimeEndCdSelect" name="deliveryTimeEndCdSelect" value="${ locationAnalysis.deliveryTimeEndCd }">
                                <select class="label select_renewal" id="deliveryTimeEndCd" name="deliveryTimeEndCd">
                                	<option value="">주 이용 시간대 종료</option>
                                	<c:forEach var="time" items="${timeList}">
								        <option value="${time.detailCd}" 
								            <c:if test="${time.detailCd == locationAnalysis.deliveryTimeEndCd}">
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
                            	<input type="hidden" id="deliveryMethodCdSelect" name="deliveryMethodCdSelect" value="${ locationAnalysis.deliveryMethodCd }">
                                <select class="label select_renewal" id="deliveryMethodCd" name="deliveryMethodCd">
                                	<option value="">주 이용 배달 앱</option>
                                	<c:forEach var="delivery" items="${deliveryList}">
								        <option value="${delivery.detailCd}" 
								            <c:if test="${delivery.detailCd == locationAnalysis.deliveryMethodCd}">
								                selected
								            </c:if>>
								            ${delivery.detailCdName}
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
                        <button class="but2" id="saveAndNextBtn" style="border:0px;">저장&다음입력</button>
                    </div>
                </div>
            </div>

        </div>
        <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />
    </div>
</body>
</html>
