package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Salary;
import com.example.service.RegisterBudgetService;
import com.example.service.SalaryService;

@Controller
@RequestMapping("/showMainMenu")
public class ShowMainMenuController {

	@Autowired
	private HttpSession session;

	@Autowired
	private SalaryService salaryService;

	@Autowired
	private RegisterBudgetService registerBudgetService;

	private static final int ONEPAGEVIEW = 6;
	
	private static final int STARTPAGENUMBER = 1;

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
		System.out.println(salary);
		// セッションIDがnullだった場合に実行
		if (salary == null) {
			salary = salaryService.findByLatestSalary();
		}
		System.out.println(salary);
		session.setAttribute("salary", salary);
		return "living_budget";
	}

	/**
	 * 登録した予算リスト履歴ページに遷移させるメソッド.
	 * 
	 * @param model リクエストスコープ
	 * @return 予算履歴ページに遷移
	 */

	@RequestMapping("/showBudgetHistory")
	public String showBudgetHistory(Model model) {
		int pageNumber = STARTPAGENUMBER;
		List<Salary> allSalaryList = registerBudgetService.getAllSalaryList();// 履歴総数を調べる.niti 
		int historyCount = allSalaryList.size();
		List<String> pagingNumberList = registerBudgetService.makePagingNumberList(historyCount, ONEPAGEVIEW,pageNumber);
		List<Salary> salaryList = registerBudgetService.getOnePageSalaryList(pageNumber, ONEPAGEVIEW);
		model.addAttribute("salaryList", salaryList);
		model.addAttribute("pagingNumberList",pagingNumberList);
		return "living_budget_history";
	}
	
	/**
	 * クリックしたページに移動するメソッド
	 * 
	 * @param pageNumber クリックしたページ番号 
	 * @param model リクエストスコープ
	 * @return 予算履歴ページに遷移
	 */
	@RequestMapping("/showNumberOfBudgetHistory")
	public String showNumberOfBudgetHistory(Integer pageNumber,Model model) {
		Integer thisPagenumber = pageNumber;
		List<String> pagingNumberList = new ArrayList<>();
		int historyCount = registerBudgetService.getAllSalaryList().size();
		System.out.println(pageNumber);
		System.out.println(pagingNumberList);
		if(pageNumber==null) {
			pageNumber=1;
		}
		pagingNumberList = registerBudgetService.makePagingNumberList(historyCount, ONEPAGEVIEW, pageNumber);
		List<Salary> salaryList = registerBudgetService.getOnePageSalaryList(pageNumber, ONEPAGEVIEW);
		model.addAttribute("salaryList", salaryList);
		model.addAttribute("pagingNumberList",pagingNumberList);
		model.addAttribute("thisPageNumber",thisPagenumber);
		return "living_budget_history";
	}
	
}
