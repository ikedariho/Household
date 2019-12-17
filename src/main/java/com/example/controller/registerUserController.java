package com.example.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.form.UserForm;
import com.example.service.RegisterUserService;

@Controller
@RequestMapping("/")
public class registerUserController {

	@Autowired
	private RegisterUserService registerUserService;

	@ModelAttribute
	public UserForm userForm() {
		return new UserForm();
	}

	@ModelAttribute
	public LoginForm loginForm() {
		return new LoginForm();
	}
	
	@Autowired
	private HttpSession session;

	/**
	 * ログイン画面を表示するメソッド.
	 * 
	 * @return ログイン画面に遷移
	 */
	@RequestMapping("")
	public String toLoginPage() {
		return "login_form";
	}

	/**
	 * ユーザ登録画面を表示するメソッド.
	 * 
	 * @return ユーザ登録画面に遷移
	 */
	@RequestMapping("/showRegisterUserForm")
	public String registerUserForm() {
		return "register_user_form";
	}

	/**
	 * ユーザ登録を行うメソッド.
	 * 
	 * @param userForm リクエストパラメータ
	 * @param result バリデーション
	 * @param model リクエストスコープ
	 * @return 成功時、ログイン画面に遷移
	 */
	@RequestMapping("/registerUser")
	public String registerUser(@Validated UserForm userForm, BindingResult result, Model model) {
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		String userId = user.getUserId();
		boolean checkUserId = registerUserService.findByUserId(userId);
		if (checkUserId == false) {
			result.rejectValue("userId", null, "そのユーザIDは登録されています");
		}
		if (result.hasErrors()) {
			return registerUserForm();
		}
		LocalDateTime nowLocalDt = LocalDateTime.now();
		Timestamp date = Timestamp.valueOf(nowLocalDt);
		user.setDate(date);
		System.out.println(user);
		registerUserService.registerUser(user);
		return "redirect:/";
	}

	/**
	 * ログインを行うメソッド.
	 * 
	 * @param loginForm リクエストパラメータ
	 * @param result ヴァリデーション
	 * @param model リクエストスコープ
	 * @return ログイン後の画面に遷移
	 */
	@RequestMapping("/login")
	public String toLogin(@Validated LoginForm loginForm, BindingResult result, Model model) {
		User user = new User();
		BeanUtils.copyProperties(loginForm, user);
		User confirmUser = registerUserService.findByUserIdAndPassword(user);
		System.out.println(confirmUser);
		if (confirmUser == null) {
			result.rejectValue("userId", null, "ユーザIDまたはパスワードが違います");
		}
		if (result.hasErrors()) {
			return toLoginPage();
		}
		
		session.setAttribute("user", confirmUser);
		return "salary_calculation";
	}

}
