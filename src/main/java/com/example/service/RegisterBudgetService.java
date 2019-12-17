package com.example.service;

import java.util.Date;

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

		Date date = new Date();
		livingBudget.setDate(date);
		
		livingBudgetRepository.insert(livingBudget);

		Category category = new Category();
		if (livingBudgetForm.getCategoryNameList() != null) {
			Integer count = 0;
			for (String categoryName : livingBudgetForm.getCategoryNameList()) {
				category.setCategoryName(categoryName);
				livingBudget.getCategoryList().add(category);
			}
			for (Integer budget : livingBudgetForm.getbugetList()) {
				livingBudget.getCategoryList().get(count).setBudget(budget);
				count += 1;
			}

			for (Category categoryitem : livingBudget.getCategoryList()) {
				category.setLivingBudgetId(livingBudget.getId());
				category.setCategoryName(categoryitem.getCategoryName());
				category.setBudget(categoryitem.getBudget());
				categoryRepository.insert(category);

			}
		}
	}

}
