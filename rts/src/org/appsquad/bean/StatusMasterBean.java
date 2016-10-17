package org.appsquad.bean;
public class StatusMasterBean {
	private Integer statusId;
	private String status;
	private int ocstatusId;
	private String ocstatus;
	private String userId;
	private boolean editButtonDisable = false;
	private boolean saveButtonDisable = true;
	private boolean statusDisabled = true;
	private Integer oldOcStatusId;
	
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
}
