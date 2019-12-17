package com.example.form;

import java.util.List;


public class testerForm {

	private List<String> categoryNameList;
	private List<Integer> budgetList;
	public List<String> getCategoryNameList() {
		return categoryNameList;
	}
	public void setCategoryNameList(List<String> categoryNameList) {
		this.categoryNameList = categoryNameList;
	}
	public List<Integer> getBudgetList() {
		return budgetList;
	}
	public void setBudgetList(List<Integer> budgetList) {
		this.budgetList = budgetList;
	}
	@Override
	public String toString() {
		return "testerForm [categoryNameList=" + categoryNameList + ", budgetList=" + budgetList + "]";
	}

	
	
}