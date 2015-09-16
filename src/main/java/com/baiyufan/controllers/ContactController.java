package com.baiyufan.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.Contact;
import com.baiyufan.respository.ContactRepository;
import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;
import com.baiyufan.utils.RequestUtils;

@RestController
public class ContactController {
	
	private static final String USER_ID = "55f00be87639b6b061dd8cc2";
	
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private UserRepository userRepository;

	@Value("${spring.data.mongodb.database}")
	private String mongodbDatabse;

	
	@RequestMapping("contact/testAdd")
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
			//mongoOperations.save(contact); //成功
			
			contactRepository.insert(contact);//成功
		}

		return mongodbDatabse;
	}

	@RequestMapping("contact/add")
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

}
