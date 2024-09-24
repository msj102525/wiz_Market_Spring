//jquery, jcrop used!!!

class Jcrop {
	
	is_crop;
	sel_files;
	jcrop_api;//크롭 API
	crop_width;//크롭 선택 영역 가로 크기
	crop_height;//크롭 선택 영역 세로 크기
	crop_num;
	file_input;
	file_index;
	
	parent_selecter_name;
	image_url_file_name;
	image_grop_data_name;
	get_image_data_url;
	
	image_select_callback;
	image_crop_callback;
	
    constructor(parent_selecter_name, image_url_file_name, image_grop_data_name, get_image_data_url, image_select_callback, image_crop_callback) {
    	this.parent_selecter_name = parent_selecter_name;
    	this.image_url_file_name = image_url_file_name;
    	this.image_grop_data_name = image_grop_data_name;
        this.get_image_data_url = get_image_data_url;
        this.image_select_callback = image_select_callback;
        this.image_crop_callback = image_crop_callback;
        this.is_crop = false;
        this.file_index = 0;
    }
    
    setFormInput(parent_selecter_name, image_url_file_name, image_grop_data_name) {
    	this.parent_selecter_name = parent_selecter_name;
    	this.image_url_file_name = image_url_file_name;
    	this.image_grop_data_name = image_grop_data_name;
    }

    fileUploadAction(index) {
    	
    	if (index === undefined || index === null || index === "") {
    		index = 0;
    	}
    	
    	this.file_index = index;
    	
    	let self = this; // `this`를 `self`에 저장
    	
    	var selector;
    	
    	if (this.parent_selecter_name.startsWith("#")) {
    		selector = $("[id='"+this.parent_selecter_name.slice(1)+"']").eq(index);
    	} else {
    		selector = $(this.parent_selecter_name).eq(index);
    	}
    	
		//생성만 되고 비어있는 파일 제거
		$(selector).find('input[name="'+this.image_url_file_name+'"]').each(function() {
    		if ($(this).val() === '') {
                $(this).remove();
            }
    	});
		
		//생성만 되고 비어있는 크롭용 이미지 데이터 제거
		$(selector).find('input[name="'+this.image_grop_data_name+'"]').each(function() {
    		if ($(this).val() === '') {
                $(this).remove();
            }
    	});
		
		//파일 input 생성
    	var file = '<input type="file" name="'+this.image_url_file_name+'" accept="image/gif, image/jpeg, image/png" style="display: none;"/> ';
    	$(selector).append(file);

    	//크롭용 이미지 데이터 input 생성
    	var image_date = '<input type="hidden" name="'+this.image_grop_data_name+'">';
    	$(selector).append(image_date);
    	
    	//생성한 파일 input 클릭 및 체인지 이벤트 생성
    	$(selector).find('input[name="'+this.image_url_file_name+'"]').each(function() {
            if ($(this).val() === '') {
            	self.file_input = $(this);
            	$(self.file_input).on("click", function() {
            		try {self.crop_close();} catch(e) {}
            		this.value = null;
                });
            	$(self.file_input).on("change", function(event) {
            	    self.handleImgFileSelect(event); // self를 사용하여 메서드를 호출
            	});
            	$(self.file_input).on('change', function() {
                	if($(self.file_input).val()!="") {
                		self.readURL(this);
                	}
                });
            	$(this).trigger('click');
            	return;
            }
        });
    	/*
    	return;
    	
    	//생성만 되고 비어있는 파일 제거
    	$(this.parent_selecter_name+' '+'input[name="'+this.image_url_file_name+'"]').each(function() {
            if ($(this).val() === '') {
                $(this).remove();
            }
        });
    	
    	//생성만 되고 비어있는 크롭용 이미지 데이터 제거
    	$(this.parent_selecter_name+' '+'input[name="'+this.image_grop_data_name+'"]').each(function() {
            if ($(this).val() === '') {
                $(this).remove();
            }
        });
    	
    	//파일 input 생성
    	var file = '<input type="file" name="'+this.image_url_file_name+'" accept="image/gif, image/jpeg, image/png" style="display: none;"/> ';
    	$(self.parent_selecter_name).append(file);

    	//크롭용 이미지 데이터 input 생성
    	var image_date = '<input type="hidden" name="'+this.image_grop_data_name+'">';
    	$(self.parent_selecter_name).append(image_date);
    	
    	//생성한 파일 input 클릭 및 체인지 이벤트 생성
    	$(this.parent_selecter_name+' '+'input[name="'+this.image_url_file_name+'"]').each(function() {
            if ($(this).val() === '') {
            	self.file_input = $(this);
            	$(self.file_input).on("click", function() {
            		try {self.crop_close();} catch(e) {}
            		this.value = null;
                });
            	$(self.file_input).on("change", function(event) {
            	    self.handleImgFileSelect(event); // self를 사용하여 메서드를 호출
            	});
            	$(self.file_input).on('change', function() {
                	if($(self.file_input).val()!="") {
                		self.readURL(this);
                	}
                });
            	$(this).trigger('click');
            	return;
            }
        });
    	*/
    }
    
