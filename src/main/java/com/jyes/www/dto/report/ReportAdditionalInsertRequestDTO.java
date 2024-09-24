package com.jyes.www.dto.report;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jyes.www.dto.common.FileInsertRequestDTO;

/*
 *  리포트 추가 생성 용도 
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportInsertRequestDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-19  |      박준태          |     최초 생성
 *  
 */

public class ReportAdditionalInsertRequestDTO {
	
	// 상점 아이디
	private Integer storeId;
	
	// 업종 시작일
	private String startDate; 
	
	// 대분류
	private Integer mainCategoryId;
	
	// 중분류
	private Integer subCategoryId;
	
	// 주소
	private String addr;
	
	// 상세 주소
	private String addrDetail;
	
	// 시도명 (api로 부터 받은 정보)
	private String siNm;
	
	// 시군구명 (api로 부터 받은 정보)
	private String sggNm;
	
	// 읍면동명 (api로 부터 받은 정보)
	private String emdNm;
	
	// 법정리명 (api로 부터 받은 정보)
	private String liNm;
	
	// 도로명 (api로 부터 받은 정보)
	private String rn;
	
	// 온라인URL 종류
	private String onlineUrlCategoryCd;
	
	// 온라인URL
	private String onlineUrl;
	
	// 일평균 매출
	private Double sales;
	
	// 크롭된 파일 목록 (크롭 기능 사용 있다면 사용)
//	private List<String> imageUrlList;
	
	// 파일 목록 (크롭 기능을 없애면 사용)
//	private List<MultipartFile> fileList;
	
	// 파일 url, 멀티파트파일 하나로 묶은 dto
	private List<MultipartFile> fileList;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
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

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	public List<MultipartFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}

}