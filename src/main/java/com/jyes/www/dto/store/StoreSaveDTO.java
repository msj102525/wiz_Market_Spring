package com.jyes.www.dto.store;

/*
 *  파일 불러오기
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.common.StoreSaveDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-22  |      박준태          |     최초 생성
 *    2024-07-25  |      박준태          |     insertDTO -> saveDTO 등록, 수정 하나로 합침 
 *  
 */

public class StoreSaveDTO {
	
	private Integer storeId;

	private String name;
	
	private String startDate;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
