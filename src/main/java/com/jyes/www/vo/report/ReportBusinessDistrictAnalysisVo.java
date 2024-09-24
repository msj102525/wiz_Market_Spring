package com.jyes.www.vo.report;

/*
 *  리포트 지역 상권 분석 관계 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.report.ReportBusinessDistrictAnalysisVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class ReportBusinessDistrictAnalysisVo {
	
	// report_business_district_analysis_id
	// report business district analysis 시퀀스
	private Integer reportBusinessDistrictAnalysisId;

	// report_id
	// report 시퀀스
	private Integer reportId;

	// business_district_analysis_id
	// business district analysis 시퀀스
	private Integer businessDistrictAnalysisId;

	// view_seq
	// 뷰 순서
	private Integer viewSeq;

	// view_is_hid
	// 뷰 숨김여부
	private String viewIsHid;

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

	public Integer getReportBusinessDistrictAnalysisId() {
		return reportBusinessDistrictAnalysisId;
	}

	public void setReportBusinessDistrictAnalysisId(Integer reportBusinessDistrictAnalysisId) {
		this.reportBusinessDistrictAnalysisId = reportBusinessDistrictAnalysisId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getBusinessDistrictAnalysisId() {
		return businessDistrictAnalysisId;
	}

	public void setBusinessDistrictAnalysisId(Integer businessDistrictAnalysisId) {
		this.businessDistrictAnalysisId = businessDistrictAnalysisId;
	}

	public Integer getViewSeq() {
		return viewSeq;
	}

	public void setViewSeq(Integer viewSeq) {
		this.viewSeq = viewSeq;
	}

	public String getViewIsHid() {
		return viewIsHid;
	}

	public void setViewIsHid(String viewIsHid) {
		this.viewIsHid = viewIsHid;
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
