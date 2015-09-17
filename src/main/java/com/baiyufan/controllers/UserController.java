package com.baiyufan.controllers;

import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.RequestUtils;
import com.mongodb.Mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Value("${spring.data.mongodb.database}")
	private String mongodbDatabse;

	// @RequestMapping(Constants.GET_USER_LIST_WITH_PRIV)
	// public String validLogin(
	// @RequestParam(value = "userName", defaultValue = "") String userName,
	// @RequestParam(value = "password", defaultValue = "") String password,
	// HttpServletRequest request) {
	//
	// for (User user : repository.findByUserName(userName)) {
	// if(password.equals(user.getPassword())){
	// return "{\"result\":\""+user.getId()+"\"}";
	// }
	// //System.out.println(customer);
	// }
	// return "{\"result\":\"failed\"}";
	// }

	@RequestMapping(Constants.USER_REST_WEBSERVICE_COUNT)
	public String countByName(
			@RequestParam(value = "userName", defaultValue = "") String userName,
			HttpServletRequest request) {

		List<User> list = repository.findByUserName(userName);
		int count = 0;
		if (list != null) {
			for (User user : list) {
				if (Constants.VALID_FLAG.equals(user.getAliveFlag())) {
					count++;
				}
			}
		}
		return "{\"result\":" + count + "}";
	}

	/******************************
	 * 
	 * There is much confusion among newcomers to AngularJS as to why the $http
	 * service shorthand functions ($http.post(), etc.) don’t appear to be
	 * swappable with the jQuery equivalents (jQuery.post(), etc.)
	 * 
	 * The difference is in how jQuery and AngularJS serialize and transmit the
	 * data. Fundamentally, the problem lies with your server language of choice
	 * being unable to understand AngularJS’s transmission natively ... By
	 * default, jQuery transmits data using
	 * 
	 * Content-Type: x-www-form-urlencoded and the familiar foo=bar&baz=moe
	 * serialization.
	 * 
	 * AngularJS, however, transmits data using
	 * 
	 * Content-Type: application/json and { "foo": "bar", "baz": "moe" }
	 * 
	 * JSON serialization, which unfortunately some Web server languages—notably
	 * PHP—do not unserialize natively.
	 */

	// 因为原有的修改密码功能在/user下，必须管理员权限才能操作；故此开放个针对个人的接口
	@RequestMapping(Constants.CHANGE_OWN_PASSWORD)
	public String changeOwnPassword(HttpServletRequest request) {
		// http头中的用户名和密码（是实际操作人的）
		String userNameFromInput = null;
		String passwordFromInput = null;

		// System.out.println(sb.toString());
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		try {
			if (json != null) {
				userNameFromInput = json.getString("userName");
				passwordFromInput = json.getString("password");
			}
			// System.err.println(userNameFromInput);
			// System.err.println(passwordFromInput);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String usernameFromAuth = RequestUtils
				.getUserNameFromRequestAuthorization(request);
		// http头中的用户名和传递过来的用户名比较，防止利用漏铜，手动的修改其他人的密码
		if (usernameFromAuth.equals(userNameFromInput)) {
			for (User user : repository.findByUserName(usernameFromAuth)) {
				if (passwordFromInput != null) {
					user.setPassword(passwordFromInput);
					repository.save(user);
					return Constants.JSON_RESULT_SUCESS;
				}
			}
		}
		return Constants.JSON_RESULT_FAILED;
	}
	
	@RequestMapping(Constants.USER_REST_WEBSERVICE_NO_PRIV)
	public List<User> getUserListWithoutPriv() {
		//System.err.println(mongoTemplate);		
		Query query = new Query(where("aliveFlag").is(Constants.VALID_FLAG).and("userName").ne(Constants.ADMIN));
		List<User> userList =mongoTemplate.find(query, User.class);
		for(User user:userList){
			user.setPassword(null);//屏蔽密码
		}
		//清除密码
		return userList;
		
		//return repository.findByAliveFlag(Constants.VALID_FLAG);
	}
}
