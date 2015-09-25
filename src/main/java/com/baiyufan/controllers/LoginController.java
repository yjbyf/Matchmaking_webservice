package com.baiyufan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.db.model.TUser;
import com.baiyufan.db.persistence.TUserMapper;
import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;

@RestController
public class LoginController {

	@Autowired
	private TUserMapper userMapper;

	@RequestMapping(Constants.LOGIN_VALID_PRE_WITH_SLASH)
	public String validLogin(
			@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "password", defaultValue = "") String password) {

		int count = 0;
		TUser param = new TUser();
		param.setUserName(userName);
		param.setAliveFlag(Constants.VALID_FLAG);
		for (TUser user : userMapper.selectClause(param)) {
			count++;
			if (password.equals(user.getPassword())
					&& Constants.VALID_FLAG.equals(user.getAliveFlag())) {
				return "{\"result\":\"" + user.getId() + "\"}";
			}
			// System.out.println(customer);
		}

		// 若用户表中无超级用户，则初始化一个
		if (count == 0 && Constants.ADMIN.equals(userName)
				&& Constants.ADMIN_INIT_PASSWORD.equals(password)) {
			TUser user = new TUser();
			user.setUserName(Constants.ADMIN);
			user.setStaff(Constants.ADMIN);
			user.setPassword(Constants.ADMIN_INIT_PASSWORD);
			user.setAliveFlag(Constants.VALID_FLAG);
			userMapper.insert(user);
		}
		return Constants.JSON_RESULT_FAILED;
	}
}
