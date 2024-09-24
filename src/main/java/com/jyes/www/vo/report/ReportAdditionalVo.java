package com.jyes.www.vo.report;

/*
 *  리포트 추가 정보 테이블
 *  
 *  역할 : Value Object
 *  위치 : com.jyes.www.vo.report.ReportAdditionalVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class ReportAdditionalVo {
	
	// report_additional_id
	// report additional 시퀀스
	private Integer reportAdditionalId;

	// report_id
	// report 시퀀스
	private Integer reportId;

	// area_id
	// area 시퀀스
	private Integer areaId;

	// main_category_id
	// 대분류 category 시퀀스
	private Integer mainCategoryId;

	// sub_category_id
	// 중분류 category 시퀀스
	private Integer subCategoryId;

	// addr
	// 주소지
	private String addr;

	// addr_detail
	// 상세 주소
	private String addrDetail;

	// online_url_category_cd
	// 온라인 URL 종류 코드
	private String onlineUrlCategoryCd;

	// online_url
	// 온라인 URL
	private String onlineUrl;

	// sales
	// 매출
	private Double sales;

	// file_group_id
	// 파일 그룹 시퀀스
	private Integer fileGroupId;

	// view_seq
	// 뷰 순서
	private Integer viewSeq;

	// is_changed
	// 변경여부
	private String isChanged;

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

	public Integer getReportAdditionalId() {
		return reportAdditionalId;
	}

	public void setReportAdditionalId(Integer reportAdditionalId) {
		this.reportAdditionalId = reportAdditionalId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getMainCategoryId() {
		return mainCategoryId;
	}

	public void setMainCategoryId(Integer mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public String getOnlineUrlCategoryCd() {
		return onlineUrlCategoryCd;
	}

	public void setOnlineUrlCategoryCd(String onlineUrlCategoryCd) {
		this.onlineUrlCategoryCd = onlineUrlCategoryCd;
	}

	public String getOnlineUrl() {
		return onlineUrl;
	}

	public void setOnlineUrl(String onlineUrl) {
		this.onlineUrl = onlineUrl;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
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

	public String getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(String isChanged) {
		this.isChanged = isChanged;
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
