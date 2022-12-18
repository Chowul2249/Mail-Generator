package com.home.controller;

import java.io.IOException;
import java.text.DateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	String otp=null;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	/*@RequestMapping(value = "/")
	public ModelAndView getData() {
		ModelAndView model = new ModelAndView("index");
		return model;
	}*/

	// userregister
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView userregister(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("i am in userregister mehods");
		
		String email = request.getParameter("email");
		
                
			String value = "insert";
			System.out.println("my email address " + email);
			otp=MailGenerator.generateMail(email);
			
			System.out.println(otp);
			
		ModelAndView model = new ModelAndView("otp");
		model.addObject("reg", value);
		return model;
	}
	@RequestMapping(value = "/otp", method = RequestMethod.POST)
	public String otp(Locale locale, Model model,HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		String otp1=req.getParameter("otp");
		String send=null;
		if(otp1.equals(otp))
		{
			model.addAttribute("message", "successfully registered");
			send="home";
		}
		else
		{
			model.addAttribute("message", "incorrect otp");
			send="otp";
		}
		return send;
		

		
			
		
	}

}
