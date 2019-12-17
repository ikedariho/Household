package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.domain.LivingBudget;
import com.example.form.LivingBudgetForm;
import com.example.repository.CategoryRepository;
import com.example.repository.LivingBudgetRepository;

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
	private LivingBudgetRepository livingBudgetRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public void registerBudget(LivingBudgetForm livingBudgetForm) {
		LivingBudget livingBudget = new LivingBudget();
		livingBudget.setSalaryId(livingBudgetForm.getSalaryId());
		livingBudget.setUserId(livingBudgetForm.getUserId());
		livingBudget.setDate(livingBudgetForm.getDate());
		livingBudgetRepository.insert(livingBudget);

		Category category = new Category();
		if (livingBudgetForm.getcategoryList() != null) {
			for (Category categoryitem : livingBudgetForm.getcategoryList()) {
				category.setLivingBudgetId(livingBudget.getId());
				category.setCategoryName(categoryitem.getCategoryName());
				category.setbudget(categoryitem.getbudget());
				categoryRepository.insert(category);

			}
		}

	}

}
