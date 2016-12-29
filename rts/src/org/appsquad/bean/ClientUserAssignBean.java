package org.appsquad.bean;

import java.sql.Date;

public class ClientUserAssignBean {
	public ClientInformationBean clientInformationBean = new ClientInformationBean();
	public UserprofileBean userprofileBean = new UserprofileBean();
	
	private String clientNameSearch;
	private String userIdSearch;
	private Integer assignId;
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName = "MASTER";
	private String chileScreenName = "CLIENT USER ASSIGN SCREEN";
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	/*************************************************************************************************************************************************/
	
	public ClientInformationBean getClientInformationBean() {
		return clientInformationBean;
	}
	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public String getClientNameSearch() {
		return clientNameSearch;
	}
	public void setClientNameSearch(String clientNameSearch) {
		this.clientNameSearch = clientNameSearch;
	}
	public String getUserIdSearch() {
		return userIdSearch;
	}
	public void setUserIdSearch(String userIdSearch) {
		this.userIdSearch = userIdSearch;
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
	public Integer getAssignId() {
		return assignId;
	}
	public void setAssignId(Integer assignId) {
		this.assignId = assignId;
	}
}
