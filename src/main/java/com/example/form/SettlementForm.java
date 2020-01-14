package com.example.form;

import javax.validation.constraints.NotBlank;

public class SettlementForm {

	@NotBlank(message = "必須項目です")
	private String nameOfPersonInCharge; // 担当者名(購入者)
	@NotBlank(message = "必須項目です")
	private String payee; // 支払先
	@NotBlank(message = "必須項目です")
	private String itemOfAccount; // 勘定項目 光熱費とか
	@NotBlank(message = "必須項目です")
	private String payment; // 支払金額


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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "SettlementForm [nameOfPersonInCharge=" + nameOfPersonInCharge + ", payee=" + payee + ", itemOfAccount="
				+ itemOfAccount + ", payment=" + payment + "]";
	}



}
