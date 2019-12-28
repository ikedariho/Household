package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showMainMenu")
public class ShowMainMenuController {

	@RequestMapping("")
	public String index() {
		return "main_menu";
	}
	
	@RequestMapping("/showSalaryCalculation")
	public String showRegisterSalaryCalculation() {
		return "salary_calculation";
	}
	
	@RequestMapping("/showlivingBudget")
	public String showlivingBudget() {
		return "living_budget";
	}
}
