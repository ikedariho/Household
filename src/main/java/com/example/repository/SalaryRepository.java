package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

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
	
	

	/**
	 * 給与登録する.
	 * 
	 * @param salary 給与情報
	 */
	public void insert(Salary salary) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(salary);
		String sql = "INSERT INTO  salaries(user_id,date,man_salary,woman_salary) VALUES (:userId,:date,:manSalary,womanSalary)";
		template.update(sql, param);
	}

}
