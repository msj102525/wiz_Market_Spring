package com.jyes.www.service.common;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jyes.www.common.Config;
import com.jyes.www.dto.common.FileInsertRequestDTO;
import com.jyes.www.mapper.common.CommonMapper;
import com.jyes.www.service.common.impl.ICommonService;
import com.jyes.www.utils.FileUtil;
import com.jyes.www.utils.LogUtils;
import com.jyes.www.utils.SnowflakeIdGenerator;
import com.jyes.www.vo.common.AreaVo;
import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.CategoryVo;
import com.jyes.www.vo.common.CodeVo;
import com.jyes.www.vo.common.FaqTitleVo;
import com.jyes.www.vo.common.FileGroupVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.common.InformationVo;
import com.jyes.www.vo.common.LocationAnalysisVo;

@Service(value="commonService")
public class CommonService implements ICommonService {
	
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;
	
	@Override
	public ArrayList<Object> commonInformationByConditionSearch(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.commonInformationByConditionSearch(vo);
	}
	
	@Override
	public int commonInformationByConditionSearchCount(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.commonInformationByConditionSearchCount(vo);
	}
	
	@Override
	public ArrayList<Object> selectFaqTitle() throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.selectFaqTitle();
	}
	
	@Override
	public int insertFaqTitle(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.insertFaqTitle(vo);
	}
	
	@Override
	public int updateFaqTitle(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.updateFaqTitle(vo);
	}
	
	@Override
	public int deleteFaqTitle(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.deleteFaqTitle(vo);
	}
	
	@Override
	public int insertCommonInformation(Object ivo, Object mfvo, Object fgvo, StringBuffer logData) throws SQLException {
		int returnValue = -1;
		if(mfvo!=null&&((LinkedHashMap<String, FileVo>)mfvo).size()>0) {
			returnValue = commonMapper.insertFileGroup(fgvo);
			((InformationVo)ivo).setFileGroupId(((FileGroupVo)fgvo).getFileGroupId());
			if(returnValue < 1) {
				throw new SQLException("insertFileGroup 결과 없음");
			}
			// HashMap의 모든 키-값 쌍을 반복
	        for (Map.Entry<String, FileVo> entry : ((LinkedHashMap<String, FileVo>)mfvo).entrySet()) {
	            String key = entry.getKey();
	            logData.append("[" + LogUtils.getCurrentTime() + "]" + " " + "insertCommonInformation Map.Entry key : " + key + "\n");
	            FileVo fvo = entry.getValue();
	            fvo.setFileGroupId(((FileGroupVo)fgvo).getFileGroupId());
				returnValue = commonMapper.insertFile(fvo);
				if(returnValue < 1) {
					throw new SQLException("insertFile 결과 없음");
				}
	        }
		}
		returnValue = commonMapper.insertCommonInformation(ivo);
		if(returnValue < 1) {
			throw new SQLException("insertCommonInformation 결과 없음");
		}
		return returnValue;
	}
	
	@Override
	public int insertFileGroup(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.insertFileGroup(vo);
	}
	
	@Override
	public int insertFile(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.insertFile(vo);
	}
	
	@Override
	public Object commonInformationById(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.commonInformationById(vo);
	}

	@Override
	public int saveFiles(List<FileInsertRequestDTO> fileInsertRequestDTOList, Integer fileGroupId, Integer regId) throws Exception {
		int result = 1;
		
		try {
			// TODO : 임의 저장 
//			String dir_path = File.separator + "home" + File.separator + "jyes" + File.separator;
			String dir_path = Config.IMAGE_PATH;
			
			// insert 성공 개수와 file 개수 비교용도
			int insertFileResult = 0;
			
			for(FileInsertRequestDTO imageFileDTO : fileInsertRequestDTOList){
				
				insertFileResult += saveFile(imageFileDTO, fileGroupId, regId);
				
			}
			
			if(insertFileResult == fileInsertRequestDTOList.size()){
				result = 1;
			}else{
				result = 0;
			}
			
		} catch(SQLException se) {
			// TODO : handle exception
			se.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public int saveFile(FileInsertRequestDTO fileInsertRequestDTO, Integer fileGroupId, Integer regId) throws Exception {
		int result = 0;
		
		try {
			// TODO : 임의 저장 
//			String dir_path = File.separator + "home" + File.separator + "jyes" + File.separator;
			String dir_path = Config.IMAGE_PATH;
			
			// insert 성공 개수와 file 개수 비교용도
			int insertFileResult = 0;
			
			String saveFileName = "";
			String file_path = "";
			String originalFilename = fileInsertRequestDTO.getMultipartFile().getOriginalFilename();
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
			String fileExtension = extension;
			byte[] image_file_byte = null;
			if(fileInsertRequestDTO.getImageUrl().startsWith("data:image")) {
				image_file_byte = Base64.decodeBase64(fileInsertRequestDTO.getImageUrl().replaceAll("data:image/"+fileExtension+";base64,", ""));
			} else {
				image_file_byte = FileUtil.getFileByte(fileInsertRequestDTO.getMultipartFile());
			}
			saveFileName = SnowflakeIdGenerator.generateUniqueFilename()+"."+fileExtension;
			file_path = dir_path + saveFileName;
			FileUtil.saveImgFile(dir_path, file_path, image_file_byte);
			
			FileVo insertFileVo = new FileVo();
			insertFileVo.setFileGroupId(fileGroupId);
			insertFileVo.setOriginalName(originalFilename);
			insertFileVo.setSavePath(dir_path);
			insertFileVo.setSaveName(saveFileName);
			// TODO : url 설정 어떻게 할지?
			insertFileVo.setUrl("#");
			insertFileVo.setRegId(regId);
			
			result = commonMapper.insertFile(insertFileVo);
			
		} catch(SQLException se) {
			// TODO : handle exception
			se.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public AreaVo areaByConditionSearch(AreaVo request) throws SQLException {
		return commonMapper.areaByConditionSearch(request);
	}

	@Override
	public int insertArea(AreaVo request) throws SQLException {
		return commonMapper.insertArea(request);
	}
	
	@Override
	public int updateInformation(Object ivo, Object mfvo, Object dfvo, Object fgvo) throws SQLException {
		int returnValue = -1;
		if(mfvo!=null&&((LinkedHashMap<String, FileVo>)mfvo).size()>0) {
			// 파일 그룹 생성
			if(((FileGroupVo)fgvo).getFileGroupId()==null) {
				returnValue = commonMapper.insertFileGroup(fgvo);
				if(returnValue < 1) {
					throw new SQLException("insertFileGroup 결과 없음");
				}
				((InformationVo)ivo).setFileGroupId(((FileGroupVo)fgvo).getFileGroupId());
			}
			// 파일 생성
	        for (Map.Entry<String, FileVo> entry : ((LinkedHashMap<String, FileVo>)mfvo).entrySet()) {
	            FileVo fvo = entry.getValue();
	            fvo.setFileGroupId(((FileGroupVo)fgvo).getFileGroupId());
            	returnValue = commonMapper.insertFile(fvo);
				if(returnValue < 1) {
					throw new SQLException("insertFile 결과 없음");
				}
	        }
		}
		// 파일 삭제
        for (Map.Entry<String, FileVo> entry : ((LinkedHashMap<String, FileVo>)dfvo).entrySet()) {
            FileVo fvo = entry.getValue();
        	returnValue = commonMapper.deleteFile(fvo);
			if(returnValue < 1) {
				throw new SQLException("deleteFile 결과 없음");
			}
        }
		//공통정보 수정
		returnValue = commonMapper.updateInformation(ivo);
		return returnValue;
	}
	
	@Override
	public ArrayList<Object> fileByGroupId(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.fileByGroupId(vo);
	}
	
	@Override
	public int deleteFile(Object vo) throws SQLException {
		// TODO Auto-generated method stub
		return commonMapper.deleteFile(vo);
	}
	
	@Override
	public int deleteFiles(List<Integer> deleteFileIdList, Integer modId) throws SQLException {
		int result = 1;
		
		try {
			int deleteFileResult = 0;
			
			for(Integer deleteFileId : deleteFileIdList){
				deleteFileResult += deleteFile(deleteFileId, modId);
			}
			
			if(deleteFileResult == deleteFileIdList.size()){
				result = 1;
			}else{
				result = 0;
			}
		} catch (SQLException se){
			// TODO: handle exception
			se.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int deleteFile(Integer deleteFileId, Integer modId) throws SQLException {
		int result = 0;
		
		try {
			FileVo deleteFileVo = new FileVo();
			deleteFileVo.setFileId(deleteFileId);
			deleteFileVo.setModId(modId);
			result = commonMapper.deleteFileVo(deleteFileVo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insertLocationAnalysis(LocationAnalysisVo request) throws SQLException {
		return commonMapper.insertLocationAnalysis(request);
	}

	@Override
	public int updateLocationAnalysis(LocationAnalysisVo request) throws SQLException {
		return commonMapper.updateLocationAnalysis(request);
	}

	@Override
	public int insertBusinessDistrictAnalysis(BusinessDistrictAnalysisVo request) throws SQLException {
		return commonMapper.insertBusinessDistrictAnalysis(request);
	}

	@Override
	public int updateBusinessDistrictAnalysis(BusinessDistrictAnalysisVo request) throws SQLException {
		return commonMapper.updateBusinessDistrictAnalysis(request);
	}

	@Override
	public List<InformationVo> commonInformationByAll() throws SQLException {
		return commonMapper.commonInformationByAll();
	}

	@Override
	public List<CategoryVo> mainCategoryList() throws SQLException {
		return commonMapper.mainCategoryList();
	}

	@Override
	public List<CategoryVo> subCategoryList() throws SQLException {
		return commonMapper.subCategoryList();
	}
	
	@Override
	public List<CodeVo> weekList() throws SQLException {
		return commonMapper.weekList();
	}

	@Override
	public List<CodeVo> genderList() throws SQLException {
		return commonMapper.genderList();
	}

	@Override
	public List<CodeVo> ageGroupList() throws SQLException {
		return commonMapper.ageGroupList();
	}

	@Override
	public List<CodeVo> timeList() throws SQLException {
		return commonMapper.timeList();
	}

	@Override
	public List<CodeVo> deliveryList() throws SQLException {
		return commonMapper.deliveryList();
	}

	@Override
	public List<CodeVo> onlineUrlList() throws SQLException {
		return commonMapper.onlineUrlList();
	}

	@Override
	public LocationAnalysisVo locationAnalysisByAreaId(Map<String, Object> param) throws SQLException {
		return commonMapper.locationAnalysisByAreaId(param);
	}

	@Override
	public LocationAnalysisVo locationAnalysisById(Map<String, Object> param) throws SQLException {
		return commonMapper.locationAnalysisById(param);
	}

	@Override
	public List<FileVo> fileListByGroupId(Map<String, Object> param) throws SQLException {
		return commonMapper.fileListByGroupId(param);
	}

	@Override
	public List<FaqTitleVo> faqTitleList() throws SQLException {
		return commonMapper.faqTitleList();
	}

	@Override
	public int saveMultipartFiles(List<MultipartFile> fileList, Integer fileGroupId, Integer regId) throws Exception {
		int result = 1;
		
		int fileResult = 0;
		
		for(MultipartFile file : fileList){
			fileResult += saveMultipartFile(file, fileGroupId, regId);
		}
		
		if(fileResult == fileList.size()){
			result = 1;
		}else{
			result = 0;
		}
		
		return result;
	}

	@Override
	public int saveMultipartFile(MultipartFile file, Integer fileGroupId, Integer regId) throws Exception {
		int result = 0;
		
		// 파일이 비어있는지 확인
        if (file.isEmpty()) {
            throw new IllegalArgumentException("Empty file cannot be saved");
        }

		try {
			// TODO : 임의 저장 
//			String dir_path = File.separator + "home" + File.separator + "jyes" + File.separator;
			String dir_path = Config.IMAGE_PATH;
			
	        // 디렉토리 생성
	        File directory = new File(dir_path);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }
	        
	        // 파일 저장 경로와 이름 생성
	        String originalName = file.getOriginalFilename();
	        String fileExtension = getFileExtension(originalName);
	        String saveFileName = SnowflakeIdGenerator.generateUniqueFilename() + "." + fileExtension;
	        String savePath = dir_path + saveFileName;
	        
	        // 파일 저장
            file.transferTo(new File(savePath));
			
			FileVo insertFileVo = new FileVo();
			insertFileVo.setFileGroupId(fileGroupId);
			insertFileVo.setOriginalName(originalName);
			insertFileVo.setSavePath(dir_path);
			insertFileVo.setSaveName(saveFileName);
			// TODO : url 설정 어떻게 할지?
			insertFileVo.setUrl("http://reportimg.jyes.co.kr/"+saveFileName);
			insertFileVo.setRegId(regId);
			
			result = commonMapper.insertFile(insertFileVo);
			
		} catch (IOException ioe) {
			// TODO : handle exception
			ioe.printStackTrace();
        } catch(SQLException se) {
			// TODO : handle exception
			se.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 파일 확장자를 추출하는 메소드
    private String getFileExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            return "";
        }
        return filename.substring(filename.lastIndexOf('.') + 1);
    }

}