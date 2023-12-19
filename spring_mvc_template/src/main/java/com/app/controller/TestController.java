package com.app.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
public TestController() {
	System.out.println("in ctr"+getClass());
}

@GetMapping("/test1")//equivalent to doget

public String generateDynRep(Model map){
	
	System.out.println("in grn dyn resp"+map);//empty map
	map.addAttribute("server_ts", LocalDateTime.now());
	System.out.println(map);//populated map
	return "/test/test1";
}
}
