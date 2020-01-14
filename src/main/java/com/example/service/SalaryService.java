package com.example.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Salary;
import com.example.domain.User;
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

	@Autowired
	private HttpSession session;

	/**
	 * 給与登録する.
	 * 
	 * @param salary
	 */
	public void salaryInsert(Salary salary) {
		Integer salaryId = salaryRepository.insert(salary);
		salary.setId(salaryId);
		session.removeAttribute("salary");
		session.setAttribute("salary", salary);
	}
	
	public Timestamp makeTimestamp(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate = null;
		try {
			parsedDate = format.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		return timestamp;
	}

	/**
	 * 最新の給料情報を取得するメソッド
	 * 
	 * @return 最新の給料情報
	 */
	public Salary findByLatestSalary() {
		User user = (User) session.getAttribute("user");
		String userId = user.getUserId();
		List<Salary> salarylist = salaryRepository.findByUserId(userId);
		if (salarylist != null) {
			System.out.println("サラリーリスト"+salarylist);
			return salarylist.get(0);
		}
		return null;
	}
}
