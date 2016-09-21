package org.appsquad.bean;

import java.util.Date;

public class RequirementGenerationBean {

  private String clientName;
  private String clientfName;
  private String clientsName;
  private int clientId;
  
  private String reqSkill;
  private int reqSkillId;
  
  private String jobType;
  
  private String detailedJob;
  
  private Integer nofPerResource;
  
  private Integer nofConResource;
  
  private Date raiseDate;
  private java.sql.Date	raiseDatesql;
  private String raiseDateStr;
  
  private Date closeDate;
  private java.sql.Date closeDatesql;
  private String closeDateStr;
  
  private String reqStatus;
  private int reqStatusId;
  
  private String ocStatus;
  private int ocStatusId;
  
  private String closureReason;
  
  private String userName;
  
  private String contactNo;
  private String email;

public String getClientName() {
	return clientName;
}

public void setClientName(String clientName) {
	this.clientName = clientName;
}

public String getClientfName() {
	return clientfName;
}

public void setClientfName(String clientfName) {
	this.clientfName = clientfName;
}

public String getClientsName() {
	return clientsName;
}

public void setClientsName(String clientsName) {
	this.clientsName = clientsName;
}

public int getClientId() {
	return clientId;
}

public void setClientId(int clientId) {
	this.clientId = clientId;
}

public String getReqSkill() {
	return reqSkill;
}

public void setReqSkill(String reqSkill) {
	this.reqSkill = reqSkill;
}

public int getReqSkillId() {
	return reqSkillId;
}

public void setReqSkillId(int reqSkillId) {
	this.reqSkillId = reqSkillId;
}

public String getJobType() {
	return jobType;
}

public void setJobType(String jobType) {
	this.jobType = jobType;
}

public String getDetailedJob() {
	return detailedJob;
}

public void setDetailedJob(String detailedJob) {
	this.detailedJob = detailedJob;
}



public Date getRaiseDate() {
	return raiseDate;
}

public void setRaiseDate(Date raiseDate) {
	this.raiseDate = raiseDate;
}

public String getRaiseDateStr() {
	return raiseDateStr;
}

public void setRaiseDateStr(String raiseDateStr) {
	this.raiseDateStr = raiseDateStr;
}

public Date getCloseDate() {
	return closeDate;
}

public void setCloseDate(Date closeDate) {
	this.closeDate = closeDate;
}

public String getCloseDateStr() {
	return closeDateStr;
}

public void setCloseDateStr(String closeDateStr) {
	this.closeDateStr = closeDateStr;
}

public String getReqStatus() {
	return reqStatus;
}

public void setReqStatus(String reqStatus) {
	this.reqStatus = reqStatus;
}

public int getReqStatusId() {
	return reqStatusId;
}

public void setReqStatusId(int reqStatusId) {
	this.reqStatusId = reqStatusId;
}

public String getClosureReason() {
	return closureReason;
}

public void setClosureReason(String closureReason) {
	this.closureReason = closureReason;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getContactNo() {
	return contactNo;
}

public void setContactNo(String contactNo) {
	this.contactNo = contactNo;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Integer getNofPerResource() {
	return nofPerResource;
}

public void setNofPerResource(Integer nofPerResource) {
	this.nofPerResource = nofPerResource;
}

public Integer getNofConResource() {
	return nofConResource;
}

public void setNofConResource(Integer nofConResource) {
	this.nofConResource = nofConResource;
}

public String getOcStatus() {
	return ocStatus;
}

public void setOcStatus(String ocStatus) {
	this.ocStatus = ocStatus;
}

public int getOcStatusId() {
	return ocStatusId;
}

public void setOcStatusId(int ocStatusId) {
	this.ocStatusId = ocStatusId;
}

public java.sql.Date getRaiseDatesql() {
	return raiseDatesql;
}

public void setRaiseDatesql(java.sql.Date raiseDatesql) {
	this.raiseDatesql = raiseDatesql;
}

public java.sql.Date getCloseDatesql() {
	return closeDatesql;
}

public void setCloseDatesql(java.sql.Date closeDatesql) {
	this.closeDatesql = closeDatesql;
}	
}
