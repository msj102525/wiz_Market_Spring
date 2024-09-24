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
 *  위치 : com.jyes.www.dto.report.ReportInsertRequestDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-19  |      박준태          |     최초 생성
 *  
 */

public class ReportInsertRequestDTO {
	
	// 리포트 등록
	private ReportVo reportVo;
	
	// 리포트 추가 등록
	private ReportAdditionalInsertRequestDTO reportAdditonalInsertDTO;
	
	// 리포트 지역 입지 분석 등록
	private LocationAnalysisVo locationAnalysisVo;
	
	// 리포트 지역 상권 분석 등록
	private BusinessDistrictAnalysisVo businessDistrictAnalysisVo;
	
	// 리포트 세부 등록
	private ReportDetailVo reportDetailVo;
	
	// 리포트 정보 등록
	private List<ReportInformationInsertRequestDTO> reportInformationInsertDTOList;

	public ReportVo getReportVo() {
		return reportVo;
	}

	public void setReportVo(ReportVo reportVo) {
		this.reportVo = reportVo;
	}

	public ReportAdditionalInsertRequestDTO getReportAdditonalInsertDTO() {
		return reportAdditonalInsertDTO;
	}

	public void setReportAdditonalInsertDTO(ReportAdditionalInsertRequestDTO reportAdditonalInsertDTO) {
		this.reportAdditonalInsertDTO = reportAdditonalInsertDTO;
	}

	public LocationAnalysisVo getLocationAnalysisVo() {
		return locationAnalysisVo;
	}

	public void setLocationAnalysisVo(LocationAnalysisVo locationAnalysisVo) {
		this.locationAnalysisVo = locationAnalysisVo;
	}

	public BusinessDistrictAnalysisVo getBusinessDistrictAnalysisVo() {
		return businessDistrictAnalysisVo;
	}

	public void setBusinessDistrictAnalysisVo(BusinessDistrictAnalysisVo businessDistrictAnalysisVo) {
		this.businessDistrictAnalysisVo = businessDistrictAnalysisVo;
	}

	public ReportDetailVo getReportDetailVo() {
		return reportDetailVo;
	}

	public void setReportDetailVo(ReportDetailVo reportDetailVo) {
		this.reportDetailVo = reportDetailVo;
	}

	public List<ReportInformationInsertRequestDTO> getReportInformationInsertDTOList() {
		return reportInformationInsertDTOList;
	}

	public void setReportInformationInsertDTOList(List<ReportInformationInsertRequestDTO> reportInformationInsertDTOList) {
		this.reportInformationInsertDTOList = reportInformationInsertDTOList;
	}

}