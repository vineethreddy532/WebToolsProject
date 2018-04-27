package com.me.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.ProductDAO;
import com.me.pojo.Product;
import com.me.pojo.User;

@Controller
public class ProductController {
	
	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;
	
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
	protected ModelAndView afterAddProducts(HttpServletRequest request) throws Exception {
		String noOfProd = request.getParameter("noOfProducts");
		//HttpSession session = request.getSession();
		//User loggedIn = (User) session.getAttribute("loggedInUser");
        int noOfProducts = Integer.parseInt(noOfProd);
		for(int i=0;i<noOfProducts;i++){
        Product products = new Product();
        products.setProductName(request.getParameter("productName"+(i+1)));
        products.setProdLocation(request.getParameter("price"+(i+1)));
        products.setPrice(Float.parseFloat(request.getParameter("price"+(i+1))));
        productDao.register(products);
    }
		return new ModelAndView("shop-owner-init","","");
	}

}
