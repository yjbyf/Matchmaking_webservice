package com.baiyufan.respository;

import org.springframework.data.annotation.Id;

public class User {

	
	@Id private String id;
	
	private String pk;
			
	private String userName;
	private String password;
	private String staff;
	private String group;
	private String aliveFlag;
	
	public String getPk() {
		return id;
	}
	public String getId() {
		return id;
	}
	
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
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}


}
