package org.appsquad.bean;

import java.sql.Date;

public class SkillsetMasterbean {
	private Integer id;
	private String skillset;
	private String skillsetdetails;
	private String userId;
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName = "MASTER";
	private String chileScreenName = "SKILL MASTER";
	private Integer userSerialId;
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	/*******************************getter ans setter method **************************************************/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSkillset() {
		return skillset;
	}
	public void setSkillset(String skillset) {
		this.skillset = skillset;
	}
	public String getSkillsetdetails() {
		return skillsetdetails;
	}
	public void setSkillsetdetails(String skillsetdetails) {
		this.skillsetdetails = skillsetdetails;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMainScreenName() {
		return mainScreenName;
	}
	public void setMainScreenName(String mainScreenName) {
		this.mainScreenName = mainScreenName;
	}
	public String getChileScreenName() {
		return chileScreenName;
	}
	public void setChileScreenName(String chileScreenName) {
		this.chileScreenName = chileScreenName;
	}
	public Integer getUserSerialId() {
		return userSerialId;
	}
	public void setUserSerialId(Integer userSerialId) {
		this.userSerialId = userSerialId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getSessionUserId() {
		return sessionUserId;
	}
	public void setSessionUserId(String sessionUserId) {
		this.sessionUserId = sessionUserId;
	}
	public Integer getOperationId() {
		return operationId;
	}
	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}	
}
