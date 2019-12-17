package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

/**
 * CategoryRepository
 * 
 * @author riho.ikeda
 *
 */
@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 予算情報を挿入する.
	 * 
	 * @param category
	 */
	public void insert(Category category) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		String sql = "INSERT INTO categories(living_budget_id,category_name,budget)VALUES(:livingBudgetId,:categoryName,:budget)";
		template.update(sql, param);
	}

}
