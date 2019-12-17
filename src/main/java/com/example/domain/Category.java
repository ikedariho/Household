package com.example.domain;

/**
 * CategoryNameのドメイン.
 * 
 * @author riho.ikeda
 *
 */
public class Category {
	/* ID */
	private Integer id;
	/* LivingBudgetsID */
	private Integer livingBudgetId;
	/* カテゴリー名前 */
	private String categoryName;
	/* カテゴリー予算 */
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
	 * @return the livingBudgetId
	 */
	public Integer getLivingBudgetId() {
		return livingBudgetId;
	}
	/**
	 * @param livingBudgetId the livingBudgetId to set
	 */
	public void setLivingBudgetId(Integer livingBudgetId) {
		this.livingBudgetId = livingBudgetId;
	}
	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Category [id=" + id + ", livingBudgetId=" + livingBudgetId + ", categoryName=" + categoryName
				+ ", budget=" + budget + "]";
	}

	

}
