<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>레포트공통정보</title>
<link rel="stylesheet" href="<%=path%>/css/jquery.Jcrop.css" type="text/css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script type="text/javascript"  src="<%=path%>/js/jquery.Jcrop.js"></script>
<script type="text/javascript"  src="<%=path%>/js/jquery.jcrop.common.js"></script>
<script type="text/javascript"  src="<%=path%>/js/common.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	get_image_data_url = "<%=path %>/common/getJCropImageData";
	setImageEvent('0');
	setImageEvent('1');
	setImageEvent('2');
	setImageEvent('3');
});
function save() {
	var form = $('#searchFrm');
	var fields = form.find('input');
	var formData = collectFormData(fields);
	$.ajax({
		url  : "<%=path %>/common/setJCropImageData",
		type : 'POST',
		data : formData,
		async : true,
		contentType : false,//formData 전송시 필수
		processData : false,//formData 전송시 필수
		dataType : "json",
		success : function(data) {
			alert("완료");
		}
	});
}
</script>
</head>
<body>
<form name="searchFrm" id="searchFrm" method="POST">
<%@ include file="/WEB-INF/views/jsp/common/link_info.jsp" %>
<br/>
레포트공통정보
<br/>
<input type="button" class="btn_blue" value="이미지 불러오기" onclick="javascript:fileUploadAction('0');">
<input type="button" class="btn_blue" value="이미지 불러오기" onclick="javascript:fileUploadAction('1');">
<input type="button" class="btn_blue" value="이미지 불러오기" onclick="javascript:fileUploadAction('2');">
<input type="button" class="btn_blue" value="이미지 불러오기" onclick="javascript:fileUploadAction('3');">
<img id="image_url_img_0" style="width:100px; hegith:100px; display: none;" />
<img id="image_url_img_1" style="width:100px; hegith:100px; display: none;" />
<img id="image_url_img_2" style="width:100px; hegith:100px; display: none;" />
<img id="image_url_img_3" style="width:100px; hegith:100px; display: none;" />
<div id="crop_input_elements" style="display: none;"></div>
<br/>
<a href="/report/common_info">삭제(레포트공통정보등록)</a><br/>
<a href="/report/common_info" onclick="javascript:save();return false;">저장(레포트공통정보등록)</a>저장후 공통목록으로 이동<br/>
</form>
</body>
</html>