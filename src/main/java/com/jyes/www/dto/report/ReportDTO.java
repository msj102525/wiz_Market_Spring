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
 *  위치 : com.jyes.www.dto.report.ReportDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-09  |      박준태          |     최초 생성
 *  
 */

public class ReportDTO {
    
	// report_id
    // report 시퀀스
    private Integer reportId;
    
    // store_id
 	// 매장 시퀀스
 	private Integer storeId;
 	
 	// name
 	// 매장 이름
 	private String name;
 	
 	// start_date
 	// 업종 시작 일자 (ex: 2024-07-10)
 	private String startDate;
    
    // url
    // URL
    private String url;
    
    // hits
    // 조회수
    private Integer hits;
    
    // is_exposure
    // 노출여부
    private String isExposed;
    
    // view_seq
    // 뷰 순서
    private Integer viewSeq;
    
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
    
    private Integer vol;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
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

	public Integer getViewSeq() {
		return viewSeq;
	}

	public void setViewSeq(Integer viewSeq) {
		this.viewSeq = viewSeq;
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

	public Integer getVol() {
		return vol;
	}

	public void setVol(Integer vol) {
		this.vol = vol;
	}

}