package org.appsquad.bean;

import java.util.Date;

public class ResourceAllocationTrackingBean {

	public ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
	
	private String clientName;
	private Integer clientId;
	private String clientNameSearch;
	
	private Integer reqId;
	private Integer r_idSearch;
	
	private String skillSet;
   
	private Date internalInterviewDate;
	private java.sql.Date internalInterviewDateSql;
	private String internalInterviewDateStr;
	private String internalInterviewDateValue;
	
	private Date clientInterviewDate;
	private java.sql.Date clientInterviewDateSql;
	private String clientInterviewDateStr;
	private String clientInterviewDateValue;
	
	private Date onboardDate;
	private java.sql.Date onboardDateSql;
	private String onboardDateStr;
	private String onboardDateValue;
	
	
	
	
	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public Integer getClientId() {
		return clientId;
	}


	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}


	public Integer getReqId() {
		return reqId;
	}


	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}


	public String getClientNameSearch() {
		return clientNameSearch;
	}


	public void setClientNameSearch(String clientNameSearch) {
		this.clientNameSearch = clientNameSearch;
	}


	public Integer getR_idSearch() {
		return r_idSearch;
	}


	public void setR_idSearch(Integer r_idSearch) {
		this.r_idSearch = r_idSearch;
	}


	public String getSkillSet() {
		return skillSet;
	}


	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}


	public ResourceMasterBean getResourceMasterBean() {
		return resourceMasterBean;
	}


	public void setResourceMasterBean(ResourceMasterBean resourceMasterBean) {
		this.resourceMasterBean = resourceMasterBean;
	}


	public Date getInternalInterviewDate() {
		return internalInterviewDate;
	}


	public void setInternalInterviewDate(Date internalInterviewDate) {
		this.internalInterviewDate = internalInterviewDate;
	}


	public java.sql.Date getInternalInterviewDateSql() {
		return internalInterviewDateSql;
	}


	public void setInternalInterviewDateSql(java.sql.Date internalInterviewDateSql) {
		this.internalInterviewDateSql = internalInterviewDateSql;
	}


	public String getInternalInterviewDateStr() {
		return internalInterviewDateStr;
	}


	public void setInternalInterviewDateStr(String internalInterviewDateStr) {
		this.internalInterviewDateStr = internalInterviewDateStr;
	}


	public String getInternalInterviewDateValue() {
		return internalInterviewDateValue;
	}


	public void setInternalInterviewDateValue(String internalInterviewDateValue) {
		this.internalInterviewDateValue = internalInterviewDateValue;
	}


	public Date getClientInterviewDate() {
		return clientInterviewDate;
	}


	public void setClientInterviewDate(Date clientInterviewDate) {
		this.clientInterviewDate = clientInterviewDate;
	}


	public java.sql.Date getClientInterviewDateSql() {
		return clientInterviewDateSql;
	}


	public void setClientInterviewDateSql(java.sql.Date clientInterviewDateSql) {
		this.clientInterviewDateSql = clientInterviewDateSql;
	}


	public String getClientInterviewDateStr() {
		return clientInterviewDateStr;
	}


	public void setClientInterviewDateStr(String clientInterviewDateStr) {
		this.clientInterviewDateStr = clientInterviewDateStr;
	}


	public String getClientInterviewDateValue() {
		return clientInterviewDateValue;
	}


	public void setClientInterviewDateValue(String clientInterviewDateValue) {
		this.clientInterviewDateValue = clientInterviewDateValue;
	}


	public Date getOnboardDate() {
		return onboardDate;
	}


	public void setOnboardDate(Date onboardDate) {
		this.onboardDate = onboardDate;
	}


	public java.sql.Date getOnboardDateSql() {
		return onboardDateSql;
	}


	public void setOnboardDateSql(java.sql.Date onboardDateSql) {
		this.onboardDateSql = onboardDateSql;
	}


	public String getOnboardDateStr() {
		return onboardDateStr;
	}


	public void setOnboardDateStr(String onboardDateStr) {
		this.onboardDateStr = onboardDateStr;
	}


	public String getOnboardDateValue() {
		return onboardDateValue;
	}


	public void setOnboardDateValue(String onboardDateValue) {
		this.onboardDateValue = onboardDateValue;
	}

	
}
