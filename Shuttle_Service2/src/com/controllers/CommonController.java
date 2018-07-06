//@Author : Sourav Bhattacharya
//@Date : 21 May 2018

package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beans.LoginCredentials;
import com.beans.User;
import com.services.composite.contract.CommonService;

@Controller
public class CommonController {

	@Autowired
	CommonService commonService;
	
	@RequestMapping(value = "register.sb", method = RequestMethod.POST)
	public ModelAndView registerUser(HttpServletRequest request, HttpServletResponse response) {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		User user = new User(firstName, lastName, email, password, question, answer);
		
		boolean result = commonService.register(user);
		
		ModelAndView mad = new ModelAndView();
		
		if(result) {
			mad.addObject("message", "Registration Successful!!!!");
			mad.setViewName("success");
		} else {
			mad.addObject("message", "Registration failed. Please try again!!!!");
			mad.setViewName("failure");
		}
		
		return mad;
	}
	
	@RequestMapping(value = "login.sb", method = RequestMethod.POST)
	public ModelAndView loginProcessor(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		LoginCredentials credentials = new LoginCredentials(userName, password);
		
		String result = commonService.login(credentials);
		
		ModelAndView mad = new ModelAndView();
		
		if(null != result) {
			mad.addObject("message", result);
			mad.setViewName("success");
		} else {
			mad.addObject("message", "Login failed.");
			mad.setViewName("failure");
		}
		
		return mad;
	}
}
