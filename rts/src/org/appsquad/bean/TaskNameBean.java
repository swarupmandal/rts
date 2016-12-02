package org.appsquad.bean;

import java.util.Date;

public class TaskNameBean {
    private String taskName;
    private String assignedBy;
    private Integer assignedById;
    private String assignedTo;
    private Integer assignedToId;
    private String assignedByUserId;
    private Integer rtsTaskId;
    
    private String userIdSearch;
   
    private Date createdDate;
    private java.sql.Date createdDateSql;
    private String createdDateStr;
    private String createdDateValue;
    private boolean divVisibility = false;
    
    public UserprofileBean userprofileBean = new UserprofileBean();
   
    
    /*************************************************************************************************************************************************/
    
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getAssignedBy() {
		return assignedBy;
	}
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}
	public Integer getAssignedById() {
		return assignedById;
	}
	public void setAssignedById(Integer assignedById) {
		this.assignedById = assignedById;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Integer getAssignedToId() {
		return assignedToId;
	}
	public void setAssignedToId(Integer assignedToId) {
		this.assignedToId = assignedToId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public java.sql.Date getCreatedDateSql() {
		return createdDateSql;
	}
	public void setCreatedDateSql(java.sql.Date createdDateSql) {
		this.createdDateSql = createdDateSql;
	}
	public String getCreatedDateStr() {
		return createdDateStr;
	}
	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}
	public String getCreatedDateValue() {
		return createdDateValue;
	}
	public void setCreatedDateValue(String createdDateValue) {
		this.createdDateValue = createdDateValue;
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public String getUserIdSearch() {
		return userIdSearch;
	}
	public void setUserIdSearch(String userIdSearch) {
		this.userIdSearch = userIdSearch;
	}
	public String getAssignedByUserId() {
		return assignedByUserId;
	}
	public void setAssignedByUserId(String assignedByUserId) {
		this.assignedByUserId = assignedByUserId;
	}
	public Integer getRtsTaskId() {
		return rtsTaskId;
	}
	public void setRtsTaskId(Integer rtsTaskId) {
		this.rtsTaskId = rtsTaskId;
	}
	public boolean isDivVisibility() {
		return divVisibility;
	}
	public void setDivVisibility(boolean divVisibility) {
		this.divVisibility = divVisibility;
	}
}
