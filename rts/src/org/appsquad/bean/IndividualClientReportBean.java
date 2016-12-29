package org.appsquad.bean;

import java.util.ArrayList;
import java.util.Date;

public class IndividualClientReportBean {
	
	private Date fromDate;
	private java.sql.Date fromDateSql;
	private String fromDateValue;
	private String fromDateStr;
	
	private Date toDate;
	private java.sql.Date toDateSql;
	private String toDateValue;
	private String toateStr;
	
	private String userId;
	private String selectedRadioButton;
	private String clientNameSearch;
	private String skillSetSearch;
	
	private Integer reqId;
	
	private Date createdDate;
	private java.sql.Date createdDateSql;
	private String createdDateValue;
	private String createdDateStr;
	
	
	private String rIdLabel = "RID : ";
	private String rIdDateLabel = "RID Date : ";
	private String skillSetLabel = "Skill Set : ";
	
	
	private Integer skillId;
	private String skillSet;
	private String companyName;
	private String clientFullName;
	
	private int statusId;
	private String status;
	private String resourceName;
	//private Integer yoExp;
	private double yoExp;
	private String contNo;
	private String emailId;
	
	private Date intIntvDate;
	private java.sql.Date intIntvSql;
	private String intIntvValue;
	private String intIntvStr;
	
	private Date clntIntvDate;
	private java.sql.Date clntIntvSql;
	private String clntIntvValue;
	private String clntIntvStr;
	
	private String resOtherInfo;
	
	private String total;
	private int totalCount;
	
	private boolean detailChecked;
	private boolean summaryChecked;
	private boolean detailsDivVis = false;
	private boolean summaryDivVis = false;
	
	private boolean statusFieldVis = false;
	private boolean resNameFieldVis = false;
	private boolean yoExpFieldVis = false;
	private boolean contNoFieldVis = false;
	private boolean emailFieldVis = false;
	private boolean intIntvDateFieldVis = false;
	private boolean clIntvDateFieldVis = false;
	
	private boolean ridLbFieldVis = false;
	private boolean ridFieldVis = false;
	private boolean ridDatelbFieldVis = false;
	private boolean ridDateFieldVis = false;
	private boolean sklStLbFieldVis = false;
	private boolean sklStFieldVis = false;
	private boolean clNameLbFieldVis = false;
	private boolean clNameFieldVis = false;
	private boolean companyFieldVis = false;
	
	private boolean pdfBtnDis;
	private boolean excelBtnDis;
	
	private String noOfReqLebel;
	private int noOfReq;
	private int NoOfResources;
	private boolean noOfReqVis = false;
	private boolean noOfResourcesVis = false;
	
	private String boldStyle = "font-weight: bold; color: black";
	private String lighterStyle = "font-weight: lighter; color: black";
	private String backGroundpaParent = "background-color: #ffe6f9";
	private String totalbackGroundCount = "background-color: #ffffe6";
	
	//private String backGroundpaParent = "background-color: yellow";
	private String style;
	private String backGroundStyle;
	
	private Integer r_idSearch;
	
	public SkillsetMasterbean masterbean = new SkillsetMasterbean();
	public StatusMasterBean statusMasterBean = new StatusMasterBean();
	public UserprofileBean userprofileBean = new UserprofileBean();
	public SkillsetMasterbean skillsetMasterbean = new SkillsetMasterbean();
	public ClientInformationBean clientInformationBean = new ClientInformationBean();
	public IndividualClientReportSubBean subBean = new IndividualClientReportSubBean();
	
