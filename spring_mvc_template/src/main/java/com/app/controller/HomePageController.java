package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomePageController {

	public HomePageController() {
		System.out.println("in ctr of"+getClass());
}
	@RequestMapping("/")
	public String showHomePage(){
		System.out.println("in homepage"+getClass()); 
		return "/index";// home page handler rtn avn/web-inf/views/index.jsp
	}
}
