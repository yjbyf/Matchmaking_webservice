package com.baiyufan.db.model;

public class TContractExt extends TContract {
	private String name;
	private String gender;
	private String checkerName;
	private String matchTimes;
	private String constellation;
	
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
	public void setMatchTimes(String matchTimes) {
		this.matchTimes = matchTimes;
	}
	
	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
}
