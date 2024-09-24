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
 *  위치 : com.jyes.www.dto.report.ReportDetailResponseDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-19  |      박준태          |     최초 생성
 *  
 */

public class ReportDetailResponseDTO {
	
	// report_detail_id
	// report detail 시퀀스
	private Integer reportDetailId;
	
	// report_id
	// 리포트 시퀀스
	private Integer reportId;
	
	// store_id
	// 매장 시퀀스
	private Integer storeId;
	
	// name
	// 매장 이름
	private String name;
	
	// report_additional_id
	// 리포트 추가 정보 시퀀스
	private Integer reportAdditionalId;
	
    // area_id
    // 지역 시퀀스
    private Integer areaId;
	
	// reg_date
	// 리포트 등록 일시
	private String regDate;
	
	// si_nm
	// 시도명
	private String siNm;

	// sgg_nm
	// 시군구명
	private String sggNm;

	// emd_nm
	// 읍면동명
	private String emdNm;

	// li_nm
	// 법정리명
	private String liNm;
	
	// 메인 카테고리
	private Integer mainCategoryId;
	
	// 메인 카테고리 명
	private String mainCategoryName;
	
	// 서브 카테고리
	private Integer subCategoryId;
	
	// 서브 카테고리 명
	private String subCategoryName;
	
	// subway_distance
	// 지하철 거리
	private Double subwayDistance;

	// bus_distance
	// 버스정류장 거리
	private Double busDistance;

	// road
	// 가까운도로 거리
	private Double roadDistance;

	// gpts_strategy
	// GPT's 전략수집
	private String gptsStrategy;

	// gpts_slogan
	// GPT's 슬로건
	private String gptsSlogan;

	public Integer getReportDetailId() {
		return reportDetailId;
	}

	public void setReportDetailId(Integer reportDetailId) {
		this.reportDetailId = reportDetailId;
	}

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

	public Integer getReportAdditionalId() {
		return reportAdditionalId;
	}

	public void setReportAdditionalId(Integer reportAdditionalId) {
		this.reportAdditionalId = reportAdditionalId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getSiNm() {
		return siNm;
	}

	public void setSiNm(String siNm) {
		this.siNm = siNm;
	}

	public String getSggNm() {
		return sggNm;
	}

	public void setSggNm(String sggNm) {
		this.sggNm = sggNm;
	}

	public String getEmdNm() {
		return emdNm;
	}

	public void setEmdNm(String emdNm) {
		this.emdNm = emdNm;
	}

	public String getLiNm() {
		return liNm;
	}

	public void setLiNm(String liNm) {
		this.liNm = liNm;
	}

	public Integer getMainCategoryId() {
		return mainCategoryId;
	}

	public void setMainCategoryId(Integer mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}

	public String getMainCategoryName() {
		return mainCategoryName;
	}

	public void setMainCategoryName(String mainCategoryName) {
		this.mainCategoryName = mainCategoryName;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Double getSubwayDistance() {
		return subwayDistance;
	}

	public void setSubwayDistance(Double subwayDistance) {
		this.subwayDistance = subwayDistance;
	}

	public Double getBusDistance() {
		return busDistance;
	}

	public void setBusDistance(Double busDistance) {
		this.busDistance = busDistance;
	}

	public Double getRoadDistance() {
		return roadDistance;
	}

	public void setRoadDistance(Double roadDistance) {
		this.roadDistance = roadDistance;
	}

	public String getGptsStrategy() {
		return gptsStrategy;
	}

	public void setGptsStrategy(String gptsStrategy) {
		this.gptsStrategy = gptsStrategy;
	}

	public String getGptsSlogan() {
		return gptsSlogan;
	}

	public void setGptsSlogan(String gptsSlogan) {
		this.gptsSlogan = gptsSlogan;
	}

}