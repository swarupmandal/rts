package org.appsquad.bean;

import java.util.ArrayList;
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
    private String anotherUserIdSearch;
    private String venue;
    private Integer week;
    private Integer month;
   
    private Date createdDate;
    private java.sql.Date createdDateSql;
    private String createdDateStr;
    private String createdDateValue;
    
    private Date scheduledDate;
    private java.sql.Date scheduledDateSql;
    private String scheduledDateStr;
    private String scheduledDateValue;
    
    private Date actualCompletionDate;
    private java.sql.Date actualCompletionDateSql;
    private String actualCompletionDateStr;
    private String actualCompletionDateValue;
    
    private boolean divVisibility = false;
    private String status;
    private String remarksOrResults;
    private String selectedRadioButton;
    private String selectedInnerRadioButton;
    private boolean innerRadioGroup = false;
    
    public UserprofileBean userprofileBean = new UserprofileBean();
    public UserprofileBean userprofileBean2 = new UserprofileBean();
    private TaskStatusBean taskStatusBean = new TaskStatusBean();
    private ArrayList<TaskStatusBean> taskStatusBeanList = new ArrayList<TaskStatusBean>();
    
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
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public java.sql.Date getScheduledDateSql() {
		return scheduledDateSql;
	}
	public void setScheduledDateSql(java.sql.Date scheduledDateSql) {
		this.scheduledDateSql = scheduledDateSql;
	}
	public String getScheduledDateStr() {
		return scheduledDateStr;
	}
	public void setScheduledDateStr(String scheduledDateStr) {
		this.scheduledDateStr = scheduledDateStr;
	}
	public String getScheduledDateValue() {
		return scheduledDateValue;
	}
	public void setScheduledDateValue(String scheduledDateValue) {
		this.scheduledDateValue = scheduledDateValue;
	}
	public Date getActualCompletionDate() {
		return actualCompletionDate;
	}
	public void setActualCompletionDate(Date actualCompletionDate) {
		this.actualCompletionDate = actualCompletionDate;
	}
	public java.sql.Date getActualCompletionDateSql() {
		return actualCompletionDateSql;
	}
	public void setActualCompletionDateSql(java.sql.Date actualCompletionDateSql) {
		this.actualCompletionDateSql = actualCompletionDateSql;
	}
	public String getActualCompletionDateStr() {
		return actualCompletionDateStr;
	}
	public void setActualCompletionDateStr(String actualCompletionDateStr) {
		this.actualCompletionDateStr = actualCompletionDateStr;
	}
	public String getActualCompletionDateValue() {
		return actualCompletionDateValue;
	}
	public void setActualCompletionDateValue(String actualCompletionDateValue) {
		this.actualCompletionDateValue = actualCompletionDateValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarksOrResults() {
		return remarksOrResults;
	}
	public void setRemarksOrResults(String remarksOrResults) {
		this.remarksOrResults = remarksOrResults;
	}
	public String getSelectedRadioButton() {
		return selectedRadioButton;
	}
	public void setSelectedRadioButton(String selectedRadioButton) {
		this.selectedRadioButton = selectedRadioButton;
	}
	public boolean isInnerRadioGroup() {
		return innerRadioGroup;
	}
	public void setInnerRadioGroup(boolean innerRadioGroup) {
		this.innerRadioGroup = innerRadioGroup;
	}
	public String getSelectedInnerRadioButton() {
		return selectedInnerRadioButton;
	}
	public void setSelectedInnerRadioButton(String selectedInnerRadioButton) {
		this.selectedInnerRadioButton = selectedInnerRadioButton;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public TaskStatusBean getTaskStatusBean() {
		return taskStatusBean;
	}
	public void setTaskStatusBean(TaskStatusBean taskStatusBean) {
		this.taskStatusBean = taskStatusBean;
	}
	public ArrayList<TaskStatusBean> getTaskStatusBeanList() {
		return taskStatusBeanList;
	}
	public void setTaskStatusBeanList(ArrayList<TaskStatusBean> taskStatusBeanList) {
		this.taskStatusBeanList = taskStatusBeanList;
	}
	public String getAnotherUserIdSearch() {
		return anotherUserIdSearch;
	}
	public void setAnotherUserIdSearch(String anotherUserIdSearch) {
		this.anotherUserIdSearch = anotherUserIdSearch;
	}
	public UserprofileBean getUserprofileBean2() {
		return userprofileBean2;
	}
	public void setUserprofileBean2(UserprofileBean userprofileBean2) {
		this.userprofileBean2 = userprofileBean2;
	}
}
