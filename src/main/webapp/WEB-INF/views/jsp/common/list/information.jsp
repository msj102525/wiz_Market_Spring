<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공통정보 목록</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/jquery.Jcrop.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/tab.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/css/areport-mvp_style.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.jcrop.common.js"></script>
<script type="text/javascript" src="<%=path%>/js/common.js"></script>
<script type="text/javascript">
function setSearchData() {
	var curpage = (arguments[0] == null) ? "" : arguments[0];
	var blockCount = (arguments[1] == null) ? "" : arguments[1];
	var searchKeyword = (arguments[2] == null) ? "" : arguments[2];
	$('#curpage').val(curpage);
	$('#blockCount').val(blockCount);
	$('#searchKeyword').val(searchKeyword);
}
function search() {
	var curpage = "1";
	var blockCount = $('#blockCount').val();
	var searchKeyword = $('#searchKeyword').val();
	setSearchData(curpage, blockCount, searchKeyword);
	$('#searchFrm').attr('action', '<%=path%>/common/list/information/view').submit();
}

function pageLink(url , curpage) {
	var curpage = curpage;
	var blockCount = $('#blockCount').val();
	var searchKeyword = $('#searchKeyword').val();
	setSearchData(curpage, blockCount, searchKeyword);
	$('#searchFrm').attr("action", url).submit();
}
</script>
</head>
<body>
<!-- 공통정보 목록 파라미터 -->

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
            
				<div id="tab02">
                    <div class="areport-mvp">
                        <div class="container-6">
                            <div>
								<form class="search-box" name="searchFrm" id="searchFrm" method="GET">
								<input type="hidden" name="curpage" id="curpage" value="<c:out value="${curpage }" />">
								<input type="hidden" name="blockCount" id="blockCount" value="<c:out value="${blockCount }" />">
								<input type="hidden" name="commonInformationId" id="commonInformationId" value="">
                                <input class="search-txt" type="text" placeholder="검색할 내용을 입력해 주세요." name="searchKeyword" id="searchKeyword" value="<c:out value="${searchKeyword }" />">
                                <button class="search-btn" type="button" onclick="javascript:search();">
                                    <img class="search-1" src="<%=path%>/assets/vectors/search.svg" />
                                </button>
								</form>
                            </div>
                        </div>
                        <div class="container-3">
                            <div class="frame-1261152495">
                            
                                <div class="frame-1261152492">
                                    <span class="report-id">No</span>
                                    <span class="tablehead name">제목</span>
                                    <span class="tablehead img">이미지</span>
                                    <span class="tablehead day">등록일</span>
                                </div>

                                <div class="rectangle-224"></div>
                                
                                <c:set var="disp_page" value="${curpage - 1}" />
								<c:set var="disp_page" value="${disp_page * blockCount}" />
								<c:set var="disp_page" value="${totalCnt - disp_page}" />
								<c:forEach items="${commonInfoList }" var="vo" varStatus="status">
								<div class="frame-1261152496">
                                    <div class="frame-1261152493">
                                        <div class="container-9"><c:out value="${disp_page}" /><c:set var="disp_page" value="${disp_page - 1}" /></div>
                                        <div class="container-10"><a href="<%=path%>/common/information?commonInformationId=${vo.commonInformationId }&searchKeyword=${searchKeyword }&curpage=${curpage }&blockCount=${blockCount }">${vo.title }</a></div>
                                        <div class="container-11"><c:if test="${not empty vo.fileGroupId }"><img class="vector" src="<%=path%>/assets/vectors/image.svg" /></c:if></div>
                                        <div class="container-12"><fmt:parseDate value="${vo.regDate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" /><fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm" /></div>
                                    </div>
                                    <div class="rectangle-161"></div>
                                </div>
								</c:forEach>
                                
                            </div>
                        </div>
                        
                        <c:out value="${paging_html}"  escapeXml="false"/>
                        
                    </div>

                </div>
			
			</div>
        </div><!--tab-->
    </div>
    
    <c:import url="/WEB-INF/views/jsp/layout/left.jsp" />

</div>


</body>
</html>