	ArrayList<IndividualClientReportSubBean> subBeanList = new ArrayList<IndividualClientReportSubBean>();
	
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSelectedRadioButton() {
		return selectedRadioButton;
	}
	public void setSelectedRadioButton(String selectedRadioButton) {
		this.selectedRadioButton = selectedRadioButton;
	}
	public SkillsetMasterbean getMasterbean() {
		return masterbean;
	}
	public void setMasterbean(SkillsetMasterbean masterbean) {
		this.masterbean = masterbean;
	}
	public StatusMasterBean getStatusMasterBean() {
		return statusMasterBean;
	}
	public void setStatusMasterBean(StatusMasterBean statusMasterBean) {
		this.statusMasterBean = statusMasterBean;
	}
	public UserprofileBean getUserprofileBean() {
		return userprofileBean;
	}
	public void setUserprofileBean(UserprofileBean userprofileBean) {
		this.userprofileBean = userprofileBean;
	}
	public SkillsetMasterbean getSkillsetMasterbean() {
		return skillsetMasterbean;
	}
	public void setSkillsetMasterbean(SkillsetMasterbean skillsetMasterbean) {
		this.skillsetMasterbean = skillsetMasterbean;
	}
	public ClientInformationBean getClientInformationBean() {
		return clientInformationBean;
	}
	public void setClientInformationBean(ClientInformationBean clientInformationBean) {
		this.clientInformationBean = clientInformationBean;
	}
	public String getClientNameSearch() {
		return clientNameSearch;
	}
	public void setClientNameSearch(String clientNameSearch) {
		this.clientNameSearch = clientNameSearch;
	}
	public String getSkillSetSearch() {
		return skillSetSearch;
	}
	public void setSkillSetSearch(String skillSetSearch) {
		this.skillSetSearch = skillSetSearch;
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
	public Integer getReqId() {
		return reqId;
	}
	public void setReqId(Integer reqId) {
		this.reqId = reqId;
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
	public String getCreatedDateValue() {
		return createdDateValue;
	}
	public void setCreatedDateValue(String createdDateValue) {
		this.createdDateValue = createdDateValue;
	}
	public String getCreatedDateStr() {
		return createdDateStr;
	}
	public void setCreatedDateStr(String createdDateStr) {
		this.createdDateStr = createdDateStr;
	}
	public Integer getSkillId() {
		return skillId;
	}
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	public String getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getClientFullName() {
		return clientFullName;
	}
	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}
	public String getrIdLabel() {
		return rIdLabel;
	}
	public void setrIdLabel(String rIdLabel) {
		this.rIdLabel = rIdLabel;
	}
	public String getrIdDateLabel() {
		return rIdDateLabel;
	}
	public void setrIdDateLabel(String rIdDateLabel) {
		this.rIdDateLabel = rIdDateLabel;
	}
	public String getSkillSetLabel() {
		return skillSetLabel;
	}
	public void setSkillSetLabel(String skillSetLabel) {
		this.skillSetLabel = skillSetLabel;
	}
	public IndividualClientReportSubBean getSubBean() {
		return subBean;
	}
	public void setSubBean(IndividualClientReportSubBean subBean) {
		this.subBean = subBean;
	}
	public ArrayList<IndividualClientReportSubBean> getSubBeanList() {
		return subBeanList;
	}
	public void setSubBeanList(ArrayList<IndividualClientReportSubBean> subBeanList) {
		this.subBeanList = subBeanList;
	}
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
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getIntIntvDate() {
		return intIntvDate;
	}
	public void setIntIntvDate(Date intIntvDate) {
		this.intIntvDate = intIntvDate;
	}
	public java.sql.Date getIntIntvSql() {
		return intIntvSql;
	}
	public void setIntIntvSql(java.sql.Date intIntvSql) {
		this.intIntvSql = intIntvSql;
	}
	public String getIntIntvValue() {
		return intIntvValue;
	}
	public void setIntIntvValue(String intIntvValue) {
		this.intIntvValue = intIntvValue;
	}
	public String getIntIntvStr() {
		return intIntvStr;
	}
	public void setIntIntvStr(String intIntvStr) {
		this.intIntvStr = intIntvStr;
	}
	public Date getClntIntvDate() {
		return clntIntvDate;
	}
	public void setClntIntvDate(Date clntIntvDate) {
		this.clntIntvDate = clntIntvDate;
	}
	public java.sql.Date getClntIntvSql() {
		return clntIntvSql;
	}
	public void setClntIntvSql(java.sql.Date clntIntvSql) {
		this.clntIntvSql = clntIntvSql;
	}
	public String getClntIntvValue() {
		return clntIntvValue;
	}
	public void setClntIntvValue(String clntIntvValue) {
		this.clntIntvValue = clntIntvValue;
	}
	public String getClntIntvStr() {
		return clntIntvStr;
	}
	public void setClntIntvStr(String clntIntvStr) {
		this.clntIntvStr = clntIntvStr;
	}
	public boolean isStatusFieldVis() {
		return statusFieldVis;
	}
	public void setStatusFieldVis(boolean statusFieldVis) {
		this.statusFieldVis = statusFieldVis;
	}
	public boolean isResNameFieldVis() {
		return resNameFieldVis;
	}
	public void setResNameFieldVis(boolean resNameFieldVis) {
		this.resNameFieldVis = resNameFieldVis;
	}
	public boolean isYoExpFieldVis() {
		return yoExpFieldVis;
	}
	public void setYoExpFieldVis(boolean yoExpFieldVis) {
		this.yoExpFieldVis = yoExpFieldVis;
	}
	public boolean isContNoFieldVis() {
		return contNoFieldVis;
	}
	public void setContNoFieldVis(boolean contNoFieldVis) {
		this.contNoFieldVis = contNoFieldVis;
	}
	public boolean isEmailFieldVis() {
		return emailFieldVis;
	}
	public void setEmailFieldVis(boolean emailFieldVis) {
		this.emailFieldVis = emailFieldVis;
	}
	public boolean isIntIntvDateFieldVis() {
		return intIntvDateFieldVis;
	}
	public void setIntIntvDateFieldVis(boolean intIntvDateFieldVis) {
		this.intIntvDateFieldVis = intIntvDateFieldVis;
	}
	public boolean isClIntvDateFieldVis() {
		return clIntvDateFieldVis;
	}
	public void setClIntvDateFieldVis(boolean clIntvDateFieldVis) {
		this.clIntvDateFieldVis = clIntvDateFieldVis;
	}
	public boolean isRidLbFieldVis() {
		return ridLbFieldVis;
	}
	public void setRidLbFieldVis(boolean ridLbFieldVis) {
		this.ridLbFieldVis = ridLbFieldVis;
	}
	public boolean isRidFieldVis() {
		return ridFieldVis;
	}
	public void setRidFieldVis(boolean ridFieldVis) {
		this.ridFieldVis = ridFieldVis;
	}
	public boolean isRidDatelbFieldVis() {
		return ridDatelbFieldVis;
	}
	public void setRidDatelbFieldVis(boolean ridDatelbFieldVis) {
		this.ridDatelbFieldVis = ridDatelbFieldVis;
	}
	public boolean isRidDateFieldVis() {
		return ridDateFieldVis;
	}
	public void setRidDateFieldVis(boolean ridDateFieldVis) {
		this.ridDateFieldVis = ridDateFieldVis;
	}
	public boolean isSklStLbFieldVis() {
		return sklStLbFieldVis;
	}
	public void setSklStLbFieldVis(boolean sklStLbFieldVis) {
		this.sklStLbFieldVis = sklStLbFieldVis;
	}
	public boolean isSklStFieldVis() {
		return sklStFieldVis;
	}
	public void setSklStFieldVis(boolean sklStFieldVis) {
		this.sklStFieldVis = sklStFieldVis;
	}
	public boolean isClNameLbFieldVis() {
		return clNameLbFieldVis;
	}
	public void setClNameLbFieldVis(boolean clNameLbFieldVis) {
		this.clNameLbFieldVis = clNameLbFieldVis;
	}
	public boolean isClNameFieldVis() {
		return clNameFieldVis;
	}
	public void setClNameFieldVis(boolean clNameFieldVis) {
		this.clNameFieldVis = clNameFieldVis;
	}
	public boolean isCompanyFieldVis() {
		return companyFieldVis;
	}
	public void setCompanyFieldVis(boolean companyFieldVis) {
		this.companyFieldVis = companyFieldVis;
	}
	public Integer getR_idSearch() {
		return r_idSearch;
	}
	public void setR_idSearch(Integer r_idSearch) {
		this.r_idSearch = r_idSearch;
	}
	public boolean isDetailsDivVis() {
		return detailsDivVis;
	}
	public void setDetailsDivVis(boolean detailsDivVis) {
		this.detailsDivVis = detailsDivVis;
	}
	public boolean isSummaryDivVis() {
		return summaryDivVis;
	}
	public void setSummaryDivVis(boolean summaryDivVis) {
		this.summaryDivVis = summaryDivVis;
	}
	public int getNoOfReq() {
		return noOfReq;
	}
	public void setNoOfReq(int noOfReq) {
		this.noOfReq = noOfReq;
	}
	public int getNoOfResources() {
		return NoOfResources;
	}
	public void setNoOfResources(int noOfResources) {
		NoOfResources = noOfResources;
	}
	public boolean isNoOfReqVis() {
		return noOfReqVis;
	}
	public void setNoOfReqVis(boolean noOfReqVis) {
		this.noOfReqVis = noOfReqVis;
	}
	public boolean isNoOfResourcesVis() {
		return noOfResourcesVis;
	}
	public void setNoOfResourcesVis(boolean noOfResourcesVis) {
		this.noOfResourcesVis = noOfResourcesVis;
	}
	public String getNoOfReqLebel() {
		return noOfReqLebel;
	}
	public void setNoOfReqLebel(String noOfReqLebel) {
		this.noOfReqLebel = noOfReqLebel;
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
	public String getBackGroundpaParent() {
		return backGroundpaParent;
	}
	public void setBackGroundpaParent(String backGroundpaParent) {
		this.backGroundpaParent = backGroundpaParent;
	}
	public String getBackGroundStyle() {
		return backGroundStyle;
	}
	public void setBackGroundStyle(String backGroundStyle) {
		this.backGroundStyle = backGroundStyle;
	}
	public String getResOtherInfo() {
		return resOtherInfo;
	}
	public void setResOtherInfo(String resOtherInfo) {
		this.resOtherInfo = resOtherInfo;
	}
	public double getYoExp() {
		return yoExp;
	}
	public void setYoExp(double yoExp) {
		this.yoExp = yoExp;
	}
	public boolean isDetailChecked() {
		return detailChecked;
	}
	public void setDetailChecked(boolean detailChecked) {
		this.detailChecked = detailChecked;
	}
	public boolean isSummaryChecked() {
		return summaryChecked;
	}
	public void setSummaryChecked(boolean summaryChecked) {
		this.summaryChecked = summaryChecked;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getTotalbackGroundCount() {
		return totalbackGroundCount;
	}
	public void setTotalbackGroundCount(String totalbackGroundCount) {
		this.totalbackGroundCount = totalbackGroundCount;
	}
	public boolean isPdfBtnDis() {
		return pdfBtnDis;
	}
	public void setPdfBtnDis(boolean pdfBtnDis) {
		this.pdfBtnDis = pdfBtnDis;
	}
	public boolean isExcelBtnDis() {
		return excelBtnDis;
	}
	public void setExcelBtnDis(boolean excelBtnDis) {
		this.excelBtnDis = excelBtnDis;
	}
	
	
	
}
