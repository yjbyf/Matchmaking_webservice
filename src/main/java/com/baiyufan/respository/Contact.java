package com.baiyufan.respository;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Contact {
	
	@Id private String id;
	

	private String pk;
	public String getPk() {
		return id;
	}



	//@DBRef
	private User contacter; 
	
//	private List<String> contacteeName;
//	
//	public List<String> getContacteeName() {
//		List<String> names = new ArrayList<String>();
//		names.add("aaa");
//		names.add("bbb");
//		return names;
//	}

	
	public void setContacter(User contacter) {
		this.contacter = contacter;
	}



	private List<User> contactee;
	/*
	private List<String> contacterName;
	
	

	private List<String> contacteeName;
	
	public void setContacterName(List<String> contacterName) {
		this.contacterName = contacterName;
	}



	public void setContacteeName(List<String> contacteeName) {
		this.contacteeName = contacteeName;
	}
	public List<String> getContacteeName() {
		if(contactee!=null){
			List<String> list = new ArrayList<String>();
			for(User user :contacter){
				list.add(user.getUserName());
			}
			return list;
		}
		return contacteeName;
	}

	

	public List<String>  getContacterName() {
		return contacterName;
	}*/

	
	public List<User> getContactee() {
		return contactee;
	}

	

	public String getId() {
		return id;
	}

	

	public User getContacter() {
		return contacter;
	}

	

//	public void setContacteeName(List<String> contacteeName) {
//		this.contacteeName = contacteeName;
//	}



	

	


	 
}
