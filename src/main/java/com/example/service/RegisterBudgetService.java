package com.example.service;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.domain.LivingBudget;
import com.example.domain.Salary;
import com.example.form.LivingBudgetForm;
import com.example.repository.CategoryRepository;
import com.example.repository.LivingBudgetRepository;
import com.example.repository.SalaryRepository;

/**
 * 予算登録するサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class RegisterBudgetService {

	@Autowired
	private SalaryRepository salaryRepository;

	@Autowired
	private LivingBudgetRepository livingBudgetRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 予算情報をインサートする.
	 * 
	 * @param livingBudgetForm
	 */
	public void registerBudget(LivingBudgetForm livingBudgetForm) {
		LivingBudget livingBudget = new LivingBudget();
		livingBudget.setSalaryId(livingBudgetForm.getSalaryId());
		livingBudget.setUserId(livingBudgetForm.getUserId());

		Date date = new Date();
		livingBudget.setDate(date);

		livingBudgetRepository.insert(livingBudget);
		System.out.println(livingBudgetForm);
		Category category = null;
		if (livingBudgetForm.getCategoryNameList() != null) {
			Integer count = 0;
			List<Category> CategoryList = new ArrayList<>();
			for (String categoryName : livingBudgetForm.getCategoryNameList()) {
				category = new Category();
				category.setLivingBudgetId(livingBudget.getId());
				category.setCategoryName(categoryName);
				CategoryList.add(category);

			}
			livingBudget.setCategoryList(CategoryList);

			for (Integer budget : livingBudgetForm.getbudgedList()) {
				category = new Category();
				category = livingBudget.getCategoryList().get(count);
				category.setBudget(budget);
				count += 1;
				categoryRepository.insert(category);
			}
		}
	};

	/**
	 * 確認画面を表示させる.
	 * 
	 * @param livingBudgetForm
	 * @return
	 */
	public Salary comfirm(LivingBudgetForm livingBudgetForm) {
		return salaryRepository.findBySalaryId(livingBudgetForm.getSalaryId());

	}

}
