package com.example.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Salary;
import com.example.domain.User;
import com.example.form.SalaryForm;
import com.example.service.SalaryService;

/**
 * 給与登録するコントローラ.
 * 
 * @author riho.ikeda
 *
 */
@Controller
@RequestMapping("/salary")
public class SalaryController {

	@Autowired
	private SalaryService salaryService;
	
	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String index(Model model) {
		
		
		
		return "salary_calculation";
	}

	/**
	 * 給与情報を登録する.
	 * 
	 * @param salaryForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/salaryInsert")
	public String salaryInsert(SalaryForm salaryForm, Model model) {
		Salary salary = new Salary();
		BeanUtils.copyProperties(salaryForm, salary);
		salary.setManSalary(salaryForm.getIntManSalary());
		salary.setWomanSalary(salaryForm.getIntWomanSalary());
		LocalDateTime nowLocalDt = LocalDateTime.now();
		Timestamp date = Timestamp.valueOf(nowLocalDt);
		salary.setDate(date);
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		salary.setUserId(userId);
		Integer salaryld = salaryService.salaryInsert(salary);
		model.addAttribute("salaryId", salaryld);
		return "living_budget";
		

	}

}
