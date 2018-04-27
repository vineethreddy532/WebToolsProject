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

import com.me.dao.ProductDAO;
import com.me.pojo.Product;
import com.me.pojo.User;

@Controller
public class ProductController {

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@RequestMapping(value = "/user-shop-checkout.htm", method = RequestMethod.GET)
	protected ModelAndView checkedProducts(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return new ModelAndView("user-login");
		}
		List<User> userList = new ArrayList();
		ModelAndView mv = new ModelAndView();
		userList = productDao.getAllUsers();
		mv.addObject("userList", userList);
		mv.setViewName("user-shop-checkout");
		return mv;
	}

	@RequestMapping(value = "/addShopProducts.htm", method = RequestMethod.GET)
	protected String viewProduct(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return "user-login";
		}
		return "addShopProducts";
	}

	@RequestMapping(value = "/shopOwner.htm", method = RequestMethod.GET)
	protected String shopMain(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return "user-login";
		}
		return "shop-owner-init";
	}

	@RequestMapping(value = "/viewshopproduct.htm", method = RequestMethod.GET)
	protected ModelAndView addProducts(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return new ModelAndView("user-login");
		}
		List<Product> checkedList = new ArrayList();
		ModelAndView mv = new ModelAndView();
		checkedList = productDao.findProductList();
		mv.addObject("checkedList", checkedList);
		mv.setViewName("viewshopproduct");
		return mv;
	}

	@RequestMapping(value = "/sproducts.htm", method = RequestMethod.POST)
	protected ModelAndView numberOfProducts(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return new ModelAndView("user-login");
		}
		return new ModelAndView("sproducts", "noOfProducts", request.getParameter("noOfProducts"));
	}

	@RequestMapping(value = "/afteraddproducts", method = RequestMethod.POST)
	protected ModelAndView afterAddProducts(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return new ModelAndView("user-login");
		}
		String noOfProd = request.getParameter("noOfProducts");
		// HttpSession session = request.getSession();
		// User loggedIn = (User) session.getAttribute("loggedInUser");
		int noOfProducts = Integer.parseInt(noOfProd);
		for (int i = 0; i < noOfProducts; i++) {
			Product products = new Product();
			products.setProductName(request.getParameter("productName" + (i + 1)));
			products.setProdLocation(request.getParameter("prodLocation" + (i + 1)));
			products.setPrice(Float.parseFloat(request.getParameter("price" + (i + 1))));
			products.setProdStatus("created");
			productDao.register(products);
		}
		return new ModelAndView("shop-owner-init", "", "");
	}

	@RequestMapping(value = "/viewConfirmedProd", method = RequestMethod.POST)
	protected ModelAndView toConfirmProd(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return new ModelAndView("user-login");
		}
		String email = request.getParameter("productList");
		List<Product> prodList = productDao.getProducts(email);
		session.setAttribute("prodList", prodList);
		ModelAndView mv = new ModelAndView();
		mv.addObject("prodList", prodList);
		mv.setViewName("viewConfirmedProd");
		return mv;
	}

	@RequestMapping(value = "/shop-owner-init", method = RequestMethod.POST)
	protected String afterConfirm(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return "user-login";
		}
		List<Product> prodList = (List<Product>) session.getAttribute("prodList");
		for (Product p : prodList) {
			productDao.updateStatus(p);
		}
		return "shop-owner-init";
	}
}
