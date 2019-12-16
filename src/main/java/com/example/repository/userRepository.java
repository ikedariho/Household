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

import com.example.domain.User;

/**
 * usersテーブルのリポジトリ.
 * 
 * @author yosuke.yamada
 *
 */
@Repository
public class userRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;
	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("password"));
		user.setManName(rs.getString("man_name"));
		user.setWomanName(rs.getString("woman_name"));
		user.setDate(rs.getTimestamp("date"));
		return user;
	};

	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("users");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}

	/**
	 * ユーザを追加するメソッド.
	 * 
	 * @param user ユーザ情報
	 * @return 新規登録したuserId
	 */
	public User insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		Number key = insert.executeAndReturnKey(param);
		user.setId(key.intValue());
		return user;
	}

	public User findByUserId(String userId) {
		String sql = "SELECT id,user_id,password,man_name,woman_name,date FROM users WHERE user_id = :userId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		System.out.println(userList.size());
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	/**
	 * ユーザIdとパスワードからuserを取得するメソッド.
	 * 
	 * @param userId   ユーザID
	 * @param password パスワード
	 * @return user情報
	 */
	public User findByUserIdAndPassword(String userId, String password) {
		String sql = "SELECT id,user_id,password,man_name,woman_name,date FROM users WHERE user_id = :userId AND password = :password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("password",
				password);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if(userList.size()==0) {
			return null;
		}
		return userList.get(0);
	}

}
