package com.me.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
	
	@RequestMapping(value = "/user-shop-checkout", method = RequestMethod.POST)
	protected String checkedProducts(HttpServletRequest request) throws Exception {
		return "user-shop-checkout";
	}
	
	@RequestMapping(value = "/addShopProduct.htm", method = RequestMethod.POST)
	protected String viewProduct(HttpServletRequest request) throws Exception {
		return "addShopProduct.htm";
	}
	
	@RequestMapping(value = "/viewShopProduct", method = RequestMethod.POST)
	protected String addProducts(HttpServletRequest request) throws Exception {
		return "viewShopProduct";
	}
	
	@RequestMapping(value = "/afteraddproducts", method = RequestMethod.POST)
	protected String afterAddProducts(HttpServletRequest request) throws Exception {
		return "shop-owner-init";
	}

}
