package org.appsquad.bean;

import java.util.Date;

public class CurrentOpportunitiesReportGenerationBean {
	private Date fromDate;
	private java.sql.Date fromDateSql;
	private String fromDateValue;
	private String fromDateStr;
	
	private Date toDate;
	private java.sql.Date toDateSql;
	private String toDateValue;
	private String toateStr;
	
	private String backGroundpaParent = "background-color: #ffe6f9";
	private String backGroundChild = "background-color: #D5D1D3";
	private String backGround;
	private Integer trackingDetailsId;
	
	private CurrentOpportunitiesReportBean currentOpportunitiesReportBean = new CurrentOpportunitiesReportBean();
	
	private ClientInformationBean clientInformationBean = new ClientInformationBean();
	private RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
	private SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	private ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
	
    /**************************************** GETTER AND SETTER METHOD *************************************************************************/
	
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
	public String getBackGround() {
		return backGround;
	}
	public void setBackGround(String backGround) {
		this.backGround = backGround;
	}
	public CurrentOpportunitiesReportBean getCurrentOpportunitiesReportBean() {
		return currentOpportunitiesReportBean;
	}
	public void setCurrentOpportunitiesReportBean(
			CurrentOpportunitiesReportBean currentOpportunitiesReportBean) {
		this.currentOpportunitiesReportBean = currentOpportunitiesReportBean;
	}
	public ClientInformationBean getClientInformationBean() {
		return clientInformationBean;
	}
	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
	}
	public RequirementGenerationBean getRequirementGenerationBean() {
		return requirementGenerationBean;
	}
	public void setRequirementGenerationBean(
			RequirementGenerationBean requirementGenerationBean) {
		this.requirementGenerationBean = requirementGenerationBean;
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
	public Integer getTrackingDetailsId() {
		return trackingDetailsId;
	}
	public void setTrackingDetailsId(Integer trackingDetailsId) {
		this.trackingDetailsId = trackingDetailsId;
	}
	public String getBackGroundpaParent() {
		return backGroundpaParent;
	}
	public void setBackGroundpaParent(String backGroundpaParent) {
		this.backGroundpaParent = backGroundpaParent;
	}
	public String getBackGroundChild() {
		return backGroundChild;
	}
	public void setBackGroundChild(String backGroundChild) {
		this.backGroundChild = backGroundChild;
	}
}
