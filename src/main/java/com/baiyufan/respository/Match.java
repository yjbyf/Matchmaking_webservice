package com.baiyufan.respository;

import org.springframework.data.annotation.Id;

public class Match {
	@Id
	private String id;
	private String pk;// json返回id用，实现方式见get方法

	private String matchDate;// 配对日期 
	private String name;//姓名
	private String fee;//收费
	private String checkEmployee;//买单老师 
	private float serviceTimes;//服务次数 
	private String serviceEmployee;//服务老师 
	private String matchPerson;//配对对象 
	private String visitResult;//回访结果 还在进行，结束，成功 
	private String visitRemark;//回访情况说明

}
