package com.example.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.RegisterUserService;

@Controller
@RequestMapping("/")
public class registerUserController {

	@Autowired
	private RegisterUserService registerUserService;
	
	@ModelAttribute
	public UserForm userForm(){
	return new UserForm();
	}
	
	@RequestMapping("")
	public String loginForm() {
		return "login_form";
	}

	@RequestMapping("/showRegisterUserForm")
	public String registerUserForm() {
		return "register_user_form";
	}
	
	@RequestMapping("/registerUser")
	public String registerUser(@Validated UserForm userForm,BindingResult result,Model model) {
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		String userId = user.getUserId();
		boolean checkUserId = registerUserService.findByUserId(userId);
		if(checkUserId==false) {
			result.rejectValue("userId",null, "そのユーザIDは登録されています");
		}
		if(result.hasErrors()) {
			return registerUserForm();
		}
		LocalDateTime nowLocalDt = LocalDateTime.now();
		Timestamp date = Timestamp.valueOf(nowLocalDt);
		user.setDate(date);
		System.out.println(user);
		registerUserService.registerUser(user);
		return loginForm();
	}

}
