package com.jyes.www.vo.common;

/*
 *  코드 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.common.CodeVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class CodeVo {

	// group_cd
	// 그룹코드
	private String groupCd;

	// detail_cd
	// 상세코드
	private String detailCd;

	// group_cd_name
	// 그룹코드 명칭
	private String groupCdName;

	// detail_cd_name
	// 상세명칭
	private String detailCdName;

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

	public String getGroupCd() {
		return groupCd;
	}

	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}

	public String getDetailCd() {
		return detailCd;
	}

	public void setDetailCd(String detailCd) {
		this.detailCd = detailCd;
	}

	public String getGroupCdName() {
		return groupCdName;
	}

	public void setGroupCdName(String groupCdName) {
		this.groupCdName = groupCdName;
	}

	public String getDetailCdName() {
		return detailCdName;
	}

	public void setDetailCdName(String detailCdName) {
		this.detailCdName = detailCdName;
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
