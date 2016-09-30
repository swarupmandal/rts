package org.appsquad.bean;

import java.util.Date;

public class IndividualRequirementReportBean {
	
	private Integer r_idSearch;
	private Date fromDate;
	private Date toDate;
	private String userId;
	private String selectedRadioButton;
	
	private SkillsetMasterbean masterbean = new SkillsetMasterbean();
	private StatusMasterBean statusMasterBean = new StatusMasterBean();
	private UserprofileBean userprofileBean = new UserprofileBean();
	private SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	private ClientInformationBean clientInformationBean = new ClientInformationBean();
	private RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
	
	
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSelectedRadioButton() {
		return selectedRadioButton;
	}
	public void setSelectedRadioButton(String selectedRadioButton) {
		this.selectedRadioButton = selectedRadioButton;
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
	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
	}
	public RequirementGenerationBean getRequirementGenerationBean() {
		return requirementGenerationBean;
	}
	public void setRequirementGenerationBean(
			RequirementGenerationBean requirementGenerationBean) {
		this.requirementGenerationBean = requirementGenerationBean;
	}
	public Integer getR_idSearch() {
		return r_idSearch;
	}
	public void setR_idSearch(Integer r_idSearch) {
		this.r_idSearch = r_idSearch;
	}
	
}
