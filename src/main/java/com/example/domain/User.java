package com.example.domain;

import java.security.Timestamp;

/**
 * ユーザー登録に使用するドメイン.
 * 
 * @author riho.ikeda
 *
 */
public class User {
	/* ID */
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
	private Timestamp timestamp;

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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", manName=" + manName + ", womanName=" + womanName + ", userId=" + userId
				+ ", password=" + password + ", timestamp=" + timestamp + "]";
	}
	
	
}