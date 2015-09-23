package com.baiyufan.respository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.baiyufan.utils.Constants;

public class Match {
	@Id
	private String id;
	private String pk;// json返回id用，实现方式见get方法

	private String matchDate;// 配对日期
	@DBRef
	private Person name;// 姓名
	
	private String refName;// 姓名
	private String refGender;// 性别
	@DBRef
	private Contract nameContract; // 对应合同
	private String refNameContractId; //id
	// private String fee;//收费
	// private String checkEmployee;//买单老师
	// private float serviceTimes;//服务次数
	@DBRef
	private User serviceEmployee;// 服务老师
	private String refServiceEmployeeId;//id
	private String refServiceEmployee; //名称
	@DBRef
	private Person matchPerson;// 配对对象
	
	private String refMatchPerson;//名称
	@DBRef
	private Contract matchPersonContract; // 对应合同
	private String refMatchPersonContractId;//id
	private String visitResult;// 回访结果 还在进行，结束，成功
	private String visitRemark;// 回访情况说明
	private String aliveFlag;// 有效标记
	private String createdBy;// 创建人

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

	public String getRefName() {
		if (this.name != null) {
			return name.getName();
		}
		return Constants.NULL_STRING;
	}

	public String getRefGender() {
		if (this.name != null) {
			return name.getGender();
		}
		return Constants.NULL_STRING;
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

	public String getRefServiceEmployee() {
		if (this.serviceEmployee != null) {
			return serviceEmployee.getStaff();
		}
		return Constants.NULL_STRING;
	}

	public Person getMatchPerson() {
		return matchPerson;
	}

	public void setMatchPerson(Person matchPerson) {
		this.matchPerson = matchPerson;
	}

	public String getRefMatchPerson() {
		if (this.matchPerson != null) {
			return matchPerson.getName();
		}
		return Constants.NULL_STRING;
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

	public void setRefName(String refName) {
		this.refName = refName;
	}

	public void setRefGender(String refGender) {
		this.refGender = refGender;
	}

	public void setRefServiceEmployee(String refServiceEmployee) {
		this.refServiceEmployee = refServiceEmployee;
	}

	public void setRefMatchPerson(String refMatchPerson) {
		this.refMatchPerson = refMatchPerson;
	}

	public String getRefServiceEmployeeId() {
		if(this.serviceEmployee!=null){
			return this.serviceEmployee.getId();
		}			
		return Constants.NULL_STRING;
	}

	public void setRefServiceEmployeeId(String refServiceEmployeeId) {
		this.refServiceEmployeeId = refServiceEmployeeId;
	}

	public String getRefNameContractId() {
		if(this.nameContract!=null){
			return this.nameContract.getId();
		}			
		return Constants.NULL_STRING;
	}

	public void setRefNameContractId(String refNameContractId) {
		this.refNameContractId = refNameContractId;
	}

	public String getRefMatchPersonContractId() {
		if(this.matchPersonContract!=null){
			return this.matchPersonContract.getId();
		}			
		return Constants.NULL_STRING;
	}

	public void setRefMatchPersonContractId(String refMatchPersonContractId) {
		this.refMatchPersonContractId = refMatchPersonContractId;
	}
	
	

}
