package com.baiyufan.respository;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Contact {

	@Id
	private String id;
	private String pk;// json返回id用，实现方式见get方法

	@DBRef
	private User contacter; // 用来保存实际内容
	private User contacterDetail; // json中显示dbref的具体内容，实现方式见get方法
	@DBRef
	private List<User> contactee; // 实际保存用字段
	private List<User> contacteeDetail;// json中显示dbref的具体内容，实现方式见get方法

	/************************* get method start ***************************/
	public String getId() {
		return id;
	}

	public String getPk() {
		return id;
	}

	public User getContacter() {
		return contacter;
	}

	public User getContacterDetail() {
		return contacter;
	}

	public List<User> getContactee() {
		return contactee;
	}

	public List<User> getContacteeDetail() {
		return contactee;
	}

	/************************* get method end ***************************/
	// ///////////////////set method start/////////////////////////////
	public void setContacter(User contacter) {
		this.contacter = contacter;
	}

	public void setContactee(List<User> contactee) {
		this.contactee = contactee;
	}
	// ///////////////////set method end/////////////////////////////
}
