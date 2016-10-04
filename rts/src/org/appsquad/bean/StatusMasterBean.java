package org.appsquad.bean;
public class StatusMasterBean {
	private int statusId;
	private String status;
	private int ocstatusId;
	private String ocstatus;
	private String userId;
	private boolean editButtonDisable = false;
	private boolean saveButtonDisable = true;
	private boolean statusDisabled = true;
	
	/**************************************************************************************************************************************/
	
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
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
}
