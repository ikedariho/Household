package com.example.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
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

}
