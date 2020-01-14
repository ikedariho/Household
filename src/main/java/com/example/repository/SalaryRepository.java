package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;
import com.example.domain.LivingBudget;
import com.example.domain.Salary;

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

	private static final RowMapper<Salary> SALARY_ROW_MAPPER = (rs, i) -> {
		Salary salary = new Salary();
		salary.setId(rs.getInt("id"));
		salary.setUserId(rs.getString("user_id"));
		salary.setManSalary(rs.getInt("man_salary"));
		salary.setWomanSalary(rs.getInt("woman_salary"));
		salary.setDate(rs.getTimestamp("date"));
		return salary;

	};

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
				livingBudget.setSalaryId(rs.getInt("l_salary_id"));
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

	/**
	 * 給料情報を取得するメソッド.
	 * 
	 * @param id ユーザID
	 * @return 給料情報
	 */
	public Salary findBySalaryId(Integer id) {
		String sql = "SELECT s.id AS s_id,s.date AS s_date,s.user_id AS s_user_id,s.man_salary AS s_man_salary,s.woman_salary AS s_woman_salary,"
				+ "l.id AS l_id,l.salary_id AS l_salary_id,l.user_id AS l_user_id,l.date AS l_date,"
				+ "c.id AS c_id,c.living_budget_id AS c_living_budget_id,c.category_name AS c_category_name,c.budget AS c_budget "
				+ "FROM salaries AS s LEFT OUTER JOIN living_budgets AS l ON s.id=l.salary_id  "
				+ "LEFT OUTER JOIN categories AS c ON l.id=c.living_budget_id " + "WHERE s.id=:id ORDER BY s_id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Salary> salaryList = template.query(sql, param, SALARY_RESULT_SET_EXTRACTER);
		if (salaryList.size() == 0) {
			return null;
		}
		return salaryList.get(0);
	}

	/**
	 * 給料リストを取得するメソッド.
	 * 
	 * @param userId ユーザID
	 * @return 給料リスト
	 */
	public List<Salary> findByUserId(String userId) {
		String sql = "SELECT id,user_id,man_salary,woman_salary,date FROM salaries WHERE user_id=:userId ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<Salary> salaryList = template.query(sql, param, SALARY_ROW_MAPPER);
		if(salaryList.size() == 0) {
			return null;
		}
		return salaryList;

	}

	/**
	 * 給料リストを取得するメソッド.
	 * 
	 * @param userId ユーザID
	 * @return 給料リスト
	 */
	public List<Salary> findByUserIdUsingResultSetExtractor(String userId) {
		String sql = "SELECT s.id AS s_id,s.date AS s_date,s.user_id AS s_user_id,s.man_salary AS s_man_salary,s.woman_salary AS s_woman_salary,"
				+ "l.id AS l_id,l.salary_id AS l_salary_id,l.user_id AS l_user_id,l.date AS l_date,"
				+ "c.id AS c_id,c.living_budget_id AS c_living_budget_id,c.category_name AS c_category_name,c.budget AS c_budget "
				+ "FROM salaries AS s LEFT OUTER JOIN living_budgets AS l ON s.id=l.salary_id  "
				+ "LEFT OUTER JOIN categories AS c ON l.id=c.living_budget_id "
				+ "WHERE s.user_id=:userId ORDER BY s_id DESC;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<Salary> salaryList = template.query(sql, param, SALARY_RESULT_SET_EXTRACTER);
		return salaryList;
	}

	/**
	 * 1ページ辺りの給料リストを取得するメソッド.
	 * 
	 * @param userId ユーザID
	 * @param limit  1ページ辺りの表示件数
	 * @param offset スタート位置
	 * @return 給料リスト
	 */
	public List<Salary> findByUserIdWithMinSalaryIdAndmaxSalaryIdUsingResultSetExtractor(String userId,
			Integer minSalaryId, Integer maxSalaryId) {
		String sql = "SELECT s.id AS s_id,s.date AS s_date,s.user_id AS s_user_id,s.man_salary AS s_man_salary,s.woman_salary AS s_woman_salary,"
				+ "l.id AS l_id,l.salary_id AS l_salary_id,l.user_id AS l_user_id,l.date AS l_date,"
				+ "c.id AS c_id,c.living_budget_id AS c_living_budget_id,c.category_name AS c_category_name,c.budget AS c_budget "
				+ "FROM salaries AS s LEFT OUTER JOIN living_budgets AS l ON s.id=l.salary_id  "
				+ "LEFT OUTER JOIN categories AS c ON l.id=c.living_budget_id "
				+ "WHERE s.user_id=:userId AND s.id BETWEEN :minSalaryId AND :maxSalaryId ORDER BY s_id DESC,c.id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId)
				.addValue("minSalaryId", minSalaryId).addValue("maxSalaryId", maxSalaryId);
		List<Salary> salaryList = template.query(sql, param, SALARY_RESULT_SET_EXTRACTER);
		if (salaryList.size() == 0) {
			return null;
		}
		return salaryList;
	}

}