    handleImgFileSelect(e) {
    	let self = this; // `this`를 `self`에 저장
        // 이미지 정보들을 초기화
    	self.sel_files = [];
        var files = e.target.files;
        var filesArr = Array.prototype.slice.call(files);
        if(files.length==0) {//이미지 영역 초기화시 호출 되는 것 방지
        	//alert("파일 없음");
        } else {
        	//로딩이 호출이 안되서 비동기 추가
    		setTimeout(function() {
    	        var index = 0;
    	        filesArr.forEach(function(f) {
    	            if(!f.type.match("image.*")) {
    	                alert("확장자는 이미지 확장자만 가능합니다.");
    	                return;
    	            }
    	            self.sel_files.push(f);
    	            var reader = new FileReader();
    	            reader.onload = function(e) {
    	                if(self.is_crop) {
	    	            	var html = '<img src="' + e.target.result + '" id="crop_img" data-file="'+f.name+'">';
	    	                $("#to_html").append(html);
	    	        		
	    	        		$('#img_crop').css("display", "inline");
	    	                
	    	        		//이미지 크기가 html 크기보다 크면 html 크기 세팅(width, height)
	    	        		var width_html = $("html").width();
	    	                var width_crop_img = $("#crop_img").width();
	    	                var width = (width_crop_img>width_html)?width_html:width_crop_img;
	    	                $("#img_crop").width(width);
	    	                self.crop_width = width/2;
	    					
	    	                var height_html = $("html").height()
	    	                var height_crop_img = $("#crop_img").height();
	    	                var height = (height_crop_img>height_html)?height_html:height_crop_img;
	    	                $("#img_crop").height(height);
	    	                self.crop_height = height/2;
        	                self.onCrop();
    	                }
    	            }
    	            reader.readAsDataURL(f);
    	        });
    		}, 500);
        }
    }

    onCrop() {
    	let self = this; // `this`를 `self`에 저장
    	setTimeout(function(){//처음 호출시 이미지 못불러 오는 현상 해결(크롬)
    		self.initJcrop(self.crop_width, self.crop_height);
    	}, 0);
    }

    readURL(input) {
    	let self = this; // `this`를 `self`에 저장
        if (input.files && input.files[0]) {
        	var reader = new FileReader();
        	reader.onload = function (e) {
        		self.image_select_callback(e.target.result)
    		}
    		reader.readAsDataURL(input.files[0]);
        }
    }

    initJcrop(w, h) {
    	let self = this; // `this`를 `self`에 저장
    	self.jcrop_api = $.Jcrop('#crop_img', {
    		onChange : self.setCoords,
    		onSelect : self.setCoords
    	});
    	self.jcrop_api.setSelect([ 0, 0, w, h ]);
    	self.jcrop_api.setOptions({
    		bgFade : true,
    		allowSelect : false,
    		allowMove : true,
    		allowResize : true
    	});
    }

