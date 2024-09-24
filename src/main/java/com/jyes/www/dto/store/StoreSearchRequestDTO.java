package com.jyes.www.dto.store;

/*
 *  검색 용도
 *  
 *  역할 : Data Transfer Object
 *  위치 : com.jyes.www.dto.common.StoreSearchRequestDTO
 * 
 *  ------------------------------------------------
 *      생성 일자        |      성 함            |       목적            
 *  ------------------------------------------------
 *    2024-07-26  |      박준태          |     최초 생성
 *  
 */

public class StoreSearchRequestDTO {
	
	private int pageRow;
	
	private int page;

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

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

}
