package com.example.form;

import java.util.Date;
import java.util.List;

/**
 * 予算登録する際に使用するフォーム.
 * 
 * @author riho.ikeda
 *
 */
public class LivingBudgetForm {

	/* SalaryID */
	private Integer salaryId;
	/* UserID */
	private String userId;
	/* 日付 */
	private Date date;
	/* CategoryList */
	private List<String> categoryNameList;

	private List<Integer> budgedList;

	/**
	 * @return the salaryId
	 */
	public Integer getSalaryId() {
		return salaryId;
	}

	/**
	 * @param salaryId the salaryId to set
	 */
	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the categoryNameList
	 */
	public List<String> getCategoryNameList() {
		return categoryNameList;
	}

	/**
	 * @param categoryNameList the categoryNameList to set
	 */
	public void setCategoryNameList(List<String> categoryNameList) {
		this.categoryNameList = categoryNameList;
	}

	/**
	 * @return the list
	 */
	public List<Integer> getbudgedList() {
		return budgedList;
	}

	/**
	 * @param list the list to set
	 */
	public void setbudgedList(List<Integer> budgedList) {
		this.budgedList = budgedList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LivingBudgetForm [salaryId=" + salaryId + ", userId=" + userId + ", date=" + date
				+ ", categoryNameList=" + categoryNameList + ", budgedList=" + budgedList + "]";
	}

}
