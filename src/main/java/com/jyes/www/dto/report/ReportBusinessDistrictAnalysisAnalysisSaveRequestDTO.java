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

public class ReportBusinessDistrictAnalysisAnalysisSaveRequestDTO {
	
	// report_id
	private Integer reportId;
	
	// report_business_district_analysis_id
	// 리포트 지역 상권 분석 시퀀스
	private Integer reportBusinessDistrictAnalysisId;
	
	// business_district_analysis_id
    // business district analysis 시퀀스
    private Integer businessDistrictAnalysisId;

    // area_id
    // area 시퀀스
    private Integer areaId;

    // main_category_id
    // 대분류 category 시퀀스
    private Integer mainCategoryId;

    // sub_category_id
    // 중분류 category 시퀀스
    private Integer subCategoryId;

    // average_cost
    // 평균비용
    private Double averageCost;

    // average_earning
    // 평균수익
    private Double averageEarning;

    // operate_profit
    // 영업이익 "%"
    private Double operateProfit;

    // operate_expense
    // 영업비용 "%"
    private Double operateExpense;

    // all_business_density
    // 전국 업종밀집도 "%"
    private Double allBusinessDensity;

    // sido_business_density
    // 시/도 업종밀집도 "%"
    private Double sidoBusinessDensity;

    // dong_business_density
    // 동 업종밀집도 "%"
    private Double dongBusinessDensity;

    // market_sales_scale
    // 시장 매출 규모
    private Double marketSalesScale;

    // store_sales_scale
    // 점포당 매출 규모
    private Double storeSalesScale;

    // unit_price
    // 단가
    private Double unitPrice;

    // uses
    // 이용건수
    private Integer uses;

    // peak_gender_cd
    // 최고 이용 성별
    private String peakGenderCd;

    // peak_age_range_cd
    // 최고 이용 연령대
    private String peakAgeRangeCd;

    // peak_week_cd
    // 최고 이용 요일
    private String peakWeekCd;

    // peak_time_start_cd
    // 최고 이용 시간대 시간
    private String peakTimeStartCd;

    // peak_time_end_cd
    // 최고 이용 시간대 종료
    private String peakTimeEndCd;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getReportBusinessDistrictAnalysisId() {
		return reportBusinessDistrictAnalysisId;
	}

	public void setReportBusinessDistrictAnalysisId(Integer reportBusinessDistrictAnalysisId) {
		this.reportBusinessDistrictAnalysisId = reportBusinessDistrictAnalysisId;
	}

	public Integer getBusinessDistrictAnalysisId() {
		return businessDistrictAnalysisId;
	}

	public void setBusinessDistrictAnalysisId(Integer businessDistrictAnalysisId) {
		this.businessDistrictAnalysisId = businessDistrictAnalysisId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getMainCategoryId() {
		return mainCategoryId;
	}

	public void setMainCategoryId(Integer mainCategoryId) {
		this.mainCategoryId = mainCategoryId;
	}

	public Integer getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Integer subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public Double getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(Double averageCost) {
		this.averageCost = averageCost;
	}

	public Double getAverageEarning() {
		return averageEarning;
	}

	public void setAverageEarning(Double averageEarning) {
		this.averageEarning = averageEarning;
	}

	public Double getOperateProfit() {
		return operateProfit;
	}

	public void setOperateProfit(Double operateProfit) {
		this.operateProfit = operateProfit;
	}

	public Double getOperateExpense() {
		return operateExpense;
	}

	public void setOperateExpense(Double operateExpense) {
		this.operateExpense = operateExpense;
	}

	public Double getAllBusinessDensity() {
		return allBusinessDensity;
	}

	public void setAllBusinessDensity(Double allBusinessDensity) {
		this.allBusinessDensity = allBusinessDensity;
	}

	public Double getSidoBusinessDensity() {
		return sidoBusinessDensity;
	}

	public void setSidoBusinessDensity(Double sidoBusinessDensity) {
		this.sidoBusinessDensity = sidoBusinessDensity;
	}

	public Double getDongBusinessDensity() {
		return dongBusinessDensity;
	}

	public void setDongBusinessDensity(Double dongBusinessDensity) {
		this.dongBusinessDensity = dongBusinessDensity;
	}

	public Double getMarketSalesScale() {
		return marketSalesScale;
	}

	public void setMarketSalesScale(Double marketSalesScale) {
		this.marketSalesScale = marketSalesScale;
	}

	public Double getStoreSalesScale() {
		return storeSalesScale;
	}

	public void setStoreSalesScale(Double storeSalesScale) {
		this.storeSalesScale = storeSalesScale;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getUses() {
		return uses;
	}

	public void setUses(Integer uses) {
		this.uses = uses;
	}

	public String getPeakGenderCd() {
		return peakGenderCd;
	}

	public void setPeakGenderCd(String peakGenderCd) {
		this.peakGenderCd = peakGenderCd;
	}

	public String getPeakAgeRangeCd() {
		return peakAgeRangeCd;
	}

	public void setPeakAgeRangeCd(String peakAgeRangeCd) {
		this.peakAgeRangeCd = peakAgeRangeCd;
	}

	public String getPeakWeekCd() {
		return peakWeekCd;
	}

	public void setPeakWeekCd(String peakWeekCd) {
		this.peakWeekCd = peakWeekCd;
	}

	public String getPeakTimeStartCd() {
		return peakTimeStartCd;
	}

	public void setPeakTimeStartCd(String peakTimeStartCd) {
		this.peakTimeStartCd = peakTimeStartCd;
	}

	public String getPeakTimeEndCd() {
		return peakTimeEndCd;
	}

	public void setPeakTimeEndCd(String peakTimeEndCd) {
		this.peakTimeEndCd = peakTimeEndCd;
	}

}