package com.jyes.www.dto.report;

/*
 *  검색 용도
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.common.ReportSearchRequestDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-08-08  |      박준태          |     최초 생성
 *  
 */

public class ReportSearchRequestDTO {
	
	private int pageRow;
	
	private int page;
	
	private String searchKey;

	private String searchKeyword;

	public int getPageRow() {
		return pageRow;
	}

	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page){
        this.page = ((page - 1 ) * pageRow);
    }

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

}
