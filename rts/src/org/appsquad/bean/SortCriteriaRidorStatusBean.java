package org.appsquad.bean;	

import java.util.Date;

public class SortCriteriaRidorStatusBean {
	private Date fromDate;
	private Date toDate;
	private String userId;
	private String selectedRadioButton;
	private boolean divVisibility = false;
	
	private SkillsetMasterbean masterbean = new SkillsetMasterbean();
	private StatusMasterBean statusMasterBean = new StatusMasterBean();
	private UserprofileBean userprofileBean = new UserprofileBean();
	private SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	private ClientInformationBean clientInformationBean = new ClientInformationBean();
	private ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
	
	/************************************************************************************************************************************************/
	
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getSelectedRadioButton() {
		return selectedRadioButton;
	}
	public void setSelectedRadioButton(String selectedRadioButton) {
		this.selectedRadioButton = selectedRadioButton;
	}
	public ResourceMasterBean getResourceMasterBean() {
		return resourceMasterBean;
	}
	public void setResourceMasterBean(ResourceMasterBean resourceMasterBean) {
		this.resourceMasterBean = resourceMasterBean;
	}
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}

}
