package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
	
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs,i) ->{
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setLivingBudgetId(rs.getInt("living_budget_id"));
		category.setBudget(rs.getInt("budget"));
		category.setCategoryName(rs.getString("category_name"));
		return category;
	};
	

	/**
	 * 予算情報を挿入する.
	 * 
	 * @param category
	 */
	public void insert(Category category) {
		System.out.println(category);
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		String sql = "INSERT INTO categories(living_budget_id,category_name,budget)VALUES(:livingBudgetId,:categoryName,:budget)";
		template.update(sql, param);
	}
	
	public List<Category> findByLivingBudgetId(Integer livingBudgetId){
		String sql = "SELECT id,living_budget_id,budget,category_name FROM categories WHERE living_budget_id = :livingBudgetId ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("livingBudgetId", livingBudgetId);
		List<Category> categoryList = template.query(sql, param,CATEGORY_ROW_MAPPER);
		if(categoryList.size()==0) {
			return null;
		}
		return categoryList;
	}

}
