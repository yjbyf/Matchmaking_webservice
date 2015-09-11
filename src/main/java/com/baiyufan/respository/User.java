package com.baiyufan.respository;

import org.springframework.data.annotation.Id;

public class User {

	
	@Id private String id;

	
	public String getId() {
		return id;
	}
	
	private String userName;
	private String password;
	private String group;
	
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


}
