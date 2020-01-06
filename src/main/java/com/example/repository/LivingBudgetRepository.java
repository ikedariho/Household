package com.example.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
	
	private static final RowMapper<LivingBudget> LIVING_BUDGET_ROWMAPPER = (rs,i) ->{
		LivingBudget livinbudget = new LivingBudget();
		livinbudget.setId(rs.getInt("id"));
		livinbudget.setUserId(rs.getString("user_id"));
		livinbudget.setSalaryId(rs.getInt("salary_id"));
		livinbudget.setDate(rs.getDate("date"));		
		return livinbudget;
	};

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
	
	/**
	 * ユーザIDから過去の予算リストを検索する.
	 * 
	 * @param userId ユーザID
	 * @return 予算リスト
	 */
	public List<LivingBudget> findByUserId(String userId){
		String sql ="SELECT id,user_id,salary_id,date FROM living_budgets WHERE user_id=:userId ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<LivingBudget> livingBudgetList = template.query(sql, param,LIVING_BUDGET_ROWMAPPER);
		if(livingBudgetList.size()==0) {
			return null;
		}
		return livingBudgetList;
	}
}
