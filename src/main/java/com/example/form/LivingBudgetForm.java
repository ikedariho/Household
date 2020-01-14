package com.example.form;

import java.util.List;

/**
 * 予算登録する際に使用するフォーム.
 * 
 * @author riho.ikeda
 *
 */
public class LivingBudgetForm {
	
	/* UserID */
	private String userId;
	/* 男性給料 */
	private String manSalary;
	/* 女性給料 */
	private String womanSalary;
	/* 日付 */
	private String date;
	/* SalaryID */
	private Integer salaryId;
	/* CategoryList */
	private List<String> categoryNameList;
	private List<Integer> budgedList;
	public Integer getIntManSalary() {
		return Integer.parseInt(manSalary);
	}
	
	public Integer getIntWomanSalary() {
		return Integer.parseInt(womanSalary);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getManSalary() {
		return manSalary;
	}

	public void setManSalary(String manSalary) {
		this.manSalary = manSalary;
	}

	public String getWomanSalary() {
		return womanSalary;
	}

	public void setWomanSalary(String womanSalary) {
		this.womanSalary = womanSalary;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

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
		return "LivingBudgetForm [userId=" + userId + ", manSalary=" + manSalary + ", womanSalary=" + womanSalary
				+ ", date=" + date + ", salaryId=" + salaryId + ", categoryNameList=" + categoryNameList
				+ ", budgedList=" + budgedList + "]";
	}
	

}
