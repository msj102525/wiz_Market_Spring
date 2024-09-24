package com.jyes.www.service.common.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jyes.www.dto.common.FileInsertRequestDTO;
import com.jyes.www.vo.common.AreaVo;
import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.CategoryVo;
import com.jyes.www.vo.common.CodeVo;
import com.jyes.www.vo.common.FaqTitleVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.common.InformationVo;
import com.jyes.www.vo.common.LocationAnalysisVo;

public interface ICommonService {
	public ArrayList<Object> commonInformationByConditionSearch(Object vo) throws SQLException;
	public int commonInformationByConditionSearchCount(Object vo) throws SQLException;
	public ArrayList<Object> selectFaqTitle() throws SQLException;
	public int insertFaqTitle(Object vo) throws SQLException;
	public int updateFaqTitle(Object vo) throws SQLException;
	public int deleteFaqTitle(Object vo) throws SQLException;
	@Transactional(rollbackFor={SQLException.class})
	public int insertCommonInformation(Object ivo, Object mfvo, Object gfvo, StringBuffer logData) throws SQLException;
	public int insertFileGroup(Object vo) throws SQLException;
	@Transactional(rollbackFor={SQLException.class})
	public int insertFile(Object vo) throws SQLException;
	public Object commonInformationById(Object vo) throws SQLException;
	
	public int saveFiles(List<FileInsertRequestDTO> fileInsertRequestDTOList, Integer fileGroupId, Integer regId) throws Exception;
	
	public int saveFile(FileInsertRequestDTO fileInsertRequestDTO, Integer fileGroupId, Integer regId) throws Exception;
	
	public int saveMultipartFiles(List<MultipartFile> fileList, Integer fileGroupId, Integer regId) throws Exception;
	
	public int saveMultipartFile(MultipartFile file, Integer fileGroupId, Integer regId) throws Exception;
	
	public AreaVo areaByConditionSearch(AreaVo request) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int insertArea(AreaVo request) throws SQLException;
	public int updateInformation(Object ivo, Object afvo, Object dfvo, Object fgvo) throws SQLException;
	public ArrayList<Object> fileByGroupId(Object vo) throws SQLException;
	public int deleteFile(Object vo) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int deleteFiles(List<Integer> deleteFileIdList, Integer modId) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int deleteFile(Integer deleteFileId, Integer modId) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int insertLocationAnalysis(LocationAnalysisVo request) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int updateLocationAnalysis(LocationAnalysisVo request) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
	public int insertBusinessDistrictAnalysis(BusinessDistrictAnalysisVo request) throws SQLException;
	
	@Transactional(rollbackFor={SQLException.class})
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