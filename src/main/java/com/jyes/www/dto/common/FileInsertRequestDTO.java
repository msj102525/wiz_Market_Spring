package com.jyes.www.dto.common;

import org.springframework.web.multipart.MultipartFile;

/*
 *  파일 불러오기
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.common.FileInsertrequestDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-22  |      박준태          |     최초 생성
 *  
 */

public class FileInsertRequestDTO {

	private MultipartFile multipartFile;

	private String imageUrl;

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
