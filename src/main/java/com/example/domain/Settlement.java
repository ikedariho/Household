package com.example.domain;

import java.sql.Timestamp;
import java.time.LocalDate;

public class Settlement {

	private Integer id;
	private Timestamp date;
	private String userId;
	private String nameOfPersonInCharge; // 担当者名(購入者)
	private String payee; // 支払先
	private String itemOfAccount; // 勘定項目 光熱費とか
	private Integer payment; // 支払金額

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNameOfPersonInCharge() {
		return nameOfPersonInCharge;
	}

	public void setNameOfPersonInCharge(String nameOfPersonInCharge) {
		this.nameOfPersonInCharge = nameOfPersonInCharge;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getItemOfAccount() {
		return itemOfAccount;
	}

	public void setItemOfAccount(String itemOfAccount) {
		this.itemOfAccount = itemOfAccount;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Settlement [id=" + id + ", date=" + date + ", userId=" + userId + ", nameOfPersonInCharge="
				+ nameOfPersonInCharge + ", payee=" + payee + ", itemOfAccount=" + itemOfAccount + ", payment="
				+ payment + "]";
	}

}
