package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Settlement;
import com.example.domain.User;
import com.example.form.SettlementForm;
import com.example.repository.SettlementRepository;


@Service
public class RegisterSettlementService {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private SettlementRepository settlementRepository;
	
	
	public void registerSettlement(SettlementForm settlementForm) {
		Settlement settlement = new Settlement();
		BeanUtils.copyProperties(settlementForm, settlement);
		User user = (User) session.getAttribute("user");
		LocalDateTime localDateTime = LocalDateTime.now();
		Timestamp timestamp = Timestamp.valueOf(localDateTime);
		String userId = user.getUserId();
		settlement.setPayment(Integer.parseInt(settlementForm.getPayment()));
		settlement.setUserId(userId);
		settlement.setDate(timestamp);
		settlementRepository.insert(settlement);
	}
	
	public List<Settlement> findByUserId(String userId){
		List<Settlement> settlementList = settlementRepository.findByUserId(userId);
		return settlementList;
	}

}
