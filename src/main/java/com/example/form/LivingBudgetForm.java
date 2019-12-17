package com.example.form;

import java.security.Timestamp;
import java.util.List;

import com.example.domain.Category;

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
	private Timestamp date;
	/* CategoryNameList */
	private List<Category> categoryList;

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
	public Timestamp getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @return the category
	 */
	public List<Category> getcategoryList() {
		return categoryList;
	}

	/**
	 * @param category the category to set
	 */
	public void setcategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LivingBudgetForm [salaryId=" + salaryId + ", userId=" + userId + ", date=" + date + ", categoryList="
				+ categoryList + "]";
	}

}
