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

}
