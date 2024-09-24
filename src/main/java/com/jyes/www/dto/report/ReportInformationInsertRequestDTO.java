package com.jyes.www.dto.report;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jyes.www.dto.common.FileInsertRequestDTO;
import com.jyes.www.vo.report.ReportInformationVo;

/*
 *  리포트 정보 생성 용도 
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportInformationInsertRequestDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-19  |      박준태          |     최초 생성
 *  
 */

public class ReportInformationInsertRequestDTO {
	
    // report_id
    // report 시퀀스
    private Integer reportId;
    
    // 정보 리스트
    private List<ReportInformationSaveDTO> reportInformationList;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public List<ReportInformationSaveDTO> getReportInformationList() {
		return reportInformationList;
	}

	public void setReportInformationList(List<ReportInformationSaveDTO> reportInformationList) {
		this.reportInformationList = reportInformationList;
	}

}