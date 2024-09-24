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
 *  위치 : com.jyes.www.dto.report.ReportBusinessDistrictAnalysisDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-09  |      박준태          |     최초 생성
 *  
 */

public class ReportBusinessDistrictAnalysisDTO {

	// report_business_district_analysis_id
	// report business district analysis 시퀀스
	private Integer reportBusinessDistrictAnalysisId;

	// report_id
	// report 시퀀스
	private Integer reportId;

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
    
    private String peakGenderName;

    // peak_age_range_cd
    // 최고 이용 연령대
    private String peakAgeRangeCd;
    
    private String peakAgeRangeName;

    // peak_week_cd
    // 최고 이용 요일
    private String peakWeekCd;
    
    private String peakWeekName;

    // peak_time_start_cd
    // 최고 이용 시간대 시간
    private String peakTimeStartCd;
    
    private String peakTimeStartName;

    // peak_time_end_cd
    // 최고 이용 시간대 종료
    private String peakTimeEndCd;
    
    private String peakTimeEndName;

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

}