package com.jyes.www.vo.common;

/*
 *  파일 그룹 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.common.FileGroupVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class FileGroupVo {

	// file_group_id
    // file group 시퀀스
    private Integer fileGroupId;
    
    // reg_id
    // 작성자 user 시퀀스
    private Integer regId;
    
    // reg_date
    // 작성일시
    private String regDate;

	public Integer getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(Integer fileGroupId) {
		this.fileGroupId = fileGroupId;
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

}
