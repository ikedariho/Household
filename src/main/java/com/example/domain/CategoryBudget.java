package com.example.domain;

/**
 * CategoryBudgetのドメイン.
 * 
 * @author riho.ikeda
 *
 */
public class CategoryBudget {
	/* ID */
	private Integer id;
	/* CategoryNameID */
	private Integer categoryNameId;
	/* 予算 */
	private Integer budget;

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
	 * @return the categoryNameId
	 */
	public Integer getCategoryNameId() {
		return categoryNameId;
	}

	/**
	 * @param categoryNameId the categoryNameId to set
	 */
	public void setCategoryNameId(Integer categoryNameId) {
		this.categoryNameId = categoryNameId;
	}

	/**
	 * @return the budget
	 */
	public Integer getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(Integer budget) {
		this.budget = budget;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CategoryBudget [id=" + id + ", categoryNameId=" + categoryNameId + ", budget=" + budget + "]";
	}

}
