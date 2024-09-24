package com.jyes.www.dto.report;

import java.util.List;

import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.FileVo;
import com.jyes.www.vo.common.LocationAnalysisVo;
import com.jyes.www.vo.report.ReportDetailVo;
import com.jyes.www.vo.report.ReportVo;

/*
 *  file 포함 정보
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportAdditionalDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-09  |      박준태          |     최초 생성
 *  
 */

public class ReportLocationAnalysisDTO {
    
	// report_location_analysis_id
	// report location analysis 시퀀스
	private Integer reportLocationAnalysisId;

	// report_id
	// report 시퀀스
	private Integer reportId;

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
    
    private String flowAgeRangeName;

    // flow_time_start_cd
    // 최다 시간대 시작
    private String flowTimeStartCd;
    
    private String flowTimeStartName;

    // flow_time_end_cd
    // 최다 시간대 종료
    private String flowTimeEndCd;
    
    private String flowTimeEndName;

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
    
    private String deliveryGenderName;

    // delivery_age_range_cd
    // 배달 이용 연령대
    private String deliveryAgeRangeCd;
    
    private String deliveryAgeRangeName;

    // delivery_week_cd
    // 배달 이용 요일
    private String deliveryWeekCd;
    
    private String deliveryWeekName;

    // delivery_time_start_cd
    // 배달 이용 시간대 시작
    private String deliveryTimeStartCd;
    
    private String deliveryTimeStartName;

    // delivery_time_end_cd
    // 배달 이용 시간대 종료
    private String deliveryTimeEndCd;
    
    private String deliveryTimeEndName;

    // delivery_method_cd
    // 배달 방법
    private String deliveryMethodCd;
    
    private String deliveryMethodName;

	// view_seq
	// 뷰 순서
	private Integer viewSeq;

	// view_is_hid
	// 뷰 숨김여부
	private String viewIsHid;

	// is_deleted
	// 삭제여부
	private String isDeleted;

	// etc
	// 비고
	private String etc;

	// reg_id
	// 작성자 user 시퀀스
	private Integer regId;

	// reg_date
	// 작성일시
	private String regDate;

	// mod_id
	// 수정자 user 시퀀스
	private Integer modId;

	// mod_date
	// 수정일시
	private String modDate;

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

	public String getFlowAgeRangeName() {
		return flowAgeRangeName;
	}

	public void setFlowAgeRangeName(String flowAgeRangeName) {
		this.flowAgeRangeName = flowAgeRangeName;
	}

	public String getFlowTimeStartCd() {
		return flowTimeStartCd;
	}

	public void setFlowTimeStartCd(String flowTimeStartCd) {
		this.flowTimeStartCd = flowTimeStartCd;
	}

	public String getFlowTimeStartName() {
		return flowTimeStartName;
	}

	public void setFlowTimeStartName(String flowTimeStartName) {
		this.flowTimeStartName = flowTimeStartName;
	}

	public String getFlowTimeEndCd() {
		return flowTimeEndCd;
	}

	public void setFlowTimeEndCd(String flowTimeEndCd) {
		this.flowTimeEndCd = flowTimeEndCd;
	}

	public String getFlowTimeEndName() {
		return flowTimeEndName;
	}

	public void setFlowTimeEndName(String flowTimeEndName) {
		this.flowTimeEndName = flowTimeEndName;
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

	public String getDeliveryGenderName() {
		return deliveryGenderName;
	}

	public void setDeliveryGenderName(String deliveryGenderName) {
		this.deliveryGenderName = deliveryGenderName;
	}

	public String getDeliveryAgeRangeCd() {
		return deliveryAgeRangeCd;
	}

	public void setDeliveryAgeRangeCd(String deliveryAgeRangeCd) {
		this.deliveryAgeRangeCd = deliveryAgeRangeCd;
	}

	public String getDeliveryAgeRangeName() {
		return deliveryAgeRangeName;
	}

	public void setDeliveryAgeRangeName(String deliveryAgeRangeName) {
		this.deliveryAgeRangeName = deliveryAgeRangeName;
	}

	public String getDeliveryWeekCd() {
		return deliveryWeekCd;
	}

	public void setDeliveryWeekCd(String deliveryWeekCd) {
		this.deliveryWeekCd = deliveryWeekCd;
	}

	public String getDeliveryWeekName() {
		return deliveryWeekName;
	}

	public void setDeliveryWeekName(String deliveryWeekName) {
		this.deliveryWeekName = deliveryWeekName;
	}

	public String getDeliveryTimeStartCd() {
		return deliveryTimeStartCd;
	}

	public void setDeliveryTimeStartCd(String deliveryTimeStartCd) {
		this.deliveryTimeStartCd = deliveryTimeStartCd;
	}

	public String getDeliveryTimeStartName() {
		return deliveryTimeStartName;
	}

	public void setDeliveryTimeStartName(String deliveryTimeStartName) {
		this.deliveryTimeStartName = deliveryTimeStartName;
	}

	public String getDeliveryTimeEndCd() {
		return deliveryTimeEndCd;
	}

	public void setDeliveryTimeEndCd(String deliveryTimeEndCd) {
		this.deliveryTimeEndCd = deliveryTimeEndCd;
	}

	public String getDeliveryTimeEndName() {
		return deliveryTimeEndName;
	}

	public void setDeliveryTimeEndName(String deliveryTimeEndName) {
		this.deliveryTimeEndName = deliveryTimeEndName;
	}

	public String getDeliveryMethodCd() {
		return deliveryMethodCd;
	}

	public void setDeliveryMethodCd(String deliveryMethodCd) {
		this.deliveryMethodCd = deliveryMethodCd;
	}

	public String getDeliveryMethodName() {
		return deliveryMethodName;
	}

	public void setDeliveryMethodName(String deliveryMethodName) {
		this.deliveryMethodName = deliveryMethodName;
	}

	public Integer getViewSeq() {
		return viewSeq;
	}

	public void setViewSeq(Integer viewSeq) {
		this.viewSeq = viewSeq;
	}

	public String getViewIsHid() {
		return viewIsHid;
	}

	public void setViewIsHid(String viewIsHid) {
		this.viewIsHid = viewIsHid;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public Integer getRegId() {
		return regId;
	}

	public void setRegId(Integer regId) {
		this.regId = regId;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public Integer getModId() {
		return modId;
	}

	public void setModId(Integer modId) {
		this.modId = modId;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

}