package com.jyes.www.dto.report;

import java.util.List;

import com.jyes.www.vo.common.BusinessDistrictAnalysisVo;
import com.jyes.www.vo.common.LocationAnalysisVo;
import com.jyes.www.vo.report.ReportDetailVo;
import com.jyes.www.vo.report.ReportVo;

/*
 *  리포트 세부 생성/수정 용도 
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportDetailSaveDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-24  |      박준태          |     최초 생성
 *  
 */

public class ReportDetailSaveRequestDTO {
	
	// report_detail_id
	// report detail 시퀀스
	private Integer reportDetailId;

	// report_id
	// report 시퀀스
	private Integer reportId;

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