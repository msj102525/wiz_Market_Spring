package com.jyes.www.dto.report;

import java.util.List;

/*
 *  리포트 생성 용도 
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportLocationAnalysisSaveRequestDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-19  |      박준태          |     최초 생성
 *  
 */

public class ReportLocationAnalysisSaveRequestDTO {
	
	// report 시퀀스
	private Integer reportId;
	
	// report_location_analysis_id
    // report location analysis 시퀀스
    private Integer reportLocationAnalysisId;
	
	// location_analysis_id
    // location analysis 시퀀스
    private Integer locationAnalysisId;

    // area_id
    // area 시퀀스
    private Integer areaId;

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

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getReportLocationAnalysisId() {
		return reportLocationAnalysisId;
	}

	public void setReportLocationAnalysisId(Integer reportLocationAnalysisId) {
		this.reportLocationAnalysisId = reportLocationAnalysisId;
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