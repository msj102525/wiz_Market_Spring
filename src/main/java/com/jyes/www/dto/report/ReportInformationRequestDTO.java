package com.jyes.www.dto.report;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jyes.www.dto.common.FileInsertRequestDTO;
import com.jyes.www.vo.report.ReportInformationVo;

/*
 *  리포트 정보 생성 용도 
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.report.ReportInformationRequestDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-09  |      박준태          |     최초 생성
 *  
 */

public class ReportInformationRequestDTO {
	
	// report_information_id
    // report information 시퀀스
    private Integer reportInformationId;

    // common_information_id
    // common information 시퀀스
    private Integer commonInformationId;

    // report_id
    // report 시퀀스
    private Integer reportId;

    // title
    // 제목
    private String title;

    // content
    // 내용
    private String content;

    // file_group_id
    // 파일 그룹 시퀀스
    private Integer fileGroupId;

    // view_seq
    // 뷰 순서
    private Integer viewSeq;

    // view_is_hid
    // 숨김여부
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

    // 파일 list
 	private List<MultipartFile> fileList;
 	
 	// 삭제 file_id list
 	private List<Integer> deleteFileIdList;
 	
 	// 발행여부
 	private String isIssued;

	public Integer getReportInformationId() {
		return reportInformationId;
	}

	public void setReportInformationId(Integer reportInformationId) {
		this.reportInformationId = reportInformationId;
	}

	public Integer getCommonInformationId() {
		return commonInformationId;
	}

	public void setCommonInformationId(Integer commonInformationId) {
		this.commonInformationId = commonInformationId;
	}

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getFileGroupId() {
		return fileGroupId;
	}

	public void setFileGroupId(Integer fileGroupId) {
		this.fileGroupId = fileGroupId;
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

	public List<MultipartFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<MultipartFile> fileList) {
		this.fileList = fileList;
	}

	public List<Integer> getDeleteFileIdList() {
		return deleteFileIdList;
	}

	public void setDeleteFileIdList(List<Integer> deleteFileIdList) {
		this.deleteFileIdList = deleteFileIdList;
	}

	public String getIsIssued() {
		return isIssued;
	}

	public void setIsIssued(String isIssued) {
		this.isIssued = isIssued;
	}

}