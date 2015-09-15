package com.baiyufan.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baiyufan.respository.Contact;
import com.baiyufan.respository.ContactRepository;
import com.baiyufan.respository.User;
import com.baiyufan.respository.UserRepository;
import com.baiyufan.utils.Constants;

@RestController
public class ContactController {
	@Autowired
	private ContactRepository contactRepository;
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("contact/add")
	public String validLogin(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		// 提交json拿到并打印
		BufferedReader reader = null;
		try {
			reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append('\n');
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.err.println(sb.toString());
		List<User> user = userRepository.findById("55eef301360fbff326ceef88");
		if (user != null&&user.size()>0) {
			System.err.println("userID:"+user.get(0).getId());
			//DBRef dbref = new DBRef(null,"user",user.getId());	
			Contact contact = new Contact();
			contact.setContacter(user.get(0)); //放入的是整个对象，不是refdb
			contactRepository.insert(contact);
		}
		return Constants.JSON_RESULT_SUCESS;
	}

}
