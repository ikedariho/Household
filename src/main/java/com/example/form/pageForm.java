package com.example.form;

import java.util.List;

public class pageForm {
	
	private List<Integer> pageList;
	private String pageNumber;
	public List<Integer> getPageList() {
		return pageList;
	}
	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	@Override
	public String toString() {
		return "pageForm [pageList=" + pageList + ", pageNumber=" + pageNumber + "]";
	}

}
