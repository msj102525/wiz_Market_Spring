package com.jyes.www.dto.report;

import java.util.List;

import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.common.LocationAnalysisVo;
import com.jyes.www.vo.report.ReportDetailVo;
import com.jyes.www.vo.report.ReportVo;

/*
 *  file 포함 정보
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportAdditionalResponseDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-09  |      박준태          |     최초 생성
 *  
 */

public class ReportAdditionalResponseDTO {
	
	// report_id
 	// report 시퀀스
 	private Integer reportId;
 	
 	// store_id
 	// store 시퀀스
 	private Integer storeId;
    
	// report_additional_id
 	// report additional 시퀀스
 	private Integer reportAdditionalId;
 	
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

 	// reg_date
 	// 작성일시
 	private String regDate;
 	
 	// mod_date
 	// 수정일시
 	private String modDate;

 	private List<FileVo> fileList;

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

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public List<FileVo> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVo> fileList) {
		this.fileList = fileList;
	}

}