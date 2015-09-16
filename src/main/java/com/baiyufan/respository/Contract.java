package com.baiyufan.respository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Contract {
	@Id
	private String id;
	private String pk;// json返回id用，实现方式见get方法

	// 合同管理
	// 合同号
	private String NO;
	// 姓名
	// 性别
	@DBRef
	private Person person;
	private Person personInfo;
	// 收费
	private float fee;
	// 服务次数
	private int serviceTimes;
	// 买单老师
	@DBRef
	private Employee checker;
	private Employee checkerInfo;
	// 发票号
	private String VatNO;
	// 合同开始日期
	private String startDate;
	// 合同结束日期
	private String endDate;

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

	public String getNO() {
		return NO;
	}

	public void setNO(String nO) {
		NO = nO;
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

	public int getServiceTimes() {
		return serviceTimes;
	}

	public void setServiceTimes(int serviceTimes) {
		this.serviceTimes = serviceTimes;
	}

	public Employee getChecker() {
		return checker;
	}

	public void setChecker(Employee checker) {
		this.checker = checker;
	}

	public String getVatNO() {
		return VatNO;
	}

	public void setVatNO(String vatNO) {
		VatNO = vatNO;
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

	public Employee getCheckerInfo() {
		return checker;
	}

	public void setCheckerInfo(Employee checkerInfo) {
		this.checkerInfo = checkerInfo;
	}

}
