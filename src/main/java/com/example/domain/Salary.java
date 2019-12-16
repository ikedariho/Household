package com.example.domain;

import java.security.Timestamp;

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
	private String manSalary;
	/* 女性給料 */
	private String womanSalary;
	/* 日付 */
	private Timestamp date;
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
	public Timestamp getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Timestamp date) {
		this.date = date;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WomanSalary [id=" + id + ", userId=" + userId + ", manSalary=" + manSalary + ", womanSalary="
				+ womanSalary + ", date=" + date + "]";
	}

	

}
