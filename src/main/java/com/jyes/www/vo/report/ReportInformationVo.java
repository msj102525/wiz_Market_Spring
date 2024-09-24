package com.jyes.www.vo.report;

/*
 *  리포트 정보 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.report.ReportInformationVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *    2024-07-18  |      박준태          | 자주쓰는질문 사용하였을 때, 수정하면 전체 수정 될 때 대비하여 common_information_id 추가
 *    2024-07-19  |      박준태          | common_information_id -> faq_title_id로 변경
 *  
 */

public class ReportInformationVo {
	
	// report_information_id
    // report information 시퀀스
    private Integer reportInformationId;

    // common_information_id
    // common information id 시퀀스
    private Integer commonInformationId;

    // report_id
    // report 시퀀스
    private Integer reportId;

    // title
    // 제목
    private String title;

    // content
    // 내용
    private String content;

    // file_group_id
    // 파일 그룹 시퀀스
    private Integer fileGroupId;

    // view_seq
    // 뷰 순서
    private Integer viewSeq;

    // view_is_hid
    // 숨김여부
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

	public Integer getReportInformationId() {
		return reportInformationId;
	}

	public void setReportInformationId(Integer reportInformationId) {
		this.reportInformationId = reportInformationId;
	}

	public Integer getCommonInformationId() {
		return commonInformationId;
	}

	public void setCommonInformationId(Integer commonInformationId) {
		this.commonInformationId = commonInformationId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
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
