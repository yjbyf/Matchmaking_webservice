package com.baiyufan.respository;

import org.springframework.data.annotation.Id;

public class Match {
	@Id
	private String id;
	private String pk;// json返回id用，实现方式见get方法

	private String matchDate;// 配对日期 
	private String name;//姓名
	//private String fee;//收费
	//private String checkEmployee;//买单老师 
	//private float serviceTimes;//服务次数 
	private String serviceEmployee;//服务老师 
	private String matchPerson;//配对对象 
	private String visitResult;//回访结果 还在进行，结束，成功 
	private String visitRemark;//回访情况说明
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
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
	public String getServiceEmployee() {
		return serviceEmployee;
	}
	public void setServiceEmployee(String serviceEmployee) {
		this.serviceEmployee = serviceEmployee;
	}
	public String getMatchPerson() {
		return matchPerson;
	}
	public void setMatchPerson(String matchPerson) {
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

}
