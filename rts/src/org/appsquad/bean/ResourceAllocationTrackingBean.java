package org.appsquad.bean;

import java.util.Date;

public class ResourceAllocationTrackingBean {

	public ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
	
	private boolean isCheck = false;
	
	private String clientName;
	private Integer clientId;
	private String clientNameSearch;
	private String otherDetails;
	
	private Integer reqId;
	private Integer r_idSearch;
	
	private String skillSet;
   
	private Date internalInterviewDate;
	private Date previousInternalInterviewDate;
	private java.sql.Date internalInterviewDateSql;
	private String internalInterviewDateStr;
	private String internalInterviewDateValue;
	
	private Date clientInterviewDate;
	private Date previousClientInterviewDate;
	private java.sql.Date clientInterviewDateSql;
	private String clientInterviewDateStr;
	private String clientInterviewDateValue;
	
	private Date onboardDate;
	private Date previousOnboardDate;
	private java.sql.Date onboardDateSql;
	private String onboardDateStr;
	private String onboardDateValue;
	
	private int resourceTypeId;
	private String resourceType;
	
	private String status;
	private int statusId;
	private int previousStatusId;
	
	private String resNameSeach;
	private boolean internalInterviewDateDisable = true;
	private boolean clientInterviewDateDisable = true;
	private boolean onboardInterviewDateDisable = true;
	
	private Integer oldStatusId;
	private String oldStatus;
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName = "TRANSACTION";
	private String chileScreenName = "RESOURCE ALLOCATION TRACKING";
	private Integer userSerialId;
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	
	/************************************************************************************************************************************************/
	
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
	public int getResourceTypeId() {
		return resourceTypeId;
	}
	public void setResourceTypeId(int resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public boolean isCheck() {
		return isCheck;
	}
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public String getResNameSeach() {
		return resNameSeach;
	}
	public void setResNameSeach(String resNameSeach) {
		this.resNameSeach = resNameSeach;
	}
	public Date getPreviousInternalInterviewDate() {
		return previousInternalInterviewDate;
	}
	public void setPreviousInternalInterviewDate(Date previousInternalInterviewDate) {
		this.previousInternalInterviewDate = previousInternalInterviewDate;
	}
	public Date getPreviousClientInterviewDate() {
		return previousClientInterviewDate;
	}
	public void setPreviousClientInterviewDate(Date previousClientInterviewDate) {
		this.previousClientInterviewDate = previousClientInterviewDate;
	}
	public Date getPreviousOnboardDate() {
		return previousOnboardDate;
	}
	public void setPreviousOnboardDate(Date previousOnboardDate) {
		this.previousOnboardDate = previousOnboardDate;
	}
	public int getPreviousStatusId() {
		return previousStatusId;
	}
	public void setPreviousStatusId(int previousStatusId) {
		this.previousStatusId = previousStatusId;
	}
	public String getOtherDetails() {
		return otherDetails;
	}
	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}
	public boolean isInternalInterviewDateDisable() {
		return internalInterviewDateDisable;
	}
	public void setInternalInterviewDateDisable(boolean internalInterviewDateDisable) {
		this.internalInterviewDateDisable = internalInterviewDateDisable;
	}
	public boolean isClientInterviewDateDisable() {
		return clientInterviewDateDisable;
	}
	public void setClientInterviewDateDisable(boolean clientInterviewDateDisable) {
		this.clientInterviewDateDisable = clientInterviewDateDisable;
	}
	public boolean isOnboardInterviewDateDisable() {
		return onboardInterviewDateDisable;
	}
	public void setOnboardInterviewDateDisable(boolean onboardInterviewDateDisable) {
		this.onboardInterviewDateDisable = onboardInterviewDateDisable;
	}
	public Integer getOldStatusId() {
		return oldStatusId;
	}
	public void setOldStatusId(Integer oldStatusId) {
		this.oldStatusId = oldStatusId;
	}
	public String getOldStatus() {
		return oldStatus;
	}
	public void setOldStatus(String oldStatus) {
		this.oldStatus = oldStatus;
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
