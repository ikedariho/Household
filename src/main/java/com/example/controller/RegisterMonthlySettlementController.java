package com.example.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Settlement;
import com.example.domain.User;
import com.example.form.SettlementForm;
import com.example.service.RegisterSettlementService;

@Controller
@RequestMapping("/registerMonthlySettlement")
public class RegisterMonthlySettlementController {

	@ModelAttribute
	public SettlementForm settlementForm() {
		return new SettlementForm();
	}
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private RegisterSettlementService registerSettlementService;
	

	/**
	 * 家計簿登録画面を表示するメソッド.
	 * 
	 * @return 家計簿登録画面に遷移
	 */
	@RequestMapping("")
	public String index() {
		return "register_monthly_settlement_form";
	}

	/**
	 * 家計簿を登録するメソッド.
	 * 
	 * @param settlementForm リクエストパラメータ
	 * @param result バリデーション
	 * @param model リクエストスコープ
	 * @return メインメニューに遷移
	 */
	@RequestMapping("/registerSettlement")
	public String registerSettlement(@Validated SettlementForm settlementForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return index();
		}
		registerSettlementService.registerSettlement(settlementForm);
		return "forward:/showMainMenu";
	}
	
	

}
