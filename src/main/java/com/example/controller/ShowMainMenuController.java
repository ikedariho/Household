package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Salary;
import com.example.service.SalaryService;

@Controller
@RequestMapping("/showMainMenu")
public class ShowMainMenuController {

	@Autowired
	private HttpSession session;

	@Autowired
	private SalaryService salaryService;

	@RequestMapping("")
	public String index() {
		return "main_menu";
	}

	@RequestMapping("/showSalaryCalculation")
	public String showRegisterSalaryCalculation() {
		return "forward:/salary";
	}

	@RequestMapping("/showLivingBudget")
	public String showlivingBudget() {
		Salary salary = (Salary) session.getAttribute("salary");
		// セッションIDがnullだった場合に実行
		if (salary == null) {
			salary = salaryService.findByLatestSalary();
		}
		session.setAttribute("salary", salary);
		System.out.println(salary);
		return "living_budget";
	}
}
