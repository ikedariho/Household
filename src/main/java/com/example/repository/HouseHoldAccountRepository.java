package com.example.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.HouseholdAccount;

@Repository
public class HouseHoldAccountRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<HouseholdAccount> HOUSEHOLD_ACCOUNT_ROW_MAPPER = (rs, i) -> {
		HouseholdAccount householdAccount = new HouseholdAccount();
		householdAccount.setId(rs.getInt("id"));
		householdAccount.setUserId(rs.getString("user_id"));
		householdAccount.setDate(rs.getTimestamp("date"));
		return householdAccount;
	};

	public void insert(HouseholdAccount householdAccount) {
		String sql = "INSERT INTO household_accounts(user_id,date) VALUES(:userId,:date)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(householdAccount);
		template.update(sql, param);
	}

	public HouseholdAccount load(String userId, Timestamp date) {
		String sql = "SELECT id,user_id,date FROM household_accounts WHERE user_id = :userId AND date = :date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("date", date);
		List<HouseholdAccount> householdAccountList = template.query(sql, param, HOUSEHOLD_ACCOUNT_ROW_MAPPER);
		if (householdAccountList.size() == 0) {
			return null;
		}
		return householdAccountList.get(0);
	}

}
