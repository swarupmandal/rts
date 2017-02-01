package org.appsquad.bean;

import java.util.Date;

public class CurrentOpportunitiesReportBean {
	private Date fromDate;
	private java.sql.Date fromDateSql;
	private String fromDateValue;
	private String fromDateStr;
	
	private String ModalTitle;
	
	private Date toDate;
	private java.sql.Date toDateSql;
	private String toDateValue;
	private String toateStr;
	
	public int listIndexOf;
	
	public String chqDate;
	public String chqDateString;
	
	private String isCheck = "N";
	private Boolean minusButtonVisible = true;
	private int serialId=0;
	
	private String billDate;
	private String billDateString;
	
	private String month;
	private String year;
	private String Paid="No";
	private String chqDetails;
	private Float  chqDetailsValue;
	private Double billNo;
	private Double billAmount;
	private String timesheetPath;
	private String invoiceCopyPath;
	private java.sql.Date billDateSql;
	private java.sql.Date chequeDateSql;
	private Integer trackingId;
	private Integer trackingDetailsId;
	private Double chargeOutRate;
	private String chargeOutRateString;
	private Double resourceSallary;
	private String fileName;
	private String secondFileName;
	
	private boolean fileuploaded = false;
	private boolean secondFileuploaded = false;
	private boolean secondButtonDisable = true;
	
	private String filePath;
	private String secondFilePath;
	
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
	public Double getChargeOutRate() {
		return chargeOutRate;
	}
	public void setChargeOutRate(Double chargeOutRate) {
		this.chargeOutRate = chargeOutRate;
	}
	public Double getResourceSallary() {
		return resourceSallary;
	}
	public void setResourceSallary(Double resourceSallary) {
		this.resourceSallary = resourceSallary;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSecondFileName() {
		return secondFileName;
	}
	public void setSecondFileName(String secondFileName) {
		this.secondFileName = secondFileName;
	}
	public boolean isFileuploaded() {
		return fileuploaded;
	}
	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}
	public boolean isSecondFileuploaded() {
		return secondFileuploaded;
	}
	public void setSecondFileuploaded(boolean secondFileuploaded) {
		this.secondFileuploaded = secondFileuploaded;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSecondFilePath() {
		return secondFilePath;
	}
	public void setSecondFilePath(String secondFilePath) {
		this.secondFilePath = secondFilePath;
	}
	
	@Override
	public boolean equals(Object obj) {
		if((obj == null) || (obj.getClass() != this.getClass())){
			
			return false;
		}

		if(obj instanceof CurrentOpportunitiesReportBean){
			CurrentOpportunitiesReportBean reportBean = (CurrentOpportunitiesReportBean)obj;
			if( ( (reportBean.year != null && reportBean.year.trim().length()>0) 
					&& (reportBean.month != null && reportBean.month.trim().length()>0) )){
				return ((year.equalsIgnoreCase(reportBean.year)) 
						&& (month.equalsIgnoreCase(reportBean.month)));
			}else if( (year == null && reportBean.year==null)  &&
					(month == null && reportBean.month==null) ){
				return false;
			}
			return false;
		}else{
			
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return (year != null && month !=null ) ? 
				year.hashCode() * month.hashCode()* 37 : 0;
	}
	@Override
	public String toString() {
		return "CurrentOpportunitiesReportBean [fromDate=" + fromDate
				+ ", fromDateSql=" + fromDateSql + ", fromDateValue="
				+ fromDateValue + ", fromDateStr=" + fromDateStr + ", toDate="
				+ toDate + ", toDateSql=" + toDateSql + ", toDateValue="
				+ toDateValue + ", toateStr=" + toateStr + ", month=" + month
				+ ", year=" + year + ", Paid=" + Paid + ", chqDetails="
				+ chqDetails + ", chqDetailsValue=" + chqDetailsValue
				+ ", billNo=" + billNo + ", billAmount=" + billAmount
				+ ", timesheetPath=" + timesheetPath + ", invoiceCopyPath="
				+ invoiceCopyPath + ", billDateSql=" + billDateSql
				+ ", trackingId=" + trackingId + ", trackingDetailsId="
				+ trackingDetailsId + ", chargeOutRate=" + chargeOutRate
				+ ", resourceSallary=" + resourceSallary + ", fileName="
				+ fileName + ", secondFileName=" + secondFileName
				+ ", fileuploaded=" + fileuploaded + ", secondFileuploaded="
				+ secondFileuploaded + ", filePath=" + filePath
				+ ", secondFilePath=" + secondFilePath
				+ ", clientInformationBean=" + clientInformationBean
				+ ", skillsetMasterbean=" + skillsetMasterbean
				+ ", resourceMasterBean=" + resourceMasterBean
				+ ", requirementGenerationBean=" + requirementGenerationBean
				+ ", billNoString=" + billNoString + ", billAmountString="
				+ billAmountString + ", mainScreenName=" + mainScreenName
				+ ", chileScreenName=" + chileScreenName + ", operation="
				+ operation + ", creationDate=" + creationDate
				+ ", sessionUserId=" + sessionUserId + ", operationId="
				+ operationId + "]";
	}
	public boolean isSecondButtonDisable() {
		return secondButtonDisable;
	}
	public void setSecondButtonDisable(boolean secondButtonDisable) {
		this.secondButtonDisable = secondButtonDisable;
	}
	public String getPaid() {
		return Paid;
	}
	public void setPaid(String paid) {
		Paid = paid;
	}
	public String getModalTitle() {
		return ModalTitle;
	}
	public void setModalTitle(String modalTitle) {
		ModalTitle = modalTitle;
	}
	public java.sql.Date getChequeDateSql() {
		return chequeDateSql;
	}
	public void setChequeDateSql(java.sql.Date chequeDateSql) {
		this.chequeDateSql = chequeDateSql;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public Boolean getMinusButtonVisible() {
		return minusButtonVisible;
	}
	public void setMinusButtonVisible(Boolean minusButtonVisible) {
		this.minusButtonVisible = minusButtonVisible;
	}
	public int getSerialId() {
		return serialId;
	}
	public void setSerialId(int serialId) {
		this.serialId = serialId;
	}
	public int getListIndexOf() {
		return listIndexOf;
	}
	public void setListIndexOf(int listIndexOf) {
		this.listIndexOf = listIndexOf;
	}
	public String getChargeOutRateString() {
		return chargeOutRateString;
	}
	public void setChargeOutRateString(String chargeOutRateString) {
		this.chargeOutRateString = chargeOutRateString;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getBillDateString() {
		return billDateString;
	}
	public void setBillDateString(String billDateString) {
		this.billDateString = billDateString;
	}
	public String getChqDate() {
		return chqDate;
	}
	public void setChqDate(String chqDate) {
		this.chqDate = chqDate;
	}
	public String getChqDateString() {
		return chqDateString;
	}
	public void setChqDateString(String chqDateString) {
		this.chqDateString = chqDateString;
	}
}
