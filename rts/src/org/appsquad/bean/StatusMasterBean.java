package org.appsquad.bean;

import java.sql.Date;

public class StatusMasterBean {
	private Integer statusId;
	private String clearStatus = "-Clear-";
	private String status;
	private int ocstatusId;
	private String ocstatus;
	private String userId;
	private boolean editButtonDisable = false;
	private boolean saveButtonDisable = true;
	private boolean statusDisabled = true;
	private Integer oldOcStatusId;
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName = "MASTER";
	private String chileScreenName = "STATUS MASTER";
	private Integer userSerialId;
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	/**************************************************************************************************************************************/
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOcstatusId() {
		return ocstatusId;
	}
	public void setOcstatusId(int ocstatusId) {
		this.ocstatusId = ocstatusId;
	}
	public String getOcstatus() {
		return ocstatus;
	}
	public void setOcstatus(String ocstatus) {
		this.ocstatus = ocstatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean isEditButtonDisable() {
		return editButtonDisable;
	}
	public void setEditButtonDisable(boolean editButtonDisable) {
		this.editButtonDisable = editButtonDisable;
	}
	public boolean isSaveButtonDisable() {
		return saveButtonDisable;
	}
	public boolean isStatusDisabled() {
		return statusDisabled;
	}
	public void setStatusDisabled(boolean statusDisabled) {
		this.statusDisabled = statusDisabled;
	}
	public void setSaveButtonDisable(boolean saveButtonDisable) {
		this.saveButtonDisable = saveButtonDisable;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public Integer getOldOcStatusId() {
		return oldOcStatusId;
	}
	public void setOldOcStatusId(Integer oldOcStatusId) {
		this.oldOcStatusId = oldOcStatusId;
	}
	public String getClearStatus() {
		return clearStatus;
	}
	public void setClearStatus(String clearStatus) {
		this.clearStatus = clearStatus;
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
