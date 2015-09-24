package com.baiyufan.controllers;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.Contact;
import com.baiyufan.respository.ContactRepository;
import com.baiyufan.respository.PersonCount;
import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.RequestUtils;

@RestController
public class TestController {

	private static final String USER_ID = "55f00be87639b6b061dd8cc2";

	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private UserRepository userRepository;

	@Value("${spring.data.mongodb.database}")
	private String mongodbDatabse;

	@Autowired
	private MongoTemplate mongoTemplate;

	@RequestMapping("/test/contact/testAdd")
	public String testAdd() {
		List<User> users = userRepository.findById(USER_ID);
		if (users != null && users.size() > 0) {
			User user = users.get(0);

			Contact contact = new Contact();
			contact.setContacter(user);

			List<User> contactee = new ArrayList<User>();
			contactee.add(user);
			contactee.add(user);
			contactee.add(user);

			contact.setContactee(contactee);
			// mongoOperations.save(contact); //成功

			contactRepository.insert(contact);// 成功
		}

		return mongodbDatabse;
	}

	@RequestMapping("/test/contact/add")
	public String add(HttpServletRequest request) {
		JSONObject json = RequestUtils.getJSONObjectFromRequest(request);
		List<User> user = userRepository.findById("55eef301360fbff326ceef88");
		if (user != null && user.size() > 0) {
			System.err.println("userID:" + user.get(0).getId());
			// DBRef dbref = new DBRef(null,"user",user.getId());
			Contact contact = new Contact();
			contact.setContacter(user.get(0)); // 放入的是整个对象，不是refdb
			contactRepository.insert(contact);
		}
		return Constants.JSON_RESULT_SUCESS;
	}

	@RequestMapping(value = "/test/restFilter", produces = "application/json; charset=utf-8")
	public @ResponseBody String test() {
		return "{\"result\":\"测试中文\"}";
	}

	@RequestMapping(value = "/Aggregation/personCount", produces = "application/json; charset=utf-8")
	public @ResponseBody List<PersonCount> personCount() {
		/*
		 * http://stackoverflow.com/questions/15624473/spring-data-mongodb-aggregation-framework-integration
		 * 
		 */
//		AggregationOperation match = Aggregation.match(Criteria
//				.where("aliveFlag").is("1").and("source").is("MARKUP"));
		AggregationOperation match = Aggregation.match(Criteria
				.where("aliveFlag").is("1"));
		AggregationOperation group = Aggregation.group("gender").count().as("n");
		Aggregation aggregation = newAggregation(match, group,project("n").and("gender").previousOperation());
		
		AggregationResults<PersonCount> result = this.mongoTemplate.aggregate(
				aggregation, "person", PersonCount.class);

//		TypedAggregation<Person> aggregation = newAggregation(
//				Person.class,
//				match(Criteria.where("service").is("EFT").and("source")
//						.is("MARKUP")), group("gender").count().as("n"),
//				sort(Direction.ASC, "gender")
//
//		);
//		AggregationResults<PersonCount> result = mongoTemplate.aggregate(
//				aggregation, PersonCount.class);
		List<PersonCount> list = result.getMappedResults();
		return list;
	}

}
