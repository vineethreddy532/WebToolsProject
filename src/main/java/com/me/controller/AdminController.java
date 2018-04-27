package com.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.AdminDAO;
import com.me.dao.ProductDAO;
import com.me.pojo.Product;
import com.me.pojo.User;

@Controller
public class AdminController {
	
	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;
	
	@RequestMapping(value = "/registerAdminUser.htm", method = RequestMethod.GET)
	public ModelAndView showAdminLoginForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if(u==null) {
			return new ModelAndView("user-login");
		}
		return new ModelAndView("user-create-form","admin","admin");
	}
	
	@RequestMapping(value = "/prodAndStatus.htm", method = RequestMethod.GET)
	public ModelAndView showProductsAdmin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if(u==null) {
			return new ModelAndView("user-login");
		}
		List<Product> checkedList = new ArrayList();
		ModelAndView mv = new ModelAndView();
		checkedList = adminDao.getAllProducts();
		mv.addObject("checkedList",checkedList);
		mv.setViewName("prodAndStatus");
		return mv;
	}
	
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "user-login";
	}
	
	@RequestMapping(value = "/admin.htm", method = RequestMethod.GET)
	public ModelAndView adminMain(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if(u==null) {
			return new ModelAndView("user-login");
		}
		return new ModelAndView("admin");
	}
}
