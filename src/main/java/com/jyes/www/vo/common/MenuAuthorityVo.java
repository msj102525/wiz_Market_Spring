package com.jyes.www.vo.common;

/*
 *  메뉴 권한 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.common.MenuAuthorityVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class MenuAuthorityVo {

	// menu_authority_id
	// menu authority 시퀀스
	private Integer menuAuthorityId;

	// menu_id
	// menu 시퀀스
	private Integer menuId;

	// group_id
	// group 시퀀스
	private Integer groupId;

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

	public Integer getMenuAuthorityId() {
		return menuAuthorityId;
	}

	public void setMenuAuthorityId(Integer menuAuthorityId) {
		this.menuAuthorityId = menuAuthorityId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
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
