package org.appsquad.bean;

public class StatusMasterBean {
	private int statusId;
	private String status;
	private int ocstatusId;
	private String ocstatus;
	
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
}
