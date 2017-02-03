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
	
	private boolean innerComboGroup = false;
	private String monthName;
	
	private String billingStatus;
	
	private String backGroundpaParent = "background-color: #ffe6f9";
	private String backGroundChild = "background-color: #D5D1D3";
	private String boldStyle = "font-weight: bold; color: black";
	private String lighterStyle = "font-weight: lighter; color: black";
	private String anotherStyle = "background-color: #FFFFCB";
	private String backGround;
	private String style;
	private Integer trackingDetailsId;
	private String selectedRadioButton;
	private String clientNameSearch;
	private String firstClientNameSearch;
	private String resourceNameSearch;
	private boolean innerResourceCombo = false;
	private boolean innerClientGroup = false;
	
	private String resourcenameString;
	private String clientNameString;
	private String chargeOutString;
	private String marginString;
	private String percentage;
	private Double percenatgeValue;
	
	private Integer rtsTrackingDetailsId;
	private String resourceFlag = "N";
	private String clientFlag = "N";
	
	private boolean clientNameColumnVisibility = true;
	private boolean resourceNameColumnVisibility = true;
	private boolean chargeOutRateColumnVisibility = true;
	private boolean marginColumnVisibility = true;
	
	private CurrentOpportunitiesBean currentOpportunitiesBean = new CurrentOpportunitiesBean();
	private CurrentOpportunitiesReportBean currentOpportunitiesReportBean = new CurrentOpportunitiesReportBean();
	
	private ClientInformationBean clientInformationBean = new ClientInformationBean();
	private RequirementGenerationBean requirementGenerationBean = new RequirementGenerationBean();
	private SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	private ResourceMasterBean resourceMasterBean = new ResourceMasterBean();
	
	private ClientInformationBean clientBean = new ClientInformationBean();
	
    /******************************************************* GETTER AND SETTER METHOD *******************************************************************/
	
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
	public String getBoldStyle() {
		return boldStyle;
	}
	public void setBoldStyle(String boldStyle) {
		this.boldStyle = boldStyle;
	}
	public String getLighterStyle() {
		return lighterStyle;
	}
	public void setLighterStyle(String lighterStyle) {
		this.lighterStyle = lighterStyle;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public boolean isInnerComboGroup() {
		return innerComboGroup;
	}
	public void setInnerComboGroup(boolean innerComboGroup) {
		this.innerComboGroup = innerComboGroup;
	}
	public String getSelectedRadioButton() {
		return selectedRadioButton;
	}
	public void setSelectedRadioButton(String selectedRadioButton) {
		this.selectedRadioButton = selectedRadioButton;
	}
	public String getClientNameSearch() {
		return clientNameSearch;
	}
	public void setClientNameSearch(String clientNameSearch) {
		this.clientNameSearch = clientNameSearch;
	}
	public boolean isInnerResourceCombo() {
		return innerResourceCombo;
	}
	public void setInnerResourceCombo(boolean innerResourceCombo) {
		this.innerResourceCombo = innerResourceCombo;
	}
	public boolean isInnerClientGroup() {
		return innerClientGroup;
	}
	public void setInnerClientGroup(boolean innerClientGroup) {
		this.innerClientGroup = innerClientGroup;
	}
	public String getResourceNameSearch() {
		return resourceNameSearch;
	}
	public void setResourceNameSearch(String resourceNameSearch) {
		this.resourceNameSearch = resourceNameSearch;
	}
	public String getMonthName() {
		return monthName;
	}
	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}
	public CurrentOpportunitiesBean getCurrentOpportunitiesBean() {
		return currentOpportunitiesBean;
	}
	public void setCurrentOpportunitiesBean(CurrentOpportunitiesBean currentOpportunitiesBean) {
		this.currentOpportunitiesBean = currentOpportunitiesBean;
	}
	public Integer getRtsTrackingDetailsId() {
		return rtsTrackingDetailsId;
	}
	public void setRtsTrackingDetailsId(Integer rtsTrackingDetailsId) {
		this.rtsTrackingDetailsId = rtsTrackingDetailsId;
	}
	public String getResourceFlag() {
		return resourceFlag;
	}
	public void setResourceFlag(String resourceFlag) {
		this.resourceFlag = resourceFlag;
	}
	public String getClientFlag() {
		return clientFlag;
	}
	public void setClientFlag(String clientFlag) {
		this.clientFlag = clientFlag;
	}
	public boolean isClientNameColumnVisibility() {
		return clientNameColumnVisibility;
	}
	public void setClientNameColumnVisibility(boolean clientNameColumnVisibility) {
		this.clientNameColumnVisibility = clientNameColumnVisibility;
	}
	public boolean isResourceNameColumnVisibility() {
		return resourceNameColumnVisibility;
	}
	public void setResourceNameColumnVisibility(boolean resourceNameColumnVisibility) {
		this.resourceNameColumnVisibility = resourceNameColumnVisibility;
	}
	public boolean isChargeOutRateColumnVisibility() {
		return chargeOutRateColumnVisibility;
	}
	public void setChargeOutRateColumnVisibility(
			boolean chargeOutRateColumnVisibility) {
		this.chargeOutRateColumnVisibility = chargeOutRateColumnVisibility;
	}
	public boolean isMarginColumnVisibility() {
		return marginColumnVisibility;
	}
	public void setMarginColumnVisibility(boolean marginColumnVisibility) {
		this.marginColumnVisibility = marginColumnVisibility;
	}
	public String getResourcenameString() {
		return resourcenameString;
	}
	public void setResourcenameString(String resourcenameString) {
		this.resourcenameString = resourcenameString;
	}
	public String getClientNameString() {
		return clientNameString;
	}
	public void setClientNameString(String clientNameString) {
		this.clientNameString = clientNameString;
	}
	public String getChargeOutString() {
		return chargeOutString;
	}
	public void setChargeOutString(String chargeOutString) {
		this.chargeOutString = chargeOutString;
	}
	public String getMarginString() {
		return marginString;
	}
	public void setMarginString(String marginString) {
		this.marginString = marginString;
	}
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public Double getPercenatgeValue() {
		return percenatgeValue;
	}
	public void setPercenatgeValue(Double percenatgeValue) {
		this.percenatgeValue = percenatgeValue;
	}
	public ClientInformationBean getClientBean() {
		return clientBean;
	}
	public void setClientBean(ClientInformationBean clientBean) {
		this.clientBean = clientBean;
	}
	public String getBillingStatus() {
		return billingStatus;
	}
	public void setBillingStatus(String billingStatus) {
		this.billingStatus = billingStatus;
	}
	public String getFirstClientNameSearch() {
		return firstClientNameSearch;
	}
	public String getAnotherStyle() {
		return anotherStyle;
	}
	public void setAnotherStyle(String anotherStyle) {
		this.anotherStyle = anotherStyle;
	}
	public void setFirstClientNameSearch(String firstClientNameSearch) {
		this.firstClientNameSearch = firstClientNameSearch;
	}
}
