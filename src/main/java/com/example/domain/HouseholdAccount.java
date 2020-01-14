package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

public class HouseholdAccount {

	private Integer id;
	private String userId;
	private Timestamp date;
	private LivingBudget livingBudget;
	private List<Settlement> settlementList;

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

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public LivingBudget getLivingBudget() {
		return livingBudget;
	}

	public void setLivingBudget(LivingBudget livingBudget) {
		this.livingBudget = livingBudget;
	}

	public List<Settlement> getSettlementList() {
		return settlementList;
	}

	public void setSettlementList(List<Settlement> settlementList) {
		this.settlementList = settlementList;
	}

	@Override
	public String toString() {
		return "HouseholdAccount [id=" + id + ", userId=" + userId + ", date=" + date + ", livingBudget=" + livingBudget
				+ ", settlementList=" + settlementList + "]";
	}

}
