package com.baiyufan.controllers;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.Contract;
import com.baiyufan.respository.ContractRepository;
import com.baiyufan.respository.Person;
import com.baiyufan.respository.PersonRepository;
import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.JSONUtils;
import com.baiyufan.utils.RequestUtils;
import com.google.gson.Gson;

@RestController
public class ContractController {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private UserRepository userRepository;// 用于开票老师

	@Autowired
	private PersonRepository personRepository;// 用于合同挂靠人员

	// 合同新增和修改入口
	@RequestMapping(Constants.CONTRACT_SAVE_REST_WEBSERVICE_PATH)
	public String save(HttpServletRequest request) throws JSONException {
		JSONUtils json = RequestUtils.getJSONObjectFromRequest(request);//提交参数的json对象获取

		if (json != null) {
			//拿到@dbref的主键
			JSONObject personInfo = (JSONObject) json.get("personInfo"); //拿到其中的person的json字符串
			JSONObject checkerInfo = (JSONObject) json.get("checkerInfo");
			
			Person person = personRepository.findOne(personInfo.getString("pk")); // 根据主键拿到对象
			User user = userRepository.findOne(checkerInfo.getString("pk"));
			
			//String checkerId = (String) json.getValue("checkerId");// 同上
			//User checker = userRepository.findOne(checkerId);

			json.remove("person"); // json中拿掉@dbref字段对应的值，防止json转换bean出错
			json.remove("checker");
			json.remove("personId");
			json.remove("name");
			json.remove("gender");
			json.remove("pk");			
			json.remove("personInfo");
			json.remove("checkerInfo");
			json.remove("checkerName");
			// System.err.println(json.toString());
			Contract contract = new Gson().fromJson(json.toString(),
					Contract.class);
			contract.setPerson(person);// 设置@dbref字段内容
			contract.setChecker(user);			
			if (contract.getId() != null && contract.getId().length() > 0) {
				
				if(Constants.INVALID_FLAG.equals(contract.getAliveFlag())){
					//删除---只要设置标记位，其他都不用修改
					Contract toBeDeleted = contractRepository.findOne(contract.getId());
					toBeDeleted.setAliveFlag(Constants.INVALID_FLAG);
					contractRepository.save(toBeDeleted);
				}else{
					contractRepository.save(contract);// 修改
				}
			} else {
				contract.setAliveFlag(Constants.VALID_FLAG);
				contractRepository.insert(contract);// 新增
			}

		}

		/*
		 * if (users != null && users.size() > 0) { User user = users.get(0);
		 * 
		 * Contact contact = new Contact(); contact.setContacter(user);
		 * 
		 * List<User> contactee = new ArrayList<User>(); contactee.add(user);
		 * contactee.add(user); contactee.add(user);
		 * 
		 * contact.setContactee(contactee); //mongoOperations.save(contact);
		 * //成功
		 * 
		 * contactRepository.insert(contact);//成功 }
		 */

		return "";
	}

}
