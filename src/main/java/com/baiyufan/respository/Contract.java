package com.baiyufan.respository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Contract {
	@Id
	private String id;
	private String pk;//add// json返回id用，实现方式见get方法

	// 合同管理
	// 合同号
	private String no;

	// 姓名
	// 性别
	@DBRef
	private Person person;
	private Person personInfo;//add
	private String personId;//add
	private String name;//add
	private String gender;//add
	// 收费
	private float fee;
	// 服务次数
	private float serviceTimes;
	// 买单老师
	@DBRef
	private User checker;
	private User checkerInfo;//add
	private String checkerName;//add
	// 发票号
	private String vatNO;
	// 合同开始日期
	private String startDate;
	// 合同结束日期
	private String endDate;

	private String aliveFlag; // 有效标记
	private String createdBy;//创建人

	// 如果合同结束日期小于当天日期归档到历史数据
	// 功能 新增 修改 删除（管理员有权限） 查询

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPk() {
		return id;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		no = no;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public float getFee() {
		return fee;
	}

	public void setFee(float fee) {
		this.fee = fee;
	}

	public float getServiceTimes() {
		return serviceTimes;
	}

	public void setServiceTimes(float serviceTimes) {
		this.serviceTimes = serviceTimes;
	}

	public User getChecker() {
		return checker;
	}

	public void setChecker(User checker) {
		this.checker = checker;
	}

	public String getVatNO() {
		return vatNO;
	}

	public void setVatNO(String vatNO) {
		vatNO = vatNO;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Person getPersonInfo() {
		return person;
	}

	public void setPersonInfo(Person personInfo) {
		this.personInfo = personInfo;
	}

	public User getCheckerInfo() {
		return checker;
	}

	public void setCheckerInfo(User checkerInfo) {
		this.checkerInfo = checkerInfo;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public String getPersonId() {
		if (this.person != null) {
			return person.getId();
		}
		return "";
	}

	public String getName() {
		if (this.person != null) {
			return person.getName();
		}
		return "";
	}

	public String getGender() {
		if (this.person != null) {
			return person.getGender();
		}
		return "";
	}

	public String getCheckerName() {
		if (this.checker != null) {
			return checker.getStaff();
		}
		return "";
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
