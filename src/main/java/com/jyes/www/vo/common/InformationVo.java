package com.jyes.www.vo.common;

/*
 *  공통 정보 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.common.InformationVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-18  |      박준태          |     최초 생성
 *  
 */

public class InformationVo {
	
	// common_information_id
    // common information 시퀀스
    private Integer commonInformationId;

    // title
    // 제목
    private String title;

    // content
    // 내용
    private String content;

    // file_group_id
    // 파일 그룹 시퀀스
    private Integer fileGroupId;

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

	public Integer getCommonInformationId() {
		return commonInformationId;
	}

	public void setCommonInformationId(Integer commonInformationId) {
		this.commonInformationId = commonInformationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(Integer fileGroupId) {
		this.fileGroupId = fileGroupId;
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
