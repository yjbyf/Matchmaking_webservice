package com.baiyufan.db.model;

import java.math.BigDecimal;

public class TPerson {
    private Integer id;

    private String name;

    private String gender;

    private String birthDate;
    
    private String constellation;

    private BigDecimal height;

    private String education;

    private String census;

    private String company;

    private BigDecimal salary;

    private String family;

    private String house;
    
    private String marriedHis;//married_his

    private String phone;

    private String requirement;

    private String aliveFlag;

    private Integer createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getCensus() {
        return census;
    }

    public void setCensus(String census) {
        this.census = census;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getMarriedHis() {
		return marriedHis;
	}

	public void setMarriedHis(String marriedHis) {
		this.marriedHis = marriedHis;
	}
    
    
}