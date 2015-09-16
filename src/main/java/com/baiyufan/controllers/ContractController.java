package com.baiyufan.controllers;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.Contract;
import com.baiyufan.respository.ContractRepository;
import com.baiyufan.respository.Employee;
import com.baiyufan.respository.EmployeeRepository;
import com.baiyufan.respository.Person;
import com.baiyufan.respository.PersonRepository;
import com.baiyufan.respository.User;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.RequestUtils;
import com.google.gson.Gson;

@RestController
public class ContractController {

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private EmployeeRepository employeeRepository;// 用于开票老师

	@Autowired
	private PersonRepository personRepository;// 用于合同挂靠人员

	//合同新增和修改入口
	@RequestMapping(Constants.CONTRACT_SAVE_REST_WEBSERVICE_PATH)
	public String save(HttpServletRequest request) {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		try {
			if (json != null) {				
				
				String personId = (String) json.get("person"); //拿到@dbref的主键
				String checkerId = (String) json.get("checker");//同上
								
				Person person = personRepository.findOne(personId); //根据主键拿到对象
				Employee checker = employeeRepository.findOne(checkerId); 
				
				json.remove("person"); //json中拿掉@dbref字段对应的值，防止json转换bean出错
				json.remove("checker");
				//System.err.println(json.toString());
				Contract contract = new Gson().fromJson(json.toString(), Contract.class);
				contract.setPerson(person);//设置@dbref字段内容
				contract.setChecker(checker);
				if(contract.getId()!=null&&contract.getId().length()>0){
					contractRepository.save(contract);//修改
				}else{
					contractRepository.insert(contract);//新增
				}
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
