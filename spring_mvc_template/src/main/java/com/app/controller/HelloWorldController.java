package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  //Mandatory cls level anno to tell sc, following a spring bean containing logic(=handler)
			 //singleton eager	
public class HelloWorldController {
	public HelloWorldController() {
		System.out.println("in ctr of"+getClass());
	}
	@RequestMapping("/hello")//equivalent to service of Servlet 
					//to tell sc,following is a req handling method 
					//which can accept--get/post/delete/patch
	public String sayHello() {
		System.out.println("in say hello");  //called once per request 
		return "/display";  //entries in handler mapping will get started
		//logical forword view name send from handler to dispatcher servlet(ds)
		//ds sent it to view resolver-->job translate from avm:/WEB-INF/views/diaplay.jsp
		//control came back to ds
		//D>S>RD rd= request.getRD("/WEB-INF/views/diaplay.jsp");
		//rd.forword(request,resp)-->view layer(jsp)
	}
}
