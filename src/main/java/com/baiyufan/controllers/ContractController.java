package com.baiyufan.controllers;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
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
	
	@Autowired
	private MongoTemplate mongoTemplate;

	// 合同校验
	@RequestMapping(Constants.CONTRACT_VALID_REST_WEBSERVICE_PATH)
	public String valid(HttpServletRequest request) throws JSONException  {
		boolean valid = true;
		//判断是新增还是修改
		JSONUtils json = RequestUtils.getJSONObjectFromRequest(request);
		JSONObject personInfo = (JSONObject) json.get("personInfo"); // 拿到其中的person的json字符串
		String personId = personInfo.getString("pk");
		String id=null;
		try {
			id = (String)json.get("id");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//根据personId去查找此人所有的合同
		List<Contract> contractList;
		Query query;
		if(id!=null&&id.length()>0){
			//修改,需要排除已有的合同
			query = new Query(where("person.id").is(personId).and("id").ne(id));
		}else{
			//新增
			query = new Query(where("person.id").is(personId));
		}
		contractList =mongoTemplate.find(query, Contract.class);
		if(contractList!=null&&contractList.size()>0){
			//判断两个日期是否在范围之内
			for(Contract contract : contractList){
				String startDate = (String)json.get("startDate");
				String endDate = (String)json.get("endDate");
				String otherStartDate = contract.getStartDate();
				String otherEndDate = contract.getEndDate();
				//System.err.println("2015-01".compareTo("2015-02"));
				if(startDate.compareTo(otherEndDate)>=0||endDate.compareTo(otherStartDate)<=0){
				}else{
					//落入范围之内
					valid= false;
					break;
				}
			}
		}
		if(valid){
			return Constants.JSON_RESULT_SUCESS;
		}else{
			return Constants.JSON_RESULT_FAILED;
		}
	}

	// 合同新增和修改入口
	@RequestMapping(Constants.CONTRACT_SAVE_REST_WEBSERVICE_PATH)
	public String save(HttpServletRequest request) throws JSONException {
		JSONUtils json = RequestUtils.getJSONObjectFromRequest(request);// 提交参数的json对象获取

		if (json != null) {
			// 拿到@dbref的主键
			JSONObject personInfo = (JSONObject) json.get("personInfo"); // 拿到其中的person的json字符串
			JSONObject checkerInfo = (JSONObject) json.get("checkerInfo");

			Person person = personRepository
					.findOne(personInfo.getString("pk")); // 根据主键拿到对象
			User user = userRepository.findOne(checkerInfo.getString("pk"));

			// String checkerId = (String) json.getValue("checkerId");// 同上
			// User checker = userRepository.findOne(checkerId);

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

				if (Constants.INVALID_FLAG.equals(contract.getAliveFlag())) {
					// 删除---只要设置标记位，其他都不用修改
					Contract toBeDeleted = contractRepository.findOne(contract
							.getId());
					toBeDeleted.setAliveFlag(Constants.INVALID_FLAG);
					contractRepository.save(toBeDeleted);
				} else {
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
