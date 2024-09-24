package com.jyes.www.dto.report;

import java.util.List;

import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.LocationAnalysisVo;
import com.jyes.www.vo.report.ReportDetailVo;
import com.jyes.www.vo.report.ReportVo;

/*
 *  리포트 생성 용도 
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportListResponseDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-07  |      박준태          |     최초 생성
 *  
 */

public class ReportListResponseDTO {
	
	// report_id
	private Integer reportId;
	
	// 매장 이름
	private String name;
    
    // 주소
    private String addr;
    
    // 미리보기 url
    private String url;
    
    // 등록일
    private String regDate;
    
    // Vol
    private Integer vol;
    
    // 게시중 : Y / 멈춤 : N (노출여부)
    private String isExposed;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Integer getVol() {
		return vol;
	}

	public void setVol(Integer vol) {
		this.vol = vol;
	}

	public String getIsExposed() {
		return isExposed;
	}

	public void setIsExposed(String isExposed) {
		this.isExposed = isExposed;
	}

}