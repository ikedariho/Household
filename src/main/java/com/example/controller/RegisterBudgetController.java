package com.example.controller;

<<<<<<< HEAD
=======
import javax.servlet.http.HttpSession;
>>>>>>> 717f769eb688ae63a59992af14a4b336cb0939d6

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.LivingBudget;
import com.example.domain.Salary;
import com.example.domain.User;
import com.example.form.LivingBudgetForm;
import com.example.service.RegisterBudgetService;

/**
 * 予算登録しその後確認画面を表示する際に使用するコントローラ.
 * 
 * @author riho.ikeda
 *
 */
@Controller
@RequestMapping("/registerBudget")
public class RegisterBudgetController {
	@Autowired
	private RegisterBudgetService registerBudgetService;

//	@Autowired
//	private HttpSession session;

	/**
	 * @param livingBudgetForm
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String registerBudget(LivingBudgetForm livingBudgetForm, Model model) {
		registerBudgetService.registerBudget(livingBudgetForm);
		Salary salary = registerBudgetService.comfirm(livingBudgetForm);
		model.addAttribute("salary", salary);
		return "confirm";
	}
	
	@RequestMapping("/latestBudget")
	@ResponseBody
	public LivingBudget latestBudget(String userId){
		LivingBudget livingBudget = registerBudgetService.latestBudget(userId);
		return livingBudget;
	}

}
