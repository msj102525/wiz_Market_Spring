function collectFormData(fields) {
	var formData = new FormData();
	for (var i = 0; i < fields.length; i++) {
        var $item = $(fields[i]);
        var name = $item.attr('name');
        
        console.log(name + " : " + $item.val());
        
        // name 속성이 없는 필드는 무시
        if (!name) {
            continue;
        }

        if ($item.attr('type') === "file") {
            // 파일 입력 필드 처리
            var files = $item.prop("files");
            for (var j = 0; j < files.length; j++) {
                formData.append(name, files[j]);
            }
        } else if ($item.is('select')) {
            // <select> 태그 처리
            var selectedValues = $item.val();
            
            // 단일 선택 또는 다중 선택 처리
            if (Array.isArray(selectedValues)) {
                for (var k = 0; k < selectedValues.length; k++) {
                    formData.append(name, selectedValues[k]);
                }
            } else {
                formData.append(name, selectedValues);
            }
        } else if ($item.attr('type') === "checkbox" || $item.attr('type') === "radio") {
            // 체크박스 및 라디오 버튼 처리
            if ($item.is(':checked')) {
                formData.append(name, $item.val());
            }
        } else {
            // 일반 입력 필드 처리
            formData.append(name, $item.val());
        }
    }
	
	return formData;
}
function moveUrl(url, isNotHistory) {
	if (isNotHistory === undefined || isNotHistory === null || isNotHistory.trim() === '') {
	    window.location.href = url;
        return;
    } 
	// URL을 변경하고 히스토리에 새 항목을 추가하지 않음
	window.location.replace(url);
}