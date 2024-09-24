package com.jyes.www.vo.report;

/*
 *  리포트 수정 시, 보여질 지역 상권 분석 데이터 
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.vo.report.ReportBusinessDistrictAnalysisInformationVo
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-10  |      박준태          |     최초 생성
 *  
 */

public class ReportBusinessDistrictAnalysisInformationVo {
	
	// report_business_district_analysis_id
    // report business district analysis 시퀀스
    private Integer reportBusinessDistrictAnalysisId;

    // report_id
    // report 시퀀스
    private Integer reportId;

    // business_district_analysis_id
    // business district analysis 시퀀스
    private Integer businessDistrictAnalysisId;

    // view_seq
    // 뷰 순서
    private Integer viewSeq;

    // view_is_hid
    // 뷰 숨김여부
    private String viewIsHid;

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
    private Long averageCost;

    // average_earning
    // 평균수익
    private Long averageEarning;

    // operate_profit
    // 영업이익 "%"
    private Integer operateProfit;

    // operate_expense
    // 영업비용 "%"
    private Integer operateExpense;

    // all_business_density
    // 전국 업종밀집도 "%"
    private Integer allBusinessDensity;

    // sido_business_density
    // 시/도 업종밀집도 "%"
    private Integer sidoBusinessDensity;

    // dong_business_density
    // 동 업종밀집도 "%"
    private Integer dongBusinessDensity;

    // market_sales_scale
    // 시장 매출 규모
    private Long marketSalesScale;

    // store_sales_scale
    // 점포당 매출 규모
    private Long storeSalesScale;

    // unit_price
    // 단가
    private Long unitPrice;

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

	public Integer getReportBusinessDistrictAnalysisId() {
		return reportBusinessDistrictAnalysisId;
	}

	public void setReportBusinessDistrictAnalysisId(Integer reportBusinessDistrictAnalysisId) {
		this.reportBusinessDistrictAnalysisId = reportBusinessDistrictAnalysisId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getBusinessDistrictAnalysisId() {
		return businessDistrictAnalysisId;
	}

	public void setBusinessDistrictAnalysisId(Integer businessDistrictAnalysisId) {
		this.businessDistrictAnalysisId = businessDistrictAnalysisId;
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

	public Long getAverageCost() {
		return averageCost;
	}

	public void setAverageCost(Long averageCost) {
		this.averageCost = averageCost;
	}

	public Long getAverageEarning() {
		return averageEarning;
	}

	public void setAverageEarning(Long averageEarning) {
		this.averageEarning = averageEarning;
	}

	public Integer getOperateProfit() {
		return operateProfit;
	}

	public void setOperateProfit(Integer operateProfit) {
		this.operateProfit = operateProfit;
	}

	public Integer getOperateExpense() {
		return operateExpense;
	}

	public void setOperateExpense(Integer operateExpense) {
		this.operateExpense = operateExpense;
	}

	public Integer getAllBusinessDensity() {
		return allBusinessDensity;
	}

	public void setAllBusinessDensity(Integer allBusinessDensity) {
		this.allBusinessDensity = allBusinessDensity;
	}

	public Integer getSidoBusinessDensity() {
		return sidoBusinessDensity;
	}

	public void setSidoBusinessDensity(Integer sidoBusinessDensity) {
		this.sidoBusinessDensity = sidoBusinessDensity;
	}

	public Integer getDongBusinessDensity() {
		return dongBusinessDensity;
	}

	public void setDongBusinessDensity(Integer dongBusinessDensity) {
		this.dongBusinessDensity = dongBusinessDensity;
	}

	public Long getMarketSalesScale() {
		return marketSalesScale;
	}

	public void setMarketSalesScale(Long marketSalesScale) {
		this.marketSalesScale = marketSalesScale;
	}

	public Long getStoreSalesScale() {
		return storeSalesScale;
	}

	public void setStoreSalesScale(Long storeSalesScale) {
		this.storeSalesScale = storeSalesScale;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
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
