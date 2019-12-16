package com.example.form;


import javax.validation.constraints.NotBlank;

public class UserForm {

	/* 男性名前 */
	@NotBlank(message = "必須項目です")
	private String manName;
	/* 女性名前 */
	@NotBlank(message = "必須項目です")
	private String womanName;
	/* UserID */
	@NotBlank(message = "必須項目です")
	private String userId;
	/* パスワード */
	@NotBlank(message = "必須項目です")
	private String password;

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

	@Override
	public String toString() {
		return "UserForm [manName=" + manName + ", womanName=" + womanName + ", userId=" + userId + ", password="
				+ password + "]";
	}

}