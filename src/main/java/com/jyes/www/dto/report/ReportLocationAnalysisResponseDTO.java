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
 *  위치 : com.jyes.www.dto.report.ReportLocationAnalysisResponseDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-19  |      박준태          |     최초 생성
 *  
 */

public class ReportLocationAnalysisResponseDTO {
	
	// report_location_analysis_id
	// 리포트 지역 입지 분석 시퀀스
	private Integer reportLocationAnalysisId;
	
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
	
	// location_analysis_id
    // location analysis 시퀀스
    private Integer locationAnalysisId;
    
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
	
    // aprtment_household
    // 아파트 세대 수
    private Integer aprtmentHousehold;

    // aprtment_transaction_price
    // 아파트 실 거래가
    private Double aprtmentTransactionPrice;

    // aprtment_residence
    // 아파트 거주인원 비율
    private Double aprtmentResidence;

    // flow_population
    // 유동인구
    private Long flowPopulation;

    // flow_age_range_cd
    // 최다 연령대
    private String flowAgeRangeCd;

    // flow_time_start_cd
    // 최다 시간대 시작
    private String flowTimeStartCd;

    // flow_time_end_cd
    // 최다 시간대 종료
    private String flowTimeEndCd;

    // residence_population
    // 주거인구
    private Long residencePopulation;

    // work_population
    // 직장인구
    private Long workPopulation;

    // household
    // 세대 수
    private Integer household;

    // business
    // 업소 수
    private Integer business;

    // income
    // 소득
    private Double income;

    // delivery_gender_cd
    // 배달 이용 성별
    private String deliveryGenderCd;

    // delivery_age_range_cd
    // 배달 이용 연령대
    private String deliveryAgeRangeCd;

    // delivery_week_cd
    // 배달 이용 요일
    private String deliveryWeekCd;

    // delivery_time_start_cd
    // 배달 이용 시간대 시작
    private String deliveryTimeStartCd;

    // delivery_time_end_cd
    // 배달 이용 시간대 종료
    private String deliveryTimeEndCd;

    // delivery_method_cd
    // 배달 방법
    private String deliveryMethodCd;

	public Integer getReportLocationAnalysisId() {
		return reportLocationAnalysisId;
	}

	public void setReportLocationAnalysisId(Integer reportLocationAnalysisId) {
		this.reportLocationAnalysisId = reportLocationAnalysisId;
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

	public Integer getLocationAnalysisId() {
		return locationAnalysisId;
	}

	public void setLocationAnalysisId(Integer locationAnalysisId) {
		this.locationAnalysisId = locationAnalysisId;
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

	public Integer getAprtmentHousehold() {
		return aprtmentHousehold;
	}

	public void setAprtmentHousehold(Integer aprtmentHousehold) {
		this.aprtmentHousehold = aprtmentHousehold;
	}

	public Double getAprtmentTransactionPrice() {
		return aprtmentTransactionPrice;
	}

	public void setAprtmentTransactionPrice(Double aprtmentTransactionPrice) {
		this.aprtmentTransactionPrice = aprtmentTransactionPrice;
	}

	public Double getAprtmentResidence() {
		return aprtmentResidence;
	}

	public void setAprtmentResidence(Double aprtmentResidence) {
		this.aprtmentResidence = aprtmentResidence;
	}

	public Long getFlowPopulation() {
		return flowPopulation;
	}

	public void setFlowPopulation(Long flowPopulation) {
		this.flowPopulation = flowPopulation;
	}

	public String getFlowAgeRangeCd() {
		return flowAgeRangeCd;
	}

	public void setFlowAgeRangeCd(String flowAgeRangeCd) {
		this.flowAgeRangeCd = flowAgeRangeCd;
	}

	public String getFlowTimeStartCd() {
		return flowTimeStartCd;
	}

	public void setFlowTimeStartCd(String flowTimeStartCd) {
		this.flowTimeStartCd = flowTimeStartCd;
	}

	public String getFlowTimeEndCd() {
		return flowTimeEndCd;
	}

	public void setFlowTimeEndCd(String flowTimeEndCd) {
		this.flowTimeEndCd = flowTimeEndCd;
	}

	public Long getResidencePopulation() {
		return residencePopulation;
	}

	public void setResidencePopulation(Long residencePopulation) {
		this.residencePopulation = residencePopulation;
	}

	public Long getWorkPopulation() {
		return workPopulation;
	}

	public void setWorkPopulation(Long workPopulation) {
		this.workPopulation = workPopulation;
	}

	public Integer getHousehold() {
		return household;
	}

	public void setHousehold(Integer household) {
		this.household = household;
	}

	public Integer getBusiness() {
		return business;
	}

	public void setBusiness(Integer business) {
		this.business = business;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public String getDeliveryGenderCd() {
		return deliveryGenderCd;
	}

	public void setDeliveryGenderCd(String deliveryGenderCd) {
		this.deliveryGenderCd = deliveryGenderCd;
	}

	public String getDeliveryAgeRangeCd() {
		return deliveryAgeRangeCd;
	}

	public void setDeliveryAgeRangeCd(String deliveryAgeRangeCd) {
		this.deliveryAgeRangeCd = deliveryAgeRangeCd;
	}

	public String getDeliveryWeekCd() {
		return deliveryWeekCd;
	}

	public void setDeliveryWeekCd(String deliveryWeekCd) {
		this.deliveryWeekCd = deliveryWeekCd;
	}

	public String getDeliveryTimeStartCd() {
		return deliveryTimeStartCd;
	}

	public void setDeliveryTimeStartCd(String deliveryTimeStartCd) {
		this.deliveryTimeStartCd = deliveryTimeStartCd;
	}

	public String getDeliveryTimeEndCd() {
		return deliveryTimeEndCd;
	}

	public void setDeliveryTimeEndCd(String deliveryTimeEndCd) {
		this.deliveryTimeEndCd = deliveryTimeEndCd;
	}

	public String getDeliveryMethodCd() {
		return deliveryMethodCd;
	}

	public void setDeliveryMethodCd(String deliveryMethodCd) {
		this.deliveryMethodCd = deliveryMethodCd;
	}

}