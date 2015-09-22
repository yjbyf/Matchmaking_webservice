package com.baiyufan.controllers;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.Contract;
import com.baiyufan.respository.ContractRepository;
import com.baiyufan.respository.Match;
import com.baiyufan.respository.MatchRepository;
import com.baiyufan.respository.Person;
import com.baiyufan.respository.PersonRepository;
import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.JSONUtils;
import com.baiyufan.utils.RequestUtils;
import com.google.gson.Gson;

@RestController
public class MatchController {
	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private UserRepository userRepository;// 用于服务老师

	@Autowired
	private PersonRepository personRepository;// 用于配对双方
	
	@Autowired
	private ContractRepository contractRepository; //用于合同

	// 配对新增入口
	@RequestMapping(Constants.MATCH_NEW_REST_WEBSERVICE_PATH)
	public String newMatch(HttpServletRequest request) throws JSONException {
		JSONUtils json = RequestUtils.getJSONObjectFromRequest(request);// 提交参数的json对象获取
		if (json != null) {
			if (json.has("nameId") && json.has("serviceEmployeeId")&&json.has("matchPersonId")) {
				String nameId = (String) json.get("nameId"); // 拿到其中的person的json字符串
				String serviceEmployeeId = (String) json.get("serviceEmployeeId");
				String matchPersonId = (String) json.get("matchPersonId");
				String nameContractId = (String) json.get("nameContractId");
				String matchPersonContractId = (String) json.get("matchPersonContractId");;

				Person person = personRepository.findOne(nameId); // 根据主键拿到对象
				Person object = personRepository.findOne(matchPersonId); // 根据主键拿到对象
				User user = userRepository.findOne(serviceEmployeeId);
				Contract personContract = contractRepository.findOne(nameContractId);
				Contract objectContract = contractRepository.findOne(matchPersonContractId);

				// 拿到@dbref的主键
				Match match = new Gson().fromJson(json.toString(), Match.class);
				match.setName(person);
				match.setMatchPerson(object);
				match.setServiceEmployee(user);
				match.setNameContract(personContract);
				match.setMatchPersonContract(objectContract);
				match.setPk(null);

				matchRepository.save(match);
			}
		}
		return Constants.NULL_STRING;
	}
}
