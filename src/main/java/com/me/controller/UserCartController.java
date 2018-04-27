package com.me.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserCartController {
	
	@RequestMapping(value = "/user-shop-checkout1", method = RequestMethod.POST)
	protected String checkedProducts(HttpServletRequest request) throws Exception {
		return "user-shop-checkout";
	}
}
