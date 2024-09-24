package com.jyes.www.mapper.report;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jyes.www.dto.report.ReportAdditionalDTO;
import com.jyes.www.dto.report.ReportAdditionalResponseDTO;
import com.jyes.www.dto.report.ReportBusinessDistrictAnalysisDTO;
import com.jyes.www.dto.report.ReportBusinessDistrictAnalysisResponseDTO;
import com.jyes.www.dto.report.ReportDTO;
import com.jyes.www.dto.report.ReportDetailDTO;
import com.jyes.www.dto.report.ReportDetailResponseDTO;
import com.jyes.www.dto.report.ReportInformationDTO;
import com.jyes.www.dto.report.ReportListResponseDTO;
import com.jyes.www.dto.report.ReportLocationAnalysisDTO;
import com.jyes.www.dto.report.ReportLocationAnalysisResponseDTO;
import com.jyes.www.dto.report.ReportSearchRequestDTO;
import com.jyes.www.dto.report.ReportViewResponseDTO;
import com.jyes.www.vo.report.ReportAdditionalVo;
import com.jyes.www.vo.report.ReportBusinessDistrictAnalysisVo;
import com.jyes.www.vo.report.ReportChangedListVo;
import com.jyes.www.vo.report.ReportDetailVo;
import com.jyes.www.vo.report.ReportInformationVo;
import com.jyes.www.vo.report.ReportLocationAnalysisVo;
import com.jyes.www.vo.report.ReportVo;
import com.jyes.www.vo.store.StoreVo;

@Repository(value = "reportMapper")
public interface ReportMapper {
	
	public List<ReportChangedListVo> addrOrCategoryChanedReportList(Map<String, Object> param) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int insertReport(ReportVo request) throws SQLException;
	
	public ReportAdditionalVo beforeReportAdditionalByStoreId(Map<String, Object> param) throws SQLException;
	
	public int insertReportAdditional(ReportAdditionalVo request) throws SQLException;
	
	public int updateReportAdditional(ReportAdditionalVo request) throws SQLException;
	
	public int insertReportLocationAnalysis(ReportLocationAnalysisVo request) throws SQLException;
	
	public int insertReportBusinessDistrictAnalysis(ReportBusinessDistrictAnalysisVo request) throws SQLException;
	
	public int insertReportDetail(ReportDetailVo request) throws SQLException;
	
	public int updateReportDetail(ReportDetailVo request) throws SQLException;
	
	public int insertReportInformation(ReportInformationVo request) throws SQLException;
	
	public int updateReportInformation(ReportInformationVo request) throws SQLException;
	
	public int deleteReportInformation(ReportInformationVo request) throws SQLException;
	
	public ReportAdditionalVo getReportAreaAndCaterory(Map<String, Object> param) throws SQLException;
	
	public ReportLocationAnalysisResponseDTO getReportLocationAnalysisView(Map<String, Object> param) throws SQLException;
	
	public ReportBusinessDistrictAnalysisResponseDTO getReportBusinessDistrictAnalysisView(Map<String, Object> param) throws SQLException;
	
	public ReportDetailResponseDTO getReportDetailView(Map<String, Object> param) throws SQLException;
	
	public List<ReportVo> addrOrCategoryChanedReportSimpleList(Map<String, Object> param) throws SQLException;
	
	public ReportChangedListVo addrOrCategoryChanedReport(Map<String, Object> param) throws SQLException;
	
	public int deleteReportByStoreId(StoreVo reuqest) throws SQLException;
	
	public int deleteReportAdditionalByStoreId(StoreVo reuqest) throws SQLException;
	
	public int deleteReportDetailByStoreId(StoreVo reuqest) throws SQLException;
	
	public int deleteReportLocationAnalysisByStoreId(StoreVo reuqest) throws SQLException;
	
	public int deleteReportBusinessDistrictAnalysisByStoreId(StoreVo reuqest) throws SQLException;
	
	public List<ReportListResponseDTO> reportListByConditionSearch(ReportSearchRequestDTO request) throws SQLException;
	
	public int reportListByConditionSearchCount(ReportSearchRequestDTO request) throws SQLException;
	
	public int changeExposureReport(ReportVo request) throws SQLException;
	
	public ReportViewResponseDTO getReportView(Map<String, Object> param) throws SQLException;
	
	public List<ReportInformationDTO> reportInformationByReportId(Map<String, Object> param) throws SQLException;
	
	public int getReportInformationLastViewSeq(Map<String, Object> param) throws SQLException;
	
	public ReportInformationDTO getReportInformationById(Map<String, Object> param) throws SQLException;
	
	public ReportVo reportById(Map<String, Object> param) throws SQLException;
	
	public ReportAdditionalDTO viewReportAdditional(Map<String, Object> param) throws SQLException;
	
	public ReportLocationAnalysisDTO viewReportLocationAnalysis(Map<String, Object> param) throws SQLException;
	
	public ReportBusinessDistrictAnalysisDTO viewReportBusinessDistrictAnalysis(Map<String, Object> param) throws SQLException;
	
	public List<ReportInformationDTO> viewReportInformation(Map<String, Object> param) throws SQLException;
	
	public ReportDTO viewReport(Map<String, Object> param) throws SQLException;
	
	public ReportAdditionalResponseDTO getReportAdditional(Map<String, Object> param) throws SQLException;
	
	public int deleteReport(ReportVo reuqest) throws SQLException;
	
	public int deleteReportAdditionalByReportId(ReportVo reuqest) throws SQLException;
	
	public int deleteReportDetailByReportId(ReportVo reuqest) throws SQLException;
	
	public int deleteReportLocationAnalysisByReportId(ReportVo reuqest) throws SQLException;
	
	public int deleteReportBusinessDistrictAnalysisByReportId(ReportVo reuqest) throws SQLException;
	
	public int deleteReportInformationByReportId(ReportVo reuqest) throws SQLException;
	
	public int insertReportUrl(ReportVo reuqest) throws SQLException;
	
	public ReportDetailDTO viewReportDetail(Map<String, Object> param) throws SQLException;

}