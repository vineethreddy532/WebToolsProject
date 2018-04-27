package com.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.CartDAO;
import com.me.dao.ProductDAO;
import com.me.pojo.Product;
import com.me.pojo.User;

@Controller
public class UserCartController {
	
	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;
	
	@RequestMapping(value = "/cartChange.htm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String submit(HttpServletRequest request, @RequestParam("name") String name,@RequestParam("price") String price, @RequestParam("location") String location) {
		HttpSession session = request.getSession();
		name = name.replaceAll("^\"|\"$", "");
		price = price.replaceAll("^\"|\"$", "");
		location = location.replaceAll("^\"|\"$", "");
		double d = Double.parseDouble(price);
		System.out.println(d);
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		cartDao.updateCheckedProduct(name,price,location,loggedInUser);
		return null;
	}
	
	@RequestMapping(value = "/removeCart.htm", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String removeCart(HttpServletRequest request, @RequestParam("name") String name,@RequestParam("price") String price, @RequestParam("location") String location) {
		HttpSession session = request.getSession();
		name = name.replaceAll("^\"|\"$", "");
		price = price.replaceAll("^\"|\"$", "");
		location = location.replaceAll("^\"|\"$", "");
		double d = Double.parseDouble(price);
		User loggedInUser = (User) session.getAttribute("loggedInUser");
		cartDao.updateRemovedProduct(name,price,location,loggedInUser);
		return null;
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	protected ModelAndView afterAddProducts(HttpServletRequest request) throws Exception {
		List<Product> checkedList = new ArrayList();
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		checkedList = cartDao.findCheckedList(u);
		mv.addObject("checkedList",checkedList);
		mv.setViewName("checkout");
		return mv;
	}
	
	@RequestMapping(value = "/submitProduct", method = RequestMethod.POST)
	protected ModelAndView afterSubmitProducts(HttpServletRequest request) throws Exception {
		List<Product> submitedList = new ArrayList();
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		submitedList = cartDao.findCheckedList(u);
		for(Product p:submitedList) {
			cartDao.updateConfirmedStatus(p);
		}
		mv.setViewName("submitProduct");
		return mv;
	}
}
