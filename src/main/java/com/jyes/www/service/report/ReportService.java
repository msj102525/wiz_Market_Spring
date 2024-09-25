package com.jyes.www.service.report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jyes.www.dto.report.ReportAdditionalDTO;
import com.jyes.www.dto.report.ReportAdditionalInsertRequestDTO;
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
import com.jyes.www.mapper.common.CommonMapper;
import com.jyes.www.mapper.report.ReportMapper;
import com.jyes.www.mapper.store.StoreMapper;
import com.jyes.www.service.report.impl.IReportService;
import com.jyes.www.vo.common.FileGroupVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.report.ReportAdditionalVo;
import com.jyes.www.vo.report.ReportBusinessDistrictAnalysisVo;
import com.jyes.www.vo.report.ReportChangedListVo;
import com.jyes.www.vo.report.ReportDetailVo;
import com.jyes.www.vo.report.ReportInformationVo;
import com.jyes.www.vo.report.ReportLocationAnalysisVo;
import com.jyes.www.vo.report.ReportVo;
import com.jyes.www.vo.store.StoreVo;

@Service(value="reportService")
public class ReportService implements IReportService {
	
	@Resource(name="reportMapper")
	private ReportMapper reportMapper;
	
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;

	// 변경 이력이 있는 리포트 조회
	@Override
	public List<ReportChangedListVo> addrOrCategoryChanedReportList(Map<String, Object> param) {
		List<ReportChangedListVo> result = new ArrayList<ReportChangedListVo>();
		
		// 변경 이력이 있는 리포트 조회
		try {
			result = reportMapper.addrOrCategoryChanedReportList(param);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		// 변경 이력이 있는 리포트 이미지 셋팅
		if(result.size() > 0){
			for(ReportChangedListVo reportChangedVo : result){
				if(reportChangedVo.getFileGroupId() != null && reportChangedVo.getFileGroupId() > 0){
					Map<String, Object> fileParam = new HashMap<>();
					fileParam.put("fileGroupId", reportChangedVo.getFileGroupId());
					
					List<FileVo> fileList = new ArrayList<FileVo>();
					try {
						fileList = commonMapper.fileByGroupId(fileParam);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					reportChangedVo.setFileList(fileList);
					
				}
				
			}
		}
		
		return result;
	}

	@Override
	public int insertReport(ReportVo request) throws SQLException {
		return reportMapper.insertReport(request);
	}

	@Override
	public int insertReportAdditional(ReportAdditionalVo request) throws SQLException {
		return reportMapper.insertReportAdditional(request);
	}

	@Override
	public ReportAdditionalVo beforeReportAdditionalByStoreId(Map<String, Object> param) throws SQLException {
		return reportMapper.beforeReportAdditionalByStoreId(param);
	}

	@Override
	public int updateReportAdditional(ReportAdditionalVo request) throws SQLException {
		return reportMapper.updateReportAdditional(request);
	}

	@Override
	public int insertReportLocationAnalysis(ReportLocationAnalysisVo request) throws SQLException {
		return reportMapper.insertReportLocationAnalysis(request);
	}

	@Override
	public int insertReportBusinessDistrictAnalysis(ReportBusinessDistrictAnalysisVo request) throws SQLException {
		return reportMapper.insertReportBusinessDistrictAnalysis(request);
	}

	@Override
	public int insertReportDetail(ReportDetailVo request) throws SQLException {
		return reportMapper.insertReportDetail(request);
	}

	@Override
	public int updateReportDetail(ReportDetailVo request) throws SQLException {
		return reportMapper.updateReportDetail(request);
	}

	@Override
	public int insertReportInformation(ReportInformationVo request) throws SQLException {
		return reportMapper.insertReportInformation(request);
	}

	@Override
	public int updateReportInformation(ReportInformationVo request) throws SQLException {
		return reportMapper.updateReportInformation(request);
	}

	@Override
	public int deleteReportInformation(ReportInformationVo request) throws SQLException {
		return reportMapper.deleteReportInformation(request);
	}

	@Override
	public ReportAdditionalVo getReportAreaAndCaterory(Map<String, Object> param) throws SQLException {
		return reportMapper.getReportAreaAndCaterory(param);
	}

	@Override
	public ReportLocationAnalysisResponseDTO getReportLocationAnalysisView(Map<String, Object> param) throws SQLException {
		return reportMapper.getReportLocationAnalysisView(param);
	}

	@Override
	public ReportBusinessDistrictAnalysisResponseDTO getReportBusinessDistrictAnalysisView(Map<String, Object> param) throws SQLException {
		return reportMapper.getReportBusinessDistrictAnalysisView(param);
	}

	@Override
	public ReportDetailResponseDTO getReportDetailView(Map<String, Object> param) throws SQLException {
		return reportMapper.getReportDetailView(param);
	}

	@Override
	public ReportChangedListVo addrOrCategoryChanedReport(Map<String, Object> param) throws SQLException {
		return reportMapper.addrOrCategoryChanedReport(param);
	}

	@Override
	public List<ReportVo> addrOrCategoryChanedReportSimpleList(Map<String, Object> param) throws SQLException {
		return reportMapper.addrOrCategoryChanedReportSimpleList(param);
	}

	@Override
	public int deleteReportByStoreId(StoreVo reuqest) throws SQLException {
		return reportMapper.deleteReportByStoreId(reuqest);
	}

	@Override
	public int deleteReportAdditionalByStoreId(StoreVo reuqest) throws SQLException {
		return reportMapper.deleteReportAdditionalByStoreId(reuqest);
	}

	@Override
	public int deleteReportDetailByStoreId(StoreVo reuqest) throws SQLException {
		return reportMapper.deleteReportDetailByStoreId(reuqest);
	}

	@Override
	public int deleteReportLocationAnalysisByStoreId(StoreVo reuqest) throws SQLException {
		return reportMapper.deleteReportLocationAnalysisByStoreId(reuqest);
	}

	@Override
	public int deleteReportBusinessDistrictAnalysisByStoreId(StoreVo reuqest) throws SQLException {
		return reportMapper.deleteReportBusinessDistrictAnalysisByStoreId(reuqest);
	}

	@Override
	public List<ReportListResponseDTO> reportListByConditionSearch(ReportSearchRequestDTO request) throws SQLException {
		return reportMapper.reportListByConditionSearch(request);
	}

	@Override
	public int reportListByConditionSearchCount(ReportSearchRequestDTO request) throws SQLException {
		return reportMapper.reportListByConditionSearchCount(request);
	}

	@Override
	public int changeExposureReport(ReportVo request) throws SQLException {
		return reportMapper.changeExposureReport(request);
	}

	@Override
	public ReportViewResponseDTO getReportView(Map<String, Object> param) throws SQLException {
		return reportMapper.getReportView(param);
	}

	@Override
	public List<ReportInformationDTO> reportInformationByReportId(Map<String, Object> param) throws SQLException {
		return reportMapper.reportInformationByReportId(param);
	}

	@Override
	public int getReportInformationLastViewSeq(Map<String, Object> param) throws SQLException {
		return reportMapper.getReportInformationLastViewSeq(param);
	}

	@Override
	public ReportInformationDTO getReportInformationById(Map<String, Object> param) throws SQLException {
		return reportMapper.getReportInformationById(param);
	}

	@Override
	public ReportVo reportById(Map<String, Object> param) throws SQLException {
		return reportMapper.reportById(param);
	}

	@Override
	public ReportAdditionalDTO viewReportAdditional(Map<String, Object> param) throws SQLException {
		return reportMapper.viewReportAdditional(param);
	}
	
	@Override
	public ReportLocationAnalysisDTO viewReportLocationAnalysis(Map<String, Object> param) throws SQLException {
		return reportMapper.viewReportLocationAnalysis(param);
	}

	@Override
	public ReportBusinessDistrictAnalysisDTO viewReportBusinessDistrictAnalysis(Map<String, Object> param) throws SQLException {
		return reportMapper.viewReportBusinessDistrictAnalysis(param);
	}

	@Override
	public List<ReportInformationDTO> viewReportInformation(Map<String, Object> param) throws SQLException {
		return reportMapper.viewReportInformation(param);
	}

	// 고객 제공
	@Override
	public ReportDTO viewReport(Map<String, Object> param) throws SQLException {
		return reportMapper.viewReport(param);
	}

	@Override
	public ReportAdditionalResponseDTO getReportAdditional(Map<String, Object> param) throws SQLException {
		return reportMapper.getReportAdditional(param);
	}

	@Override
	public int deleteReport(ReportVo reuqest) throws SQLException {
		return reportMapper.deleteReport(reuqest);
	}

	@Override
	public int deleteReportAdditionalByReportId(ReportVo reuqest) throws SQLException {
		return reportMapper.deleteReportAdditionalByReportId(reuqest);
	}
	
	@Override
	public int deleteReportDetailByReportId(ReportVo reuqest) throws SQLException {
		return reportMapper.deleteReportDetailByReportId(reuqest);
	}

	@Override
	public int deleteReportLocationAnalysisByReportId(ReportVo reuqest) throws SQLException {
		return reportMapper.deleteReportLocationAnalysisByReportId(reuqest);
	}

	@Override
	public int deleteReportBusinessDistrictAnalysisByReportId(ReportVo reuqest) throws SQLException {
		return reportMapper.deleteReportBusinessDistrictAnalysisByReportId(reuqest);
	}

	@Override
	public int deleteReportInformationByReportId(ReportVo reuqest) throws SQLException {
		return reportMapper.deleteReportInformationByReportId(reuqest);
	}

	@Override
	public int insertReportUrl(ReportVo reuqest) throws SQLException {
		return reportMapper.insertReportUrl(reuqest);
	}

	@Override
	public ReportDetailDTO viewReportDetail(Map<String, Object> param) throws SQLException {
		return reportMapper.viewReportDetail(param);
	}

}