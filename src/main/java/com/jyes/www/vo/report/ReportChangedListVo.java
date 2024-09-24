package com.jyes.www.vo.report;

import java.util.List;

import com.jyes.www.vo.common.FileVo;

/*
 *  
 *  변경 이력이 있는 리포트 (추가 정보) 목록 Data
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.vo.report.ReportChangedListVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-18  |      박준태          |     최초 생성
 *  
 */

public class ReportChangedListVo {

	// report_id
	// report 시퀀스
	private Integer reportId;
	
	// report_additional_id
	// report additional 시퀀스
	private Integer reportAdditionalId;

	// main_category_id
	// 대분류 category 시퀀스
	private Integer mainCategoryId;
	
	// main_category_name
	// 대분류 category 명
	private String mainCategoryName;

	// sub_category_id
	// 중분류 category 시퀀스
	private Integer subCategoryId;
	
	// sub_category_name
	// 중분류 category 명
	private String subCategoryName;

	// addr
	// 주소지
	private String addr;

	// addr_detail
	// 상세 주소
	private String addrDetail;
	
	// si_nm
	// 시도명
	private String siNm;

	// sgg_nm
	// 시군구명
	private String sggNm;

	// emd_nm
	// 읍면동명
	private String emdNm;

	// li_nm
	// 법정리명
	private String liNm;

	// rn
	// 도로명
	private String rn;

	// online_url_category_cd
	// 온라인 URL 종류 코드
	private String onlineUrlCategoryCd;

	// online_url
	// 온라인 URL
	private String onlineUrl;

	// sales
	// 매출
	private Long sales;
	
	private String displaySales;

	// file_group_id
	// 파일 그룹 시퀀스
	private Integer fileGroupId;

	// reg_date
	// 작성일시
	private String regDate;
	
	// 이미지 정보 가져오기
	private List<FileVo> fileList;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getReportAdditionalId() {
		return reportAdditionalId;
	}

	public void setReportAdditionalId(Integer reportAdditionalId) {
		this.reportAdditionalId = reportAdditionalId;
	}

	public Integer getMainCategoryId() {
		return mainCategoryId;
	}

	public void setMainCategoryId(Integer mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}

	public String getMainCategoryName() {
		return mainCategoryName;
	}

	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
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

	public String getSiNm() {
		return siNm;
	}

	public void setSiNm(String siNm) {
		this.siNm = siNm;
	}

	public String getSggNm() {
		return sggNm;
	}

	public void setSggNm(String sggNm) {
		this.sggNm = sggNm;
	}

	public String getEmdNm() {
		return emdNm;
	}

	public void setEmdNm(String emdNm) {
		this.emdNm = emdNm;
	}

	public String getLiNm() {
		return liNm;
	}

	public void setLiNm(String liNm) {
		this.liNm = liNm;
	}

	public String getRn() {
		return rn;
	}

	public void setRn(String rn) {
		this.rn = rn;
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

	public Long getSales() {
		return sales;
	}

	public void setSales(Long sales) {
		this.sales = sales;
	}

	public String getDisplaySales() {
		return displaySales;
	}

	public void setDisplaySales(String displaySales) {
		this.displaySales = displaySales;
	}

	public Integer getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(Integer fileGroupId) {
		this.fileGroupId = fileGroupId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public List<FileVo> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVo> fileList) {
		this.fileList = fileList;
	}

}
