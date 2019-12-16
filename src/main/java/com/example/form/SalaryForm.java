package com.example.form;


/**
 * 給与登録の際に使用するフォーム.
 * 
 * @author riho.ikeda
 *
 */
public class SalaryForm {
	/* UserID */
	private String userId;
	/* 男性給料 */
	private String manSalary;
	/* 女性給料 */
	private String womanSalary;
	/* 日付 */
	private String date;

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
	 * @return the manSalary
	 */
	public String getManSalary() {
		return manSalary;
	}

	/**
	 * @param manSalary the manSalary to set
	 */
	public void setManSalary(String manSalary) {
		this.manSalary = manSalary;
	}

	/**
	 * @return the womanSalary
	 */
	public String getWomanSalary() {
		return womanSalary;
	}

	/**
	 * @param womanSalary the womanSalary to set
	 */
	public void setWomanSalary(String womanSalary) {
		this.womanSalary = womanSalary;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ManSalaryForm [userId=" + userId + ", manSalary=" + manSalary + ", womanSalary=" + womanSalary
				+ ", date=" + date + "]";
	}

}
