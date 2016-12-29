package org.appsquad.bean;

import java.util.Date;

public class ResourceAllocationBean {
	private Integer requiredResourcenumber;
	private Integer allocatedResourceNumber;
	private Integer remainingResourceNumber;
	private String userId;
	private int statusId;
	private boolean chkSelect = false;
	private boolean divVisibility = false;
	private boolean assignButtonVisibility = false;
	
	private RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
	private ClientInformationBean clientInformationBean = new ClientInformationBean();
	private ResourceTypeBean resourceTypeBean = new ResourceTypeBean();
	private SkillsetMasterbean masterbean = new SkillsetMasterbean();
	private ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName = "TRANSACTION";
	private String chileScreenName = "RESOURCE ALLOCATION SCREEN";
	private Integer userSerialId;
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	/*****************************************************************************************************************************************/
	public Integer getRequiredResourcenumber() {
		return requiredResourcenumber;
	}
	public void setRequiredResourcenumber(Integer requiredResourcenumber) {
		this.requiredResourcenumber = requiredResourcenumber;
	}
	public Integer getAllocatedResourceNumber() {
		return allocatedResourceNumber;
	}
	public void setAllocatedResourceNumber(Integer allocatedResourceNumber) {
		this.allocatedResourceNumber = allocatedResourceNumber;
	}
	public Integer getRemainingResourceNumber() {
		return remainingResourceNumber;
	}
	public void setRemainingResourceNumber(Integer remainingResourceNumber) {
		this.remainingResourceNumber = remainingResourceNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public RequirementGenerationBean getRequirementGenerationBean() {
		return requirementGenerationBean;
	}
	public void setRequirementGenerationBean(
			RequirementGenerationBean requirementGenerationBean) {
		this.requirementGenerationBean = requirementGenerationBean;
	}
	public ClientInformationBean getClientInformationBean() {
		return clientInformationBean;
	}
	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
	}
	public ResourceTypeBean getResourceTypeBean() {
		return resourceTypeBean;
	}
	public void setResourceTypeBean(ResourceTypeBean resourceTypeBean) {
		this.resourceTypeBean = resourceTypeBean;
	}
	public SkillsetMasterbean getMasterbean() {
		return masterbean;
	}
	public void setMasterbean(SkillsetMasterbean masterbean) {
		this.masterbean = masterbean;
	}
	public ResourceMasterBean getResourceMasterBean() {
		return resourceMasterBean;
	}
	public void setResourceMasterBean(ResourceMasterBean resourceMasterBean) {
		this.resourceMasterBean = resourceMasterBean;
	}
	public boolean isChkSelect() {
		return chkSelect;
	}
	public void setChkSelect(boolean chkSelect) {
		this.chkSelect = chkSelect;
	}
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
	public boolean isAssignButtonVisibility() {
		return assignButtonVisibility;
	}
	public void setAssignButtonVisibility(boolean assignButtonVisibility) {
		this.assignButtonVisibility = assignButtonVisibility;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
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
