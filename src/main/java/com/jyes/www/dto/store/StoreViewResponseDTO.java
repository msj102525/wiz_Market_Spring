package com.jyes.www.dto.store;

/*
 *  파일 불러오기
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.common.StoreViewResponseDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-05  |      박준태          |     최초 생성
 *  
 */

public class StoreViewResponseDTO {
	
	private Integer storeId;
	
	private String name;
	
	private String startDate;
	
	private String displayStartDateDayPass;

	private String regDate;

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

	public String getDisplayStartDateDayPass() {
		return displayStartDateDayPass;
	}

	public void setDisplayStartDateDayPass(String displayStartDateDayPass) {
		this.displayStartDateDayPass = displayStartDateDayPass;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
