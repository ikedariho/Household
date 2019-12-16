package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.service.RegisterUserService;

@Controller
@RequestMapping("/")
public class registerUserController {

	@Autowired
	private RegisterUserService registerUserService;
	
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
			result.rejectValue("error",null, "そのメールアドレスは登録されています");
			return registerUserForm();
		}
		registerUserService.registerUser(user);
		return loginForm();
	}

}
