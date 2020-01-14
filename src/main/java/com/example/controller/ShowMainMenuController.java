package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.HouseholdAccount;
import com.example.domain.Salary;
import com.example.domain.Settlement;
import com.example.domain.User;
import com.example.form.SettlementForm;
import com.example.service.RegisterBudgetService;
import com.example.service.RegisterSettlementService;
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

	@Autowired
	private RegisterSettlementService registerSettlementService;

	private static final int ONEPAGEVIEW = 6;

	private static final int STARTPAGENUMBER = 1;

	@RequestMapping("")
	public String index() {
		return "main_menu";
	}
	
	@RequestMapping("/toLivingBudgetPage")
	public String toLivingBUdgetPage() {
		return "register_living_budget";
	}
	

	/**
	 * 給料登録画面を表示するメソッド.
	 * 
	 * @return 給料登録画面に遷移
	 */
	@RequestMapping("/showSalaryCalculation")
	public String showRegisterSalaryCalculation() {
		return "forward:/salary";
	}

	/**
	 * 予算登録画面に遷移するメソッド.
	 * 
	 * @return 予算登録画面に遷移
	 */
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
		User user = (User) session.getAttribute("user");
		HouseholdAccount houseHoldAccount = null;
		String userId = user.getUserId();
		int pageNumber = STARTPAGENUMBER;
		List<Salary> allSalaryList = registerBudgetService.getAllSalaryList();// 履歴総数を調べる.niti
		Integer salaryHistoryCount = allSalaryList.size();
		session.setAttribute("salaryHistoryCount", salaryHistoryCount);
		List<Settlement> allSettlementList = registerSettlementService.findByUserId(userId);
		Integer settlementCount = allSettlementList.size();
		session.setAttribute("settlementCount", settlementCount);
		List<String> pagingNumberList = new ArrayList<>();
		List<Salary> salaryList = new ArrayList<>();
		List<Settlement> settlementList = new ArrayList<>();
			pagingNumberList = registerBudgetService.makePagingNumberList(salaryHistoryCount, ONEPAGEVIEW,
					pageNumber);
			salaryList = registerBudgetService.getOnePageSalaryList(pageNumber, ONEPAGEVIEW, salaryHistoryCount,
					allSalaryList);
			for(Salary salary:salaryList) {
				houseHoldAccount = new HouseholdAccount();
//				houseHoldAccount.setLivingBudget(livingBudget);
				settlementList = registerSettlementService.findByUserId(userId);
				houseHoldAccount.setSettlementList(settlementList);
			}
				
			model.addAttribute("settelementList", settlementList);
			model.addAttribute("salaryList", salaryList);
			model.addAttribute("pagingNumberList", pagingNumberList);
		return "living_budget_history";
	}

	/**
	 * クリックしたページに移動するメソッド
	 * 
	 * @param pageNumber クリックしたページ番号
	 * @param model      リクエストスコープ
	 * @return 予算履歴ページに遷移
	 */
	@RequestMapping("/showNumberOfBudgetHistory")
	public String showNumberOfBudgetHistory(Integer pageNumber, Model model) {
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		Integer thisPagenumber = pageNumber;
		List<String> pagingNumberList = new ArrayList<>();
		List<Salary> allSalaryList = registerBudgetService.getAllSalaryList();
		Integer salaryHistoryCount = (Integer)session.getAttribute("salaryHistoryCount");
		Integer settelementCount = (Integer)session.getAttribute("settlementCount");
		System.out.println(salaryHistoryCount + "aaaa");
		if (salaryHistoryCount != 0 && salaryHistoryCount != null) {
			if (pageNumber == null) {
				pageNumber = 1;
			}
			pagingNumberList = registerBudgetService.makePagingNumberList(salaryHistoryCount, ONEPAGEVIEW, pageNumber);
			List<Salary> salaryList = registerBudgetService.getOnePageSalaryList(pageNumber, ONEPAGEVIEW, salaryHistoryCount,
					allSalaryList);
			model.addAttribute("salaryList", salaryList);
			model.addAttribute("pagingNumberList", pagingNumberList);
			model.addAttribute("thisPageNumber", thisPagenumber);
			return "living_budget_history";
		}
		
		List<Settlement> settlementList = registerSettlementService.findByUserId(userId);
		model.addAttribute("settelementList", settlementList);
		return showBudgetHistory(model);
	}

	@RequestMapping("/showRegisterSettlementForm")
	public String showRegisterSettlementForm(SettlementForm settlementForm, BindingResult result, Model model) {
		return "register_monthly_settlement_form";
	}

}
