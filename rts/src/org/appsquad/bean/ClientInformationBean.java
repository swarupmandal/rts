package org.appsquad.bean;

import java.sql.Date;

public class ClientInformationBean {
	private String name;
	private String surName;
	private String companyName;
	private String address;
	private String state;
	private String country;
	private String emailId;
	private String pinZipCode;
	private String contactNo;
	private String userId;
	private String fullName;
	private Integer clientId;
	private String clientOriginalName;
	private boolean countryDropdownDisable = false;
	private boolean stateDropdownDisable = false;
	
	CountryBean countryBean = new CountryBean();
	StateBean stateBean = new StateBean();
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName = "MASTER";
	private String chileScreenName = "CLIENT INFORMATION MASTER";
	private Integer userSerialId;
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	/*************************** GETTER AND SETTER METHOD ********************************/
	
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPinZipCode() {
		return pinZipCode;
	}
	public void setPinZipCode(String pinZipCode) {
		this.pinZipCode = pinZipCode;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public CountryBean getCountryBean() {
		return countryBean;
	}
	public void setCountryBean(CountryBean countryBean) {
		this.countryBean = countryBean;
	}
	public StateBean getStateBean() {
		return stateBean;
	}
	public void setStateBean(StateBean stateBean) {
		this.stateBean = stateBean;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public boolean isStateDropdownDisable() {
		return stateDropdownDisable;
	}
	public void setStateDropdownDisable(boolean stateDropdownDisable) {
		this.stateDropdownDisable = stateDropdownDisable;
	}
	public boolean isCountryDropdownDisable() {
		return countryDropdownDisable;
	}
	public void setCountryDropdownDisable(boolean countryDropdownDisable) {
		this.countryDropdownDisable = countryDropdownDisable;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getClientId() {
		return clientId;
	}
	public String getClientOriginalName() {
		return clientOriginalName;
	}
	public void setClientOriginalName(String clientOriginalName) {
		this.clientOriginalName = clientOriginalName;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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
