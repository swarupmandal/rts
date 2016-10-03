package org.appsquad.bean;

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
	private Double ctc;
	private Integer yearOfExperience;
	private String fullName;
	private boolean chkSelect = false;
	private String cvPath;
	private String isAllocable;
	private String filePath;
	private boolean countryVisibility = true;
	private Double profit;
	private byte[] fileContent;
	
	CountryBean countryBean = new CountryBean();
	StateBean stateBean = new StateBean();	
	StatusMasterBean statusMasterBean = new StatusMasterBean();
	SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	
	private ArrayList<Byte> binaryList = new ArrayList<Byte>();
	
	/*******************************************************************************************************/
	
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
}
