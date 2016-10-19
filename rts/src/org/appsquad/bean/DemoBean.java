package org.appsquad.bean;

import java.util.Date;

public class DemoBean {
	private String name;
	private String surName;
	private Integer id;
	private boolean chkSelect = false;
	private boolean chkVerify = false;
	private String assignedOrNot;
	private String cvPath;
	private String fullName;
	private Integer experience;
	private String address;
	private String emailId;
	private String contactNumber;
	
	private Date fromDate;
	private java.sql.Date fromDateSql;
	private String fromDateValue;
	private String fromDateStr;
	
	private Date toDate;
	private java.sql.Date toDateSql;
	private String toDateValue;
	private String toateStr;
	
	public SkillsetMasterbean masterbean = new SkillsetMasterbean();
	public StatusMasterBean statusMasterBean = new StatusMasterBean();
	public UserprofileBean userprofileBean = new UserprofileBean();
	public SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	public ClientInformationBean clientInformationBean = new ClientInformationBean();
	
	
	private String resourceName;
	private String clientOriginalName;
	private Integer reqId;
    private String reqStatus;
    private String reqSkillName;
    private Integer resourceId;
	
	/**************************************************************************************************************************************************/
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	public boolean isChkSelect() {
		return chkSelect;
	}
	public void setChkSelect(boolean chkSelect) {
		this.chkSelect = chkSelect;
	}
	public String getCvPath() {
		return cvPath;
	}
	public void setCvPath(String cvPath) {
		this.cvPath = cvPath;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public java.sql.Date getFromDateSql() {
		return fromDateSql;
	}
	public void setFromDateSql(java.sql.Date fromDateSql) {
		this.fromDateSql = fromDateSql;
	}
	public String getFromDateValue() {
		return fromDateValue;
	}
	public void setFromDateValue(String fromDateValue) {
		this.fromDateValue = fromDateValue;
	}
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public java.sql.Date getToDateSql() {
		return toDateSql;
	}
	public void setToDateSql(java.sql.Date toDateSql) {
		this.toDateSql = toDateSql;
	}
	public String getToDateValue() {
		return toDateValue;
	}
	public void setToDateValue(String toDateValue) {
		this.toDateValue = toDateValue;
	}
	public String getToateStr() {
		return toateStr;
	}
	public void setToateStr(String toateStr) {
		this.toateStr = toateStr;
	}
	public SkillsetMasterbean getMasterbean() {
		return masterbean;
	}
	public void setMasterbean(SkillsetMasterbean masterbean) {
		this.masterbean = masterbean;
	}
	public StatusMasterBean getStatusMasterBean() {
		return statusMasterBean;
	}
	public void setStatusMasterBean(StatusMasterBean statusMasterBean) {
		this.statusMasterBean = statusMasterBean;
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public SkillsetMasterbean getSkillsetMasterbean() {
		return skillsetMasterbean;
	}
	public void setSkillsetMasterbean(SkillsetMasterbean skillsetMasterbean) {
		this.skillsetMasterbean = skillsetMasterbean;
	}
	public ClientInformationBean getClientInformationBean() {
		return clientInformationBean;
	}
	public boolean isChkVerify() {
		return chkVerify;
	}
	public void setChkVerify(boolean chkVerify) {
		this.chkVerify = chkVerify;
	}
	public String getAssignedOrNot() {
		return assignedOrNot;
	}
	public void setAssignedOrNot(String assignedOrNot) {
		this.assignedOrNot = assignedOrNot;
	}
	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getClientOriginalName() {
		return clientOriginalName;
	}
	public void setClientOriginalName(String clientOriginalName) {
		this.clientOriginalName = clientOriginalName;
	}
	public Integer getReqId() {
		return reqId;
	}
	public void setReqId(Integer reqId) {
		this.reqId = reqId;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getReqSkillName() {
		return reqSkillName;
	}
	public void setReqSkillName(String reqSkillName) {
		this.reqSkillName = reqSkillName;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
}
