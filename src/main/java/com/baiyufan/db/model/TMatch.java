package com.baiyufan.db.model;


public class TMatch {
    private Integer id;

    private String matchDate;

    private Integer name;

    private Integer nameContract;

    private Integer serviceEmployee;

    private Integer matchPerson;

    private Integer matchPersonContract;

    private String visitResult;

    private String visitRemark;

    private String aliveFlag;

    private Integer createBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(String matchDate) {
        this.matchDate = matchDate;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
        this.name = name;
    }

    public Integer getNameContract() {
        return nameContract;
    }

    public void setNameContract(Integer nameContract) {
        this.nameContract = nameContract;
    }

    public Integer getServiceEmployee() {
        return serviceEmployee;
    }

    public void setServiceEmployee(Integer serviceEmployee) {
        this.serviceEmployee = serviceEmployee;
    }

    public Integer getMatchPerson() {
        return matchPerson;
    }

    public void setMatchPerson(Integer matchPerson) {
        this.matchPerson = matchPerson;
    }

    public Integer getMatchPersonContract() {
        return matchPersonContract;
    }

    public void setMatchPersonContract(Integer matchPersonContract) {
        this.matchPersonContract = matchPersonContract;
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

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
}