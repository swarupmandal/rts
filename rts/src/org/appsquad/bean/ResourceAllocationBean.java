package org.appsquad.bean;

public class ResourceAllocationBean {
	private Integer requiredResourcenumber;
	private Integer allocatedResourceNumber;
	private Integer remainingResourceNumber;
	private String userId;
	private boolean chkSelect = false;
	private boolean divVisibility = false;
	
	private RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
	private ClientInformationBean clientInformationBean = new ClientInformationBean();
	private ResourceTypeBean resourceTypeBean = new ResourceTypeBean();
	private SkillsetMasterbean masterbean = new SkillsetMasterbean();
	private ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
	
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
}
