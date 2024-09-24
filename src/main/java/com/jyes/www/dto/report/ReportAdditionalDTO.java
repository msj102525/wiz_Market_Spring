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
 *  위치 : com.jyes.www.dto.report.ReportAdditionalDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-09  |      박준태          |     최초 생성
 *  
 */

public class ReportAdditionalDTO {
    
	// report_additional_id
 	// report additional 시퀀스
 	private Integer reportAdditionalId;

 	// report_id
 	// report 시퀀스
 	private Integer reportId;

 	// area_id
 	// area 시퀀스
 	private Integer areaId;
 	
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
 	
 	// main_category_id
 	// 대분류 category 시퀀스
 	private Integer mainCategoryId;
 	
 	private String mainCategoryName;

 	// sub_category_id
 	// 중분류 category 시퀀스
 	private Integer subCategoryId;
 	
 	private String subCategoryName;

 	// addr
 	// 주소지
 	private String addr;

 	// addr_detail
 	// 상세 주소
 	private String addrDetail;

 	// online_url_category_cd
 	// 온라인 URL 종류 코드
 	private String onlineUrlCategoryCd;
 	
 	private String onlineUrlCategoryName;

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
 	
 	private List<FileVo> fileList;

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

	public String getOnlineUrlCategoryCd() {
		return onlineUrlCategoryCd;
	}

	public void setOnlineUrlCategoryCd(String onlineUrlCategoryCd) {
		this.onlineUrlCategoryCd = onlineUrlCategoryCd;
	}

	public String getOnlineUrlCategoryName() {
		return onlineUrlCategoryName;
	}

	public void setOnlineUrlCategoryName(String onlineUrlCategoryName) {
		this.onlineUrlCategoryName = onlineUrlCategoryName;
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

	public List<FileVo> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileVo> fileList) {
		this.fileList = fileList;
	}

}