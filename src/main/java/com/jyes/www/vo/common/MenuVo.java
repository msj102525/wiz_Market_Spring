package com.jyes.www.vo.common;

/*
 *  메뉴 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.common.MenuVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class MenuVo {
	
	// menu_id
	// menu 시퀀스
	private Integer menuId;

	// parent_menu_id
	// 부모 menu 시퀀스
	private Integer parentMenuId;

	// name
	// 메뉴 명
	private String name;

	// navigator
	// 메뉴 위치
	private String navigator;

	// url
	// URL
	private String url;

	// seq
	// 순서
	private Integer seq;

	// is_use
	// 사용여부
	private String isUsed;

	// is_deleted
	// 삭제여부
	private String isDeleted;

	// etc
	// 비고
	private String etc;

	// reg_id
	// 작성자 user 시퀀스
	private Integer regId;

	// reg_date
	// 작성일시
	private String regDate;

	// mod_id
	// 수정자 user 시퀀스
	private Integer modId;

	// mod_date
	// 수정일시
	private String modDate;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNavigator() {
		return navigator;
	}

	public void setNavigator(String navigator) {
		this.navigator = navigator;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
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
