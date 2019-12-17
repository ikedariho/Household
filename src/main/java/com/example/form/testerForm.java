package com.example.form;

import java.util.List;


public class testerForm {

	private List<String> categoryNameList;
	private List<Integer> budgedList;
	
	public List<String> getCategoryNameList() {
		return categoryNameList;
	}
	public void setCategoryNameList(List<String> categoryNameList) {
		this.categoryNameList = categoryNameList;
	}
	public List<Integer> getBudgedList() {
		return budgedList;
	}
	public void setBudgedList(List<Integer> budgedList) {
		this.budgedList = budgedList;
	}
	@Override
	public String toString() {
		return "testerForm [categoryNameList=" + categoryNameList + ", budgedList=" + budgedList + "]";
	}

	
	
}
