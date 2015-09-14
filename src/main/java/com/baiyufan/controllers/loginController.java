package com.baiyufan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;

@RestController
public class loginController {

	@Autowired
	private UserRepository repository;
	
	@RequestMapping(Constants.LOGIN_VALID_PRE_WITH_SLASH)
	public String validLogin(
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "password", defaultValue = "") String password) {
		
		int count =0;
		for (User user : repository.findByUserName(userName)) {
			count ++;
			if(password.equals(user.getPassword())){
				return "{\"result\":\""+user.getId()+"\"}";
			}
			//System.out.println(customer);
		}
		
		//若用户表中无超级用户，则初始化一个
		if(count==0&&Constants.ADMIN.equals(userName)&&Constants.ADMIN_INIT_PASSWORD.equals(password)){
			User user = new User();
			user.setUserName(Constants.ADMIN);
			user.setPassword(Constants.ADMIN_INIT_PASSWORD);
			repository.insert(user);
		}
		return "{\"result\":\"failed\"}";
	}
}
