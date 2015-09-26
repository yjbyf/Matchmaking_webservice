package com.baiyufan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.db.model.TUser;
import com.baiyufan.db.persistence.TUserMapper;

@RestController
public class TestController {

	private static final String USER_ID = "55f00be87639b6b061dd8cc2";


	@Value("${spring.data.mongodb.database}")
	private String mongodbDatabse;

	@Autowired
	private MongoTemplate mongoTemplate;


	@RequestMapping(value = "/test/restFilter", produces = "application/json; charset=utf-8")
	public @ResponseBody String test() {
		return "{\"result\":\"测试中文\"}";
	}


	
	@Autowired
	private TUserMapper userMapper;
	
	@RequestMapping(value = "/mybatis/user", produces = "application/json; charset=utf-8")
	public @ResponseBody List<TUser> userList() {
		TUser user = new TUser();
		user.setUserName("aa");
		return userMapper.selectClause(user);
	}
	

}
