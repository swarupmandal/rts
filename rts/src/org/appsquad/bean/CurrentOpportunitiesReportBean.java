package org.appsquad.bean;

import java.util.Date;

public class CurrentOpportunitiesReportBean {
	private Date fromDate;
	private java.sql.Date fromDateSql;
	private String fromDateValue;
	private String fromDateStr;
	
	private Date toDate;
	private java.sql.Date toDateSql;
	private String toDateValue;
	private String toateStr;
	
	private String month;
	private String year;
	private String Paid;
	private String chqDetails;
	private Float  chqDetailsValue;
	private Double billNo;
	private Double billAmount;
	private String timesheetPath;
	private String invoiceCopyPath;
	private java.sql.Date billDateSql;
	private Integer trackingId;
	private Integer trackingDetailsId;
	
	public ClientInformationBean clientInformationBean = new ClientInformationBean();
	public SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	public ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
	public RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
	
    /***********************************************************************************************/
	private String billNoString;
	private String billAmountString;
	
	/*********Log-Audit Purpose ************/
	private String mainScreenName = "REPORT";
	private String chileScreenName = "CURRENT OPPORTUNITIES OPERATION SCREEN";
	private String operation;
	private Date creationDate;
	private String sessionUserId;
	private Integer operationId;
	
	 /****************************************************** GETTER AND SETTER METHOD ***********************************************************/
	
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
	public ClientInformationBean getClientInformationBean() {
		return clientInformationBean;
	}
	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
	}
	public SkillsetMasterbean getSkillsetMasterbean() {
		return skillsetMasterbean;
	}
	public void setSkillsetMasterbean(SkillsetMasterbean skillsetMasterbean) {
		this.skillsetMasterbean = skillsetMasterbean;
	}
	public ResourceMasterBean getResourceMasterBean() {
		return resourceMasterBean;
	}
	public void setResourceMasterBean(ResourceMasterBean resourceMasterBean) {
		this.resourceMasterBean = resourceMasterBean;
	}
	public RequirementGenerationBean getRequirementGenerationBean() {
		return requirementGenerationBean;
	}
	public void setRequirementGenerationBean(
			RequirementGenerationBean requirementGenerationBean) {
		this.requirementGenerationBean = requirementGenerationBean;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPaid() {
		return Paid;
	}
	public void setPaid(String paid) {
		Paid = paid;
	}
	public String getChqDetails() {
		return chqDetails;
	}
	public void setChqDetails(String chqDetails) {
		this.chqDetails = chqDetails;
	}
	public Double getBillNo() {
		return billNo;
	}
	public void setBillNo(Double billNo) {
		this.billNo = billNo;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public String getTimesheetPath() {
		return timesheetPath;
	}
	public void setTimesheetPath(String timesheetPath) {
		this.timesheetPath = timesheetPath;
	}
	public String getInvoiceCopyPath() {
		return invoiceCopyPath;
	}
	public void setInvoiceCopyPath(String invoiceCopyPath) {
		this.invoiceCopyPath = invoiceCopyPath;
	}
	public java.sql.Date getBillDateSql() {
		return billDateSql;
	}
	public void setBillDateSql(java.sql.Date billDateSql) {
		this.billDateSql = billDateSql;
	}
	public Float getChqDetailsValue() {
		return chqDetailsValue;
	}
	public void setChqDetailsValue(Float chqDetailsValue) {
		this.chqDetailsValue = chqDetailsValue;
	}
	public Integer getTrackingId() {
		return trackingId;
	}
	public void setTrackingId(Integer trackingId) {
		this.trackingId = trackingId;
	}
	public Integer getTrackingDetailsId() {
		return trackingDetailsId;
	}
	public void setTrackingDetailsId(Integer trackingDetailsId) {
		this.trackingDetailsId = trackingDetailsId;
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
	public String getBillNoString() {
		return billNoString;
	}
	public void setBillNoString(String billNoString) {
		this.billNoString = billNoString;
	}
	public String getBillAmountString() {
		return billAmountString;
	}
	public void setBillAmountString(String billAmountString) {
		this.billAmountString = billAmountString;
	}
}
