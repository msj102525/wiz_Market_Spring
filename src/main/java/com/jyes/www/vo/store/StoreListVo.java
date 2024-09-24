package com.jyes.www.vo.store;

import java.util.Date;

/*
 *  매장 목록 오브젝트
 *  
 *  역할 : List View Object
 *  위치 : com.jyes.www.vo.store.StoreVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-11  |      박준태          |     최초 생성
 *    2024-07-18  |      박준태          |    컬럼 위치 변경       
 *  
 */

public class StoreListVo {
	
	// store_id
	// 매장 시퀀스
	private Integer storeId;
	
	// name
	// 매장 이름
	private String name;
	
	// start_date
	// 업종 시작 일자 (ex: 2024-07-10)
	private String startDate;
	
	// 주소
	private String addr;
	
	// reg_date
	// 생성일시
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

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

}