    setCoords(c) {
    	let self = this; // `this`를 `self`에 저장
    	jQuery('#x1').val(c.x);
    	jQuery('#y1').val(c.y);
    	jQuery('#x2').val(c.x2);
    	jQuery('#y2').val(c.y2);
    	jQuery('#w').val(c.w);
    	jQuery('#h').val(c.h);
    }

    crop_close(clear) {
    	let self = this; // `this`를 `self`에 저장
    	if(clear=='Y') {
    		$('#image_url_'+self.crop_num).val("");
    	}
    	$('#img_crop').css("display", "none");  
    	$("#to_html").empty();
    	self.jcrop_api.release();
    }

    cropImage(){
    	let self = this; // `this`를 `self`에 저장
    	self.getImageData();
    }
    
    delImage(index) {
    	
    	var selector;
    	
    	if (this.parent_selecter_name.startsWith("#")) {
    		selector = $("[id='"+this.parent_selecter_name.slice(1)+"']").eq(index);
    	} else {
    		selector = $(this.parent_selecter_name).eq(index);
    	}
    	
		//생성 되어있는 file_input 삭제
    	$(selector).find('input[name="'+file_input_name+'"]').eq(index).remove();
		//생성 되어있는 crop_data_input 삭제
    	$(selector).find('input[name="'+crop_data_input_name+'"]').eq(index).remove();
    }

    getImageData() {
    	let self = this; // `this`를 `self`에 저장
    	var form = $('#cropFrm, '+self.parent_selecter_name+'');
    	var fields = form.find('input');
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
    	for(var i=0, len=self.sel_files.length; i<len; i++) {
    		var name = "image_"+i;
    		formData.append(name, self.sel_files[i]);
    	}
    	$.ajax({
    		url  : self.get_image_data_url,
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
    		success : function(json) {
    			if(json.msg == '1') {
        			self.image_crop_callback(json.data);
        			//크롬 이미지 데이터 input 세팅
        			$(self.parent_selecter_name+' '+'input[name="'+self.image_grop_data_name+'"]').last().val(json.data);
    				self.crop_close();
    			} else if(json.msg == '2') {
    				alert('올바른 파일 형식이 아닙니다 . 이미지 파일 형식을 다시 확인해주세요 .');
    			} else if(json.msg == '3') {
    				alert('용량을 초과 하였습니다 . 이미지 용량을 다시 확인해주세요 .');
    			} else {
    				alert('Data 오류');
    			}
    		},
    		complete : function(){
    			//imgResize();
    		}
    	});
    }
    
    init() {
    	if($('#cropFrm').size()==0) {
        	var jcrop_form_html = "";
        	jcrop_form_html += '<form name="cropFrm" id="cropFrm">';
        	jcrop_form_html += '<input type="hidden" name="x1" id="x1" value="0" />';
        	jcrop_form_html += '<input type="hidden" name="y1" id="y1" value="0" />'; 
        	jcrop_form_html += '<input type="hidden" name="x2" id="x2" value="0" />';
        	jcrop_form_html += '<input type="hidden" name="y2" id="y2" value="0" />';
        	jcrop_form_html += '<input type="hidden" name="w" id="w" value="0" />';
        	jcrop_form_html += '<input type="hidden" name="h" id="h" value="0" />';
        	jcrop_form_html += '</form>';
        	$('body').append(jcrop_form_html);
        	
        	var jcrop_poup_html = "";
        	jcrop_poup_html += '<div class="popup_bg" id="img_crop" style="display: none; position: absolute; top:0; left:0; z-index:9999;">';
        	jcrop_poup_html += '<div class="popup_title">';
        	jcrop_poup_html += '<input type="button" style="margin-top:9px;" class="btn_suc" value="이미지 저장" onclick="javascript:jCrop.cropImage();">';
        	jcrop_poup_html += '<input type="button" style="margin-top:9px;" class="btn_suc" value="닫기" onclick="javascript:jCrop.crop_close(\'Y\');">';
        	jcrop_poup_html += '</div>';
        	jcrop_poup_html += '<div class="pop" id="to_html"></div>';
        	jcrop_poup_html += '</div>';
        	$('body').append(jcrop_poup_html);
    	}
    };
}


