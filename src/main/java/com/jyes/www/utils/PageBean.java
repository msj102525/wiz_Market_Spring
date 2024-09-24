package com.jyes.www.utils;

public class PageBean {

	private int currentPage;   	// 현재페이지
	private int totalCount;	 	// 전체 게시물 수
	private int totalPage;	 	// 전체 페이지 수
	private int blockCount = 20;	// 한 페이지의  게시물의 수
	private int blockPage = 10;	// 한 화면에 보여줄 페이지 수
	private int startCount;	 	// 한 페이지에서 보여줄 게시글의 시작 번호
	private int endCount;	 	// 한 페이지에서 보여줄 게시글의 끝 번호
	private int startPage;	 	// 시작 페이지
	private int endPage;	 	// 마지막 페이지
	
	private StringBuffer pagingHtml;
	
	public PageBean(){
		
	}
	// 페이징 생성자
	public PageBean(int currentPage, int totalCount, int blockCount) {

		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.blockCount = blockCount;

		// 전체 페이지 수
		totalPage = (int) Math.ceil((double) totalCount / blockCount);
		if (totalPage == 0) {
			totalPage = 1;
		}

		// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (currentPage > totalPage) {
			this.currentPage = totalPage;			
		}

		// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
		startCount = (this.currentPage - 1) * blockCount;
		endCount = startCount + blockCount - 1;

		// 시작 페이지와 마지막 페이지 값 구하기.
		startPage = (int) ((this.currentPage - 1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage - 1;

		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}

	public PageBean(int currentPage, int totalCount, int blockCount, int blockPage) {

		this.blockCount = blockCount;
		this.blockPage = blockPage;
		this.currentPage = currentPage;
		this.totalCount = totalCount;

		// 전체 페이지 수
		totalPage = (int) Math.ceil((double) totalCount / blockCount);
		if (totalPage == 0) {
			totalPage = 1;
		}

		// 현재 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (currentPage > totalPage) {
			currentPage = totalPage;
		}

		// 현재 페이지의 처음과 마지막 글의 번호 가져오기.
		startCount = (currentPage - 1) * blockCount;
		endCount = startCount + blockCount - 1;

		// 시작 페이지와 마지막 페이지 값 구하기.
		startPage = (int) ((currentPage - 1) / blockPage) * blockPage + 1;
		endPage = startPage + blockPage - 1;

		// 마지막 페이지가 전체 페이지 수보다 크면 전체 페이지 수로 설정
		if (endPage > totalPage) {
			endPage = totalPage;
		}
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public int getStartCount() {
		return startCount;
	}

	public void setStartCount(int startCount) {
		this.startCount = startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public void setEndCount(int endCount) {
		this.endCount = endCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public StringBuffer getPagingHtml() {
		return pagingHtml;
	}

	public String setPagingHtmlv(String pUrl) {
		// 이전 block 페이지
		pagingHtml = new StringBuffer();
		
		pagingHtml.append("<script>");
		pagingHtml.append("function preisnull(){ alert('이전 목록이 없습니다.'); return; }");
		pagingHtml.append("function nextisnull(){ alert('다음 목록이 없습니다.'); return; }");
		pagingHtml.append("</script>");
		
		pagingHtml.append("<div class=\"pagination_box\">");		
		pagingHtml.append("<div class=\"pagination\">");		
		
		// 이전 block 페이지
		if (currentPage > 1) {
			pagingHtml.append("<a href=\"JavaScript:pageLink('"+pUrl+"','"+(currentPage - 1)+"');\"><img class=\"previous\" src=\"/assets/vectors/arrow.svg\" /> <span>이전</span></a>");
		}else{
			pagingHtml.append("<a href=\"javascript:preisnull();\"><img class=\"previous\" src=\"/assets/vectors/arrow.svg\" /> <span>이전</span></a>");
		}
		
		//페이지 번호.현재 페이지는 강조하고 링크를 제거.
		for (int i = startPage; i <= endPage; i++) {
			if (i > totalPage) {
				break;
			}
			if (i == currentPage) {
				pagingHtml.append("<a href=\"#\" class=\"active\">"+i+"</a>");
			} else {
				pagingHtml.append("<a href=\"JavaScript:pageLink('"+pUrl+"','"+i+"');\">"+i+"</a>");
			}
		}

		// 다음 block 페이지
		if (totalPage > currentPage) {
			pagingHtml.append("<a href=\"JavaScript:pageLink('"+pUrl+"','"+(currentPage + 1)+"');\"><span>다음</span> <img class=\"next\" src=\"/assets/vectors/arrow.svg\" /></a>");
		}else{
			pagingHtml.append("<a href=\"JavaScript:nextisnull();\"><span>다음</span> <img class=\"next\" src=\"/assets/vectors/arrow.svg\" /></a>");
		}
                
		pagingHtml.append("</div>");
		pagingHtml.append("</div>");
		return pagingHtml.toString();
	}

}