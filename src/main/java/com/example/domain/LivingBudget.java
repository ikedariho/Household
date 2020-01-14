package com.example.domain;

import java.util.Date;
import java.util.List;

/**
 * LivingBudgetドメイン.
 * 
 * @author riho.ikeda
 *
 */
public class LivingBudget {
	/* ID */
	private Integer id;
	/* SalaryID */
	private Integer salaryId;
	/* UserID */
	private String userId;
	/* 日付 */
	private Date date;
	/* CategoryList */
	private List<Category> categoryList;

	private List<Salary> salaryList;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

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
	public List<Category> getCategoryList() {
		return categoryList;
	}

	/**
	 * @param categoryNameList the categoryNameList to set
	 */
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Salary> getSalaryList() {
		return salaryList;
	}

	public void setSalaryList(List<Salary> salaryList) {
		this.salaryList = salaryList;
	}

	@Override
	public String toString() {
		return "LivingBudget [id=" + id + ", salaryId=" + salaryId + ", userId=" + userId + ", date=" + date
				+ ", categoryList=" + categoryList + ", salaryList=" + salaryList + "]";
	}

}
