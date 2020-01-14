package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Settlement;

@Repository
public class SettlementRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Settlement> SETTELEMENT_ROW_MAPPER = (rs, i) -> {
		Settlement settlement = new Settlement();
		settlement.setId(rs.getInt("id"));
		settlement.setItemOfAccount(rs.getString("item_of_account"));
		settlement.setDate(rs.getTimestamp("timestamp"));
		settlement.setNameOfPersonInCharge(rs.getString("name_of_person_in_charge"));
		settlement.setPayee(rs.getString("payee"));
		settlement.setPayment(rs.getInt("payment"));
		settlement.setUserId(rs.getString("user_id"));
		return settlement;
	};

	public void insert(Settlement settlement) {
		String sql = "INSERT INTO settlements(date,user_id,name_of_person_in_charge,payee,item_of_account,payment) VALUES(:date,:userId,:nameOfPersonInCharge,:payee,:itemOfAccount,:payment)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(settlement);
		template.update(sql, param);
	}

	public List<Settlement> findByUserId(String userId) {
		String sql = "SELECT id,date,user_id,name_of_person_in_charge,payee,item_of_account,payment FROM settlements WHERE user_id=:userId ORDER BY id DESC";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<Settlement> settlementList = template.query(sql, param, SETTELEMENT_ROW_MAPPER);
		return settlementList;
	}

}
