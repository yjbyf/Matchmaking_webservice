package com.baiyufan.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.db.model.TContract;
import com.baiyufan.db.persistence.TContractMapper;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.JSONUtils;
import com.baiyufan.utils.RequestUtils;
import com.google.gson.Gson;

@RestController
public class ContractController {

	@Autowired
	private TContractMapper contractMapper;

	// 合同查询
	@RequestMapping(value = Constants.CONTRACT_QUERY_REST_WEBSERVICE_PATH, produces = Constants.JSON_UTF8)
	public @ResponseBody
	List<TContract> getContractList(HttpServletRequest request) {
		String currentUserId = RequestUtils
				.getUserIdFromRequestAuthorization(request);
		TContract contract = new TContract();
		contract.setCreateBy(new Integer(currentUserId));
		contract.setAliveFlag(Constants.VALID_FLAG);
		return contractMapper.selectClause(contract);
	}

	// 合同校验
	@RequestMapping(Constants.CONTRACT_VALID_REST_WEBSERVICE_PATH)
	public String valid(HttpServletRequest request) throws JSONException {
		// boolean valid = true;
		JSONUtils json = RequestUtils.getJSONObjectFromRequest(request);
		TContract contract = new Gson().fromJson(json.toString(),
				TContract.class);
		JSONObject personInfo = (JSONObject) json.get("personInfo"); // 拿到其中的person的json字符串
		String personId = personInfo.getString(Constants.ID);
		Integer id = null;
		try {
			id = (Integer) json.get(Constants.ID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		// TContract contract = new TContract();
		contract.setId(id);
		contract.setAliveFlag(Constants.VALID_FLAG);
		contract.setPerson(new Integer(personId));

		// 根据personId去查找此人所有的合同
		int count = contractMapper.checkContractDate(contract);
		if (count == 0) {
			return Constants.JSON_RESULT_SUCESS;
		} else {
			return Constants.JSON_RESULT_FAILED;
		}
		/*
		 * List<Contract> contractList; Query query; if (id != null &&
		 * id.length() > 0) { // 修改,需要排除已有的合同 query = new
		 * Query(where(PERSON_ID).is(personId)
		 * .and(Constants.ALIVE_FLAG).is(Constants.VALID_FLAG)
		 * .and(Constants.ID).ne(id)); } else { // 新增 query = new
		 * Query(where(PERSON_ID).is(personId)
		 * .and(Constants.ALIVE_FLAG).is(Constants.VALID_FLAG)); } contractList
		 * = mongoTemplate.find(query, Contract.class); if (contractList != null
		 * && contractList.size() > 0) { // 判断两个日期是否在范围之内 for (Contract contract
		 * : contractList) { String startDate = (String) json.get("startDate");
		 * String endDate = (String) json.get("endDate"); String otherStartDate
		 * = contract.getStartDate(); String otherEndDate =
		 * contract.getEndDate(); //
		 * System.err.println("2015-01".compareTo("2015-02")); if
		 * (startDate.compareTo(otherEndDate) >= 0 ||
		 * endDate.compareTo(otherStartDate) <= 0) { } else { // 落入范围之内 valid =
		 * false; break; } } } if (valid) { return Constants.JSON_RESULT_SUCESS;
		 * } else { return Constants.JSON_RESULT_FAILED; }
		 */
	}

	// 新增
	@RequestMapping(Constants.CONTRACT_NEW_REST_WEBSERVICE_PATH)
	public String insert(HttpServletRequest request) throws JSONException {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		TContract contract = new Gson().fromJson(json.toString(),
				TContract.class);
		JSONObject personInfo = (JSONObject) json.get("personInfo"); // 拿到其中的person的json字符串
		JSONObject checkerInfo = (JSONObject) json.get("checkerInfo");
		Integer personId = (Integer) personInfo.get(Constants.ID);
		Integer checkerId = (Integer) checkerInfo.get(Constants.ID);
		contract.setPerson(personId);
		contract.setChecker(checkerId);
		contract.setAliveFlag(Constants.VALID_FLAG);
		contract.setCreateBy(RequestUtils.getCreatedBy(request));
		return "{\"result\":" + contractMapper.insert(contract) + "}";
	}

	// 合同修改入口
	@RequestMapping(Constants.CONTRACT_SAVE_REST_WEBSERVICE_PATH)
	public String save(HttpServletRequest request) throws JSONException {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		TContract contract = new Gson().fromJson(json.toString(),
				TContract.class);
		JSONObject personInfo = (JSONObject) json.get("personInfo"); // 拿到其中的person的json字符串
		JSONObject checkerInfo = (JSONObject) json.get("checkerInfo");
		Integer personId = (Integer) personInfo.get(Constants.ID);
		Integer checkerId = (Integer) checkerInfo.get(Constants.ID);
		contract.setPerson(personId);
		contract.setChecker(checkerId);
		return "{\"result\":"
				+ contractMapper.updateByPrimaryKeySelective(contract) + "}";
		/*
		 * JSONUtils json = RequestUtils.getJSONObjectFromRequest(request);//
		 * 提交参数的json对象获取
		 * 
		 * if (json != null) { // 拿到@dbref的主键 if (json.has("personInfo") &&
		 * json.has("checkerInfo")) { JSONObject personInfo = (JSONObject)
		 * json.get("personInfo"); // 拿到其中的person的json字符串 JSONObject checkerInfo
		 * = (JSONObject) json.get("checkerInfo");
		 * 
		 * Person person = personRepository.findOne(personInfo
		 * .getString(Constants.PK)); // 根据主键拿到对象 User user =
		 * userRepository.findOne(checkerInfo .getString(Constants.PK));
		 * 
		 * // String checkerId = (String) json.getValue("checkerId");// 同上 //
		 * User checker = userRepository.findOne(checkerId);
		 * 
		 * json.remove("person"); // json中拿掉@dbref字段对应的值，防止json转换bean出错
		 * json.remove("checker"); json.remove("checkerId");
		 * json.remove("personId"); json.remove("name"); json.remove("gender");
		 * json.remove(Constants.PK); json.remove("personInfo");
		 * json.remove("checkerInfo"); json.remove("checkerName"); //
		 * System.err.println(json.toString()); Contract contract = new
		 * Gson().fromJson(json.toString(), Contract.class);
		 * contract.setPerson(person);// 设置@dbref字段内容 contract.setChecker(user);
		 * if (contract.getId() != null && contract.getId().length() > 0) {
		 * contractRepository.save(contract);// 修改 } else {
		 * contract.setAliveFlag(Constants.VALID_FLAG);
		 * contractRepository.insert(contract);// 新增 } } else { Contract
		 * contract = new Gson().fromJson(json.toString(), Contract.class); if
		 * (Constants.INVALID_FLAG.equals(contract.getAliveFlag())) { //
		 * 删除---只要设置标记位，其他都不用修改 Contract toBeDeleted =
		 * contractRepository.findOne(contract .getId());
		 * toBeDeleted.setAliveFlag(Constants.INVALID_FLAG);
		 * contractRepository.save(toBeDeleted); } }
		 * 
		 * }
		 */

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

		// return Constants.NULL_STRING;
	}

	// 合同删除入口
	@RequestMapping(Constants.CONTRACT_DEL_REST_WEBSERVICE_PATH)
	public String delete(HttpServletRequest request) throws JSONException {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		TContract contract = new Gson().fromJson(json.toString(),
				TContract.class);
		TContract delete = contractMapper.selectByPrimaryKey(contract.getId());
		delete.setAliveFlag(Constants.INVALID_FLAG);
		return "{\"result\":"
				+ contractMapper.updateByPrimaryKeySelective(delete) + "}";
	}

}
