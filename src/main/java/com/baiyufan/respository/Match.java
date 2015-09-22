package com.baiyufan.respository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Match {
	@Id
	private String id;
	private String pk;// json返回id用，实现方式见get方法

	private String matchDate;// 配对日期
	@DBRef
	private Person name;//姓名
	@DBRef
	private Contract nameContract; //对应合同
	//private String fee;//收费
	//private String checkEmployee;//买单老师 
	//private float serviceTimes;//服务次数
	@DBRef
	private User serviceEmployee;//服务老师
	@DBRef
	private Person matchPerson;//配对对象 
	@DBRef
	private Contract matchPersonContract; //对应合同
	private String visitResult;//回访结果 还在进行，结束，成功 
	private String visitRemark;//回访情况说明
	private String aliveFlag;//有效标记
	private String createdBy;//创建人
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
		this.pk = null;
	}
	public String getMatchDate() {
		return matchDate;
	}
	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}
	public Person getName() {
		return name;
	}
	public void setName(Person name) {
		this.name = name;
	}

	// public String getFee() {
	// return fee;
	// }
	// public void setFee(String fee) {
	// this.fee = fee;
	// }
	// public String getCheckEmployee() {
	// return checkEmployee;
	// }
	// public void setCheckEmployee(String checkEmployee) {
	// this.checkEmployee = checkEmployee;
	// }
	public User getServiceEmployee() {
		return serviceEmployee;
	}
	public void setServiceEmployee(User serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}
	public Person getMatchPerson() {
		return matchPerson;
	}
	public void setMatchPerson(Person matchPerson) {
		this.matchPerson = matchPerson;
	}
	public String getVisitResult() {
		return visitResult;
	}
	public void setVisitResult(String visitResult) {
		this.visitResult = visitResult;
	}
	public String getVisitRemark() {
		return visitRemark;
	}
	public void setVisitRemark(String visitRemark) {
		this.visitRemark = visitRemark;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Contract getNameContract() {
		return nameContract;
	}
	public void setNameContract(Contract nameContract) {
		this.nameContract = nameContract;
	}
	public Contract getMatchPersonContract() {
		return matchPersonContract;
	}
	public void setMatchPersonContract(Contract matchPersonContract) {
		this.matchPersonContract = matchPersonContract;
	}

}
