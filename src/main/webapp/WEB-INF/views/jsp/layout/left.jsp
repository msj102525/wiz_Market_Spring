<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<div class="lnb">
    <div class="frame-13">
        <span class="title-2">Wiz market</span>
    </div>
    <div class="frame-5">
        <a href="<%=path %>/report/list">
            <div class="rinews-line">
                <img class="vector" src="<%=path%>/assets/vectors/vector_32_x2.svg">
            </div>
            <span class="sidebar-menu<c:choose><c:when test="${leftCode eq '0'}"></c:when><c:otherwise>-1</c:otherwise></c:choose>">리포트 목록</span>
        </a>
    </div>
    <div class="frame-1">
        <a href="<%=path %>/store/list">
            <div class="rinews-line">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" class="common-add">
                    <path d="M19.148 2.971C18.9698 2.67552 18.7185 2.43095 18.4183 2.26087C18.118 2.09079 17.779 2.00095 17.434 2H6.566C5.868 2 5.211 2.372 4.852 2.971L2.143 7.485C2.04926 7.64041 1.99981 7.8185 2 8C2.00399 8.96521 2.35949 9.89591 3 10.618V19C3 20.103 3.897 21 5 21H19C20.103 21 21 20.103 21 19V10.618C21.6405 9.89591 21.996 8.96521 22 8C22.0002 7.8185 21.9507 7.64041 21.857 7.485L19.148 2.971ZM19.984 8.251C19.9223 8.73382 19.6868 9.17764 19.3217 9.49952C18.9566 9.8214 18.4867 9.99931 18 10C16.897 10 16 9.103 16 8C16 7.932 15.975 7.872 15.961 7.808L15.981 7.804L15.22 4H17.434L19.984 8.251ZM10.819 4H13.18L13.993 8.065C13.958 9.137 13.08 10 12 10C10.92 10 10.042 9.137 10.007 8.065L10.819 4ZM6.566 4H8.78L8.02 7.804L8.04 7.808C8.025 7.872 8 7.932 8 8C8 9.103 7.103 10 6 10C5.51325 9.99931 5.04341 9.8214 4.67828 9.49952C4.31315 9.17764 4.07772 8.73382 4.016 8.251L6.566 4ZM10 19V16H14V19H10ZM16 19V16C16 14.897 15.103 14 14 14H10C8.897 14 8 14.897 8 16V19H5V11.858C5.321 11.941 5.652 12 6 12C6.56782 12.0003 7.12915 11.8794 7.6465 11.6454C8.16386 11.4114 8.6253 11.0696 9 10.643C9.733 11.475 10.807 12 12 12C13.193 12 14.267 11.475 15 10.643C15.3747 11.0696 15.8361 11.4114 16.3535 11.6454C16.8708 11.8794 17.4322 12.0003 18 12C18.348 12 18.679 11.941 19 11.858V19H16Z" fill="black"></path>
                </svg>
            </div>
            <div class="sidebar-menu<c:choose><c:when test="${leftCode eq '1'}"></c:when><c:otherwise>-1</c:otherwise></c:choose>">매장 목록</div>
        </a>
    </div>

    <div class="frame-1">
        <a href="<%=path %>/report/save/list">
            <div class="rinews-line">
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" class="common-add">
                    <path d="M5 19V5H12V12H19V13C19.7 13 20.37 13.13 21 13.35V9L15 3H5C3.89 3 3 3.89 3 5V19C3 19.5304 3.21071 20.0391 3.58579 20.4142C3.96086 20.7893 4.46957 21 5 21H13.35C13.13 20.37 13 19.7 13 19H5ZM14 4.5L19.5 10H14V4.5ZM23 18V20H20V23H18V20H15V18H18V15H20V18H23Z" fill="black"></path>
                </svg>
            </div>
            <div class="sidebar-menu<c:choose><c:when test="${leftCode eq '2'}"></c:when><c:otherwise>-1</c:otherwise></c:choose>">신규 리포트 생성</div>
        </a>
    </div>
    <div class="frame-1">
        <a href="<%=path %>/common/information">
            <div class="rinews-line">
                <img class="common-add" src="<%=path%>/assets/vectors/common_add_5_x2.svg">
            </div>
            <div class="sidebar-menu<c:choose><c:when test="${leftCode eq '3'}"></c:when><c:otherwise>-1</c:otherwise></c:choose>">공통정보 등록 | 목록</div>
        </a>
    </div>

    <div class="frame-14" style="visibility: hidden;">
        <span class="support">관리</span>
    </div>
    
    <div class="frame-15" style="visibility: hidden;">
        <div class="risurvey-line">
            <img class="vector-4" src="<%=path%>/assets/vectors/vector_3_x2.svg">
        </div>
        <div class="sidebar-menu-3">
            <a href="javascript:;">설문조사 작성 | 목록</a>
        </div>
    </div>
    
    <div class="frame-16" style="visibility: hidden;">
        <div class="footer">
            <img class="vector-5" src="<%=path%>/assets/vectors/vector_2_x2.svg">
        </div>
        <div class="sidebar-menu-4">
            <a href="javascript:;">푸터 설정</a>
        </div>
    </div>
    
    <div class="line-20"></div>
    
    <div class="frame-10">
        <div class="frame-11">
            <div class="kakao-talk-photo-202401111255031">
            </div>
        </div>
        <div class="name">Administrator</div>
        <span class="name-1">raraavis2@naver.com</span>
    </div>
    
</div>
