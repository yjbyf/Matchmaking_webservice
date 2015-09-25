package com.baiyufan.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.db.model.TUser;
import com.baiyufan.db.persistence.TUserMapper;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.RequestUtils;
import com.google.gson.Gson;

@RestController
public class UserController {

	@Autowired
	private TUserMapper userMapper;

	//查询
	@RequestMapping(value = Constants.USER_REST_WEBSERVICE_PATH_PRE_WTIH_SLASH, produces = "application/json; charset=utf-8")
	public @ResponseBody
	List<TUser> getUserList() {
		return userMapper.selectAll(null);
	}

	// 按用户名计数
	@RequestMapping(Constants.USER_REST_WEBSERVICE_COUNT)
	public String countByName(
			@RequestParam(value = "userName", defaultValue = "") String userName,
			HttpServletRequest request) {
		TUser param = new TUser();
		param.setUserName(userName);
		param.setAliveFlag(Constants.VALID_FLAG);
		int count = userMapper.countClause(param);
		return "{\"result\":" + count + "}";
	}

	//新增
	@RequestMapping(Constants.USER_REST_WEBSERVICE_INSERT)
	public String insert(HttpServletRequest request) {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		TUser user = new Gson().fromJson(json.toString(), TUser.class);
		user.setAliveFlag(Constants.VALID_FLAG);
		return "{\"result\":" + userMapper.insert(user) + "}";
	}
	
	//修改和删除
	@RequestMapping(Constants.USER_REST_WEBSERVICE_MOD)
	public String mod(HttpServletRequest request) {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		TUser user = new Gson().fromJson(json.toString(), TUser.class);
		return "{\"result\":" + userMapper.updateByPrimaryKeySelective(user) + "}";
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

		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		try {
			if (json != null) {
				userNameFromInput = json.getString("userName");
				passwordFromInput = json.getString("password");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String usernameFromAuth = RequestUtils
				.getUserNameFromRequestAuthorization(request);
		// http头中的用户名和传递过来的用户名比较，防止利用漏铜，手动的修改其他人的密码
		if (usernameFromAuth.equals(userNameFromInput)) {
			TUser param = new TUser();
			param.setUserName(usernameFromAuth);
			param.setAliveFlag(Constants.VALID_FLAG);
			for (TUser user : userMapper.selectClause(param)) {
				if (passwordFromInput != null) {
					user.setPassword(passwordFromInput);
					userMapper.updateByPrimaryKeySelective(user);
					return Constants.JSON_RESULT_SUCESS;
				}
			}
		}
		return Constants.JSON_RESULT_FAILED;
	}

	@RequestMapping(Constants.USER_REST_WEBSERVICE_NO_PRIV)
	public List<TUser> getUserListWithoutPriv() {
		TUser param = new TUser();
		param.setUserName(Constants.ADMIN);
		param.setAliveFlag(Constants.VALID_FLAG);
		List<TUser> userList = userMapper.selectExceptName(param);
		for (TUser user : userList) {
			user.setPassword(null);// 屏蔽密码
		}
		// 清除密码
		return userList;
	}
}
