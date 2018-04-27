package com.me.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.captcha.botdetect.web.servlet.Captcha;
import com.me.dao.ProductDAO;
import com.me.dao.UserDAO;
import com.me.pojo.User;
import com.me.pojo.Product;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("productDao")
	ProductDAO productDao;

	@PostConstruct
	public void init() {
		User existUser = userDao.checkInitialUser("admin@admin.com");
		if (existUser == null) {
			User u = new User();
			u.setUserEmail("admin@admin.com");
			u.setPassword("1");
			u.setStatus(1);
			u.setRoleName("admin");
			try {
				u = userDao.register(u);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView userLoginForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return new ModelAndView("user-login");
		} else {
			if (u.getRoleName().equals("admin")) {
				return new ModelAndView("admin");
			} else if (u.getRoleName().equals("shopowner")) {
				return new ModelAndView("shop-owner-init");
			} else {
				ModelAndView mv = new ModelAndView();
				List<Product> prodList = new ArrayList();
				prodList = productDao.getAllProducts();
				int checkedProd = productDao.getCheckedProducts();
				mv.addObject("prodList", prodList);
				mv.addObject("checkedProd", checkedProd);
				List<Product> cartProdList = new ArrayList();
				cartProdList = productDao.getUserProducts(u.getId());
				mv.addObject("cartProdList", cartProdList);
				mv.setViewName("customer");
				return mv;
			}
		}
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String showLoginForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return "user-login";
		}
		return "user-login";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
	public ModelAndView handleLoginForm(HttpServletRequest request, UserDAO userDao, ModelMap map) {

		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ModelAndView mv = new ModelAndView();
		try {
			User u = userDao.get(username, password);
			if (u != null && u.getStatus() == 1) {
				session.setAttribute("loggedInUser", u);
				if (u.getRoleName().equals("admin")) {
					return new ModelAndView("admin");
				} else if (u.getRoleName().equals("shopowner")) {
					return new ModelAndView("shop-owner-init");
				} else {
					List<Product> prodList = new ArrayList();
					prodList = productDao.getAllProducts();
					int checkedProd = productDao.getCheckedProducts();
					mv.addObject("prodList", prodList);
					mv.addObject("checkedProd", checkedProd);
					List<Product> cartProdList = new ArrayList();
					cartProdList = productDao.getUserProducts(u.getId());
					mv.addObject("cartProdList", cartProdList);
					mv.setViewName("customer");
					return mv;
				}
			} else if (u != null && u.getStatus() == 0) {
				map.addAttribute("errorMessage", "Please activate your account to login!");
				return new ModelAndView("error");
			} else {
				map.addAttribute("errorMessage", "Invalid username/password!");
				return new ModelAndView("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@RequestMapping(value = "/create.htm", method = RequestMethod.GET)
	public ModelAndView showCreateForm(HttpServletRequest request) {

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedInUser");
		if (u == null) {
			return new ModelAndView("user-create-form");
		} else {
			if (u.getRoleName().equals("admin")) {
				return new ModelAndView("admin");
			} else if (u.getRoleName().equals("shopowner")) {
				return new ModelAndView("shop-owner-init");
			} else {
				ModelAndView mv = new ModelAndView();
				List<Product> prodList = new ArrayList();
				prodList = productDao.getAllProducts();
				int checkedProd = productDao.getCheckedProducts();
				mv.addObject("prodList", prodList);
				mv.addObject("checkedProd", checkedProd);
				List<Product> cartProdList = new ArrayList();
				cartProdList = productDao.getUserProducts(u.getId());
				mv.addObject("cartProdList", cartProdList);
				mv.setViewName("customer");
				return mv;
			}
		}
	}

	@RequestMapping(value = "/create.htm", method = RequestMethod.POST)
	public String handleCreateForm(HttpServletRequest request, UserDAO userDao, ModelMap map) {
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		HttpSession session = request.getSession();
		User ul = (User) session.getAttribute("loggedInUser");
		if (captcha.validate(captchaCode)) {
			String useremail = request.getParameter("username");
			String password = request.getParameter("password");
			User user = new User();
			user.setUserEmail(useremail);
			user.setPassword(password);
			user.setStatus(0);
			String role = request.getParameter("role");
			if (role == null) {
				user.setRoleName("customer");
			} else {
				user.setRoleName(role);
			}
			try {
				URL url;
				url = new URL(request.getRequestURL().toString());
				String scheme = url.getProtocol();
				String host = url.getHost();
				int port = url.getPort();
				String contextPath = request.getContextPath();
				User u = userDao.register(user);
				Random rand = new Random();
				int randomNum1 = rand.nextInt(5000000);
				int randomNum2 = rand.nextInt(5000000);
				try {
					String str = scheme + "://" + host + ":" + port + contextPath + "/validateemail.htm?email="
							+ useremail + "&key1=" + randomNum1 + "&key2=" + randomNum2;
					session.setAttribute("key1", randomNum1);
					session.setAttribute("key2", randomNum2);
					sendEmail(useremail, "Click on this link to activate your account : " + str);
				} catch (Exception e) {
					System.out.println("Email cannot be sent");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			map.addAttribute("errorMessage", "Invalid Captcha!");
			return "user-create-form";
		}

		return "user-created";
	}

	@RequestMapping(value = "/forgotpassword.htm", method = RequestMethod.GET)
	public String getForgotPasswordForm(HttpServletRequest request) {

		return "forgot-password";
	}

	@RequestMapping(value = "/forgotpassword.htm", method = RequestMethod.POST)
	public String handleForgotPasswordForm(HttpServletRequest request, UserDAO userDao) {

		String useremail = request.getParameter("useremail");
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");

		if (captcha.validate(captchaCode)) {
			User user = userDao.get(useremail);
			sendEmail(useremail, "Your password is : " + user.getPassword());
			return "forgot-password-success";
		} else {
			request.setAttribute("captchamsg", "Captcha not valid");
			return "forgot-password";
		}
	}

	@RequestMapping(value = "user/resendemail.htm", method = RequestMethod.POST)
	public String resendEmail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String useremail = request.getParameter("username");
		Random rand = new Random();
		int randomNum1 = rand.nextInt(5000000);
		int randomNum2 = rand.nextInt(5000000);
		try {
			URL url;
			url = new URL(request.getRequestURL().toString());
			String scheme = url.getProtocol();
			String host = url.getHost();
			int port = url.getPort();
			String contextPath = request.getContextPath();
			String str = scheme + "://" + host + ":" + port + contextPath + "/validateemail.htm?email=" + useremail
					+ "&key1=" + randomNum1 + "&key2=" + randomNum2;
			session.setAttribute("key1", randomNum1);
			session.setAttribute("key2", randomNum2);
			sendEmail(useremail, "Click on this link to activate your account : " + str);
		} catch (Exception e) {
			System.out.println("Email cannot be sent");
		}

		return "user-created";
	}

	public void sendEmail(String useremail, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu"); // This user email does not
													// exist
			email.setSubject("Password Reminder");
			email.setMsg(message); // Retrieve email from the DAO and send this
			email.addTo(useremail);
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
	}

	@RequestMapping(value = "validateemail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request, UserDAO userDao, ModelMap map) {

		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		int key1 = Integer.parseInt(request.getParameter("key1"));
		int key2 = Integer.parseInt(request.getParameter("key2"));
		System.out.println(session.getAttribute("key1"));
		System.out.println(session.getAttribute("key2"));

		if ((Integer) (session.getAttribute("key1")) == key1 && ((Integer) session.getAttribute("key2")) == key2) {
			try {
				System.out.println("HI________");
				boolean updateStatus = userDao.updateUser(email);
				if (updateStatus) {
					return "user-login";
				} else {

					return "error";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			map.addAttribute("errorMessage", "Link expired , generate new link");
			map.addAttribute("resendLink", true);
			return "error";
		}

		return "user-login";

	}

}
