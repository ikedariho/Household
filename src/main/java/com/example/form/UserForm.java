package com.example.form;

import java.sql.Timestamp;

public class UserForm {

	private Integer id;
	/* 男性名前 */
	private String manName;
	/* 女性名前 */
	private String womanName;
	/* UserID */
	private String userId;
	/* パスワード */
	private String password;
	/* 登録日付 */
	private Timestamp date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getManName() {
		return manName;
	}

	public void setManName(String manName) {
		this.manName = manName;
	}

	public String getWomanName() {
		return womanName;
	}

	public void setWomanName(String womanName) {
		this.womanName = womanName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", manName=" + manName + ", womanName=" + womanName + ", userId=" + userId
				+ ", password=" + password + ", date=" + date + "]";
	}

	
	
}