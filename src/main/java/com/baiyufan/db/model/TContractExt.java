package com.baiyufan.db.model;

public class TContractExt extends TContract {
	private String name;
	private String gender;
	private String checkerName;
	private String matchTimes;
	private String constellation;
	private String birthDate;
	private String age;
	private String education;
	private String marriedHis;
	private String salary;
	private String house;
	private String company;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	
	public String getMatchTimes() {
		return matchTimes;
	}
	public String getMarriedHis() {
		return marriedHis;
	}
	public void setMarriedHis(String marriedHis) {
		this.marriedHis = marriedHis;
	}
	public void setMatchTimes(String matchTimes) {
		this.matchTimes = matchTimes;
	}
	
	public String getConstellation() {
		return constellation;
	}

	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
}
