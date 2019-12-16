package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class registerUserController {

	@RequestMapping("")
	public String registerUser() {
		return "login_form";
	}

	@RequestMapping("/showRegisterUserForm")
	public String registerUserForm() {
		return "register_user_form";
	}

}
