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

public class ReportAdditionalUpdateRequestDTO {
	
	// 상점 아이디
	private Integer storeId;
	
	// 업종 시작일
	private String startDate; 
	
	// 리포트 아이디
	private Integer reportId;
	
	// 리포트 추가 정보 아이디
	private Integer reportAdditionalId;
	
	// 온라인URL 종류
	private String onlineUrlCategoryCd;
	
	// 온라인URL
	private String onlineUrl;
	
	// 일평균 매출
	private Double sales;
	
	// 파일 그룹 
	private Integer fileGroupId;
	
	// 파일 url, 멀티파트파일 하나로 묶은 dto
	private List<MultipartFile> fileList;
	
	private List<Integer> deleteFileIdList;

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

	public List<MultipartFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}

	public List<Integer> getDeleteFileIdList() {
		return deleteFileIdList;
	}

	public void setDeleteFileIdList(List<Integer> deleteFileIdList) {
		this.deleteFileIdList = deleteFileIdList;
	}

}