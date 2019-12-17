package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.LivingBudgetForm;
import com.example.service.RegisterBudgetService;

/**
 * 予算登録する際に使用するコントローラ.
 * 
 * @author riho.ikeda
 *
 */
@Controller
@RequestMapping("/registerBudget")
public class RegisterBudgetController {
	@Autowired
	private RegisterBudgetService registerBudgetService;

	@RequestMapping("")

	public String registerBudget(LivingBudgetForm livingBudgetForm) {
		registerBudgetService.registerBudget(livingBudgetForm);
		return "";
	}

}
