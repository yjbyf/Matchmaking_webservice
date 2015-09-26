package com.baiyufan.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.db.model.TPerson;
import com.baiyufan.db.persistence.TPersonMapper;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.RequestUtils;
import com.google.gson.Gson;
@RestController
public class PersonController {

	@Autowired
	private TPersonMapper personMapper;

	// 查询
	@RequestMapping(value = Constants.PERSON_REST_WEBSERVICE_PATH_PRE_WTIH_SLASH, produces = Constants.JSON_UTF8)
	public @ResponseBody
	List<TPerson> getPersonList(HttpServletRequest request) {
		String currentUserId = RequestUtils
				.getUserIdFromRequestAuthorization(request);
		TPerson person = new TPerson();
		person.setCreateBy(new Integer(currentUserId));
		person.setAliveFlag(Constants.VALID_FLAG);
		return personMapper.selectClause(person);
	}

	// 新增
	@RequestMapping(Constants.PERSON_REST_WEBSERVICE_INSERT)
	public String insert(HttpServletRequest request) {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		TPerson person = new Gson().fromJson(json.toString(), TPerson.class);
		person.setAliveFlag(Constants.VALID_FLAG);
		person.setCreateBy(RequestUtils.getCreatedBy(request));
		return "{\"result\":" + personMapper.insert(person) + "}";
	}

	// 修改和删除
	@RequestMapping(Constants.PERSON_REST_WEBSERVICE_MOD)
	public String mod(HttpServletRequest request) {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		TPerson person = new Gson().fromJson(json.toString(), TPerson.class);
		return "{\"result\":"
				+ personMapper.updateByPrimaryKeySelective(person) + "}";
	}
}
