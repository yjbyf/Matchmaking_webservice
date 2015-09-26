package com.baiyufan.db.model;

public class TMatchExt extends TMatch {
	private String personName;
	private String gender;
	private String serviceEmployeeName;
	private String matchPersonName;
	
	
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getServiceEmployeeName() {
		return serviceEmployeeName;
	}
	public void setServiceEmployeeName(String serviceEmployeeName) {
		this.serviceEmployeeName = serviceEmployeeName;
	}
	public String getMatchPersonName() {
		return matchPersonName;
	}
	public void setMatchPersonName(String matchPersonName) {
		this.matchPersonName = matchPersonName;
	}
}
