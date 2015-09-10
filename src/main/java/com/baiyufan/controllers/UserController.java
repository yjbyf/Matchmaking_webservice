package com.baiyufan.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;

@RestController
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@RequestMapping(Constants.GET_USER_LIST_WITH_PRIV)
	public String validLogin(
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "password", defaultValue = "") String password,
			HttpServletRequest request) {
		//System.err.println("userName:"+userName);
		//System.err.println("password:"+password);
		//String auth = request.getHeader("Authorization");
		
		for (User user : repository.findByUserName(userName)) {
			if(password.equals(user.getPassword())){
				return "{\"result\":\"success\"}";
			}
			//System.out.println(customer);
		}
		return "{\"result\":\"failed\"}";
	}
}
