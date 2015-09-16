package com.baiyufan.respository;

import org.springframework.data.annotation.Id;

public class Employee {
	@Id private String id;
	private String pk;// json返回id用，实现方式见get方法，冗余字段不需要set，否则数据库会有该字段
	
	private String name;
	
	private String aliveFlag; //有效标记
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPk() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
}
