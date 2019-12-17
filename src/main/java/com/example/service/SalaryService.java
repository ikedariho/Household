package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Salary;
import com.example.repository.SalaryRepository;

/**
 * 給与登録するサービス.
 * 
 * @author riho.ikeda
 *
 */
@Service
@Transactional
public class SalaryService {

	@Autowired
	private SalaryRepository salaryRepository;

	/**
	 * 給与登録する.
	 * 
	 * @param salary
	 */
	public Integer salaryInsert(Salary salary) {
		Integer salaryId =  salaryRepository.insert(salary);
		return salaryId;
	}

}
