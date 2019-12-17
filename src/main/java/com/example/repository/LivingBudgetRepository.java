package com.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.LivingBudget;

/**
 * LivingBudgetRepository
 * 
 * @author riho.ikeda
 *
 */
@Repository
public class LivingBudgetRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("living_budgets");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}

	/**
	 * 予算情報を挿入する.
	 * 
	 * @param livingBudget
	 */
	public LivingBudget insert(LivingBudget livingBudget) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(livingBudget);
		Number key = insert.executeAndReturnKey(param);
		livingBudget.setId(key.intValue());
		return livingBudget;
	}
}
