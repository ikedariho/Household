package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.testerForm;

@Controller
@RequestMapping("/test")
public class testController {

	@RequestMapping
	public String index() {
		return "living_budged";
	}
	
	@RequestMapping("/testdata")
	public String testData(testerForm testerForm) {
		System.out.println(testerForm);
		return index();
		
	}
}
