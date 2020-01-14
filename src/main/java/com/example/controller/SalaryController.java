package com.example.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String index() {
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
		
		String date = salaryForm.getDate();
		System.out.println(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = null;
		try {
			parsedDate = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		salary.setDate(timestamp);
		User user = (User)session.getAttribute("user");
		String userId = user.getUserId();
		salary.setUserId(userId);
		salaryService.salaryInsert(salary);
		return "main_menu";
	}
	

}
