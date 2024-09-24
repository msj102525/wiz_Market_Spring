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

public class ReportDetailDTO {
    
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