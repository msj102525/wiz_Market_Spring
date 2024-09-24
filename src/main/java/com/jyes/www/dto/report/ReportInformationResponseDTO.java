package com.jyes.www.dto.report;

import java.util.List;

import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.common.LocationAnalysisVo;
import com.jyes.www.vo.report.ReportDetailVo;
import com.jyes.www.vo.report.ReportVo;

/*
 *  리포트 정보 화면 DTO
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportInformationResponseDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-09  |      박준태          |     최초 생성
 *  
 */

public class ReportInformationResponseDTO {
	
	private ReportViewResponseDTO view;
	
	private List<ReportInformationDTO> reportInformationList;

	public ReportViewResponseDTO getView() {
		return view;
	}

	public void setView(ReportViewResponseDTO view) {
		this.view = view;
	}

	public List<ReportInformationDTO> getReportInformationList() {
		return reportInformationList;
	}

	public void setReportInformationList(List<ReportInformationDTO> reportInformationList) {
		this.reportInformationList = reportInformationList;
	}

}