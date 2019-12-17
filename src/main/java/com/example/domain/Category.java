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
	private String budget;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getLivingBudgetId() {
		return livingBudgetId;
	}
	public void setLivingBudgetId(Integer livingBudgetId) {
		this.livingBudgetId = livingBudgetId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", livingBudgetId=" + livingBudgetId + ", categoryName=" + categoryName
				+ ", budget=" + budget + "]";
	}


	

}
