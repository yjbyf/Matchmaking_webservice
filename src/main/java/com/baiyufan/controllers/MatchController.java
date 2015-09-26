package com.baiyufan.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.db.model.TMatch;
import com.baiyufan.db.persistence.TMatchMapper;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.JSONUtils;
import com.baiyufan.utils.RequestUtils;
import com.google.gson.Gson;

@RestController
public class MatchController {

	@Autowired
	private TMatchMapper matchMapper;

	// 配对查询
	@RequestMapping(value = Constants.MATCH_QUERY_REST_WEBSERVICE_PATH, produces = Constants.JSON_UTF8)
	public @ResponseBody
	List<TMatch> getContractList(HttpServletRequest request) {
		String currentUserId = RequestUtils
				.getUserIdFromRequestAuthorization(request);
		TMatch match = new TMatch();
		match.setCreateBy(new Integer(currentUserId));
		match.setAliveFlag(Constants.VALID_FLAG);
		return matchMapper.selectClause(match);
	}

	private TMatch prepareSave(HttpServletRequest request) throws JSONException {
		JSONUtils json = RequestUtils.getJSONObjectFromRequest(request);// 提交参数的json对象获取
		if (json != null) {
			if (json.has("nameId") && json.has("serviceEmployeeId")
					&& json.has("matchPersonId")) {
				Integer nameId = (Integer) json.get("nameId"); // 拿到其中的person的json字符串
				Integer serviceEmployeeId = (Integer) json
						.get("serviceEmployeeId");
				Integer matchPersonId = (Integer) json.get("matchPersonId");
				Integer nameContractId = (Integer) json.get("nameContractId");
				Integer matchPersonContractId = (Integer) json
						.get("matchPersonContractId");
				;
				

				// 拿到@dbref的主键
				TMatch match = new Gson().fromJson(json.toString(), TMatch.class);
				match.setName(nameId);
				match.setMatchPerson(matchPersonId);
				match.setServiceEmployee(serviceEmployeeId);
				match.setNameContract(nameContractId);
				match.setMatchPersonContract(matchPersonContractId);
				match.setAliveFlag(Constants.VALID_FLAG);
				match.setCreateBy(RequestUtils.getCreatedBy(request));
				return match;
			}
		}
		return null;
	}
	
	// 配对新增入口
	@RequestMapping(Constants.MATCH_NEW_REST_WEBSERVICE_PATH)
	public String newMatch(HttpServletRequest request) throws JSONException {
		TMatch match = prepareSave(request);
		if(match!=null){
			return "{\"result\":" + matchMapper.insert(match) + "}";
		}
		return Constants.NULL_STRING;
	}

	// 配对修改入口
	@RequestMapping(Constants.MATCH_MOD_REST_WEBSERVICE_PATH)
	public String modMatch(HttpServletRequest request) throws JSONException {
		TMatch match = prepareSave(request);
		if(match!=null){
			return "{\"result\":" + matchMapper.updateByPrimaryKey(match) + "}";
		}
		return Constants.NULL_STRING;
	}

	// 配对删除入口
	@RequestMapping(Constants.MATCH_DEL_REST_WEBSERVICE_PATH)
	public String delMatch(HttpServletRequest request) throws JSONException {
		JSONUtils json = RequestUtils.getJSONObjectFromRequest(request);// 提交参数的json对象获取
		if (json != null) {
			TMatch match = new Gson().fromJson(json.toString(), TMatch.class);
			TMatch toBeDelete = matchMapper.selectByPrimaryKey(match.getId());
			toBeDelete.setAliveFlag(Constants.INVALID_FLAG);
			return "{\"result\":" + matchMapper.updateByPrimaryKey(match) + "}";
		}
		return Constants.NULL_STRING;
	}
}
