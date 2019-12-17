package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.userRepository;

@Service
public class RegisterUserService {
	
	@Autowired
	private userRepository userRepository;
	
	/**
	 * ユーザ情報を登録するメソッド.
	 * 
	 * @param user
	 */
	public void registerUser(User user ) {
		userRepository.insert(user);
	}
	
	/**
	 * userIdの重複確認を行うメソッド.
	 * 
	 * @param userId ユーザId
	 * @return 検索結果
	 */
	public boolean findByUserId(String userId) {
		User user = userRepository.findByUserId(userId);
		System.out.println(user);
		if(user == null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Userを検索するメソッド.
	 * 
	 * @param user 検索するユーザ情報(userIdとパスワード)
	 * @return ユーザ情報
	 */
	public User findByUserIdAndPassword(User user) {
		String userId = user.getUserId();
		String password = user.getPassword();
		User confirmUser = userRepository.findByUserIdAndPassword(userId, password);
		if(confirmUser != null) {
			confirmUser.setPassword(null);
		}
		return confirmUser;
	}
	

}
