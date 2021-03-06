package org.appsquad.bean;

import java.sql.Date;
import java.util.ArrayList;

public class ResourceMasterBean {
	private String name;
	private String surName;
	private String address;
	private String picCode;
	private String contactNumber;
	private String emailId;
	private int resourceId;
	private String userId;
	private Double ctc,expectedCtc;
	private Integer yearOfExperience;
	private String fullName;
	private boolean chkSelect = false;
	private String cvPath;
	private String isAllocable;
	private String filePath;
	private boolean countryVisibility = true;
	private Double profit;
	private byte[] fileContent;
	private String otherInfo;
	private String country;
	private String state;
	private String fileName;
	private String resourceNameStyle = "text-align:center;";
	
	CountryBean countryBean = new CountryBean();
	StateBean stateBean = new StateBean();	
	StatusMasterBean statusMasterBean = new StatusMasterBean();
	SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	
	private ArrayList<Byte> binaryList = new ArrayList<Byte>();
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName = "MASTER";
	private String chileScreenName = "RESOURCE MASTER";
	private Integer userSerialId;
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	/*******************************************************************************************************/
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicCode() {
		return picCode;
	}
	public void setPicCode(String picCode) {
		this.picCode = picCode;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public StatusMasterBean getStatusMasterBean() {
		return statusMasterBean;
	}
	public void setStatusMasterBean(StatusMasterBean statusMasterBean) {
		this.statusMasterBean = statusMasterBean;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SkillsetMasterbean getSkillsetMasterbean() {
		return skillsetMasterbean;
	}
	public Double getCtc() {
		return ctc;
	}
	public void setCtc(Double ctc) {
		this.ctc = ctc;
	}
	public boolean isChkSelect() {
		return chkSelect;
	}
	public void setChkSelect(boolean chkSelect) {
		this.chkSelect = chkSelect;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public ArrayList<Byte> getBinaryList() {
		return binaryList;
	}
	public void setBinaryList(ArrayList<Byte> binaryList) {
		this.binaryList = binaryList;
	}
	public Integer getYearOfExperience() {
		return yearOfExperience;
	}
	public void setYearOfExperience(Integer yearOfExperience) {
		this.yearOfExperience = yearOfExperience;
	}
	public void setSkillsetMasterbean(SkillsetMasterbean skillsetMasterbean) {
		this.skillsetMasterbean = skillsetMasterbean;
	}
	public String getCvPath() {
		return cvPath;
	}
	public void setCvPath(String cvPath) {
		this.cvPath = cvPath;
	}
	public String getIsAllocable() {
		return isAllocable;
	}
	public void setIsAllocable(String isAllocable) {
		this.isAllocable = isAllocable;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public boolean isCountryVisibility() {
		return countryVisibility;
	}
	public Double getProfit() {
		return profit;
	}
	public void setProfit(Double profit) {
		this.profit = profit;
	}
	public void setCountryVisibility(boolean countryVisibility) {
		this.countryVisibility = countryVisibility;
	}
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getOperationId() {
		return operationId;
	}
	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Double getExpectedCtc() {
		return expectedCtc;
	}
	public void setExpectedCtc(Double expectedCtc) {
		this.expectedCtc = expectedCtc;
	}
	public String getResourceNameStyle() {
		return resourceNameStyle;
	}
	public void setResourceNameStyle(String resourceNameStyle) {
		this.resourceNameStyle = resourceNameStyle;
	}
}
