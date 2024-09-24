package com.jyes.www.vo.report;

/*
 *  리포트 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.report.ReportVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class ReportVo {
	
	// report_id
    // report 시퀀스
    private Integer reportId;
    
    // store_id
    // store 시퀀스
    private Integer storeId;
    
    // url
    // URL
    private String url;
    
    // hits
    // 조회수
    private Integer hits;
    
    // is_exposure
    // 노출여부
    private String isExposed;
    
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

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public String getIsExposed() {
		return isExposed;
	}

	public void setIsExposed(String isExposed) {
		this.isExposed = isExposed;
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
