package org.appsquad.bean;

import java.util.Date;

public class LogAuditBean {
	private Date fromDate;
	private java.sql.Date fromDateSql;
	private String fromDateValue;
	private String fromDateStr;
	
	private Date toDate;
	private java.sql.Date toDateSql;
	private String toDateValue;
	private String toateStr;
	
	private Date dateTimestamp;
	private java.sql.Date dateTimestampSql;
	private String dateTimestampValue;
	private String dateTimestampStr;
	
	private boolean logAuditDivVisibility = false;
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName;
	private String chileScreenName;
	private Integer userSerialId;
	private String operation;
	private Date creationDate;
	private java.sql.Date creationDateSql;
	private String creationDateValue;
	private String creationDateStr;
	private String sessionUserId;

	/************************************GETTER AND SETTER METHOD ***************************************************************************/
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public java.sql.Date getFromDateSql() {
		return fromDateSql;
	}
	public void setFromDateSql(java.sql.Date fromDateSql) {
		this.fromDateSql = fromDateSql;
	}
	public String getFromDateValue() {
		return fromDateValue;
	}
	public void setFromDateValue(String fromDateValue) {
		this.fromDateValue = fromDateValue;
	}
	public String getFromDateStr() {
		return fromDateStr;
	}
	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public java.sql.Date getToDateSql() {
		return toDateSql;
	}
	public void setToDateSql(java.sql.Date toDateSql) {
		this.toDateSql = toDateSql;
	}
	public String getToDateValue() {
		return toDateValue;
	}
	public void setToDateValue(String toDateValue) {
		this.toDateValue = toDateValue;
	}
	public String getToateStr() {
		return toateStr;
	}
	public void setToateStr(String toateStr) {
		this.toateStr = toateStr;
	}
	public boolean isLogAuditDivVisibility() {
		return logAuditDivVisibility;
	}
	public void setLogAuditDivVisibility(boolean logAuditDivVisibility) {
		this.logAuditDivVisibility = logAuditDivVisibility;
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
	public java.sql.Date getCreationDateSql() {
		return creationDateSql;
	}
	public void setCreationDateSql(java.sql.Date creationDateSql) {
		this.creationDateSql = creationDateSql;
	}
	public String getCreationDateValue() {
		return creationDateValue;
	}
	public void setCreationDateValue(String creationDateValue) {
		this.creationDateValue = creationDateValue;
	}
	public String getCreationDateStr() {
		return creationDateStr;
	}
	public void setCreationDateStr(String creationDateStr) {
		this.creationDateStr = creationDateStr;
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
	public Date getDateTimestamp() {
		return dateTimestamp;
	}
	public void setDateTimestamp(Date dateTimestamp) {
		this.dateTimestamp = dateTimestamp;
	}
	public java.sql.Date getDateTimestampSql() {
		return dateTimestampSql;
	}
	public void setDateTimestampSql(java.sql.Date dateTimestampSql) {
		this.dateTimestampSql = dateTimestampSql;
	}
	public String getDateTimestampValue() {
		return dateTimestampValue;
	}
	public void setDateTimestampValue(String dateTimestampValue) {
		this.dateTimestampValue = dateTimestampValue;
	}
	public String getDateTimestampStr() {
		return dateTimestampStr;
	}
	public void setDateTimestampStr(String dateTimestampStr) {
		this.dateTimestampStr = dateTimestampStr;
	}
}
