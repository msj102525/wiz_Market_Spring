<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<title>Home</title>
<script src="<%=path%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
/**
 * IE 10 부터 지원
 */
function collectFormData(fields) {
	var formData = new FormData();
	for (var i = 0; i < fields.length; i++) {
		var $item = $(fields[i]);
		if ($item.attr('type') =="file") {
			var file = $item.prop("files")[0];
			formData.append($item.attr('name') , file);
		} else {
			formData.append($item.attr('name') , $item.val());
		}
	}
	return formData;
}
function ajax() {
	var form = $('#form_id');
	var fields = form.find('input,textarea');
	var formData = collectFormData(fields);
	$.ajax({
		url  : '<%=path%>/formData',
		type : 'POST',
		enctype : "multipart/form-data",
		scriptCharset : "utf-8",
		data : formData,
		async : true,
		cache : false,
		contentType : false,
		processData : false,
		timeout: 600000,
		dataType : "json",
		success : function(data) {
			if(data.msg == "0" ) {
				alert("성공");
			} else {
				alert("실패");
			}
		}
	});
}
</script>
</head>
<body>
	<h1>Hello world!</h1>
	<P>The time on the server is ${serverTime}.</P>
	<c:forEach var="sample" items="${sampleList}" varStatus="status">
		<c:if test="${status.count < 2}">
			<c:out value="${status.count }" />
			<c:out value="${sample.getUser_name()}" />
			<br />
		</c:if>
	</c:forEach>
	<form id="form_id">
		<input type="hidden" id="input_id" name="input_id" value="input_id 한글" />
		<input type="file" id="input_file" name="input_file" /> <br />
		<a href="javascript:void(0);" onclick="ajax();return false;">AJAX 테스트</a>
	</form> 
</body>
</html>