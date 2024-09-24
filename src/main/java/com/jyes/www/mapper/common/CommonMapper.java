package com.jyes.www.mapper.common;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jyes.www.vo.common.AreaVo;
import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.CategoryVo;
import com.jyes.www.vo.common.CodeVo;
import com.jyes.www.vo.common.FaqTitleVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.common.InformationVo;
import com.jyes.www.vo.common.LocationAnalysisVo;

@Repository(value = "commonMapper")
public interface CommonMapper {
	public ArrayList<Object> commonInformationByConditionSearch(Object vo) throws SQLException;
	public int commonInformationByConditionSearchCount(Object vo) throws SQLException;
	public ArrayList<Object> selectFaqTitle() throws SQLException;
	public int insertFaqTitle(Object vo) throws SQLException;
	public int updateFaqTitle(Object vo) throws SQLException;
	public int deleteFaqTitle(Object vo) throws SQLException;
	public int insertCommonInformation(Object vo) throws SQLException;
	public int insertFileGroup(Object vo) throws SQLException;
	public int insertFile(Object vo) throws SQLException;
	public Object commonInformationById(Object vo) throws SQLException;
	
	public AreaVo areaByConditionSearch(AreaVo request) throws SQLException;
	
	public List<FileVo> fileByGroupId(Map<String, Object> param) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int insertArea(AreaVo request) throws SQLException;
	public int updateInformation(Object vo) throws SQLException;
	public ArrayList<Object> fileByGroupId(Object vo) throws SQLException;
	public int deleteFile(Object vo) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int insertFileVo(FileVo request) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int deleteFileVo(FileVo request) throws SQLException;
	
	public int insertLocationAnalysis(LocationAnalysisVo request) throws SQLException;
	
	public int updateLocationAnalysis(LocationAnalysisVo request) throws SQLException;
	
	public int insertBusinessDistrictAnalysis(BusinessDistrictAnalysisVo request) throws SQLException;
	
	public int updateBusinessDistrictAnalysis(BusinessDistrictAnalysisVo request) throws SQLException;
	
	public List<InformationVo> commonInformationByAll() throws SQLException;
	
	public List<CategoryVo> mainCategoryList() throws SQLException;
	
	public List<CategoryVo> subCategoryList() throws SQLException;
	
	public List<CodeVo> weekList() throws SQLException;
	
	public List<CodeVo> genderList() throws SQLException;
	
	public List<CodeVo> ageGroupList() throws SQLException;
	
	public List<CodeVo> timeList() throws SQLException;
	
	public List<CodeVo> deliveryList() throws SQLException;
	
	public List<CodeVo> onlineUrlList() throws SQLException;
	
	public LocationAnalysisVo locationAnalysisByAreaId(Map<String, Object> param) throws SQLException;
	
	public LocationAnalysisVo locationAnalysisById(Map<String, Object> param) throws SQLException;
	
	public List<FileVo> fileListByGroupId(Map<String, Object> param) throws SQLException;
	
	public List<FaqTitleVo> faqTitleList() throws SQLException;

}