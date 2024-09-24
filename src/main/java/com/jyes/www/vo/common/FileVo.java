package com.jyes.www.vo.common;

/*
 *  파일 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.common.FileVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class FileVo {

	// file_id
	// file 시퀀스
	private Integer fileId;

	// file_group_id
	// file group 시퀀스
	private Integer fileGroupId;

	// original_name
	// 파일 원본명
	private String originalName;

	// save_path
	// 파일 저장 위치
	private String savePath;

	// save_name
	// 파일 저장 이름
	private String saveName;

	// url
	// URL
	private String url;

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

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Integer getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(Integer fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
