package com.jyes.www.vo.store;

import java.util.Date;

/*
 *  매장 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.store.StoreVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class StoreVo {
	
	// store_id
	// 매장 시퀀스
	private Integer storeId;
	
	// name
	// 매장 이름
	private String name;
	
	// start_date
	// 업종 시작 일자 (ex: 2024-07-10)
	private String startDate;
	
	// is_deleted
	// 삭제 여부
	private String isDeleted;
	
	// etc
	// 비고
	private String etc;
	
	// reg_id
	// 생성자 user 시퀀스
	private Integer regId;
	
	// reg_date
	// 생성일시
	private String regDate;
	
	// mod_id
	// 수정자 user 시퀀스
	private Integer modId;
	
	// mod_date
	// 수정일시
	private String modDate;

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

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Integer getModId() {
		return modId;
	}

	public void setModId(Integer modId) {
		this.modId = modId;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	
}
