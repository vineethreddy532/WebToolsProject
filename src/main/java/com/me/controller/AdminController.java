package com.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
	
	@RequestMapping(value = "/registerAdminUser.htm", method = RequestMethod.GET)
	public ModelAndView showAdminLoginForm() {
		
		return new ModelAndView("user-create-form","admin","admin");
	}
}
