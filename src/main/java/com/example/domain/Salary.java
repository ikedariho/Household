package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * 給与登録の際に使用するドメイン.
 * 
 * @author riho.ikeda
 *
 */

public class Salary {

	/* ID */
	private Integer id;
	/* UserID */
	private String userId;
	/* 男性給料 */
	private Integer manSalary;
	/* 女性給料 */
	private Integer womanSalary;
	/* 日付 */
	private Timestamp date;
	/* LivingBudgetList */
	private List<LivingBudget> livingBudgetList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getManSalary() {
		return manSalary;
	}

	public void setManSalary(Integer manSalary) {
		this.manSalary = manSalary;
	}

	public Integer getWomanSalary() {
		return womanSalary;
	}

	public void setWomanSalary(Integer womanSalary) {
		this.womanSalary = womanSalary;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	/**
	 * @return the livingBudgetList
	 */
	public List<LivingBudget> getLivingBudgetList() {
		return livingBudgetList;
	}

	/**
	 * @param livingBudgetList the livingBudgetList to set
	 */
	public void setLivingBudgetList(List<LivingBudget> livingBudgetList) {
		this.livingBudgetList = livingBudgetList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Salary [id=" + id + ", userId=" + userId + ", manSalary=" + manSalary + ", womanSalary=" + womanSalary
				+ ", date=" + date + ", livingBudgetList=" + livingBudgetList + "]";
	}

	

}