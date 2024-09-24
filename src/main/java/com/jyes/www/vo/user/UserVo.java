package com.jyes.www.vo.user;

/*
 *  사용자 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.user.UserVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

// TODO : 유저테이블은 한번 더 수정이 필요!!!
public class UserVo {

	// user_id
	// user 시퀀스
	private Integer userId;

	// password
	// 비밀번호
	private String password;

	// is_deleted
	// 삭제여부
	private String isDeleted;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
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
