package com.example.repository;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.LivingBudget;
import com.example.domain.Salary;
import com.example.domain.Category;

/**
 * 給与登録するリポジトリ.
 * 
 * @author riho.ikeda
 *
 */
@Repository
public class SalaryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("salaries");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}

	/**
	 * 給与登録する.
	 * 
	 * @param salary 給与情報
	 */
	public Integer insert(Salary salary) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(salary);
		Number key = insert.executeAndReturnKey(param);
		return key.intValue();
	}
	
//	private static final RowMapper<Salary> SALARY_ROW_MAPPER = (rs,i) ->{
//		
//	};
	

	public static final ResultSetExtractor<List<Salary>> SALARY_RESULT_SET_EXTRACTER = (rs) -> {
		List<Salary> salaryList = new ArrayList<>();
		List<LivingBudget> livingBudgeList = null;
		List<Category> categoryList = null;

		int salaryPreId = 0;
		int livingBudgePreId = 0;

		while (rs.next()) {
			if (rs.getInt("s_id") != salaryPreId) {
				Salary salary = new Salary();
				salary.setId(rs.getInt("s_id"));
				salary.setDate(rs.getTimestamp("s_date"));
				salary.setUserId(rs.getString("s_user_id"));
				salary.setManSalary(rs.getInt("s_man_salary"));
				salary.setWomanSalary(rs.getInt("s_woman_salary"));
				livingBudgeList = new ArrayList<>();
				salary.setLivingBudgetList(livingBudgeList);
				salaryList.add(salary);
			}
			salaryPreId = rs.getInt("s_id");

			if (rs.getInt("l_id") != livingBudgePreId) {
				LivingBudget livingBudget = new LivingBudget();
				livingBudget.setId(rs.getInt("l_id"));
				livingBudget.setSalaryId(rs.getInt("salary_id"));
				livingBudget.setUserId(rs.getString("l_user_id"));
				livingBudget.setDate(rs.getDate("l_date"));
				categoryList = new ArrayList<>();
				livingBudget.setCategoryList(categoryList);
				livingBudgeList.add(livingBudget);
			}
			livingBudgePreId = rs.getInt("l_id");

			if (rs.getInt("c_id") != 0) {
				Category category = new Category();
				category.setId(rs.getInt("c_id"));
				category.setLivingBudgetId(rs.getInt("c_living_budget_id"));
				category.setCategoryName(rs.getString("c_category_name"));
				category.setBudget(rs.getInt("c_budget"));
				categoryList.add(category);
			}

		}
		return salaryList;
		

	};

	public Salary findBySalaryId(Integer id) {
		String sql = "SELECT s.id AS s_id,s.date AS s_date,s.user_id AS s_user_id,s.man_salary AS s_man_salary,s.woman_salary AS s_woman_salary,"
				+ "l.id AS l_id,l.salary_id AS l_salary_id,l.user_id AS l_user_id,l.date AS l_date,"
				+ "c.id AS c_id,c.living_budget_id AS c_living_budget_id,c.category_name AS c_category_name,c.budget AS c_budget,"
				+ "FROM salaries AS s LEFT OUTER JOIN living_budgets AS l ON s.id=l.salary_id  "
				+ "LEFT OUTER JOIN categories AS c ON l.id=c.living_budget_id " + "WHERE s.id=:id ORDER BY s_id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Salary> salaryList = template.query(sql, param, SALARY_RESULT_SET_EXTRACTER);
		if (salaryList.size() == 0) {
			return null;
		}
		return salaryList.get(0);
	}

}
